package com.wkc.leetcode.BitOp;

/**
 * Created on 2022/3/16.
 *
 * @author Weikaichen
 */
/*
 * 位运算
 * 0^n=n  n^n=0
 * a^b=b^a  a^b^c=b^c^a
 * */
public class PrintOddTimeNum {
    /*
     * 数组中有一种数出现奇数次，其它数出现偶数次  打印出现奇数次的数
     *
     * 数组中有二种数出现奇数次，其它数出现偶数次  打印出现奇数次的数
     * */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5, 4, 2, 1};
        int[] nums2 = {1, 2, 3, 4, 5, 5, 4, 1};
        print1(nums);
        print2(nums2);
    }

    private static void print2(int[] nums2) {
        int count = 0;
        for (int i = 0; i < nums2.length; i++) {
            count = count ^ nums2[i];
        }
        //count=a^b
        //a!=b!=0
        int rightOne = count & (~count + 1);//提取最右边(不同)的1
        int countOne=0;
        for (int i : nums2) {
            if ((i & rightOne)==0){
                countOne^=i;
            }
        }
        System.out.println(countOne+"<====>"+(count^countOne));
    }

    private static void print1(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count ^ nums[i];
        }
        System.out.println(count);
    }


}
