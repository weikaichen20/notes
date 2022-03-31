package com.wkc.gc;

/**
 * Created on 2022/2/28.
 *
 * @author Weikaichen
 */
public class GCTuning_11 {

    /*
     * 调优领域
     * 内存
     * 锁竞争
     * cpu占用
     * io
     * */


    /*
     * 确定目标
     * 低延迟还是高吞吐量 ，选择合适的回收器
     * CMS，G1，ZGC  低延迟
     * ParallelGC   高吞吐量
     * */

    /*
     * 最快的gc是不发生GC
     * 数据是不是太多？
     *   resultSet=statement.executeQuery("select * from 大表")
     * 数据表示是否太臃肿
     * 对象图
     * 对象大小16 Integer 24 int 6
     * 是否存在内存泄露
     * static Map map
     * 软
     * 弱
     * 第三方缓存实现
     * */
}
