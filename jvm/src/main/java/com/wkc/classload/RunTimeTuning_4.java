package com.wkc.classload;

import java.util.Objects;

/**
 * Created on 2022/3/2.
 *
 * @author Weikaichen
 */
public class RunTimeTuning_4 {
    //jit
    //-XX:+PrintComilation -XX:-DoEscapeAnalysis
    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                new Object();
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\n", i, (end - start));
        }
    }

    /*
     * JVM将执行状态分成了5个层次
     * 0层，解释执行（Interpreter）
     * 1层，使用C1即时编译器编译执行（不带profiling）
     * 2层，使用C1即时编译器编译执行（带基本的profiling）
     * 3层，使用C1即时编译器编译执行（带完全的profiling）
     * 4层，使用C2即时编译器编译执行
     *   profiling是指在运行过程中收集一些程序执行状态的数据，例如【方法的调用次数】，【循环的回边次数】
     * 即时编译器（JIT）与解释器的区别
     * 解释器是将字节码解释为机器码，下次即使遇到相同的字节码，还会执行重复的解释
     * JIT是将一些字节码编译为字节码，并存入Code Cache，下次遇到相同的代码，直接执行，无需在编译
     * 解释器是将字节码解释为针对所有平台都通用的机器码
     * JIT会根据平台类型，生成平台特定的机器码
     *
     * 对于占据大部分不常用的代码，我们无需耗费时间将其编译成机器码，而是采取解释器解释执行的方法运行，另一方面
     * ，对于仅占小部分的热点代码，我们则可以将其编译成机器码，以达到理想的运行速度，执行效率上简单比较一下
     * Interpreter<C1<C2,总的目标时发现热点代码（hotspot的由来），优化之
     *
     * 刚才的一种优化手段称之为【逃逸分析】，发现新建的对象是否逃逸，可以使用-XX:-DoEscapeAnalysis关闭挑一分析，再查看运行结果
     * */


    /*
     * 反射优化
     * 通过查看ReflectFactoty源码
     * sun.reflect.noInflation可以用来禁用膨胀（直接生成GeneratedMethodAccessort），但首次生成耗时
     * 如果仅反射调用用一次，不划算
     * sun.reflect.inflationThreshold可以修改膨胀阈值
     * */
}
