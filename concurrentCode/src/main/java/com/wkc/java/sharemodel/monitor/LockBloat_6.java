package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
public class LockBloat_6 {
    /*
    * 如果在尝试加轻量级锁的过程中，Cas操作无法成功，这时一种情况就是有其他线程为此
    * 对象加上了轻量级锁（有竞争），这时需要进行锁膨胀，将轻量级锁变为重量级锁
    *
    * 当Thread-1进行轻量级锁加锁时，Thread-0已经对该对象加了轻量级锁
    *
    * 这时Thread-1加轻量级锁失败，进入锁膨胀流程
    *   即为Object对象申请Monitor锁，让Object指向重量级锁地址 （后两位10 为重量级锁，00为轻量级锁）
    *   然后自己进入Monitor的EntryList BLOCKED
    *
    * 当Thread-0退出同步块解锁，使用CAS将Mark Word的值恢复给对象头，失败，这时会进入重量级解锁流程，
    * 即按照Moniter地址找到Moniter对象，设置Owner为null，唤醒EntryList中的BLOCKED线程
    *
    * */
}
