package com.wkc.java.threadpool;

/**
 * Created on 2022/4/2.
 *
 * @author Weikaichen
 */
public class ThreadPoolExecutor_2 {
    /* 线程池状态
     * ThreadPoolExecutor使用int的高3位表示线程池状态，低29位表示线程数量
     *
     * 状态名      高3位     接受新任务       处理阻塞队列任务        说明
     * Running      111         Y                   Y
     * SHUTDOWN     000         N                   Y               不会接受新任务，但会处理阻塞队列剩余任务
     * STOP         001         N                   N               会中断正在执行的任务，并抛弃阻塞队列任务
     * TIDYING      010                                             任务全部执行完毕，活动线程为0进入终结
     * TERMINATED   011                                             终结状态
     *
     * 这些信息存储在一个原子变量ctl中，目的是将线程池状态与线程个数合二为一，这样就可以用一次cas原子操作进行赋值
     *
     *
     *          执行流程
     * 1.线程池中刚开始没有线程，当一个任务提交给线程池后，线程池会创建一个新线程来执行任务
     * 2.当线程数达到corepoolsize并没有线程空闲，这是再加入任务，新加的任务会被加入worequeue队列排队，直到又空闲的线程
     * 3.如果队列选择了有界队列，那么任务超过了队列大小，会创建maxpoolsize-corepoolsize数目的线程救急
     * 4.如果线程达到maxpoolsize仍然又新任务这时会执行拒绝策略，jujueceljdk提供四种实现，其他框架也提供了实现
     *      Abortpolicy让调用者抛出RejectExecutionException异常，这是默认策略
     *      CallRunsPolicy让调用者运行任务
     *      DiscardPolicy放弃本次任务
     *      DiscardOlderPolicy放弃队列中最早的任务，本任务取而代之
     *      Dubbo的实现，在抛出RejectExecutionException异常之前会记录日志，并dump线程栈信息，方便定位问题
     *      Netty的实现，是创建一个新线程来执行任务
     *      ActiveMQ的实现，带超市等待（60s）尝试放入队列
     *      Pinpoint实现，它使用一个拒绝策略链，会逐一尝试策略链中的每种拒绝策略
     * 当高峰过去后，超过corepoolsize的救急线程如果一段时间没有任务做，需要结束节省资源，这个时间由keepAliveTime和unit控制
     *
     *
     *
     *
     * */
}
