package com.trampcr.developerrepository.algorithm.leetcode;

/**
 *
 * @author trampcr
 * @date 2019/9/24
 */

import java.lang.reflect.Proxy;

/**
 * 题目：报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 */
public class CountAndSay38 {
    public String countAndSay(int n) {
        String initStr = "1";

        if (n <= 0) {
            return "";
        }

        if (n == 1) {
            return initStr;
        }

        for (int i = 1; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            char temp = initStr.charAt(0);
            int count = 0;
            for (int j = 0; j < initStr.length(); j++) {
                if (temp == initStr.charAt(j)) {
                    count++;
                } else {
                    stringBuilder.append(count);
                    stringBuilder.append(temp);
                    temp = initStr.charAt(j);
                    count = 1;
                }
            }

            stringBuilder.append(count);
            stringBuilder.append(initStr.charAt(initStr.length() - 1));
            initStr = stringBuilder.toString();
        }

        return initStr;
    }
}
