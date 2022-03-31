package com.wkc.classload;

/**
 * Created on 2022/2/28.
 *
 * @author Weikaichen
 */
public class Bytecodes_1 {
    /*
     * javap反编译工具
     * javap -v helloclass.class
     *
     * a++ 是先iload 在iinc ++a相反
     *
     * 条件判断指令
     * byte short char 都会按int比较，因为操作数栈都是4字节，不足会补齐（byte short char）
     * 比较成功后使用goto用来跳转指定行号的字节码
     *
     * */
    /*
     * 构造函数
     * <cinit>()V
     * 编译器会按从上至下的顺序，搜集所有static静态代码块和静态成员
     * 赋值的代码，合并为一个特殊的方法<cinit>()V
     * <cinit>()V方法会在类加载的初始化阶段被调用
     *
     * <init>()V
     * 编译器会按照从上至下的顺序，收集所有的初始化{}代码块和成员变量
     * 赋值的代码，形成新的构造方法，但原始构造方法内的代码总是在最后
     *
     * */

    /*
     * 多态原理
     * 1.运行代码
     * 停在System.in.read()方法上，这是运行jps获取进程id
     * 2.运行HSDB工具
     * 进入JDK安装目录
     * java -cp ./lib/sa-jdi.jar sun.jvm.hotspot.HSDB
     *
     * 当执行invokevirtual指令时
     * 1.先通过栈帧中的对象引用找到对象
     * 2.分析对象头，找到对象的实际Class
     * 3.Class结构中有vtable，他在类加载的链接阶段就已经根据方法的重写
     * 规则生成好了
     * 4.查表得到方法的具体地址
     * 5.执行方法的字节码
     *
     * */


    /*
     * try catch
     * 通过javap字节码可以看到多出来一个Exception table结构，[from,to)是前闭后开
     * 的检测范围，执行出现异常，则通过type匹配异常类型，如果一致，进入target所指示的行号
     *
     * finally
     * 可以看到finally中的代码被复制了3份，分别放入try流程，catch流程以及catch剩余异常错误类型
     *
     *
     * 注意事项
     * 由于finally中的ireturn被插入了所有可能的流程，因此返回的结果肯定以finally的为准
     * 如果在finally出现了return，会吞掉异常
     *
     *
     * 如果在try中return，在finally对值进行操作不会改变值，因为在try中对值进行了暂存
     * */


    /*
     * Synchronized代码块原理
     * 方法级别的Synchronized不会在字节码指令中有所体现
     *
     * */


    /*
     * 编译器处理
     * 语法糖
     * 1.默认构造器
     * 2.自动拆装箱
     * 3.泛型集合取值
     *   泛型也是在jDK5开始加入的特性，但java在编译泛型代码后会执行泛型擦除的动作，即
     * 泛型信息在编译为字节码后就丢失了，实际的类型当作了object类型来处理
     * 4.可变参数
     *   String... args (如果不传参数，是创建了一个空数组，而不会传递null进去)
     * 5.switch 字符串和enmu
     *   执行了两遍switch，第一遍是根据字符串的hashCode和equals将字符串的转换为相应的byte类型，
     * 第二遍才是利用byte执行进行比较
     *   hashcode是为了提高效率，减少可能的比较，而equals是为了防止hashcode冲突，例如BM和C，字符串的
     * hashcode相等都是2123.
     * 6.try-with-resources(资源对象需要实现AutoCloseable)
     * 7.匿名内部类
     *   这同时解释了为什么匿名内部类引用局部变量时，局部变量必须是final的，因为在创建candy11$1
     * 对象时，将x的值赋值给candy11$1对象的value$x属性，所以x不应该在发生变化了，如果变化，那么valx
     * 属性没有机会再跟着一起变化
     *
     *
     * */
}
