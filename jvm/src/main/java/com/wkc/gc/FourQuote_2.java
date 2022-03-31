package com.wkc.gc;

/**
 * Created on 2022/1/24.
 *
 * @author Weikaichen
 */
public class FourQuote_2 {
    /*
    * 1、强应用
        强引用是指在java程序中普遍存在的对象，比如我们自己new出来的；这部分对象只要引用还存在，就不会被垃圾回收器回收。
        * 比如：spring中的service、controller，或者其中的成员变量。
        2、软引用
        这部分对象是一些不必要的对象（比如一些简单的缓存场景）；虚拟机为对象分配内存时，如果发现堆空间不够则会执行一次Minor GC回收这类对象，
        * 如果回收完还不够则会抛出OOM异常。
        java 中我们用 SoftReference reference = new SoftReference<>(Object);来生成软引用
        3、弱引用
        这部分对象也是不必要的对象，相比软应用而言更弱，只能活到下次GC时，如果发生GC，这部分对象一定会被回收。
        java中用WeakReference reference = new WeakReference<>(Object);生成弱引用。
        4、虚引用
        又称幽灵引用或者幻影引用，虚拟机可能随时回收这部分对象，其唯一作用可能是对象被回收时收到一个系统通知；暂时没发现其应用场景。
    * */
    /*
     * 强引用： 只有所有GC roots对象都不通过强引用引用该对象，该对象才能被垃圾回收，例如 Long a=new Long(); a=null; 调用System.gc();a就会被垃圾回收
     *
     * 软引用（SoftReference）
     * 仅有软引用引用该对象时，在垃圾回收后，内存依然不足会再次进垃圾回收，回收引用对象
     * 可以配合引用队列来释放软引用本身
     *
     * 弱引用（WeakReference）
     * 仅有弱引用引用该对象时，在垃圾回收时，无论内存是否充足，都会回收弱引用对象
     * 可以配合引用队列来释放弱引用自身
     *
     * 虚引用 （PhantomReference）
     * 必须配合队列使用，主要配合ByteBuffer使用，被引用对象回收时，会将虚引用入队，由Reference
     * Handler 线程调用虚引用相关方法释放直接内存
     *
     * 终结器引用（FinalReference）
     * 无需手动编码，但其内部配合引用队列使用，在垃圾回收时，终结器引用入队（被引用的对象暂时没有被
     * 回收），再由Finalizer Handler线程通过终结器找到备用用的对象并调用他们的finalize方法，第二次
     * GC才能回收被引用对象
     * */
}
