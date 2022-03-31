package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int e = arr.length - 1; e > 0; e--) {//0 ~ e
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[r];
        arr[r] = arr[l];
        arr[l] = temp;
    }
}
