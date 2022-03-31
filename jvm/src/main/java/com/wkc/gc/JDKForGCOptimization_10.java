package com.wkc.gc;

/**
 * Created on 2022/2/7.
 *
 * @author Weikaichen
 */
public class JDKForGCOptimization_10 {
    /*
     * Jdk 8u20 字符串去重
     * 优点：节省大量内存
     * 缺点：略微多占用了cpu时间，新生代回收时间略微增加
     *
     * -XX:+UseStringDeduplication
     * 将所有新分配的字符串放入一个队列
     * 当新生代回收时，G1并发检查是否有字符串重复
     * 如果它们值一样，让他们引用同一个char[]
     * 注意：与String.intern()
     *   String.intern()关注的是字符串对象
     *   而字符串去重关注的char[]
     *   在jvm内部，使用了不同的字符串表
     * */

    /*
     * Jdk 8u40 并发标记类卸载
     * 所有对象都经过并发标记后，就能知道那些类不被再使用，当一个类加载器所有的类不再使用，则卸载
     * 它所加载的所有类
     * -XX:+ClassUnloadingWithConcurrentMark默认启用
     * 框架中自定义类加载器比较有用
     *
     * Jdk8u60 回收巨型对象
     * 一个对象大于region的一半时，称为巨型对象
     * G1不会对巨型对象进行拷贝
     * 回收时优先考虑
     * G1会跟踪老年代所有的incomeing引用，这样老年代incoming引用为0的巨型对象可以
     * 在新生代垃圾回收时处理掉
     *
     * */


    /*
     * JDK9并发标记起始时间的调整
     * 并发标记必须在堆空间占满，否则退化为fullGC
     * JDK9之前需要使用-XX:InitiatingHeapOccupancyPercent
     * JDK9可以动态调整
     * -XX:InitiatingHeapOccupancyPercent 用来设置初始值
     * 进行数据采集并动态调整
     * 总会添加一个安全的空挡空间
     * */

}
