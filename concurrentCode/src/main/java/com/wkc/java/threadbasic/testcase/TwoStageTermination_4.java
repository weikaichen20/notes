package com.wkc.java.threadbasic.testcase;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
//todo 二阶段终止模式
@Slf4j
public class TwoStageTermination_4 {
    /*
     * 在一个线程T1中如何优雅终止线程T2？
     *
     * 错误思路
     * 使用线程对象的stop()方法停止线程
     *   stop方法会真正的杀死线程，如果这时线程锁住了共享资源，那么当他被杀死后就再也
     * 没有机会释放锁，其他线程将永远无法获取锁
     *
     * 使用System.exit()方法停止线程
     *   目的仅是停止一个线程，但这种做法会让整个程序都停止
     *
     * */
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();

        Thread.sleep(3500);
        twoPhaseTermination.stop();
    }
}
@Slf4j(topic = "t.monitor")
class TwoPhaseTermination {
    private Thread monitor;

    //启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);//情况1
                    log.debug("执行监控记录");//情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread.interrupt();
                }

            }
        });
        monitor.start();
    }


    //停止监控的线程
    public void stop() {
        monitor.interrupt();
    }
}
