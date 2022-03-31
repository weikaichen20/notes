package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
public class DeadLock_15 {
    /*
     * 活跃性
     *
     * 死锁
     * 当一个线程需要同时获取多把锁，这时就容易发生死锁
     * */


    /*
    * 定位死锁
    * 检测死锁可使用jconsole工具，或者使用jps定位进程id，再用jstack定位死锁
    *
    *
    * */


    public static final String LOCK_1 = "lock1";
    public static final String LOCK_2 = "lock2";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Thread threadA = new Thread(() -> {
            try {
                while (true) {
                    synchronized (DeadLock_15.LOCK_1) {
                        System.out.println(Thread.currentThread().getName() + " 锁住 lock1");
                        Thread.sleep(1000);
                        synchronized (DeadLock_15.LOCK_2) {
                            System.out.println(Thread.currentThread().getName() + " 锁住 lock2");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                while (true) {
                    synchronized (DeadLock_15.LOCK_2) {
                        System.out.println(Thread.currentThread().getName() + " 锁住 lock2");
                        Thread.sleep(1000);
                        synchronized (DeadLock_15.LOCK_1) {
                            System.out.println(Thread.currentThread().getName() + " 锁住 lock1");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
    }
}
