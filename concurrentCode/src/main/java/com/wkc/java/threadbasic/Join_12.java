package com.wkc.java.threadbasic;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class Join_12 {
    public static  int i=0;
    public static void main(String[] args) throws InterruptedException {
        log.debug("start");
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                log.debug("start");
                log.debug("end");
                i = 10;
            }
        };

        thread.start();
        thread.join();
        log.debug("res {}",i);
    }
}
