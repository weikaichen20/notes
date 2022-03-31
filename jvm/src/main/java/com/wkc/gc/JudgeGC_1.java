package com.wkc.gc;

/**
 * Created on 2022/1/20.
 *
 * @author Weikaichen
 */
public class JudgeGC_1 {
    /*
    * 判断对象是否回收
    * 1.引用计数法 引用数为0被回收
    * 2.可达性分析算法
    * */

    /*
    * java虚拟机使用可达性分析算法
    * Java虚拟机中的垃圾回收器采用可达性分析来探索所有存活的对象
    * 扫描堆中的对象，看是否能够沿着GC Root对象为起点的引用链找到该对象，找不到表示可以回收
    * 哪些对象作为GC Root
    * */

    /*
    * 需要对heap进行专业分析MemoryAnalyzer使用eclipse插件
    * 测试类Demo_1
    * 使用jps获得线程号
    * 使用jmap 获取快照信息
    * jmap -dump:live,format=b,file=a.bin pid
    * 在记录快照的时候取存活的对象进行记录
    * */
}
