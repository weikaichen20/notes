package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "cl")
public class LiveLock_17 {
    /*
     * 活锁
     * 活锁出现在两个线程互相改变对方的结束条件，最后谁也无法结束
     * */


    /* 饥饿
     * 一个线程由于优先级太低，始终得不到cpu调度，也不能够结束。
     *
     * */
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                log.debug("count {}", count);
            }

        }, "t1").start();

        new Thread(() -> {
            while (count < 20) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                log.debug("count {}", count);
            }
        }, "t2").start();
    }
}
