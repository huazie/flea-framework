package com.huazie.fleaframework.algorithm.leetcode.medium;

import com.huazie.fleaframework.algorithm.leetcode.medium.m3.LengthOfLongestSubstring;
import org.junit.Test;

public class LengthOfLongestSubstringTest {

    private LengthOfLongestSubstring soultion = new LengthOfLongestSubstring();

    @Test
    public void lengthOfLongestSubstring() {
        String str1 = "abcabcbb";
        System.out.println(soultion.lengthOfLongestSubstring(str1));

        String str2 = "bbbbb";
        System.out.println(soultion.lengthOfLongestSubstring(str2));

        String str3 = "pwwkew";
        System.out.println(soultion.lengthOfLongestSubstring(str3));
    }
}