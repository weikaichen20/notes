package com.wkc.classload;

/**
 * Created on 2022/3/1.
 *
 * @author Weikaichen
 */
public class ClassLoad_2 {
    /*
     * 4.1加载
     * 将类的字节码载入方法区中，内部采用C++的instanceKlass描述java类，它的重要field有：
     * _java_mirror即java的类镜像，例如对String来说，就是String.class,作用是把klass暴露给java使用
     * _super即父类
     * _fields即成员方法
     * _methods即方法
     * _constants即常量池
     * _class_loader即类加载器
     * _vtable虚方法表
     * _itable接口方法表
     *
     * 如果这个类还有父类没有加载，先加载父类
     * 加载和链接可能是交替运行的
     *
     * 注意
     *   instanceKlass这样的元数据是存储在方法区（1.8后的元空间），但是_java_mirror是存储在堆中
     *   可以通过HSDB工具查看
     * 4.2链接
     * 验证：验证是否符合JVM规范，安全性检查
     *   用UE等支持二进制的编辑器修改HelloWorld.class的魔数，在控制台运行报错
     * 准备：为static变量分配空间，设置默认值
     *   static变量在JDK7之前存储与instanceKlass末尾即元空间（永久代），从JDK7开始存储与_java_mirror末尾
     *   static变量分配空间和赋值是两个阶段，分配空间在准备阶段完成，赋值在初始化阶段完成
     *   如果static变量是final的基本类型（字符串常量），那么编译阶段的值就确定了，赋值在准备阶段
     *   如果static变量是final的引用类型，那么赋值也会在初始化阶段完成
     * 解析：将常量池的符号引用解析为直接引用
     *
     * 4.3初始化
     * <cinit>()v方法
     * 初始化即调用<cinit>()V,虚拟机会保证这个类的【构造方法】的线程安全
     * 发生的时机
     * 概括的说，类初始化时【懒惰的】
     * main方法所在的类，总会被首先初始化
     * 首次访问这个类的静态变量或静态方法时
     * 子类初始化，如果父类还没初始化，会引发父类的初始化
     * 子类访问父类的静态变量，只会触发父类的初始化
     * Class.forName
     * new 会导致初始化
     *
     * 不会导致初始化的情况
     * 访问类的static final静态常量（基本类型和字符串）不会触发初始化
     * 类对象.class不会触发初始化
     * 创建该类的数组不会触发初始化
     * 类加载器的loadClass方法
     * Class.forName的第二个参数为空
     *
     * */

    /*
     * 完成懒惰单例类
     *
     *
     * */
    public static void main(String[] args) {
//        Singleton.test();
        Singleton.getInstance();
    }
}

class Singleton {
    private Singleton() {
    }

    public static void test() {
        System.out.println("test");
    }

    //线程安全的
    private static class LazyHolder {
        private static final Singleton SINGLETON = new Singleton();

        static {
            System.out.println("init");
        }
    }

    public static Singleton getInstance() {
        return LazyHolder.SINGLETON;
    }
}
