package com.wkc.java.sharemodel.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "print")
//交替执行abcabcabcabcabc 三个线程各输出ab,c
public class Print_8 {
    static final Object lock = new Object();

    private volatile static int flag=1;

    static ReentrantLock relock=new ReentrantLock();

    static Condition condition=relock.newCondition();

    public static void main(String[] args) {

//        waitnotify();

//        reentrantLock();

    }



    private static void reentrantLock() {
        Thread t1 = new Thread(() -> {
            relock.lock();
            try {
                int count=0;
                while (count<5){
                    while (flag==1){
                        System.out.print("a");
                        flag=2;
                        count++;
                        condition.signalAll();
                    }
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                relock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            relock.lock();
            try {
                int count=0;
                while (count<5){
                    while (flag==2){
                        System.out.print("b");
                        flag=3;
                        count++;
                        condition.signalAll();
                    }
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                relock.unlock();
            }

        }, "t2");

        Thread t3 = new Thread(() -> {
            relock.lock();
            try {
                int count=0;
                while (count<5){
                    while (flag==3){
                        System.out.print("c");
                        flag=1;
                        count++;
                        condition.signalAll();
                    }
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                relock.unlock();
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void waitnotify() {
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                int count=0;
                while (count<5){
                    while (flag==1){
                        System.out.print("a");
                        flag=2;
                        count++;
                        lock.notifyAll();
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock){
                int count=0;
                while (count<5){
                    while (flag==2){
                        System.out.print("b");
                        flag=3;
                        count++;
                        lock.notifyAll();
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            synchronized (lock){
                int count=0;
                while (count<5){
                    while (flag==3){
                        System.out.print("c");
                        flag=1;
                        count++;
                        lock.notifyAll();
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
