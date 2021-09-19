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
        LOGGER.debug("O = {}", reverse(2147483647));
    }

    /**
     * 思路：
     * （1）如果带反转整数不为0，一直循环下面3和4
     * （2）最大或最小边界判断，如果在边界外，则直接返回0
     * （3）取带反转整数末尾数字，并添加到新数字的末尾
     * （4）带反转整数除以10，以便继续处理下一个数字
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            // 最大或最小边界判断
            if (Integer.MAX_VALUE / 10 < result || Integer.MIN_VALUE / 10 > result) {
                return 0;
            }
            // 取带反转整数末尾数字，并添加到新数字的末尾
            result = result * 10 + x % 10;
            // 带反转整数除以10，以便继续处理下一个数字
            x /= 10;
        }
        return result;
    }
}