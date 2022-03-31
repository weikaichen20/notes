package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * Created on 2022/3/18.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "c.w.j")
public class ParkAndUnpark_13 {
    /*
     * 基本使用 他们是LockSupport类中的方法
     *
     * //暂停当前线程
     * LockSupport.park()
     *
     * //恢复某个线程的运行
     * LockSupport.uppark(暂停线程对象)
     *
     * */

    /*
     * 类似与selector.wakeup();
     * wakeup类似发一张票，，当selector阻塞时，有票就不阻塞
     * */

    /*
     * 特点
     * 与Object的wait & notify相比
     * 1.wait，notify，notifyAll必须配合Object Monitor 一起使用，而park & unpark不必
     * 2.park & unpark是以线程为单位来阻塞和唤醒下次呢很难过，而notify只能随机唤醒一个等待线程
     * notifyAll是唤醒所有等待线程，就没那么【精确】
     * 3.park & unpark可以先unpark，而wait & notify不能先notify
     * */

    /*
    * park & unpark原理
    * 每个线程都有自己的一个Parker(C实现)对象，由三部分组成_counter,_cond,_mutex
    *
    * 1.线程就像一个旅人，Parker就像他随身携带的背包，条件变量就好比背包中的帐篷，_counter
    * 相当背包的备用干粮（0为耗尽 1 为充足）
    * 2.调用park就是要看需不需要停下来歇息
    *       -如果备用干粮耗尽，那么钻进帐篷歇息
    *       -如果备用干粮充足，那么不需要停留继续前进
    * 3.调用unpark，就好比令干粮充足
    *       -如果这时线程还在帐篷给，就唤醒让他继续前进
    *       -如果这时线程还在运行，那么下次调用park时，仅是消耗备用干粮，不需要停留继续前进
    *           -因为背包空间有限，多次调用unpark仅会补充一份备用干粮
    *
    *
    *
    * 1.当前线程调用Unsafe.park()
    * 2.检查_counter，本情况为0，这时获得_mutex互斥锁
    * 3.线程进入_cond条件变量阻塞
    * 4.设置_counter=0
    *
    *
    * 1.调用Unsafe.unpark(thread_0),设置_counter为1
    * 2.唤醒_cond条件变量中thread_0
    * 3.thread_0恢复运行
    * 4.设置_counter为0
    *
    *
    * 1.调用Unsafe.unpark(thread_0),设置_counter为1
    * 2.当前线程调用Unsafe.park()
    * 3.检查_counter，本情况为1，无需阻塞，继续运行
    * 4.设置_counter为0
    * */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("start..");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();

        Thread.sleep(2000);
        log.debug("unpark...");
        LockSupport.unpark(t1);
    }
}
