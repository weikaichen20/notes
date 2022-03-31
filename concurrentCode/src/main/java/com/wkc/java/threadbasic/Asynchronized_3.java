package com.wkc.java.threadbasic;

/**
 * Created on 2022/3/8.
 *
 * @author Weikaichen
 */
public class Asynchronized_3 {
    /*
    * 应用之异步调用
    * 从方法调用的角度来讲
    * 需要等待结果返回才能继续运行就是同步
    * 不需要等待结果返回，就能继续运行就是异步
    * 注意：同步在多线程还有另外一层意思，就是让多个线程步调一致
    * */


    /*
    * 1.单核cpu下，多线程不能实际提高程序运行的效率，只是为了能够在不同的人物之间切换，不同线程
    * 轮流使用cpu，不至于一个线程总占用cpu，别的线程没法干活
    * 2.多核cpu可以并行跑多个线程，但能否提高程序运行效率还是要分情况的
    *       有些任务，通过精心设计，将任务拆分，并行执行，当然可以提高程序的运行效率，但不是所有的计算
    *       任务都能拆分（参考阿姆达尔定律）
    *       也不是所有的任务都需要拆分，任务的目的如果不同，拆分和效率无意义
    * IO操作不占用Cpu，只是我们一般拷贝文件使用的是【阻塞IO】，这时相当于线程虽然不用cpu，但是需要一直等待
    * IO结果，没能充分利用线程，所以才有后面的【非阻塞IO】和异步【IO】优化
    * */
}