package com.huazie.fleaframework.algorithm.leetcode.difficult.d4;

/**
 * 寻找两个正序数组的中位数
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FindMedianSortedArrays {

    /**
     * 解法来自 Huazie
     *
     * @param nums1 正序数组
     * @param nums2 正序数组
     * @return 两个正序数组的中位数
     * @since 2.0.0
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        // nums1数组为空
        if (len1 == 0) {
            return findMedianSortedArrays(len2, nums2);
        }

        // nums2数组为空
        if (len2 == 0) {
            return findMedianSortedArrays(len1, nums1);
        }

        int i = 0;
        int j = 0;
        int k = 0;
        int len = len1 + len2;
        int[] nums = new int[len];
        // 归并排序
        for (; i < len1 && j < len2; ) {
            if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        // 处理nums1数组未遍历完的数据
        while (i < len1) {
            nums[k++] = nums1[i++];
        }
        // 处理nums2数组未遍历完的数据
        while (j < len2) {
            nums[k++] = nums2[j++];
        }
        // 取排序好的数组的中位数
        return findMedianSortedArrays(len, nums);
    }

    private double findMedianSortedArrays(int len, int[] nums) {
        if (len % 2 != 0) {
            return nums[len / 2];
        } else {
            return (nums[len / 2] + nums[len / 2 - 1]) / 2.0;
        }
    }
}
