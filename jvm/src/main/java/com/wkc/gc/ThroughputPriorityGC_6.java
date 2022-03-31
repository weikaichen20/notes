package com.wkc.gc;

/**
 * Created on 2022/2/7.
 *
 * @author Weikaichen
 */
/*
 * 并行
 * -XX:+UseParallelGC ~ -XX:+UseParallelOldGC 开启一个另一个自动开启
 * -XX:UseAdaptiveSizePolicy 使用自适应调整新生代和老年代
 * -XX:GCTimeRatio=ratio    1/(1+ratio)达不到一般调大堆
 * -XX:MaxGCPauseMillis=ms  暂停时间200ms
 * -XX:ParallelGCThreads=n  指定多垃圾回收线程的个数
 *
 *
 * */
public class ThroughputPriorityGC_6 {
}
