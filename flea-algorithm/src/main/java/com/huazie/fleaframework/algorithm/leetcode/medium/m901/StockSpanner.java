package com.huazie.fleaframework.algorithm.leetcode.medium.m901;

/**
 * <h1>股票价格跨度</h1>
 * <p> 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * <p> 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p> 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/online-stock-span
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class StockSpanner {

    private int curIndex; // 记录每日股票的索引，从0开始

    private int[] stocks; // 记录股票每日价格的数组

    private int[] days; // 记录股票每日价格跨度的数组

    public StockSpanner() {
        curIndex = -1;
        stocks = new int[10000];
        days = new int[10000];
        days[0] = 1; // 第一天默认跨度为1
    }

    public int next(int price) {
        curIndex++;
        stocks[curIndex] = price;
        int temp = 1; // 当日跨度即为1
        for (int m = curIndex - 1; m >= 0; m--) {
            if (stocks[m] <= price) { // 上一天的价格 小于等于 当天价格
                int index = days[m]; // 上一天的跨度
                temp += index; // 累加上一天的跨度
                m = m - index + 1; // 减去上一天的跨度， 加1为了抵消后面循环的减1
            } else {
                break;
            }
        }
        days[curIndex] = temp; // 设置当日的跨度
        return temp;
    }
}

