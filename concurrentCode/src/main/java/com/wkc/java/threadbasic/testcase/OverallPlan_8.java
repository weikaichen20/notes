package com.wkc.java.threadbasic.testcase;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
@Slf4j
public class OverallPlan_8 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                log.debug("洗水壶一分钟");
                Thread.sleep(1000);
                log.debug("洗水壶完成");
                log.debug("烧开水十五分钟");
                Thread.sleep(15000);
                log.debug("烧开水完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        log.debug("洗茶壶茶杯五分钟");
        Thread.sleep(5000);
        log.debug("洗茶壶茶杯完成");
        log.debug("找茶叶一分钟");
        Thread.sleep(1000);
        log.debug("找茶叶完成");
        thread.join();

        log.debug("泡茶++++++++++++++");


    }
}
