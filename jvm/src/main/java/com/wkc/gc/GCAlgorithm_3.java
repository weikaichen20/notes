package com.wkc.gc;

/**
 * Created on 2022/1/27.
 *
 * @author Weikaichen
 */
public class GCAlgorithm_3 {
    /*
     * 垃圾回收算法
     *
     * 1.标记清除
     * 速度较快
     * 会造成内存碎片
     * 通过GCRoot查找没有被引用的对象进行标记，标记完成后清除。两个阶段标记和清除
     *
     *
     * 2.标记整理
     * 速度较慢
     * 不会造成内存碎片
     * 通过GCRoot查找没有被引用的对象进行标记，标记完成后清除并进行整理 两个阶段标记和清除
     *
     *
     * 3.复制
     * 会使用双倍的空间
     * 不会造成内存碎片
     * 使用From和To两块内存空间，To为空，首先标记FROM区的无用对象，把有用对象移到TO区，对From整个清除，然后把From和to调换
     *
     * */
}