package com.huazie.frame.algorithm.factorization;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class FactorTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FactorTest.class);

    @Test
    public void testFactorForInteger() {
        Integer[] resultArr = Factor.factor(100000002);
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr));
    }

    @Test
    public void testFactorForLong() {
        Long[] resultArr = Factor.factor(100000002L);
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr));
    }

    @Test
    public void testFactorForBigInteger() {
        BigInteger[] resultArr = Factor.factor(new BigInteger("2441696060695515"));
        LOGGER.debug("因数分解的结果为：{}", Arrays.toString(resultArr));
    }

    @Test
    public void test() {
        LOGGER.debug(convert("A", 1));
    }

    public String convert(String s, int numRows) {

        StringBuilder result = new StringBuilder();
        int len = s.length();
        if (numRows == 1) {
            return s;
        }
        for (int m = 0; m < numRows; m++) {
            boolean isOdd = true; // 奇数次遍历
            int n = m;
            // n 记录新字符串中字符在s中的位置
            // 一次循环处理一行的字符
            while (n < len) {
                result.append(s.charAt(n));
                if (m == 0) { // 第一行，默认采用奇数次计算位置
                    isOdd = true;
                } else if (m == numRows - 1) { // 最后一行，默认采用偶数次计算位置
                    isOdd = false;
                }
                if (isOdd) { // 奇数次计算位置
                    n += 2 * (numRows - m) - 2;
                    isOdd = false;
                } else { // 偶数次计算位置
                    n += 2 * (m + 1) - 2;
                    isOdd = true;
                }
            }
        }

        return result.toString();
    }
}