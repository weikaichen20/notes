package com.wkc.java.threadbasic.testcase;

/**
 * Created on 2022/3/9.
 *
 * @author Weikaichen
 */
public class Daemon_6 {
    public static void main(String[] args) {
        /*
        * 默认情况下，Java进程需要等待所有线程都运行结束，才会结束，有一种特殊的线程叫做
        * 守护线程，只要其他非守护线程运行结束了，即时守护线程的代码没有执行完，也会强制结束
        * */

        /*
        * 垃圾回收器线程就是一种守护线程
        *
        * tomcat中的Accepter和Poller线程都是守护线程，所以Tomcat接收到shutdown命令后，不会等待他们处理完当前请求。
        * */

    }
}
