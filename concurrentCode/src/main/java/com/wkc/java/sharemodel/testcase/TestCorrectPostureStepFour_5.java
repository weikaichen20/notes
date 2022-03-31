package com.wkc.java.sharemodel.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "c.w.j.s.t")
public class TestCorrectPostureStepFour_5 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    /*
     *正确使用
     *
     * synchronized（lock）{
     * while（条件不成立）{
     *  lock。wait
     * }
     * //do something
     * }
     *
     * 另一个线程使用notifyAll防止虚假唤醒
     * */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
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

        new Thread(() -> {
            synchronized (room) {
                log.debug("外卖到没？[{}]", hasTakeout);
                if (!hasTakeout) {
                    log.debug("没外卖呢，先歇会");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("外卖到没？[{}]", hasTakeout);
                if (hasTakeout) {
                    log.debug("有外卖开始干活");
                }
            }
        }, "女女").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (room) {
                hasTakeout = true;
                room.notifyAll();
                log.debug("外卖送到");
            }
        }, "送外卖").start();

    }
}
