package com.huazie.fleaframework.algorithm.leetcode.difficult.d1235;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <h1>1235. 规划兼职工作</h1>
 * <p> 你打算利用空闲时间来做兼职工作赚些零花钱。
 * <p> 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * <p> 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
 * <p> 注意，时间上出现重叠的 2 份工作不能同时进行。
 * <p> 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 *
 * <p> 来源：力扣（LeetCode）
 * <p> 链接：https://leetcode.cn/problems/maximum-profit-in-job-scheduling
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class JobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;

        int[][] jobs = new int[len][]; // 记录工作的开始时间、结束时间和预计报酬

        for (int m = 0; m < len; m++) {
            jobs[m] = new int[]{startTime[m], endTime[m], profit[m]};
        }

        // 将工作按照结束时间升序排序
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] job1, int[] job2) {
                return job1[1] - job2[1];
            }
        });

        // 定义前n个工作的最大报酬为 f(n)，f(0) = 0
        int[] f = new int[len + 1];

        // f(n) 有如下两种取值，从中取最大值：
        // （1）如果不算第n个工作，则前n个工作的最大报酬为前n-1个工作的最大报酬，即 f(n) = f(n - 1)
        // （2）如果算第n个工作，则前n个工作的最大报酬为前m个工作的最大报酬 加上 第n个工作的报酬，即 f(n) = f(m) + jobs[n - 1][2]
        // f(n) = Math.max(f(n - 1), f(m) + jobs[n-1][2])
        // 注意：（2）中的 m 是满足 jobs[m - 1][1] <= jobs[n - 1][0] 的 最大工作数量; 如果找不到m，即m=0、f(m) = 0。
        // jobs[m - 1][1] 为 第m个工作的结束时间； jobs[n - 1][0]为 第n个工作的开始时间

        for(int n = 1; n <= len; n++) {
            int m = binarySearch(jobs, n, jobs[n - 1][0]);
            f[n] = Math.max(f[n-1], f[m] + jobs[n - 1][2]);
        }

        return f[len];
    }

    /**
     * 由于工作已经按结束时间升序排列，这时可以通过二分查找m，m是满足 jobs[m - 1][1] <= jobs[n - 1][0]的最大工作数量
     */
    private int binarySearch(int[][] jobs, int n, int nStart) {
        int max = n;
        int min = 1;
        while (min <= max) {
            int mid = Math.round((min + max) / 2.0f);
            if (jobs[mid - 1][1] <= nStart) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return max;
    }
}
