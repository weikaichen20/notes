package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
public class ReentrantLockVar_19 {
    /*
     * 条件变量
     * synchronized中也有条件变量，就是我们讲的waitSet休息室，当条件不满足时进入waitSet等待
     * ReentrantLock的条件变量比synchronized强大之处在于，它是支持多个条件变量的，这就好比
     *      synchronized那些不满足条件的都在同一间休息室等消息
     *      而ReentrantLock支持多间休息室，唤醒是也是按照不同休息室唤醒
     *
     * 使用流程
     *  await前需要获得锁
     *  await执行后，会释放锁，进入conditionObject等待
     *  await的线程被唤醒（或打断或超时），重新竞争lock锁
     *  竞争lock锁成功后，从await后继续执行
     *
     * */
}
