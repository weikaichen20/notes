package com.wkc.java.sharemodel.monitor;

/**
 * Created on 2022/3/10.
 *
 * @author Weikaichen
 */
public class Synchronized_2 {
    public static void main(String[] args) {
        /*
        * 应用之互斥
        * 为了避免临界区的竟态条件发生条件，有多种手段可以达到目的
        *
        * 阻塞式解决方案：Synchronized ，Lock
        * 非阻塞的解决方案：原子变量
        *
        * 使用synchronized俗称【对象锁】，它采用互斥的方式让同一时刻至多只有一个线程持有【对象锁】，
        * 其他线程再想获取【对象锁】时就会阻塞住，这样就能保证拥有锁的下次呢很难过可以安全执行临界区
        * 内的代码，不用担心线程的上下文切换
        *
        * 注意
        *   虽然java中的互斥和同步都可以采用synchronized关键字来完成，但他们还是有区别
        *       互斥是保证临界区的竟态条件发生，同一时刻只能有一个线程执行临界区代码
        *       同步是由于线程执行的先后，顺序不同，需要一个线程等待其他线程运行到某个点
        * */

        /*
        * 类比
        * 1.synchronized（对象）中的对象，可以想象一个房间（room），有唯一入口（门）
        * 房间只能一次进入一个进行计算，t1，t2可以想象为2个人
        *
        * 2.当线程t1执行到synchronized(room)时就好比t1进入这个房间，并锁住门拿走了钥匙
        * ，在门内执行count++代码
        *
        * 3.这时候如果t2也运行到了synchronized(room)时，门被锁住了，只能在门外等待
        * 发生上下文切换，进入阻塞状态
        *
        * 4.这中间如果t1的cpu时间片用完，被踢出门外（不要错误的认为锁住了对象就能一直执行下去）
        * 这时门还是锁住的，t1拿着钥匙，t2线程还在阻塞状态进不来，只有下次轮到t1自己再次获得时间片才能开门
        *
        * 5.当t1执行完synchronized()块内的代码，这时才会从obj房间出来并解开门上的锁，唤醒t2
        * 线程把钥匙给他，t2线程这是才可以进入obj房间，锁住了门拿上钥匙，执行它的count--
        *
        * */


        /*
        * synchronized
        * 加在成员方法上锁的对象为this
        * 加在静态方法上锁的对象为类名.class
        * */

    }
}
