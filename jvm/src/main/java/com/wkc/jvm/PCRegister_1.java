package com.wkc.jvm;

/**
 * Created on 2022/1/13.
 *
 * @author Weikaichen
 */
public class PCRegister_1 {
    //1.程序计数器

    //1.1定义
    /*
     * Program Counter Register 程序计数器(寄存器)
     * 二进制字节码    jvm指令               Java源代码
     * 0:getstatic     #20                     //PrintStream out =System.out;
     * 1:astore_1                              //--
     * 2:aload_1                               //out.print(1);
     * 3:iconst_1                              //--
     * 4:invokevirtual #26                     //--
     * 5:aload_1                               //out.print(2);
     * 6:iconst_2
     * ....
     * return
     *
     * 每一行jvm指令都会给-->解释器-->解释成机器码-->交给cpu执行
     * 程序计数器(作用:记住下一条jvm指令的执行地址) 物理上是通过寄存器实现的
     * 特点
     *   1.线程私有
     *   2.不会存在内存溢出
     * */

    //1.2作用
    /*
     * 作用:记住下一条jvm指令的执行地址
     * */
}
