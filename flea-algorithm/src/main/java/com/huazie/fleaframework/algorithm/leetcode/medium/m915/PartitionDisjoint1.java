package com.huazie.fleaframework.algorithm.leetcode.medium.m915;

/**
 * <h1>915. 分割数组</h1>
 * <p> 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 * <ul>
 * <li>left 中的每个元素都小于或等于 right 中的每个元素。</li>
 * <li>left 和 right 都是非空的。</li>
 * <li>left 的长度要尽可能小。</li>
 * </ul>
 *
 * <p> 在完成这样的分组后返回 left 的 长度 。
 * <p> 用例可以保证存在这样的划分方法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-array-into-disjoint-intervals
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class PartitionDisjoint1 {

    public int partitionDisjoint(int[] nums) {
        int len = nums.length;

        // 记录 right 数组 在 m 位置 的最小值数组
        int[] min = new int[len];
        min[len - 1] = nums[len - 1];
        // 从后往前遍历，取最小值数组【由题意可知，right数组索引范围 1 ~ len - 1 】
        for (int m = len - 2; m > 0; m--) {
            min[m] = Math.min(min[m + 1], nums[m]);
        }

        // 从前往后遍历，取最大值【由题意可知，left数组索引范围 0 ~ len - 2】
        int n = 0;
        int max = nums[0];
        for (; n < len - 1; n++) {
            max = Math.max(nums[n], max);
            if (max <= min[n + 1]) {
                break;
            }
        }

        return n + 1;
    }
}
