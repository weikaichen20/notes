package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/23.
 *
 * @author Weikaichen
 */
public class BucketSort {
    /*
    * 桶排序不是基于比较的排序，要根据数据状况定制
    * */


    /*
    * 排序算法的稳定性及其汇总
    *
    * 同样值的个体之间，如果不因为排序而改变相对次序，就是这个排序是有稳定性的，否则就没有
    *
    * 不具备稳定性的排序
    * 选择排序，快速排序，堆排序
    *
    * 具备稳定性的排序
    * 冒泡，插入，归并，一切桶排序思想下的排序
    *
    * 目前没有找到时间复杂度O(n*logN),额外空间复杂度O(1),又稳定的排序
    *
    * */



    /*
                       时间       空间      稳
     选择             O(n^2)      O(1)       F
     冒泡             O(n^2)      O(1)       T
     插入             O(n^2)      O(1)       T
     归并             O(n*logN)   O(N)       T
     快排             O(n*logN)   O(logN)    F   （快排常数项最低，最快）
     堆排             O(n*logN)   O(1)       F


     */
}

