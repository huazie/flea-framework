package com.huazie.fleaframework.algorithm.leetcode.medium;

import com.huazie.fleaframework.algorithm.leetcode.medium.m915.PartitionDisjoint;
import com.huazie.fleaframework.algorithm.leetcode.medium.m915.PartitionDisjoint1;
import org.junit.Test;

public class PartitionDisjointTest {

    private PartitionDisjoint partitionDisjoint = new PartitionDisjoint();

    private PartitionDisjoint1 partitionDisjoint1 = new PartitionDisjoint1();

    @Test
    public void partitionDisjoint() {
        int[] nums = {5,0,3,8,6};
        System.out.println(partitionDisjoint.partitionDisjoint(nums));
    }

    @Test
    public void partitionDisjoint1() {
        int[] nums = {5,0,3,8,6};
        System.out.println(partitionDisjoint1.partitionDisjoint(nums));
    }
}