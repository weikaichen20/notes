package com.wkc.java.sharemodel.testcase;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
public class Test_wait_1 {
    static final Object LOCK = new Object();

    public static void main(String[] args) {
        try {
            //要等待，先获取
            LOCK.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
