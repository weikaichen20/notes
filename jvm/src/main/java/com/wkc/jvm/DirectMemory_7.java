package com.wkc.jvm;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
/*
 * -XX:+DisableExplicitGC 禁用显式的GC（System.gc()）
 *  导致直接内存不能释放
 * */
public class DirectMemory_7 {
    public static void main(String[] args) {

    }
    /*
     * 直接内存 定义(java代码和系统都能同时访问的内存)
     * Direct Memory
     * 常见于NIO操作，用于数据缓冲区
     * 分配回收成本较高，但读写性能高
     * 不受JVM内存回收管理
     *
     * */

    /*
     * 使用Unsafe对象完成直接内存的分配回收，并且回收需要主动调用freeMemory方法
     * ByteBuffer的实现类内部，使用了Cleaner（虚引用）来监测ByteBuffer对象，一旦ByteBuffer对象被垃圾回收，那么就会
     * ReferenceHandler线程通过Cleaner的clean方法调用freeMemory来释放直接内存
     *
     * */
}
