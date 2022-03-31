package com.wkc.gc;

/**
 * Created on 2022/2/7.
 *
 * @author Weikaichen
 */
public class G1GC_9 {
    /*
     * Garbage First
     * JDK9 默认
     *
     * 使用场景
     * 同时注重吞吐量（Throughout）和低延迟（Low latency），默认的暂停目标是200ms
     * 超大堆内存，会将堆划分成多个大小相等的Region
     * 整体上是标记+整理算法，两个区域之间是复制算法
     *
     * 相关Jvm参数
     *
     * -XX:+UseG1GC
     * -XX:G1HeapRegionSize=size
     * -XX:MaxGCPauseMillis=time
     *
     * */

    /*
     * G1垃圾回收阶段
     *           Young
     *          Collection
     *        ^             \
     *       /               >
     *  mixed      <-----    Young Collection+
     * Collection            Concurrent Mark
     *
     *
     * Young Collection
     * 会STW 时间较短
     *
     * Young Collection+Concurrent Mark
     * 在YoungGC时会进行GC Root的初始标记
     * 老年代占用堆空间比例达到阈值时，进行并发标记（不会STW）
     * -XX:InitiatingHeapOccupancyPercent=percent(默认45%)
     *
     * Mixed Collection
     * 会对E，S，O进行全面垃圾回收
     * 最终标记（Remark）会STW
     * 拷贝存活 会STW
     * -XX:MaxGCPauseMillis=ms
     * */


    /*
     * full GC
     *
     * SerialGC
     * 新生代内存不足发生的垃圾回收-minorGC
     * 老年代内存不足的垃圾回收-fullGC
     * ParallelGC
     * 新生代内存不足发生的垃圾回收-minorGC
     * 老年代内存不足的垃圾回收-fullGC
     * CMS
     * 新生代内存不足发生的垃圾回收-minorGC
     * 老年代内存不足
     * G1
     * 新生代内存不足发生的垃圾回收-minorGC
     * 老年代内存不足
     * */

    /*
     * Young Collection跨代引用
     * 新生代回收的跨代引用（老年代引用新生代）问题
     * 卡表 Remembered Set
     * 在引用变更时通过post-write barrier+dirty card queue
     * concurrent refinement threads更新Remember Set
     *
     * Remark
     * post-write barrier+satb_mark_queue
     * */
}
