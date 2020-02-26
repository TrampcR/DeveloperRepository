package com.trampcr.developerrepository.algorithm.sort;

public class InsertSort {
    public static final String TAG = InsertSort.class.getSimpleName();
    public static int[] innerSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }

            arr[j + 1] = temp;
        }

        return arr;
    }
}
