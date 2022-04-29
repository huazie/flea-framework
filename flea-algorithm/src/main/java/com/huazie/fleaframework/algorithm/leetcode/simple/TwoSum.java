package com.huazie.fleaframework.algorithm.leetcode.simple;

/**
 * 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
 * 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * <p>
 * 题目来源：力扣（LeetCode）
 * 题目链接：https://leetcode-cn.com/problems/two-sum
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class TwoSum {

    /**
     * 递归解法，解法来自 Huazie
     *
     * @param nums   整数数组
     * @param target 整数目标值
     * @return 和为目标值target的那两个整数的数组下标的数组
     * @since 2.0.0
     */
    public int[] twoSum(int[] nums, int target) {
        return twoSum(nums, target, null, 0);
    }

    private int[] twoSum(int[] nums, int target, int[] ret, int n) {
        // 存储和为目标值target的那两个整数的数组下标的数组
        if (null == ret) {
            ret = new int[2];
        }
        // 记录第二个整数的下标
        int second = 0;
        // ret数组第一位记录第一个整数的下标
        ret[0] = n;
        // 从第一个整数的下一个位置开始遍历
        for (int i = n + 1; i < nums.length; i++) {
            // 比较两数之和是否等于目标值target
            // 如果相等，重新记录第二个整数的下标，并跳出循环
            if (nums[ret[0]] + nums[i] == target) {
                second = i;
                break;
            }
        }

        if (second == 0) {
            // 一轮递归，没有获取到符合条件的第二个整数，继续下一轮递归
            ret = twoSum(nums, target, ret, n + 1);
        } else {
            // 一轮递归，获取到了符合条件的第二个整数，记录到ret数组的第二个位置
            ret[1] = second;
        }
        return ret;
    }
}
