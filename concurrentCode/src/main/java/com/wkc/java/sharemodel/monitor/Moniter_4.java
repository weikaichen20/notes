package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/15.
 *
 * @author Weikaichen
 */
public class Moniter_4 {
    /*
     * Java对象头
     * 以32为虚拟机为例
     * 普通对象
     *                Object Header（64 bits）由以下两个组成
     * Mark Word (32bits)        Klass Word（32 bits）
     *
     * 数组对象
     *                     Object Header（96 bits）
     * Mark Word (32bits)        Klass Word（32 bits）  array length（32bits）
     *
     *
     * 其中Mark Word 结构为
    |-------------------------------------------------------|--------------------|
    | Mark Word (32 bits)                                           | State |
    |-------------------------------------------------------|--------------------|
    | hashcode:25 | age:4 | biased_lock:0 | 01                      | Normal |
    |-------------------------------------------------------|--------------------|
    | thread:23 | epoch:2 | age:4 | biased_lock:1 | 01              | Biased |
    |-------------------------------------------------------|--------------------|
    | ptr_to_lock_record:30 | 00                                    | Lightweight Locked |
    |-------------------------------------------------------|--------------------|
    | ptr_to_heavyweight_monitor:30 | 10                            | Heavyweight Locked |
    |-------------------------------------------------------|--------------------|
    | | 11                                                          | Marked for GC |
    |-------------------------------------------------------|--------------------|
     * */



    /*
    * Moniter（锁）
    * Moniter被翻译为监视器或者管程
    *
    * monitor为操作系统的对象 内含 waitSet EntryList（阻塞队列） owner
    *
    * 1.刚开始owner为null
    * 2.当thread-2执行synchronized（obj）就会将moniter中的owner设为thread-2，moniter只能有一个owner
    * 3.在Thread-2上锁的过程中，如果thread-3,thread-4,thread-5也来执行synchronized（obj），就会进入entryList BLOCKED
    * 4.Thread-2执行完同步代码块的内容，然后唤醒EntryList中等待的线程来竞争锁，竞争时是非公平的
    * 5.图中WaitSet中Thread-0，thread-1是之前获得过锁，但条件不满足进入WAITING状态的线程，
    *
    * 注意
    *   synchronized必须是进入同一个对象moniter才有上述的效果
    *   不加synchronized的对象不会关联监视器，不遵从以上规则
    * */
}
