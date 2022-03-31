package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "reent")
public class ReentrantLock_18 {
    /*
    * 相对于synchronized 具备如下特点
    * 可中断
    * 可以设置超时时间
    * 可以设置为公平锁
    * 支持多个条件变量
    * 与synchronized一样，都支持可重入
    * */

    /*
    * 可重入
    * 可重入是指同一个线程如果首次获得了这把锁，俺么因为他是这把锁的拥有者，因此有权利
    * 再次获取这把锁，如果是不可重入锁，那么第二次获得锁是，自己也会被锁住
    * */


    /*//基本语法
    ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();//获取锁
        try {
        //临界区
    }finally {
        //释放锁
        reentrantLock.unlock();
    }*/

    /*
    * ReentrantLock默认是不公平锁
    * 可以通过构造方法设置为公平锁
    * 公平锁也是为了解决饥饿问题
    * 公平锁会降低并发度
    * */
    static ReentrantLock reentrantLock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
//        reentry();//可重入（可重入就是说某个线程已经获得某个锁，可以再次获取锁而不会出现死锁）
//        interupt();//可打断
        lockTimeout();//锁超时
    }

    private static void lockTimeout() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁");
            try {
                if(!reentrantLock.tryLock(2,TimeUnit.SECONDS)){
                    log.debug("获取不到锁");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.debug("获取到锁");
            }finally {
                reentrantLock.lock();
            }

        }, "t1");

        reentrantLock.lock();
        log.debug("获得到锁");
        t1.start();
        Thread.sleep(1000);
        log.debug("释放锁");
        reentrantLock.unlock();
    }

    private static void interupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                //如果没有竞争那么此方法就会获取lock对象锁
                //如果有竞争就进入阻塞队列，可以被其他线程用interrupt方法打断
                log.debug("尝试获得锁");
                reentrantLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("获取不到锁");
                return;
            }
            try {
                log.debug("获取到锁");
            }finally {
                reentrantLock.unlock();
            }
        }, "t1");

        reentrantLock.lock();
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }

    private static void reentry() {
        method1();
    }

    private static void method1() {
        reentrantLock.lock();
        try {
            log.debug("execute method1");
            method2();
        }finally {
            reentrantLock.unlock();
        }
    }

    private static void method2() {
        reentrantLock.lock();
        try {
            log.debug("execute method2");
        }finally {
            reentrantLock.unlock();
        }
    }

    private static void method3() {

    }
}
