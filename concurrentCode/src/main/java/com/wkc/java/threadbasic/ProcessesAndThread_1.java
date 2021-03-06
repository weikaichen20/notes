package com.wkc.java.threadbasic;

/**
 * Created on 2022/3/7.
 *
 * @author Weikaichen
 */
public class ProcessesAndThread_1 {
    /*
    * 进程
    * 1.程序由指令和数据组成，但这些指令要运行，数据要读写，就必须将指令加载至CPU，数据加载至内
    * 存，在指令运行过程中还需要用到磁盘，网络等设备，进程就是用来加载指令，管理内存，管理IO
    * 2.当一个程序被运行，从磁盘加载这个程序的代码至内存，这时就开启了一个进程
    * 3.进程就可以视为程序的一个实例，大部分程序可以同时运行多个实例进程（记事本，画图，浏览器）
    * 也有的程序只能启动一个实例进程
    * */

    /*
    * 线程
    * 一个进程之内可以分为一到多个线程
    * 一个线程就是一个指令流，将指令流中的一条条指令以一定的顺序交给Cpu执行
    * Java中，线程作为最小调度单位，进程作为资源分配的最小单位，在windows中进程是不活动的，
    * 只是作为线程的容器
    *
    * */

    /*
    * 二者对比
    * 1.进程基本上是相互独立的，而线程存在于进程内，是进程的一个子集
    * 2.进程拥有共享的资源，如内存空间等，供其内部的线程共享
    * 3.进程间通信较为复杂
    *   同一台计算机的进程通信称为IPC（Inter-Process communication）
    *   不同计算机之间的进程通信，需要通过网络，并遵守共同的协议，例如HTTP
    * 4.线程通信相对简单，因为它们共享进程内的内存，一个例子是多个线程可以访问同一个共享变量
    * 5.线程更轻量，线程上下文切换成本一般要比进程上下文切换低
    * */
}
