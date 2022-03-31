package com.wkc.jvm;

/**
 * Created on 2022/1/17.
 *
 * @author Weikaichen
 */
//二进制字节码（类基本信息，常量池，类方法定义（包含了虚拟机指令））javap -v 类名.class
public class RuntimeConstPool_8 {
    //常量池就是一张表，虚拟机指令根据这张表常量表找到要执行的类名，方法名，参数类型，字面量等信息
    //运行时常量池，常量池是*.class文件中的，当他们类被加载时，他的常量池信息就会放入运行时常量池，并把里面的符号地址变成真实地址
}
