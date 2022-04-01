package com.wkc.java.sharemodel.immutable;

/**
 * Created on 2022/4/1.
 *
 * @author Weikaichen
 */
public class FinalPrinciple_4 {
    /* final原理
     * final变量的赋值也会通过putfield指令来完成，同样在这条指令之后也会加入
     * 写屏障，保证在其他线程读到他的值不会出现为0的情况
     * */


    /*
    *       无状态
    * 设计Servlet时保证线程安全，都会有建议，不要为Servlet设置成员变量，这种没有任何成员变量的类是线程安全的
    *   因为成员变量保存的数据也可以称为状态信息，没有成员变量称之为【无状态】
    *
    * */

}
