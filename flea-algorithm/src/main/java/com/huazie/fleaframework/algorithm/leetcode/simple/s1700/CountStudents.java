package com.huazie.fleaframework.algorithm.leetcode.simple.s1700;

/**
 * <h1>无法吃午餐的学生数量</h1>
 * <p> 学校的自助午餐提供圆形和方形的三明治，分别用数字 0 和 1 表示。所有学生站在一个队列里，
 * 每个学生要么喜欢圆形的要么喜欢方形的。餐厅里三明治的数量与学生的数量相同。
 * 所有三明治都放在一个栈里，每一轮：
 *
 * <ul>
 *     <li>如果队列最前面的学生 喜欢 栈顶的三明治，那么会 拿走它 并离开队列。</li>
 *     <li>否则，这名学生会 放弃这个三明治 并回到队列的尾部。</li>
 * </ul>
 *
 * <p> 这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。
 * <p> 给你两个整数数组 students 和 sandwiches ，其中 sandwiches[i] 是栈里面第 i 个三明治的类型
 * （i = 0 是栈的顶部），students[j] 是初始队列里第 j 名学生对三明治的喜好（j = 0 是队列的最开始位置）。
 * 请你返回无法吃午餐的学生数量。
 *
 * <p> 来源：力扣（LeetCode）
 * <p> 链接：https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class CountStudents {

    // TODO
    public int countStudents(int[] students, int[] sandwiches) {
        int len = students.length;

        int[] newStus = new int[len * len];

        for (int m = 0; m < newStus.length; m++) {
            if (m < len)
                newStus[m] = students[m];
            else
                newStus[m] = -1;
        }

        int count = len; // 记录未吃午餐的学生总数

        int loc = 0; // 记录待比较的三明治的位置

        int lastLoc = len;

        for (int m = 0; m < newStus.length; m++) {
            // 如果碰到-1，说明
            if (newStus[m] == -1) break;
            if (newStus[m] == sandwiches[loc]) {
                count--;
                loc++;
            } else {
                newStus[lastLoc] = newStus[m];
                lastLoc++;
            }
        }

        return count;
    }
}
