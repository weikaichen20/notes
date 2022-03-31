package com.wkc.java.jmm;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class Order_4 {
    /*
     * jvm会在不影响正确性的前提下，可以调整语句的执行顺序
     * 单线程下不会有影响，在多线程下【指令重排】会影响正确性
     *
     * 正确使用volatile可以防止指令重排
     * */
}
