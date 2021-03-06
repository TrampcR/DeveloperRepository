package com.trampcr.developerrepository.algorithm.leetcode;

/**
 * Created by trampcr on 2019/9/9.
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 */

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
