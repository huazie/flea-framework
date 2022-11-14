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
public class PartitionDisjoint {

    public int partitionDisjoint(int[] nums) {
        int len = nums.length;

        // 首先找到数组的最小值的位置
        int minIndex = 0;
        for (int k = 1, min = nums[0]; k < len; k++) {
            if (nums[k] < min) {
                minIndex = k;
                min = nums[k];
            }
        }

        int i = minIndex; // i分割nums数组，满足 left数组最大值 <= right数组最小值

        int leftMax = 0; // left数组 最大值

        while (true) {
            for (int m = 0; m < i + 1; m++) {
                if (nums[m] > leftMax) {
                    leftMax = nums[m];
                }
            }

            int rightMin = nums[i + 1]; // right数组 最小值
            int rightMinIndex = i + 1; // right数组 最小值索引
            for (int n = i + 1; n < len; n++) {
                if (nums[n] <= rightMin) {
                    rightMin = nums[n];
                    rightMinIndex = n;
                }
            }

            // 只要存在 left数组最大值 <= right数组最小值，就跳出循环
            if (leftMax <= rightMin) {
                break;
            } else {
                i = rightMinIndex;
            }
        }

        // left数组的长度即为索引 i + 1
        return i + 1;
    }
}
