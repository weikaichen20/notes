package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/17.
 *
 * @author Weikaichen
 */
public class InsertionSort {
    public static void insertionSort(int[] arr){
        if (arr==null||arr.length<2){
            return;
        }
        //0-0有序的
        //0-i想有序
        for (int i = 1; i < arr.length; i++) {//0~i做到有序
            for (int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }
    //i和j是一个位置的话会出错，内存地址相同n^n=0
    private static void swap(int[] arr, int j, int i) {
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}
