package com.wkc.java.threadbasic;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/8.
 *
 * @author Weikaichen
 */
@Slf4j
public class AlternateExecution_5 {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true){
                log.debug("=============");
            }
        }, "t1").start();
        new Thread(() -> {
            while (true){
                log.debug(">>>>>>>>>>>>>");
            }
        }, "t1").start();
    }
}
