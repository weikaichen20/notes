package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 0; i < arr.length - 1; i++) {//i ~ N-1
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {//i ~ N-1上找最小值下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[r];
        arr[r] = arr[l];
        arr[l] = temp;
    }

}
