package com.wkc.java.threadbasic.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class Park_5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            log.debug("park");
            LockSupport.park();
            log.debug("unpark..");
            Thread.interrupted();
            LockSupport.park();
            log.debug("unpark..");
        });
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
    }



}
