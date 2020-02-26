package com.trampcr.developerrepository.algorithm.sort;

public class BubbleSort {
    public static final String TAG = BubbleSort.class.getSimpleName();
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = len - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }

        return arr;
    }
}
