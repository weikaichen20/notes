package com.wkc.java.jmm;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class DoubleCheckedLocking_6 {


}

//final class Singleton {
//    private Singleton(){}
//    private static Singleton INSTANCE=null;
//    public static synchronized Singleton getInstance(){
//        if (INSTANCE==null){//t1,t2同时执行进来（不加synchronized）
//            INSTANCE=new Singleton();
//        }
//        return INSTANCE;
//    }
//}
/*
 * 以上实例化
 * 首次使用getInstance才使用synchronized加锁，后续使用无需加锁
 * 有隐含的，第一个if使用INSTANCE变量，是在同步块之外
 * 但是多线程环境下，是有问题的
 * */

//final class Singleton {
//    private Singleton() {
//    }
//
//    private static Singleton INSTANCE = null;
//
//    public static Singleton getInstance() {
//        if (INSTANCE == null) {
//            //首次访问会同步，而之后的使用没有synchronized
//            synchronized (Singleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}

//              最终版本
//final class Singleton {
//    private Singleton() {
//    }
//
//    private static volatile Singleton INSTANCE = null;
//
//    public static Singleton getInstance() {
//        //实力没创建，才会进入内部的synchronized代码块
//        if (INSTANCE == null) {
//            //也许有其他线程已经创建实例，所以再判断一次
//            synchronized (Singleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}