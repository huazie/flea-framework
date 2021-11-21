package com.huazie.fleaframework.algorithm.factorization;

import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 因数分解算法 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Factor {

    private Factor() {
    }

    /**
     * 因数分解
     *
     * @param factor 待因数分解的数
     * @return 因数分解结果数组
     */
    public static Integer[] factor(int factor) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(factor); i++) {
            if (factor % i == 0) {
                resultList.add(i);
                factor /= i;
                i--;
            }
        }
        resultList.add(factor);
        return resultList.toArray(new Integer[0]);
    }

    /**
     * 因数分解
     *
     * @param factor 待因数分解的数
     * @return 因数分解结果数组
     */
    public static Long[] factor(long factor) {
        List<Long> resultList = new ArrayList<>();
        for (Long i = 2L; i <= Math.sqrt(factor); i++) {
            if (factor % i == 0) {
                resultList.add(i);
                factor /= i;
                i--;
            }
        }
        resultList.add(factor);
        return resultList.toArray(new Long[0]);
    }

    /**
     * 大数因数分解
     *
     * @param factor 待因数分解的大数
     * @return 大数因数分解结果数组
     */
    public static BigInteger[] factor(BigInteger factor) {
        List<BigInteger> resultList = new ArrayList<>();
        BigInteger mFactor = new BigInteger(factor.toString());
        for (BigInteger first = BigInteger.valueOf(2); first.compareTo(BigIntegerMath.sqrt(mFactor, RoundingMode.DOWN)) <= 0; first = first.add(BigInteger.ONE)) {
            if (mFactor.remainder(first).compareTo(BigInteger.ZERO) == 0) {
                resultList.add(first);
                mFactor = mFactor.divide(first);
                first = first.min(BigInteger.ONE);
            }
        }
        resultList.add(mFactor);
        return resultList.toArray(new BigInteger[0]);
    }
}
