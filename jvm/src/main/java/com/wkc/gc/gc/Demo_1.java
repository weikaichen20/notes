package com.wkc.gc.gc;

import java.util.ArrayList;

/**
 * Created on 2022/1/27.
 *
 * @author Weikaichen
 */
/**
 *  演示内存的分配策略
 */
public class Demo_1 {
    private static final int _512KB = 512 * 1024;
    private static final int _1MB = 1024 * 1024;
    private static final int _6MB = 6 * 1024 * 1024;
    private static final int _7MB = 7 * 1024 * 1024;
    private static final int _8MB = 8 * 1024 * 1024;

    // -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc -XX:-ScavengeBeforeFullGC
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            ArrayList<byte[]> list = new ArrayList<>();
            list.add(new byte[_8MB]);
            list.add(new byte[_8MB]);
        }).start();

        System.out.println("sleep....");
        Thread.sleep(1000L);
    }
}
