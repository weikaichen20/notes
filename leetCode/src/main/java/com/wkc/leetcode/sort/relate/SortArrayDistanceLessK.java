package com.wkc.leetcode.sort.relate;

import java.util.PriorityQueue;

/**
 * Created on 2022/3/23.
 *
 * @author Weikaichen
 */
public class SortArrayDistanceLessK {

    /*
    * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序后，每个元素
    * 移动的距离可以不超过K，并且K相对与数组来说比较小，请选择一个合适的排序算法进行排序
    * */

    public void sortedArrDistanceLessK(int[] arr, int k) {
        //默认小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            queue.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            queue.add(arr[index]);
            arr[i] = queue.poll();
        }
        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }
}
