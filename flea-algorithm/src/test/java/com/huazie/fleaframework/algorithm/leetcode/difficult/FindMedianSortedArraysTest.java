package com.huazie.fleaframework.algorithm.leetcode.difficult;

import com.huazie.fleaframework.algorithm.leetcode.difficult.d4.FindMedianSortedArrays;
import org.junit.Test;

public class FindMedianSortedArraysTest {

    private FindMedianSortedArrays solution = new FindMedianSortedArrays();

    @Test
    public void findMedianSortedArrays() {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println(solution.findMedianSortedArrays(nums1, nums2));

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(solution.findMedianSortedArrays(nums3, nums4));
    }
}