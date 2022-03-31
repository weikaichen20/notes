package com.wkc.java.threadbasic;

/**
 * Created on 2022/3/8.
 *
 * @author Weikaichen
 */
public class Threadmethod_8 {


    /*
    * start()
    * 功能说明
    *   启动一个新线程，在新的线程运行run方法中的代码
    * 注意
    *   start方法只是让线程进入就绪，里面代码不一定立刻运行（CPU的时间片还没分给它）
    * 每个线程对象的start方法只能调用一次，多次调用会抛出异常
    *
    * run()
    * 功能说明
    *   新线程启动后调用的方法
    * 注意
    *   如果在构造Thread对象传递Runnable参数，则线程启动后会调用Runnable中的run方法
    * 否则默认不执行任何操作，但可以创建Thread的子类对象，来覆盖默认行为
    *
    * join()
    *   等待线程运行结束
    *
    * join(long n)
    *   等待线程运行结束，最多等待n毫秒
    *
    * getId()
    *   获取线程长整型id
    *   id唯一
    *
    * getName()
    *   获取线程名
    *
    * setName(String)
    *   修改线程名
    *
    * getPriority()
    *   获取线程优先级
    *
    * setPriority(int)
    *   修改线程优先级
    *   java中规定线程优先级是1-10的整数，较大的优先级能提高该线程被Cpu调度的几率
    *
    * getState
    *   获取线程状态
    *   Java中线程状态是用6个enum表示，分别是：NEW，RUNNABLE,BLOCKED,WAITING,TIMED_WAITING，
    *   TERMINATED
    *
    * isInterrupted()
    *   判断是否被打断
    *   不会清除打断标记
    *
    * isAlive()
    *   线程是否存活
    *
    * interrupt()
    *   打断线程
    *   如果被打断线程正在sleep,wait,join,会导致被打断的线程抛出interruptExecption,并
    *   清除打断标记，如果打断的正在运行的线程，则会设置打断标记，park的线程被打断，也会
    *   设置打断标记
    *
    * interrupted()  static方法
    *   判断当前线程是否被打断
    *   会清除打断标记
    *
    * currentThread() static方法
    *   获取当前正在执行的线程
    *   让当前执行的线程休眠n毫秒，休眠时间让出CPU的时间片
    *
    * yield()
    *   提示线程调度器让出线程对Cpu的使用
    *   主要是为了测试和调试
    *
    * */

    /*
     * 不推荐的方法
     *
     * stop()      停止线程运行  ===>二阶段关闭
     * suspend()   挂起暂停线程  ===>wait
     * resume()    恢复线程运行  ===>notify
     *
     * 会破坏同步代码块，导致死锁
     * */

}
