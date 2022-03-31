package com.wkc.leetcode.sort.relate;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created on 2022/3/23.
 *
 * @author Weikaichen
 */
public class HeapAndComp {

    public static class AComp implements Comparator<Integer>{
        //如果返回负数，认为第一个参数应该排在上面
        //如果返回正数，认为第一个参数应该排在下面
        //返回0，谁放上面无所谓
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new AComp());
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }


}
