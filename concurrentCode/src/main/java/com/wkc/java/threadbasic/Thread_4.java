package com.wkc.java.threadbasic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created on 2022/3/8.
 *
 * @author Weikaichen
 */
@Slf4j
public class Thread_4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        create4();
    }

    private static void create4() throws InterruptedException, ExecutionException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("runing");
            return 100;
        });
        Thread t3 = new Thread(task, "t3");
        t3.start();
        System.out.println(task.get());
    }

    /**
     * runnable lambda
     */
    private static void create3() {
        Runnable runing = ()->log.debug("runing");
        new Thread(runing, "t2").start();
        log.debug("runing");
    }

    /**
     * runnable
     */
    private static void create2() {
        //任务和线程分离
        Runnable runing = new Runnable() {
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        new Thread(runing, "t2").start();
        log.debug("runing");
    }

    /**
     * 通过thread创建线程
     */
    private static void create1() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        thread.setName("t1");
        thread.start();

        log.debug("runing");
    }
}
