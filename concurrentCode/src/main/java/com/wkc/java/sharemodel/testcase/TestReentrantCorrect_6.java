package com.wkc.java.sharemodel.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "c.w.j.R")
public class TestReentrantCorrect_6 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    static ReentrantLock ROOM = new ReentrantLock();
    static Condition waitCigaretteSet = ROOM.newCondition();
    static Condition waitTakeout = ROOM.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            ROOM.lock();
            try {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会");
                    try {
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("有烟开始干活");
                }
            } finally {
                ROOM.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            ROOM.lock();
            try {
                    log.debug("外卖到没？[{}]", hasTakeout);
                    if (!hasTakeout) {
                        log.debug("没外卖呢，先歇会");
                        try {
                            waitTakeout.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("外卖到没？[{}]", hasTakeout);
                    if (hasTakeout) {
                        log.debug("有外卖开始干活");
                    }
            }finally {
                ROOM.unlock();
            }
        }, "女女").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            ROOM.lock();
            try {
                hasTakeout = true;
                waitTakeout.signalAll();
                log.debug("外卖送到");
            }finally {
                ROOM.unlock();
            }
        }, "送外卖").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            ROOM.lock();
            try {
                hasCigarette = true;
                waitCigaretteSet.signalAll();
                log.debug("烟送到");
            }finally {
                ROOM.unlock();
            }
        }, "送烟").start();

    }
}