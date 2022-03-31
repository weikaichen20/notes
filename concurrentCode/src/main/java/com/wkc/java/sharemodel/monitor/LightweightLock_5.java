package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
public class LightweightLock_5 {
    /*
    * 轻量级锁使用场景：如果一个对象虽然有多线程访问，但多线程访问的时间是错开的（也就是没有竞争），
    * 那么可以使用轻量级锁来优化
    *
    * 轻量级锁对使用者是透明的，即语法还是Synchronized
    *
    * 轻量级锁
    * 执行到synchronized，线程创建锁记录（lock record）对象，每个线程的栈帧都会包含
    * 一个锁记录的结构，内部可以存储锁定对象的Mark word
    *
    * 让锁记录中Object reference指向锁对象，并尝试用cas替换Object的Mark Word，将
    * Mark Word的值存入锁记录
    *
    * 如果Cas替换成功，对象头中存储了锁记录地址和状态00，表示由线程给对象加锁
    *
    * 如果cas失败，有两种情况
    *   如果是其他线程已经持有了该Object的轻量级锁，这时表明由竞争，进入锁膨胀过程
    *   如果是自己执行了Synchronized锁重入（自己线程给同一个对象加锁），那么再添加一条Lock Record作为重入的计数
    *
    *
    * 当退出synchronized代码块（解锁时）如果有取值为null的锁记录，表示有重入，这时重置锁记录，表示重入计数减一
    *
    * 当退出synchronized代码块（解锁时）锁记录不为null，这时使用cas将Mark Word的值恢复给对象头
    *   成功则解锁成功
    *   失败，索命轻量级锁进行了锁膨胀或已经升级为重量级锁，进入重量级锁解锁流程
    *
    * */
}
