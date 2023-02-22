package com.huazie.fleaframework.algorithm.leetcode.difficult;

import com.huazie.fleaframework.algorithm.leetcode.difficult.d1235.JobScheduling;
import com.huazie.fleaframework.algorithm.leetcode.difficult.d1235.JobScheduling1;
import org.junit.Test;

public class JobSchedulingTest {

    private JobScheduling jobScheduling = new JobScheduling();

    private JobScheduling1 jobScheduling1 = new JobScheduling1();

    @Test
    public void jobScheduling() {
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        System.out.println(jobScheduling.jobScheduling(startTime, endTime, profit));
     }

    @Test
    public void jobScheduling1() {
        int[] startTime = {1,2,3,4,6};
        int[] endTime = {3,5,10,6,9};
        int[] profit = {20,20,100,70,60};
        System.out.println(jobScheduling1.jobScheduling(startTime, endTime, profit));
    }
}