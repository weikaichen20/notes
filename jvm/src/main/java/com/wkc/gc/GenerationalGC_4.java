package com.wkc.gc;

/**
 * Created on 2022/1/27.
 *
 * @author Weikaichen
 */
public class GenerationalGC_4 {
    /*
     * 分代垃圾回收
     *
     * 对象首先分配在伊甸园区域
     * 新生代空间不足，触发minor gc，伊甸园和幸存区from存活的对象使用copy复制到to中，存活的对象年龄加1并且交换from to
     * minor gc会引发stop the world 暂停其他用户线程，等垃圾回收结束，用户线程才恢复运行
     * 当对象寿命超过阈值，会晋生老年代，最大寿命15（4bit）
     * 当老年代空间不足，会先尝试minor gc，如果之后空间还是不足，那么出发fullgc，STW的时间更长,还是放不下OOM
     *
     * VM参数
     * 堆初始大小             -Xms
     * 堆最大大小             -Xmx或-XX:MaxHeapSize=size
     * 新生代大小             -Xmn(-XX:NewSize=size+-XX:MaxNewSize=size)
     * 幸存区比例（动态)       -XX:InitialSurvivorRatio=ratio和 -XX:+UseAdaptiveSizePolicy
     * 幸存区比例             -XX:SurvivorRatio=ratio
     * 晋升阈值               -XX:MaxTenuringThreshold=threshold
     * 晋升详情               -XX:+PrintTenuringDistribution
     * GC详情                 -XX:+PrintGCDetails -verbose:gc
     * FullGC前MinorGC        -XX:+ScavengeBeforeFullGC
     * */
}
