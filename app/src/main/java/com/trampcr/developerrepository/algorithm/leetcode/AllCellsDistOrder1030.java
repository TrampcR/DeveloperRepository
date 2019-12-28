package com.trampcr.developerrepository.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by trampcr on 2019/9/4.
 *
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 *
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 *
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
 * 其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（可以按任何满足此条件的顺序返回答案。）
 */

public class AllCellsDistOrder1030 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        if (R <= 0 || C <= 0 || r0 < 0 || c0 < 0) {
            return null;
        }

        HashMap<Integer, ArrayList<int[]>> cellMap = new HashMap<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int len = Math.abs(r - r0) + Math.abs(c - c0);
                if (cellMap.containsKey(len)) {
                    ArrayList<int[]> cellArray = cellMap.get(len);
                    cellArray.add(new int[]{r, c});
                } else {
                    ArrayList<int[]> cellArray = new ArrayList<>();
                    cellArray.add(new int[]{r, c});
                    cellMap.put(len, cellArray);
                }
            }
        }

        int key = 0;
        ArrayList<int[]> arrayList = new ArrayList<>();
        while (cellMap.containsKey(key)) {
            arrayList.addAll(cellMap.get(key));
            key++;
        }

        int[][] result = new int[R * C][2];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i][0] = arrayList.get(i)[0];
            result[i][1] = arrayList.get(i)[1];
        }

        return result;
    }
}
