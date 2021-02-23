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
        LOGGER.debug1(new Object() {},"因式分解的结果为：{}", Arrays.toString(resultArr));
    }

    @Test
    public void testFactorForLong() {
        Long[] resultArr = Factor.factor(100000002L);
        LOGGER.debug1(new Object() {},"因式分解的结果为：{}", Arrays.toString(resultArr));
    }

    @Test
    public void testFactorForBigInteger() {
        BigInteger[] resultArr = Factor.factor(new BigInteger("2441696060695515"));
        LOGGER.debug1(new Object() {},"因式分解的结果为：{}", Arrays.toString(resultArr));
    }
}