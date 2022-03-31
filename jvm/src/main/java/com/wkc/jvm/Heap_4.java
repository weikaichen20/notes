package com.wkc.jvm;

/**
 * Created on 2022/1/14.
 *
 * @author Weikaichen
 */
public class Heap_4 {
    //Heap堆
    //通过new关键字，创建对象都会使用堆内存

    //特点
    /*
     * 堆是线程共享的，堆中的对象都需要考虑线程安全的问题
     * 有垃圾回收机制
     * -Xmx8m  最大堆大小
     * -Xms 初始堆大小
     * */

    /**
     * 堆内存溢出 OOM
     *
     */

    /**
     * 堆内存诊断
     * 1.jps工具
     *  查看当前系统中有那些java进程
     * 2.jmap工具
     *  某个时刻查看堆内存占用情况
     *  jmap -heap 线程id
     *  System.gc();
     * 3.jconsole
     *  图形界面多功能的监测工具，可以连续监测
     * 4.jvisualVM
     * 堆dump
     */

    /**
     * 案例
     * 垃圾回收后，占用依然很高
     *
     */
}
