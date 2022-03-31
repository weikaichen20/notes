package com.wkc.java.sharemodel.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
@Slf4j
public class TestCorrectPostureStepTwo_3 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("有烟开始干活");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    log.debug("开始干活");
                }
            }, "其他人").start();
        }

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (room) {
                hasCigarette = true;
                room.notify();
                log.debug("烟送到了");
            }
        }, "送烟的").start();

    }
}
