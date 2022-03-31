package com.wkc.java.threadbasic;

/**
 * Created on 2022/3/8.
 *
 * @author Weikaichen
 */
public class ParalleAndConcurrency_2 {
    /*
    * 并行与并发
    *
    * 单核cpu下，线程实际还是串行执行，操作系统有一个组件叫做任务调度器，将cpu的时片
    * （windows下时间片最小为15毫秒）分给不同的线程使用，由于cpu在线程间（时间片很短）
    * de切换非常快，人类感觉是同时运行的。
    *   一般会将这种线程轮流使用cpu的做法称为并发，concurrent
    *
    * 并发是同一时间应对多件事情的能力
    * 并行是同一时间动手做多件事情的能力
    *
    * */
}
