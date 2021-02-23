package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;

import java.math.BigInteger;

/**
 * <p> 数字相关工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class NumberUtils {

    /**
     * <p> 对象值转数字值 </p>
     *
     * @param value 对象值
     * @return 数字值
     * @since 1.0.0
     */
    public static Number toNumber(Object value) {
        Number number = null;
        if (ObjectUtils.isNotEmpty(value)) {
            if (value instanceof Integer) {
                number = (Integer) value;
            } else if (value instanceof Long) {
                number = (Long) value;
            } else if (value instanceof Short) {
                number = (Short) value;
            } else if (value instanceof Float) {
                number = (Float) value;
            } else if (value instanceof Double) {
                number = (Double) value;
            } else if (value instanceof Byte) {
                number = (Byte) value;
            }
        }
        return number;
    }

    /**
     * <p> 判断value是否是正数 </p>
     *
     * @param value 数字对象值
     * @return true：正数 ； false：非正数
     * @since 1.0.0
     */
    public static boolean isPositiveNumber(Object value) {

        boolean isPositive = false;

        if (ObjectUtils.isNotEmpty(value)) {
            if (value instanceof Integer) {
                Integer number = (Integer) value;
                isPositive = isPositiveNumber(number.compareTo(CommonConstants.NumeralConstants.INT_ZERO));
            } else if (value instanceof Long) {
                Long number = (Long) value;
                isPositive = isPositiveNumber(number.compareTo(CommonConstants.NumeralConstants.ZERO));
            } else if (value instanceof Short) {
                Short number = (Short) value;
                isPositive = isPositiveNumber(number.compareTo(Short.valueOf(StringUtils.valueOf(CommonConstants.NumeralConstants.INT_ZERO))));
            } else if (value instanceof Float) {
                Float number = (Float) value;
                isPositive = isPositiveNumber(number.compareTo(Float.valueOf(StringUtils.valueOf(CommonConstants.NumeralConstants.INT_ZERO))));
            } else if (value instanceof Double) {
                Double number = (Double) value;
                isPositive = isPositiveNumber(number.compareTo(Double.valueOf(StringUtils.valueOf(CommonConstants.NumeralConstants.INT_ZERO))));
            } else if (value instanceof Byte) {
                Byte number = (Byte) value;
                isPositive = isPositiveNumber(number.compareTo(Byte.valueOf(StringUtils.valueOf(CommonConstants.NumeralConstants.INT_ZERO))));
            }
        }

        return isPositive;
    }

    /**
     * <p> 是否是正数 </p>
     *
     * @param value int类型的值
     * @return true：正数 ；false：非正数
     * @since 1.0.0
     */
    private static boolean isPositiveNumber(int value) {
        boolean isPositive = false;
        if (value > CommonConstants.NumeralConstants.INT_ZERO) {
            isPositive = true;
        }
        return isPositive;
    }

    /**
     * <p> 校验是否是非正数（即小于等于0）
     * 如果是，则抛出对应的异常；如果不是，则校验通过</p>
     *
     * @param value          数字对象值
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkNonPositiveNumber(Object value, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (!isPositiveNumber(value)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 大数的开平方根（取整）
     *
     * @param value 指定大数
     * @return 大数的开平方根（取整）
     * @since 1.0.0
     */
    public static BigInteger sqrt(BigInteger value) {
        BigInteger two = BigInteger.valueOf(2);
        BigInteger prv = value.divide(two);
        BigInteger now = prv.add(value.divide(prv)).divide(two);
        while (prv.compareTo(now) > 0) {
            prv = now;
            now = prv.add(value.divide(prv)).divide(two);
        }
        return now;
    }
}
