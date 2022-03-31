package com.wkc.java.sharemodel.testcase;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
@Slf4j
public class TestCorrectPostureStepOne_2 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    /*
    * 其他干活线程，要一直阻塞，效率太低
    * 小南下次呢很难过必须睡足两秒才能醒来，就算烟提前送到，也无法立刻醒来
    * 加了synchronized（room），就好比小南在里面反锁了门睡觉，烟根本没法送进门，main没加synchronized就好像是main线程是翻窗户进来的
    * 解决方法 使用wait-notify
    * */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("有烟开始干活");
                }
            }
        }, "小南").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    log.debug("开始干活");
                }
            }, "其他人").start();
        }

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
//            synchronized (room){
            hasCigarette = true;
            log.debug("烟送到了");
//            }
        }, "送烟的").start();

    }
}
