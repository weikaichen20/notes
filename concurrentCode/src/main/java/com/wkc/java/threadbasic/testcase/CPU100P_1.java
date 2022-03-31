package com.wkc.java.threadbasic.testcase;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
public class CPU100P_1 {
    //防止CPU占用100%
    public static void main(String[] args) {
        //sleep实现
        //在没有利用cpu来计算时，不要让while(true)空转cpu，这时可以使用yield或sleep来让出cpu
        //的使用权给其他程序
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        * 可以用wait或条件变量达到类似的效果
        * 不同的是，后两种都需要加锁，并且需要相应的的唤醒操作，一般适用于进行同步的场景
        * sleep适用于无需锁同步的场景
        * */
    }
}
