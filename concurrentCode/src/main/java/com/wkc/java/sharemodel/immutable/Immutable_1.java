package com.wkc.java.sharemodel.immutable;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Created on 2022/3/30.
 *
 * @author Weikaichen
 */
@Slf4j
public class Immutable_1 {
    /*
     * 不可变类的使用
     * 不可变类设计
     * 无状态类设计
     * */

    public static void main(String[] args) {
//        test();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                    try {
                        log.debug("{}",dtf.parse("1951-04-11"));
                    }catch (Exception e){
                        log.debug("{}",e);
                    }
            }).start();
        }
    }

    private static void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                synchronized (sdf){
                    try {
                        log.debug("{}",sdf.parse("1951-04-11"));
                    }catch (Exception e){
                        log.debug("{}",e);
                    }
                }
            }).start();
        }
    }
}
