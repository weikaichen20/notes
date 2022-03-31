package com.wkc.java.threadbasic.testcase;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class Interrupt_3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
                while (true){
                    if (Thread.currentThread().isInterrupted()){
                        log.debug("打断标记为真，退出");
                        break;
                    }
                }
        });
        thread.start();

        Thread.sleep(1000);
        log.debug("interrupt");
        thread.interrupt();
    }
}
