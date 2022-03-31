package com.wkc.java.jmm;

/**
 * Created on 2022/3/23.
 *
 * @author Weikaichen
 */
public class HappensBefore_7 {
    /*
     * happens-before规定了对共享变量的写操作对其他线程的读操作可见，它是可见性与
     * 有序性的一套规则总结，抛下happens-before规则，JMM并不能保证一个线程对共享变量
     * 的写，对于其他线程对该共享变量的读可见
     * */

    //线程解锁m之前对变量的写，对于接下来m加锁的其他线程对该变量的读可见
//    static int x;
//    static Object m = new Object();
//    public static void main(String[] args) {
//        new Thread(() -> {
//            synchronized (m) {
//                x = 10;
//            }
//        }, "t1").start();
//        new Thread(() -> {
//            synchronized (m) {
//                System.out.println(x);
//            }
//        }, "t2").start();
//    }


    //线程对volatile变量的写，对接下来其他线程对该变量的读可见
//    volatile static int x;
//    public static void main(String[] args) {
//        new Thread(() -> {
//                x = 10;
//        }, "t1").start();
//        new Thread(() -> {
//                System.out.println(x);
//        }, "t2").start();
//    }


    //线程对start对变量的写，对该线程开始后对该变量的读可见
//    static int x;
//
//    public static void main(String[] args) {
//        x = 10;
//        new Thread(() -> {
//            System.out.println(x);
//        }, "t2").start();
//    }

    //线程结束前对变量的写，对其他线程得知它结果后的读可见（比如其他线程调用t1.isAlive()或t1.join()等待它结束）
//    static int x;
//
//    public static void main(String[] args) throws InterruptedException {
//        Thread t2 = new Thread(() -> {
//            x = 10;
//        }, "t2");
//        t2.start();
//
//        t2.join();
//        System.out.println(x);
//    }


    //线程t1打断t2(interupt)前对变量的写，对于去其他线程得知t2被打断后对变量的读可见（t2.interrupted或t2.isinterrupted）
//    static int x;
//
//    public static void main(String[] args) {
//        Thread t2 = new Thread(() -> {
//            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println(x);
//                    break;
//                }
//            }
//        }, "t2");
//        t2.start();
//
//        new Thread(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            x=10;
//            t2.interrupt();
//        }).start();
//
//        while (!t2.isInterrupted()){
//            Thread.yield();
//        }
//        System.out.println(x);
//    }


    //对变量默认值（0，false，null）的写，对其他线程对该变量的读可见
    //具有传递性，如果x hb-> y 并且y hb->z那么有 x hb->z ，配合volatile的防指令重拍
//    volatile static int x;
//    static int y;
//
//    public static void main(String[] args) {
//        new Thread(()->{
//            y=10;
//            x=20;
//        }, "t1").start();
//
//        new Thread(()->{
//            //x=20对t2可见，同时对y=10也对t2可见
//            System.out.println(x);
//        }, "t2").start();
//
//    }

    //变量都是指成员变量或静态变量

}
