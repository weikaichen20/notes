package com.wkc.java.sharemodel.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
//先打印2再打印1
@Slf4j(topic = "aaa")
public class SortPrint_7 {

    static final Object lock = new Object();
    //表示t2是否执行
    static boolean t2round = false;
    static ReentrantLock reentrantLock=new ReentrantLock();
    static Condition print=reentrantLock.newCondition();

    public static void main(String[] args) {
//        waitNotify();
//        renentrantLock();
        unpark();
    }

    private static void unpark() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");


        Thread t2 = new Thread(() -> {
           log.debug("2");
           LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }

    private static void renentrantLock() {
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            try {
                while (!t2round) {
                    try {
                        print.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }finally {
                reentrantLock.unlock();
            }

        }, "t1");


        Thread t2 = new Thread(() -> {
            reentrantLock.lock();
            try {
                log.debug("2");
                t2round = true;
                print.signal();
            }finally {
                reentrantLock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    private static void waitNotify() {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!t2round) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                log.debug("2");
                t2round = true;
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
