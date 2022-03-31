package com.wkc.jvm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 2022/1/17.
 *
 * @author Weikaichen
 */
/*
 * StringTable[ ] 特点
 * 常量池中的字符串仅是符号，第一次用到的时候才变为对象
 * 利用串池的机制，来避免重复创建字符串对象
 * 字符串变量拼接的原理是 Stringbuilder（1.8）
 * 字符串常量拼接的原理是编译器优化
 * 可以使用intern方法，主动将串池中还没有的字符串对象放入串池
 *   1.8将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有则放入串池，会把串池中的对象返回
 *   1.6将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有会把此对象复制一份，放入串池，会把串池中的对象返回
 * */

/*
 * StringTable的位置
 * 1.8放在heap堆中
 * 1.6放在永久代 permGen 也就是老年代
 * 为什么转移到heap中
 * 程序大量的字符串拼接非常容易导致老年代内存过大导致fullGC,降低程序运行效率。运行fullGC时所有运行的线程都是挂起状态。一般fullGC都是运行一秒以上
 * */
/*
 * StringTable位置
 * 1.8 -Xmx=10m 堆最大内存设置 -XX:+/-UseGCOverheadLimit
 * 加号打开开关，默认是开启状态
 * 开启状态会抛出 OOM GC 如果花费98%的时间进行垃圾回收，只释放了不到2%的heap空间就会报错
 * List能长时间存活
 *
 * 1.6 -XX:MaxPermSize=10m
 * */
/*
 *
 * StringTable的优化
 * 使用VM参数进行调优
 * 设置字符串是否入池
 * */
public class StringTable_6 {
    //StringTable[ ]  hash表 ，不能扩容
    //常量池中的信息，都会被加载到运行时常量池中，这是 a b ab 都是常量池中的符号，还没有变成Java中的字符串对象
    //ldc #2 会把 a 符号变为 "a" 字符串对象后 ，会去StringTable去找，没有的话放入StringTable
    //ldc #3 会把 b 符号变为 "b" 字符串对象后 ，会去StringTable去找，没有的话放入StringTable

    public static void main(String[] args) {
        String a = "a";//懒惰的
        String b = "b";
        String ab = "ab";
        String s4 = a + b;//new java/lang/StringBuilder().append("a").append("b").toString =new String("ab")
        String s5 = "a" + "b";//javac 在编译期间的优化，结果已经在编译器确定为ab

        /*==============================================================*/
        //["a", "b"]执行下面一句后
        String s = new String("a") + new String("b");//new String("ab")

        //堆 new String("a") + new String("b") =new String("ab")
        String s2 = s.intern();//将这个字符串对象尝试放入串池，如果有则不会放入，如果没有则放入串池，会把串池中的对象返回

    }
}
