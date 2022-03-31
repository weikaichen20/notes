package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class QuickSort {
    /*
     * 荷兰国旗问题
     * 问题一
     *   给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边
     *
     * 问题二
     *   给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边
     *
     * 1. [i]<num,[i]和<区下一个交换，<区右扩 i++
     * 2. [i]==num, i++
     * 3. [i]>num,[i]和>区前一个交换，>区左扩 i不变
     * 临界条件 i== >区
     * */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    //使arr[1..r]排好序
    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    //这是一个处理arr[1..r]的函数
    //默认以arr[r]做划分，arr[r] -> p     <p    ==p     >p
    //返回等于区域（左边界，右边界），所以返回一个长度为2的数组res，res[0] res[1]
    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;//<区右边界
        int more = r;//>区左边界
        while (l < more) {//l表示当前数的位置 arr[r] ->划分值
            if (arr[l] < arr[r]) {//当前数 < 划分值
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {//当前数 > 划分值
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[r];
        arr[r] = arr[l];
        arr[l] = temp;
    }

}
