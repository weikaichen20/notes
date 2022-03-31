package com.wkc.java.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
public class visibility_2 {
    /* 退不出的循环
     *
     * 1.初始状态，t线程刚开始从主内存读取了run的值到工作内存
     * 2.因为t线程要频繁的从主内存中读取run的值，JIT编译器会将RUN的值缓存至自己工作内存的
     * 告诉缓存中，减少主存中run的访问，提高效率
     * 3.1秒以后，main线程修改了run的值，并同步至主存，而t是从自己的高速缓存中读取这个变量的值
     *
     * 解决办法
     * 使用volatile关键字
     * 加锁也能保证可见性（创建monitor重量级）
     *
     * 它可以修饰成员变量，和静态成员变量，他可以避免线程从自己的工作缓存中查找变量的值，必须到
     * 主存中获取它的值，线程volatile变量都是直接操作主存
     *
     * */

    /* 可见性 VS 原子性
     * 多个线程之间，一个线程对volatile变量的修改对另一个线程可见，不能保证原子性
     * 仅用在一个写线程，多个读线程的情况
     *
     * 例如：两个线程一个i++一个i--，只能保证看到最新值，不能解决指令交错
     *
     * 注意
     * synchronized 语句块可以保证代码块的原子性，同时也能保证代码块内变量的可见性，但是
     * synchronized是属于重量级操作，性能相对更低
     * */
}
@Slf4j
class TwoPhaseTermination {
    private Thread monitor;

    private volatile boolean stop=false;
    //启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (stop) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);//情况1
                    log.debug("执行监控记录");//情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        monitor.start();
    }


    //停止监控的线程
    public void stop() {
        stop=true;
    }
}
