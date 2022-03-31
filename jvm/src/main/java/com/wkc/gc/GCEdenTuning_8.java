package com.wkc.gc;

/**
 * Created on 2022/2/28.
 *
 * @author Weikaichen
 */
public class GCEdenTuning_8 {

    /*
     * 新生代调优
     * 新生代的特点
     * 所有的new操作的内存分配非常廉价
     * TLAB thread-local allocation buffer
     * 死亡对象的回收代价是零
     * 大部分对象用过即死
     * Minor GC的时间远远低于FullGC
     *
     * */


    /*
     * 新生代不能调的过大，不然回收时间增长
     * 新生代使用的复制算法
     *
     * 新生代能容纳所有【并发量*（请求响应数据大小）】
     * 幸存区达到能保存（当前活跃对象+需要晋升的对象）
     *
     * 晋升阈值配置得当，让长时间存活的对象尽快晋升
     * -XX:MaxTenuringThreshold=threshold
     * -XX:+PrintTenuringDistribution
     * */


    /*
     * 老年代调优
     * 以CMS为例
     * CMS的老年代内存越大越好
     * 先尝试不做调优，如果没有fullg那么已经，，，否则先尝试调优新生代
     * 观察发生fullGc的老年代内存占用，将老年代内存预设调大1/4-1/3
     *   -XX:CMSInitiatingOccupancyFraction=percent
     * 预防浮动垃圾过多，导致cms导致退化成串行化垃圾回收
     * */



    /*
     * 案例一：FullGC和MinorGC频繁
     * 调整新生代的大小
     *
     * 案例二：请求高峰期发生FullGC，单次暂停的时间特别长（CMS）
     * 一般是重新标记阶段对新生代老年代进行遍历扫描，时间较长，可以在重新标记前对新生代进行minroGC
     *
     * 案例三；老年代充裕的情况下，发生fullGC（1.7）
     * 1.7前永久代大小不足也会进行fullGC
     *
     * */
}
