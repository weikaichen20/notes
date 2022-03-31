package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/15.
 *
 * @author Weikaichen
 */
public class ThreadSafety_3 {
    /*
    * 成员变量和静态变量是否线程安全？
    * 如果他们没有共享，则线程安全
    * 如果他们被共享了，根据他们的状态是否能够改变，又分为两种情况
    *   如果只有读操作，线程安全
    *   如果有读写操作，则这段代码是临界区，需要考虑线程安全
    *
    *
    * 局部变量是否线程安全？
    * 局部变量是线程安全的
    * 局部变量引用的对象尾部
    *   如果该对象没有逃离方法的作用访问，它是线程安全的
    *   如果该对象逃离方法的作用访问，需要考虑线程安全
    * */


    /*
    * 线程安全的类
    * String，Integer其他包装类，Stringbuffer,Random,Vector,Hashtable,juc包下的类
    * 这里说的线程安全多个线程调用他们同一个实例的某个方法时，是线程安全的。
    *   他们每个方法是原子的
    *   多个方法的组合不一定是原子的
    *
    * 线程安全类方法的结合，比如 HashTable的put和get
    *
    * 不可变类线程安全行
    *   String，integer等都是不可变类，因为其内部的状态不可以改变，因此方法是线程安全的
    *
    *
    * */


    /*
    * for /L %n in (1,1,10) do java -cp ".;类需要jar包路径" 类的全包名 com.wkc.java.sharemodel.ThreadSafety_3.java
    *
    *
    * */


}
