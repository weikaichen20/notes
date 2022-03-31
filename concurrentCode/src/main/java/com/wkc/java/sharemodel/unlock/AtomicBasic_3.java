package com.wkc.java.sharemodel.unlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 2022/3/24.
 *
 * @author Weikaichen
 */
public class AtomicBasic_3 {
    /*
     * JUC提供了
     * AtomicInteger
     * AtomicBoolean
     * AtomicLong
     * */

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);

        //获取并自增 i++
        System.out.println(i.getAndIncrement());//0

        //自增并获取 ++i
        System.out.println(i.incrementAndGet());//2

        //获取并自减 i--
        System.out.println(i.getAndDecrement());//2

        //自减并获取 --i
        System.out.println(i.decrementAndGet());//0

        //获取并加值
        System.out.println(i.getAndAdd(5));//0
        System.out.println(i.addAndGet(5));//10



        AtomicInteger in = new AtomicInteger(5);
        in.updateAndGet(operand -> operand*10);
        System.out.println(in.get());

    }
}
