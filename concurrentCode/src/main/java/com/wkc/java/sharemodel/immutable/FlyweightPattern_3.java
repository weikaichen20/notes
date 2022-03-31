package com.wkc.java.sharemodel.immutable;

/**
 * Created on 2022/3/31.
 *
 * @author Weikaichen
 */
public class FlyweightPattern_3 {
    /* 享元模式
     *
     *      定义
     * 当需要重用数量有限的同一类对象时
     *      体现
     * 再JDK中Boolean等包装类提供了ValueOf方法，例如Long的valueOf会缓存
     * -128~127之间的Long对象，在这个范围内会重用对象，大于这个范围，才会新建Long对象
     *
     * */
}
