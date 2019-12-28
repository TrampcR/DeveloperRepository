package com.trampcr.developerrepository.algorithm.leetcode;

public class ClimbStairs {
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int num1 = 1;
        int num2 = 0;
        int result = 0;

        for (int i = 2; i < n; i++) {
            result = num1 + num2;
            num2 = num1;
            num1 = result;
        }

        return result;
    }
}
