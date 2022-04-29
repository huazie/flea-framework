package com.huazie.fleaframework.algorithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 题目来源：力扣（LeetCode）
 * 题目链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class LengthOfLongestSubstring {

    /**
     * 解法来自 Huazie
     *
     * @param s 给定的字符串
     * @return 不含重复字符的最长字串的长度
     * @since 2.0.0
     */
    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        int sLen = s.length();
        Set<Character> set = new HashSet<>();
        int n = 0; // 记录子串最右侧位于s的索引位置，起始位置从0开始
        for (int m = 0; m < sLen; m++) {
            if (m != 0) {
                // 去除前一个循环中添加的前一个索引位置的元素
                set.remove(s.charAt(m - 1));
            }
            // 子串右侧位置记录上一次循环找到的子串最右侧字符的索引位置
            for (; n < sLen; n++) {
                // 添加子串元素，如果add返回false，表示set中已有元素，则需要跳出循环，开始下一次选择
                if (!set.add(s.charAt(n))) {
                    break;
                }
            }
            // 取最长子串的长度
            len = Math.max(n - m, len);
        }
        return len;
    }
}
