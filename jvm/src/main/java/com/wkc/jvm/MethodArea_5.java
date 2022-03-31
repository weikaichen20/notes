package com.wkc.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Created on 2022/1/14.
 *
 * @author Weikaichen
 */

public class MethodArea_5 extends ClassLoader {//可以用来加载类的二进制字节码
    //方法区

    //定义
    /*
     * 线程共享
     * 存储着每个类的构造信息，
     * 譬如运行时的常量池，字段，方法数据，
     * 以及方法和构造方法的代码，
     * 包括一些在类和实例初始化和接口初始化时候使用的特殊方法
     *
     * 方法区在jvm启动时候被创建。虽然方法区在逻辑层面上是堆的一部分，但是就简单实现来说既不会被回收也不会被压缩。
     * 这个规范并不强制指定方法区存放的位置也不会对编译过的代码有管理策略的限制。方法区可能有一个固定的大小或者也可以
     * 通过计算大小去扩展也可以在不需要的时候被压缩。方法区的内存也不需要是连续的。
     *
     * hotspot
     * jdk1.8之前实现为永久代，实际是堆的空间，StringTable放在方法区常量池中。
     * 1.8以后为元空间直接占用系统内存，StringTable放在堆里
     * 设置元空间的参数
     * -XX:MaxMetaspaceSize=8m
     * */

    /**
     * 1.6
     * 演示永久代内存溢出
     * -XX:MaxPermSize=8m
     * @param args
     */

    /**
     * 1.8
     * 演示元空间内存溢出
     * -XX:MaxMetaspaceSize=8m
     *
     * @param args
     */
    public static void main(String[] args) {
        int j = 0;
        try {
            MethodArea_5 methodArea = new MethodArea_5();//继承ClassLoader 类加载器
            for (int i = 0; i < 10000; i++, j++) {
                //ClassWriter 作用是生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                //版本号，public ，类名 ，报名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "class" + i, null, "java/lang/Object", null);
                //返回byte数组
                byte[] bytes = classWriter.toByteArray();
                //执行了类的加载
                methodArea.defineClass("Class" + i, bytes, 0, bytes.length);//class对象
            }
        } finally {
            System.out.println(j);
        }
    }


}
