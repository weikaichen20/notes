package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
@Slf4j(topic = "c.w.j.s.p")
public class ProtectSuspend_10 {
    /*
     * 同步模式之保护性暂停
     *
     * 即Guarded Suspension，用在一个线程等待另一个线程的执行结果
     * 要点
     * 1.有一个结果需要从一个线程传递到另一个线程，让他们关联同一个GuardedObject
     * 2.如果有结果不断从一个线程到另一个线程那么使用消息队列（见生产者-消费者）
     * 3.JDK中。join的实现，Future的实现，采用的就是此模式
     * 4.因为要等待另一方的结果，因此归类到同步模式
     *
     * */
    public static void main(String[] args) {
//        noTimeout();
//        timeout();
    }

    private static void timeout() {
        GuardObject guardObject = new GuardObject();
        new Thread(() -> {
            //等待结果
            log.debug("等待结果");
            String o = (String) guardObject.get(2007);
            log.debug("结果为【{}】", o);
        }, "t1").start();
        new Thread(() -> {
            try {
                log.debug("do something");
                TimeUnit.SECONDS.sleep(2);
                guardObject.complete("Hello World TimeOut!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

    private static void noTimeout() {
        GuardObject guardObject = new GuardObject();
        new Thread(() -> {
            //等待结果
            log.debug("等待结果");
            String o = (String) guardObject.get();
            log.debug("结果为【{}】", o);
        }, "t1").start();
        new Thread(() -> {
            try {
                log.debug("do something");
                TimeUnit.SECONDS.sleep(2);
                guardObject.complete("Hello World!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}


class GuardObject {
    private Object response;

    public synchronized Object get() {
        while (response == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    //获取结果
    public synchronized Object get(long time) {
        //开始等待的时间
        long begin = System.currentTimeMillis();
        long pass = 0;
        while (response == null) {
            long wait = time - pass;
            if (wait <= 0)
                break;
            try {
                this.wait(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pass = System.currentTimeMillis() - begin;
        }
        return response;
    }

    //产生结果
    public synchronized void complete(Object source) {
        this.response = source;
        this.notifyAll();
    }
}
