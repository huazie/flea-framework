package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 数组操作工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * 检查对象数组是否为空
     *
     * @param values 对象数组
     * @return 如果对象数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(Object[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查long数组是否为空
     *
     * @param values long数组
     * @return 如果long数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(long[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查int数组是否为空
     *
     * @param values int数组
     * @return 如果int数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(int[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查short数组是否为空
     *
     * @param values short数组
     * @return 如果short数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(short[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查byte数组是否为空
     *
     * @param values byte数组
     * @return 如果byte数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(byte[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查double数组是否为空
     *
     * @param values double数组
     * @return 如果double数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(double[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查float数组是否为空
     *
     * @param values float数组
     * @return 如果float数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(float[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查boolean数组是否为空
     *
     * @param values boolean数组
     * @return 如果boolean数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(boolean[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查char数组是否为空
     *
     * @param values char数组
     * @return 如果char数组是空，返回true
     * @since 1.0.0
     */
    public static boolean isEmpty(char[] values) {
        return values == null || values.length == 0;
    }

    /**
     * 检查对象数组是否不为空
     *
     * @param values 对象数组
     * @return 如果对象数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Object[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查long数组是否不为空
     *
     * @param values long数组
     * @return 如果long数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(long[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查int数组是否不为空
     *
     * @param values int数组
     * @return 如果int数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(int[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查short数组是否不为空
     *
     * @param values short数组
     * @return 如果short数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(short[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查byte数组是否不为空
     *
     * @param values byte数组
     * @return 如果byte数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(byte[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查double数组是否不为空
     *
     * @param values double数组
     * @return 如果double数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(double[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查float数组是否不为空
     *
     * @param values float数组
     * @return 如果float数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(float[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查boolean数组是否不为空
     *
     * @param values boolean数组
     * @return 如果boolean数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(boolean[] values) {
        return !isEmpty(values);
    }

    /**
     * 检查char数组是否不为空
     *
     * @param values char数组
     * @return 如果char数组不为空，返回true
     * @since 1.0.0
     */
    public static boolean isNotEmpty(char[] values) {
        return !isEmpty(values);
    }

    /**
     * 判断两个数组长度是否相等
     *
     * @param valueArr1 数组1
     * @param valueArr2 数组2
     * @return true: 两个数组长度相等；false : 两个数组长度不相等
     * @since 1.0.0
     */
    public static boolean isSameLength(Object[] valueArr1, Object[] valueArr2) {
        if (isEmpty(valueArr1) && isEmpty(valueArr2)) {
            return true;
        }

        return isNotEmpty(valueArr1) && isNotEmpty(valueArr2) && valueArr1.length == valueArr2.length;
    }

    /**
     * 校验map集合对象，如果空，则抛出异常
     *
     * @param values         对象数组
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkEmpty(Object[] values, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isEmpty(values)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 校验map集合对象，如果非空，则抛出异常
     *
     * @param values         对象数组
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkNotEmpty(Object[] values, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isNotEmpty(values)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

}
