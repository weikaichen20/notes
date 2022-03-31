package com.wkc.java.threadbasic;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

/**
 * Created on 2022/3/8.
 *
 * @author Weikaichen
 */
@Slf4j
public class ThreadRunning_7 {
    /*
    * 栈与栈帧
    * Java Virtual Machine Stacks（JAVA虚拟机栈）
    * 栈内存给线程用的，每个线程启动后，虚拟机就会为其分配一块栈内存
    *   每个线程有多个栈帧组成，对应着每次方法调用时所占用的内存
    *   每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法
    *
    * 线程上下文切换
    *   线程的cpu时间片用完
    *   垃圾回收
    *   有更高优先级的线程需要运行
    *   线程自己调用了sleep，yield，wait，join，park，synchronized，lock等方法
    * 当Context Switch 发生时，需要由操作系统保存当前线程的状态，并恢复另一个线程的状态
    * Java中对应的概念就是程序计数器，它的作用是记住下一条jvm指令的执行地址，是线程私有的
    *
    *
    * */
    public static void main(String[] args) {
        new Thread(() -> {
            log.debug("runing");
        }).start();

        method1(10);
    }

    private static void method1(int i) {
        System.out.println(i);
    }


}
