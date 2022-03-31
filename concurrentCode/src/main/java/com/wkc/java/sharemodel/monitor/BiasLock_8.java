package com.wkc.java.sharemodel.monitor;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
@Slf4j
public class BiasLock_8 {
    /*
    * 偏向锁
    * 轻量级锁在没有竞争时（就自己这个线程），每次重入还需要执行Cas操作
    *
    * JAVA6中引入偏向锁来做进一步优化；只有第一次使用CAS将线程ID设置到对象的MarkWord头，之后
    * 发现这个线程ID是自己的就表示没有竞争，不用重新CAS，以后只要不发生竞争，这个对象就归该线程
    * 所有
    * */

    /*
    * 偏向状态
    * 一个对象创建时
    * 如果开启偏向锁（默认开启），那么对象创建后，mark Word的值为0x05即最后3位位101，这时
    * thread，epoch，age都为0
    * 偏向锁是默认是延迟的，不会再程序启动时立即生效，如果想避免延迟，使用VM参数xx:BiasedLockingStartupDelay=0来禁用延迟
    * 如果没有开启偏向锁，那么对象创建后，markword值位0x01 即后三位位001，这时它的HashCode，age都为0，第一次使用到hashcode时才会赋值
    * */

    /*
    * 禁用偏向锁
    * xx:-UseBiasedLocking
    * */

    /*
    * 撤销偏向锁-调用对象的HashCode
    * 调用了对象的hashCode，但偏向锁的对象MarkWord中存储的是线程id，如果调用HashCode
    * 会导致偏向锁被撤销
    * 轻量级锁会在锁记录中记录hashCode
    * 重量级锁会在Moniter中记录hashCode
    * 在HashCode后使用偏向锁，记得去掉-xx:UseBiasedLocking
    *
    * 撤销-其他线程使用对象
    * 当有其他线程使用偏向锁对象时，会将偏向锁升级为轻量级锁
    *
    * 撤销-调用wait/noify(wait/noify只有重量级锁有会撤销偏向锁)
    * */

    /*
    * 批量重偏向
    * 如果对象虽然被多个线程访问，但是没有竞争，这时偏向了线程T1的对象有机会重新偏向T2，重偏向对充值对象的线程ID
    * 当撤销偏向锁阈值超过20次后，jvm会觉得自己偏向错了，会给这些对象加锁时重新偏向至加锁线程
    *
    *
    * 批量撤销
    * 当撤销偏向锁阈值超过40次后，jvm会这样觉得，自己确实偏向错了，不应该偏向，于是整个类的所有
    * 对象都会变成不可偏向的，新建的对象也是不可偏向的
    *
    * */

    /*
    * 锁消除
    * JIT 即时编译器优化
    * -XX:-EliminateLocks   锁消除
    *
    * */

    //查看对象的对象头使用jol-core.jar
    public static void main(String[] args) {
        Dog d = new Dog();
        log.debug(ClassLayout.parseInstance(d).toPrintable());
        synchronized (d){
            log.debug(ClassLayout.parseInstance(d).toPrintable());
        }
        log.debug(ClassLayout.parseInstance(d).toPrintable());
    }
}
class Dog{

}
