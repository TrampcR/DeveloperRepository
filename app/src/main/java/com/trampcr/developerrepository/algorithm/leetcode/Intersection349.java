package com.trampcr.developerrepository.algorithm.leetcode;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trampcr on 2019/9/3.
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * 输出结果中的每个元素一定是唯一的。可以不考虑输出结果的顺序。
 */

public class Intersection349 {
    public static final String TAG = Intersection349.class.getSimpleName();
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        ArrayList<Integer> arrayList = new ArrayList<>();
        int temp = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                if (temp != nums1[i]) {
                    temp = nums1[i];
                    arrayList.add(temp);
                }
                i++;
                j++;
            }
        }

        int[] result = new int[arrayList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = arrayList.get(i);
        }

        return result;
    }
}
