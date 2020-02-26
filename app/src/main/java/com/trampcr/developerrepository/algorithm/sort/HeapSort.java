package com.trampcr.developerrepository.algorithm.sort;

public class HeapSort {
    public static final String TAG = HeapSort.class.getSimpleName();
    public static int[] heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        buildHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maximumHeap(arr, 0, i);
        }

        return arr;
    }

    private static void buildHeap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            maximumHeap(arr, i, len);
        }
    }

    private static void maximumHeap(int[] arr, int pNode, int len) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int temp = arr[pNode];
        for (int i = 2 * pNode + 1; i < len; i++) {
            if (i + 1 < len && arr[i + 1] > arr[i]) {
                i++;
            }

            if (arr[i] > temp) {
                arr[pNode] = arr[i];
                pNode = i;
            } else {
                break;
            }
        }

        arr[pNode] = temp;
    }
}
