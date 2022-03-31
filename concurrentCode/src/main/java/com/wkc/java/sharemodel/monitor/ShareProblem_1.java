package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/10.
 *
 * @author Weikaichen
 */
public class ShareProblem_1 {
    /*
    * 临界区Critical section
    * 一个程序运行多个线程本身是没有问题的
    * 问题出现在多个线程访问共享资源
    *   多个线程读共享资源其实也没有问题
    *   是多个线程对共享资源读写操作时发生指令交错，就会出现问题
    * 一段代码内如果存在对共享资源的多线程读写操作，称这段代码为临界区
    * */

    /*
    * 竟态条件 Race Condition
    * 多个线程在临界区内执行，由于代码的执行顺序不同而导致结果无法预测，称之为发生竟态条件
    * */
}
