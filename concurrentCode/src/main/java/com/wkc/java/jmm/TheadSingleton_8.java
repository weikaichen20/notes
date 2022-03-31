package com.wkc.java.jmm;

import java.io.Serializable;

/**
 * Created on 2022/3/23.
 *
 * @author Weikaichen
 */
/*
单例模式有很多实现方法，饿汉，懒汉，静态内部类，枚举类试分析每种实现下获取单例对象
（即调用getInstance）时的线程安全
饿汉式：类加载就会导致该实例对象被创建
懒汉式：类加载不会导致该单实例对象被创建，而是首次使用该对象才会创建

*/
public class TheadSingleton_8 {


}

////实现1
////1.为什么使用final （防止子类覆盖单例）
////2.如果实现序列化接口，还要做什么来防止反序列化破坏单例
//final class Singleton implements Serializable {
//    //3.为什么设置为私有？是否能防止反射创建新的实例（防止其他类使用，不能）
//    private Singleton() {
//    }
//    //4.这样初始化是否能保证单例对象创建时的线程安全（能保证，类加载阶段完成，由JVM保证安全性（fs））
//    private static final Singleton INSTANCE = new Singleton();
//    //5.为什么提供静态方法而不是直接将INATANCE设置为public，说出你知道理由
//    // （提供方法实现更好的封装性 ）
//    // （提供方法支持泛型 ）
//    // （提供方法对变量更好的扩展 ）
//    public static Singleton getInstance() {
//        return INSTANCE;
//    }
//    //回答第二问，反序列化也会生成一个对象，readResolve会让反序列直接使用返回的对象
//    public Object readResolve(){
//        return INSTANCE;
//    }
//}

//enum Singleton{
//    //1.枚举单例是如何限制实例个数的 (枚举类内部的静态成员变量，单实例的)
//    //2.枚举单例在创建时是否有并发问题 （不存在，静态成员变量）
//    //3.枚举单例能否被反射破坏单例  （不能）
//    //4.枚举单例能否被反序列化破坏单例 （默认实现序列化接口，可以避免反序列破快单例）
//    //5.枚举单例属于懒汉式还是饿汉式 （饿汉式（类加载））
//    //6.枚举单例如果希望加入一些单例创建时的初始化逻辑该如何做
//    INSTANCE;
//
//}

//final class Singleton{
//    public Singleton(){}
//    private static Singleton INSTANCE=null;
//    //分析这里的线程安全，并说明有什么缺点
//    private static synchronized Singleton getInstance(){
//        if (INSTANCE!=null){
//            return INSTANCE;
//        }
//        INSTANCE=new Singleton();
//        return INSTANCE;
//    }
//}


//final class Singleton {
//    private Singleton() {
//    }
//
//    private static volatile Singleton INSTANCE = null;
//
//    public static Singleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (Singleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}


final class Singleton {
    private Singleton() {
    }

    //属于懒汉式还是饿汉式（懒汉式）
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    //在创建时是否有并发问题（没有线程安全问题）
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}





