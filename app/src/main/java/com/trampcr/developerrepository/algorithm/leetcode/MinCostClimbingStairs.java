package com.trampcr.developerrepository.algorithm.leetcode;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        int costLen = cost.length;
        int firstCost = cost[0];
        int secondCost = cost[1];

        for (int i = 2; i < costLen; i++) {
            int tempCost = Math.min(firstCost, secondCost) + cost[i];
            firstCost = secondCost;
            secondCost = tempCost;
        }

        return Math.min(firstCost, secondCost);
    }
}
