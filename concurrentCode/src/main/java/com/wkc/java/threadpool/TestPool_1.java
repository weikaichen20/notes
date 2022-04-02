package com.wkc.java.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2022/4/1.
 *
 * @author Weikaichen
 */
@Slf4j
public class TestPool_1 {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10, (t, queue) -> {
            //1.死等
//            queue.put(t);
            // 2.超时等待
//            queue.offer(t, 500, TimeUnit.MILLISECONDS);
            //3.放弃任务执行
//            log.debug("放弃");
            //4.抛出异常
//            throw new RuntimeException("任务执行失败");
            //5.让调用者自己执行
//            t.run();
        });

    }
}

class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;

    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //获取任务的超时时间
    private long timeout;

    //时间单位
    private TimeUnit timeUnit;

    //拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    //执行任务
    public void execute(Runnable task) {
        //当任务数没有超过coreSize，直接交给worker
        //超过coreSize，加入任务队列
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
//                taskQueue.put(task);
                //1.死等 2.超时等待 3.放弃任务执行 4.抛出异常 5.让调用者自己执行
                taskQueue.tryPut(rejectPolicy,task);
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int capacity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        taskQueue = new BlockingQueue<>(capacity);
        this.rejectPolicy=rejectPolicy;
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //1.当task不为空，执行任务
            //2.当task执行完毕，从任务队列获取任务执行
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
            }
        }
    }

}

@FunctionalInterface
interface RejectPolicy<T>{
    void reject(T task,BlockingQueue<T> queue);
}

class BlockingQueue<T> {
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2.锁
    private ReentrantLock lock = new ReentrantLock();

    //3.生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //4.消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();


    //5.队列容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    //超时获取
    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    //需要考虑虚假唤醒问题
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }


    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T t) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(t);
            emptyWaitSet.signalAll();

        } finally {
            lock.unlock();
        }
    }

    //阻塞超时添加
    public boolean offer(T t, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(t);
            emptyWaitSet.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //判断队列是否已满
            if (queue.size()==capacity){
                rejectPolicy.reject(task, this);
            }else {
                queue.addLast(task);
                emptyWaitSet.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }
}
