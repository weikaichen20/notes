package com.wkc.java.sharemodel.unlock;

/**
 * Created on 2022/3/25.
 *
 * @author Weikaichen
 */
/*
* 原子累加器比AtomicInteger性能高
*
*
* 性能提升的原因：在有竞争时，设置多个累加单元，Thread-0累加Cell[0],而Thread-1累加
* Cell[1]最后将结果汇总，这样它们累加时操作不同的Cell变量，减少了CAS重试失败，从而提升性能
*
* */
public class LongAdder_9 {
}
