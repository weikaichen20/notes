package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
public class SpinOpt_7 {
    /*
    * 自旋优化（适合多核CPU）
    * 重量级锁竞争的时候，还可以使用自旋来进行优化，如果当前线程自旋成功（即这时候持锁线程已经退出了
    * 同步块，释放了锁），这时当前线程可以避免阻塞（阻塞会发生线程上下文切换）
    *
    * 在Java6之后自旋锁是自适应的，比如对象刚刚的一次自旋操作成功过，那么认为这次的自旋成功
    * 的可能性高，就会多自旋几次，反之，就少自旋甚至不自旋，比较智能
    *
    * 自旋会占用CPU时间，单核CPU自旋就是浪费，多核CPu自旋才能发挥优势
    *
    * JAVA7之后不能控制是否开启自旋功能
    *
    * */
}
