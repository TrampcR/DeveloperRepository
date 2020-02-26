package com.trampcr.developerrepository.algorithm.sort;

public class MergeSort {
    public static final String TAG = MergeSort.class.getSimpleName();
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void sort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0) {
            return;
        }

        if (start < end) {
            int mid = start + ((end - start) >> 1);
            sort(arr, start, mid);
            sort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int[] temp = new int[arr.length];
        int pre = start;
        int post = mid + 1;
        int result = start;

        while (pre <= mid && post <= end) {
            if (arr[pre] < arr[post]) {
                temp[result++] = arr[pre++];
            } else {
                temp[result++] = arr[post++];
            }
        }

        while (pre <= mid) {
            temp[result++] = arr[pre++];
        }

        while (post <= end) {
            temp[result++] = arr[post++];
        }

        while (start <= end) {
            arr[start] = temp[start++];
        }
    }
}
