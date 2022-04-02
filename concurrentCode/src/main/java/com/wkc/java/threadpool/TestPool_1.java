package com.wkc.java.threadpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2022/4/1.
 *
 * @author Weikaichen
 */
public class TestPool_1 {
}

class BlockingQueue<T>{
    //1.任务队列
    private Deque<T> queue=new ArrayDeque<>();

    //2.锁
    private ReentrantLock lock=new ReentrantLock();

    //3.生产者条件变量
    private Condition fullWaitSet=lock.newCondition();

    //4.消费者条件变量
    private Condition emptyWaitSet=lock.newCondition();

    //5.队列容量
    private int capacity;

    //阻塞获取
    public T take(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        }finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T t){
        lock.lock();
        try {
            while (queue.size()==capacity){
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(t);
            emptyWaitSet.signalAll();

        }finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }
}
