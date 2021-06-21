package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;

/**
 * 异常工具类，封装了自定义异常抛出的通用逻辑
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * 抛出指定的异常，对应异常类的构造方法：{@code 异常类名(String message)}
     *
     * @param exceptionClazz 异常类Class对象
     * @param message        错误描述
     * @since 1.1.0
     */
    public static void throwException(Class<? extends Exception> exceptionClazz, String message) throws Exception {
        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, message);
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }

    /**
     * 抛出指定的异常，对应异常类的构造方法：{@code 异常类名(Throwable cause)}
     *
     * @param exceptionClazz 异常类Class对象
     * @param cause          捕获的异常对象
     * @since 1.1.0
     */
    public static void throwException(Class<? extends Exception> exceptionClazz, Throwable cause) throws Exception {
        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, new Object[]{cause}, new Class<?>[]{Throwable.class});
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }

    /**
     * 抛出指定的异常，对应异常类的构造方法：{@code 异常类名(String message, Throwable cause)}
     *
     * @param exceptionClazz 异常类Class对象
     * @param message        错误描述
     * @param cause          捕获的异常对象
     * @since 1.1.0
     */
    public static void throwException(Class<? extends Exception> exceptionClazz, String message, Throwable cause) throws Exception {
        Object[] params = new Object[]{message, cause};
        Class[] paramTypes = new Class[]{String.class, Throwable.class};
        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, params, paramTypes);
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }

    /**
     * <p> 抛出指定国际化的通用异常 </p>
     *
     * @param exceptionClazz 异常类Class对象
     * @param params         参数列表（可能包含国际化资源数据关键字、异常类、替换参数）
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void throwCommonException(Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {

        if (ObjectUtils.isEmpty(exceptionClazz) || ArrayUtils.isEmpty(params)) {
            return;
        }

        int length = params.length;
        String i18nKey = StringUtils.valueOf(params[0]);

        if (CommonConstants.NumeralConstants.INT_ONE == length) {
            throwException2I18N(exceptionClazz, i18nKey);
        } else if (CommonConstants.NumeralConstants.INT_TWO == length) {
            Object paramTwo = params[1];
            if (paramTwo instanceof Throwable) {
                throwException2I18N(exceptionClazz, i18nKey, (Throwable) paramTwo);
            } else {
                throwException2I18N(exceptionClazz, i18nKey, StringUtils.valueOf(paramTwo));
            }
        } else {
            Object paramTwo = params[1];
            if (paramTwo instanceof Throwable) {
                String[] paramArr = new String[params.length - 2];
                // 取params数组中第三个以后的数据放入paramArr数组中
                for (int n = 2; n < params.length; n++) {
                    paramArr[n - 2] = StringUtils.valueOf(params[n]);
                }
                throwException2I18N(exceptionClazz, i18nKey, (Throwable) paramTwo, paramArr);
            } else {
                String[] paramArr = new String[params.length - 1];
                // 取params数组中第二个以后的数据放入paramArr数组中
                for (int n = 1; n < params.length; n++) {
                    paramArr[n - 1] = StringUtils.valueOf(params[n]);
                }
                throwException2I18N(exceptionClazz, i18nKey, paramArr);
            }
        }

    }

    /**
     * <p> 抛出指定国际化的通用异常【不替换参数】</p>
     *
     * @param exceptionClazz 异常类Class对象
     * @param i18nKey        国际化资源数据关键字
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private static void throwException2I18N(Class<? extends CommonException> exceptionClazz, String i18nKey) throws CommonException {
        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, i18nKey);
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }

    /**
     * <p> 抛出指定国际化的通用异常【带替换参数】</p>
     *
     * @param exceptionClazz 异常类Class对象
     * @param i18nKey        国际化资源数据关键字
     * @param params         替换参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private static void throwException2I18N(Class<? extends CommonException> exceptionClazz, String i18nKey, String... params) throws CommonException {
        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, i18nKey, params);
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }

    /**
     * <p> 抛出指定国际化的通用异常【不带替换参数】</p>
     *
     * @param exceptionClazz 异常类Class对象
     * @param i18nKey        国际化资源数据关键字
     * @param cause          捕获的异常对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private static void throwException2I18N(Class<? extends CommonException> exceptionClazz, String i18nKey, Throwable cause) throws CommonException {
        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, new Object[]{i18nKey, cause}, new Class[]{String.class, Throwable.class});
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }

    /**
     * <p> 抛出指定国际化的通用异常【带替换参数】</p>
     *
     * @param exceptionClazz 异常类Class对象
     * @param i18nKey        国际化资源数据关键字
     * @param cause          捕获的异常对象
     * @param params         替换参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private static void throwException2I18N(Class<? extends CommonException> exceptionClazz, String i18nKey, Throwable cause, String... params) throws CommonException {

        if (ArrayUtils.isEmpty(params)) {
            return;
        }

        Object[] mParams = new Object[params.length + 2];
        mParams[0] = i18nKey;
        mParams[1] = cause;

        Class[] mParamTypes = new Class[params.length + 2];
        mParamTypes[0] = String.class;
        mParamTypes[1] = Throwable.class;

        for (int n = 0; n < params.length; n++) {
            mParams[n + 2] = params[0];
            mParamTypes[n + 2] = String.class;
        }

        Object exceptionInstance = ReflectUtils.newInstance(exceptionClazz, mParams, mParamTypes);
        if (ObjectUtils.isNotEmpty(exceptionInstance)) {
            throw exceptionClazz.cast(exceptionInstance);
        }
    }
}
