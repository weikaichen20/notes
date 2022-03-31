package com.wkc.gc;

/**
 * Created on 2022/2/7.
 *
 * @author Weikaichen
 */
/*
   cms
          并发 标记清除
* -XX:+UseConcMarkSweepGC ~ -XX:UseParNewGC ~SerialOld 并发失败会退化成串行垃圾收集器
* -XX:ParallelGCThreads=n ~ -XX:ConGCThreads=threads cms只有在初始标记和重新标记的时候STW，其他只有并发线程工作，其他线程不受影响
* -XX:CMSInitiatingOccupanyFraction=precent  合适进行cms垃圾回收，比如80，老年代空间达到80，进行回收，预留空间给浮动垃圾
* -XX:+CMSScavengeBeforeReMark  在重新标记之前对新生代进行垃圾回收，减少重新标记的耗时
*/
public class ResponseTimePriorityGC_7 {
}
