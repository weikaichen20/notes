package com.wkc.leetcode.sort.relate;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class SmallSum {
    /*
     *   1   3   4   2   5  小和问题
     * 4个1
     * 2个3
     * 1个4
     * 1个2
     * */
    public static void main(String[] args) {
        int[] num = {1, 3, 4, 2, 5};
        System.out.println(smallSum(num));
    }

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r)
            return 0;
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid)
                + process(arr, mid + 1, r) +
                merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {

        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

}
