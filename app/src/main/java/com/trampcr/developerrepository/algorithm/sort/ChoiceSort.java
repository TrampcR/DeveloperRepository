package com.trampcr.developerrepository.algorithm.sort;

public class ChoiceSort {
    public static final String TAG = ChoiceSort.class.getSimpleName();
    public static int[] choiceSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            arr[minIndex] = arr[i];
            arr[i] = min;
        }

        return arr;
    }
}
