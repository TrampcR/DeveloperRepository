package com.trampcr.developerrepository.algorithm.sort;

public class QuickSort {
    public static final String TAG = QuickSort.class.getSimpleName();
    public static int[] quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void sort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }

        if (low < high) {
            int pivot = partition(arr, low, high);
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];

            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }

        arr[low] = temp;
        return low;
    }
}
