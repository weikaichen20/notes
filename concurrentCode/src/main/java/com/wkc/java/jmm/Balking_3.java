package com.wkc.java.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class Balking_3 {
}
@Slf4j
class TwoPhaseTermination_balking {
    private Thread monitor;

    private volatile boolean stop=false;

    //判断是否执行过start方法
    private boolean starting=false;

    //启动监控线程
    public synchronized void start() {
        if (starting)
            return;
        starting=true;
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

