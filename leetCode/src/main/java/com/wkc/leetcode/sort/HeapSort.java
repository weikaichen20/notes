package com.wkc.leetcode.sort;

/**
 * Created on 2022/3/22.
 *
 * @author Weikaichen
 */
public class HeapSort {
    /*  完全二叉树
     *
     * i 左 2*i+1
     *   右 2*i+2
     *   父 (i-1)/2
     * */
    //O(N*logN)
    public static void HeapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        for (int i = 0; i < arr.length; i++) {//O(N)
//            heapInsert(arr, i);//O(logN)
//        }

        //快速大根堆
        for (int i = arr.length-1; i >=0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {//O(N)
            heapify(arr, 0, heapSize);//O(logN)
            swap(arr, 0, --heapSize);//O(1)
        }
    }


    //某个数现在处在index位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //某个数在index位置，能否向下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {//下方还有孩子的时候
            //两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //父和较大孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[r];
        arr[r] = arr[l];
        arr[l] = temp;
    }
}
