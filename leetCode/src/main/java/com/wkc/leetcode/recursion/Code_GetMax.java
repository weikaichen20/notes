package com.wkc.leetcode.recursion;

/**
 * Created on 2022/3/21.
 *
 * @author Weikaichen
 */
//递归求最大值
public class Code_GetMax {
//    master公式
//    T(N)=a*T(N/b)+O(N^(d))
//    总  次数  子规模   其他
//    T(N)=2*T(n/2)+O(1)
    //子问题规模要一样才能使用master公式
    /*
    * log(b,a)>d===>  O(n^log(b,a))
    * log(b,a)=d===>  O(n^d* logN)
    * log(b,a)<d===>  O(n^D)
    * */

    public static int getMax(int[] arr){
        return process(arr,0,arr.length-1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L==R)
            return arr[L];
        int mid=L+((R-L)>>1);//中点，防止数组过大溢出

        int leftMax=process(arr, L, mid);
        int rightMax=process(arr, mid+1, R);

        return Math.max(leftMax,rightMax);


    }
}
