package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
public class WaNo_9 {
    /*
    * 原理 wait/notify
    *
    * Owner线程发现条件不满足，调用wait方法，即可进入WaitSet变为WAITING状态
    * BLOCKED 和 WAITING 的线程都处于阻塞状态，不占用CPU时间片
    * BLOCKED 线程会在Owner线程释放锁时唤醒
    * WAITING 线程会在Owner线程调用notify回notifyAll时唤醒，但唤醒后并不意味着立刻获得锁，
    * 需进入EntryList重新竞争
    *
    * */

    /*
    * API介绍
    *
    * obj.wait()让进入object监视器的线程到waitSet等待
    * obj.notify()让object上正在waitSet等待的线程挑一个唤醒
    * obj.notifyAll()让object上正在waitSet等待的线程全部唤醒
    *
    * 他们都是线程之间进行协作的手段，都属于Object对象的方法，必须获得此对象的锁，才能调用方法
    *
    * wait()方法释放对象的锁，进入waitSet等待区，从而让其他线程就有机会获取对象的锁，无限制等待，直到notify为止
    *
    * wait(long n)有时限的等待，到n毫秒后等待结束，或是被notify
    * */

    /*
    * sleep(long n)和wait(long n)区别
    * sleep是Thread方法，而wait是Object方法
    * sleep不需要强制和synchronized配合使用，但wait需要和synchronized一起用
    * sleep在睡眠的同时，不会释放对象锁，但wait在等待的时候会释放对象锁
    * 他们的状态都是TIMED_WAITING
    * */
}
