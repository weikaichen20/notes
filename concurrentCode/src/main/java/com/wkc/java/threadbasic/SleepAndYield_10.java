package com.wkc.java.threadbasic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class SleepAndYield_10 {
    /*
     * sleep
     * 1.调用sleep会让当前线程从Running进入Timed Waiting状态
     * 2.其他线程可以使用interrupt方法打断正在睡眠的线程，这时sleep方法会抛出InterruptException
     * 3.睡眠结束后的下次呢很难过未必会立刻得到执行
     * 4.建议使用TimeUnit的sleep代替Thread的sleep来获得更好的的可读性
     *
     * yield
     * 1.调用yield会让当前线程从Running进入Runnable状态，然后调度执行其他同优先级的线程，如果这时没有
     * 同优先级的线程，那么不能保证让当前线程很难过暂停的效果
     * 2.具体的实现依赖于操作系统的任务调度器
     *
     * 线程优先级
     * 线程优先级会提示（hint）调度器优先调度该线程，但它仅仅是一个提示，调度器可以忽略它
     * 如果cpu比较忙优先级高的会获取更多的时间片，但cpu闲时，优先级几乎没作用
     *
     * */
    public static void main(String[] args) throws InterruptedException {



    }

    private static void timeUnit() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }

    private static void interrupt() throws InterruptedException {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                log.debug("t1 enter sleep");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("t1 wake up");
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        Thread.sleep(500);
        log.debug("interrupt t1 sleep");
        thread.interrupt();
    }

    //状态
    private static void state() {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        log.debug("t1 state: {}", thread.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1 state: {}", thread.getState());
    }
}
