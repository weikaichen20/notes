package com.wkc.classload;

/**
 * Created on 2022/3/2.
 *
 * @author Weikaichen
 */
public class JMM_5 {
    /*
     * JMM（Java Memory Model）
     * JMM定义了一套在多线程读写共享数据时（成员变量，数组）时，对数据的可见性，
     * 有序性，和原子性的规则和保障
     *
     * */
    /*
     * 保证原子性
     * synchronized
     * */

    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (run) {
                //
            }
        });
        t.start();

        t.sleep(1000L);

        run = false;//线程不会停下来
    }
}
/*
 * 1.初始状态，t线程刚开始从主内存读取了run的值到工作内存
 * 2.因为t线程要频繁从主内存中读取run的值，JIT编译器会将run的值缓存至自己工作内存
 * 的高速缓存中，减少对主存中run的访问，提高效率
 * 3.1秒之后，main线程修改了run的值，并同步至主存，而t是从自己工作内存中的高速缓存中读取
 * 这个变量的值，结果永远时旧值
 *
 * */


/*
 * 2.2解决方法
 *
 * volatile（易变关键字）
 * 它可以用来修饰成员变量和静态成员变量，它可以避免线程从自己的工作缓存中查找变量的值，必须到
 * 主存中获取它的值，线程操作volatile白能量都是操作主存
 *
 * 2.3可见性
 * 前面的例子体现的实际就是可见性，他保证的是在多个线程之间，一个线程volatile变量的修改对另一个
 * 线程可见，不能保证原子性，仅用在一个写线程，多线程读的情况
 *
 *
 * volatile用在一个写线程，多线程读的情况
 * synchronized 语句块既可以保证代码块的原子性，也同时保证代码块内变量的可见性，但是缺点
 * 是synchronized属于重量级操作，性能相对更低
 *
 * */

/*
 * 有序性
 * 指令重排
 * volatile修饰的变量，可以禁用指令重排
 *
 * 同一个线程内，JVM会在不影响正确性的前提下，可以调整语句的执行顺序
 * //在某个线程内执行如下赋值操作
 * i=。。。。；较为耗时的操作
 * j=。。。；
 * 可以看到先执行i还是先执行j，对最终的结果不会产生影响，所以上面代码执行可能是i先执行也有可能是j先执行
 * 这种特性称为【指令重排】，多线程下【指令重排】会影响正确性例如著名的double-checked locking模式实现单例
 * 好处
 *   懒惰实例化
 *   首次使用getInstance()才使用synchronized枷锁，后续使用无需枷锁
 *   但是在多线程环境下，上面的代码是有问题的，
 *
 * */
//public final class Singleton{
//    private Singleton(){}
//    private [volatile] static Singleton INSTANCE=null;
//    public static Singleton getInstance(){
//        //实例没创建，才会进入内部synchronized代码块
//        if(INSTANCE==null){
//            synchronized (Singleton.class){
//                //也许有其他线程已经创建实例，所以在判断一次
//                if (INSTANCE!=null){
//                    INSTANCE=new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}