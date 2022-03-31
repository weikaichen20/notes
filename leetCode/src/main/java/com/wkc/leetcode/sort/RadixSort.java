package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/23.
 *
 * @author Weikaichen
 */
//基数排序
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    private static int maxbits(int[] arr) {
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    //arr[begin...end]排序
    private static void radixSort(int[] arr, int l, int r, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        int[] bucket = new int[r - l + 1];
        for (int d = 0; d < digit; d++) {//有多少位就进出几次
            //10个空间
            //count[0] 当前位（d位）是0的数字有多少个
            //count[1] 当前位（d位）是(0和1)的数字有多少个
            //count[2] 当前位（d位）是(0和1，2)的数字有多少个
            //count[i] 当前位（d位）是(0-i)的数字有多少个
            int[] count = new int[radix];
            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = r; i >= l; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    private static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


}
