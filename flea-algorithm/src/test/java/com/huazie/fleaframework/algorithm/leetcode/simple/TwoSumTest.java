package com.huazie.fleaframework.algorithm.leetcode.simple;

import org.junit.Test;

import java.util.Arrays;

public class TwoSumTest {

    private TwoSum twoSum = new TwoSum();

    @Test
    public void twoSum() {
        int[] nums1 = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum.twoSum(nums1, 9)));

        int[] nums2 = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum.twoSum(nums2, 6)));

        int[] nums3 = {3, 3};
        System.out.println(Arrays.toString(twoSum.twoSum(nums3, 6)));
    }

}