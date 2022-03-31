package com.wkc.java.jmm;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class VolatilePrinciple_5 {

    /* volatile的底层实现原理是内存屏障，Memory Barrier
     *
     * 对volatile变量的写指令后会加入写屏障
     * 对volatile变量的读指令前会加入读屏障
     *
     *                          如何保证可见性
     * 写屏障（sfence）保证在该屏障之前的，对共享变量的改动，都同步到主存中
     *
     * num=2;
     * ready=true;//ready是volatile赋值带写屏障
     * //写屏障 （把ready和ready之前的同步到主存）
     *
     * 而读屏障（Ifence）保证在该屏障之后，对共享变量的读取，加载的是主存中的最新数据
     *
     * //读屏障
     * //ready是volatile 读取值带读屏障
     * if(ready){
     *      r.r1=num+num;
     * }else{
     *      r.r1=1;
     * }
     *
     *
     *                         如何保证有序性
     * 写屏障会确保指令重排序时，不会将写屏障之前的代码排在写屏障之后
     *
     * num=2;
     * ready=true;//ready是volatile赋值带写屏障
     * //写屏障
     *
     * 读屏障会确保指令重排序时，不会将读屏障之后的代码排在读屏障之前
     *
     * //读屏障
     * //ready是volatile 读取值带读屏障
     * if(ready){
     *      r.r1=num+num;
     * }else{
     *      r.r1=1;
     * }
     *
     *                            注意
     * 写屏障仅仅保证之后的读能读到最新的结果，但不能保证读跑到它前面去
     * 而有序性的保证也只保证了本线程内相关代码不被重排序
     *
     * 并不能解决指令交错
     *
     *
     *
     * */
}
