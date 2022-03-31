package com.wkc.java.threadbasic.testcase;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class Sync_2 {
    /*
    * 需要等待结果返回，才能继续运行就是同步
    * 不需要等待结果同步，就能继续运行就是异步
    * */
    //interrupt一个被sleep，wait，join锁住的方法，会将打断标记置为true，然后清除打断标记，然后throw interrupted exception
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep");
            try {
                Thread.sleep(5000);//wait,join 被打断后打断标记置为false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        log.debug("main");
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
        Thread.sleep(100);
        log.debug("打断标记：{}",t1.isInterrupted());
    }
}
