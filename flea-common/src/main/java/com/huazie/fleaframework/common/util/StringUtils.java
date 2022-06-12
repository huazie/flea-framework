package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.PinyinEnum;
import com.huazie.fleaframework.common.exception.CommonException;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串相关的工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param value 待检查字符串
     * @return 如果为空或null或空白字符，返回true; 否则返回false
     * @since 1.0.0
     */
    public static boolean isBlank(String value) {
        int strLen;
        if (null == value || (strLen = value.length()) == 0)
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param value 待检查字符串
     * @return 如果不为空且不是空白字符，返回true; 否则返回false
     * @since 1.0.0
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * 校验value字符串对象，如果空，则抛出异常
     *
     * @param value          字符串对象
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkBlank(String value, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isBlank(value)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 校验value字符串对象，如果非空，则抛出异常
     *
     * @param value          字符串对象
     * @param exceptionClazz 通用异常子类类对象
     * @param params         通用异常子类构造参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkNotBlank(String value, Class<? extends CommonException> exceptionClazz, Object... params) throws CommonException {
        if (isNotBlank(value)) {
            ExceptionUtils.throwCommonException(exceptionClazz, params);
        }
    }

    /**
     * 将对象值转换为字符串
     *
     * @param value 指定对象
     * @return 对象值对应的字符串
     * @since 1.0.0
     */
    public static String valueOf(Object value) {
        return (value == null) ? "" : value.toString();
    }

    /**
     * 将str字符串两边空格去掉
     *
     * @param value 待处理的字符串
     * @return 去除两边空格的字符串
     * @since 1.0.0
     */
    public static String trim(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    /**
     * 清除字符串内容
     *
     * @param value {@code StringBuilder}对象
     * @since 1.0.0
     */
    public static void clear(StringBuilder value) {
        if (ObjectUtils.isNotEmpty(value) && isNotBlank(value.toString())) {
            value.delete(0, value.length());
        }
    }

    /**
     * 首字母转小写
     *
     * @param value 待处理的字符串
     * @return 首字母转小写的字符串
     * @since 1.0.0
     */
    public static String toLowerCaseInitial(String value) {

        if (isBlank(value)) {
            return "";
        }

        if (Character.isLowerCase(value.charAt(0))) {
            return value;
        } else {
            if (CommonConstants.NumeralConstants.INT_ONE == value.length()) {
                return Character.toLowerCase(value.charAt(0)) + "";
            } else {
                return Character.toLowerCase(value.charAt(0)) + value.substring(1);
            }
        }
    }

    /**
     * 首字母转大写
     *
     * @param value 待处理的字符串
     * @return 首字母转大写的字符串
     * @since 1.0.0
     */
    public static String toUpperCaseInitial(String value) {

        if (isBlank(value)) {
            return "";
        }

        if (Character.isUpperCase(value.charAt(0))) {
            return value;
        } else {
            if (CommonConstants.NumeralConstants.INT_ONE == value.length()) {
                return Character.toUpperCase(value.charAt(0)) + "";
            } else {
                return Character.toUpperCase(value.charAt(0)) + value.substring(1);
            }
        }
    }

    /**
     * 首字母转大写，其余字母转小写
     *
     * @param value 待处理字符串
     * @return 首字母转大写，其余字母转小写的字符串
     * @since 1.0.0
     */
    public static String toUpperCaseFirstAndLowerCaseLeft(String value) {
        if (CommonConstants.NumeralConstants.INT_ONE == value.length()) {
            return Character.toUpperCase(value.charAt(0)) + "";
        } else {
            return Character.toUpperCase(value.charAt(0)) + value.substring(1).toLowerCase();
        }
    }

    /**
     * 截取字符串 value中字符串start 和 字符串 end 之间的字符串
     *
     * @param value 待截取的字符串
     * @param start 待截取的字符串中开始处字符串
     * @param end   待截取的字符串中结束处字符串
     * @return 截取后的字符串
     * @since 1.0.0
     */
    public static String subStrBetween(String value, String start, String end) {
        if (value == null || start == null || end == null)
            return null;
        int mStart = value.indexOf(start);
        if (mStart != -1) {
            int mEnd = value.indexOf(end, mStart + start.length());
            if (mEnd != -1)
                return value.substring(mStart + start.length(), mEnd);
        }
        return null;
    }

    /**
     * 获取字符串value的后length位字符串
     * <p>（注意：length若大于value的长度，则返回原字符串value的拷贝值）
     *
     * @param value  待截取的字符串
     * @param length 待截取的长度
     * @return 截取后的字符串
     * @since 1.0.0
     */
    public static String subStrLast(String value, int length) {
        return subStr(value, length, false);
    }

    /**
     * 获取字符串value的前length位字符串
     * <p>（注意：length若大于字符串value的长度，则返回原字符串value的拷贝值）
     *
     * @param value  待截取的字符串
     * @param length 待截取的长度
     * @return 截取后的字符串
     * @since 1.0.0
     */
    public static String subStrBefore(String value, int length) {
        return subStr(value, length, true);
    }

    /**
     * 从头部或从尾部截取字符串
     *
     * @param value    待截取的字符串
     * @param length   待截取的长度
     * @param isBefore 如果true，则从头截取；否则则尾截取字符串
     * @return 截取后的字符串
     * @since 1.0.0
     */
    private static String subStr(String value, int length, boolean isBefore) {
        if (isBlank(value) || length <= 0) {
            return null;
        }
        int strLen = value.length();
        if (strLen < length) {// 字符串长度小于 待截取长度
            return value.substring(0, strLen); // 返回与待截取字符串相同拷贝值的字符串
        }
        if (isBefore) {
            return value.substring(0, length);
        } else {
            return value.substring(value.length() - length);
        }
    }

    /**
     * 字符串连接函数
     * <p>（注意：支持多个字符串之间的连接）
     *
     * @param value  待连接字符串
     * @param others 其他字符串数组
     * @return 依次连接后的字符串
     * @since 1.0.0
     */
    public static String strCat(String value, String... others) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(value);
        for (String otherStr : others) {
            strBuilder.append(otherStr);
        }
        return strBuilder.toString();
    }

    /**
     * 使用占位符连接字符串数组中的元素
     *
     * @param values      字符串数组
     * @param placeholder 占位符
     * @return 拼接后的字符串
     * @since 1.0.0
     */
    public static String strCombined(String[] values, String placeholder) {
        if (ArrayUtils.isEmpty(values)) {
            return "";
        }
        StringBuilder strBuilder = new StringBuilder();
        for (int n = 0; n < values.length; n++) {
            if (n < values.length - 1) {
                strBuilder.append(values[n]).append(placeholder);
            } else {
                strBuilder.append(values[n]);
            }
        }
        return strBuilder.toString();
    }

    /**
     * 拼接对象数组objs中每个对象的属性attrName对应的值
     * <p>（拼接方式： before + value1 + after + before + value2）
     *
     * @param objs     待拼接的对象数组
     * @param attrName 对象数组中每个对象的一个属性名attrName
     * @param before   待拼接字符串（在属性值前）
     * @param after    待拼接字符串（在属性值后）
     * @return 拼接后的字符串
     * @since 1.0.0
     */
    public static String strCombined(Object[] objs, String attrName, String before, String after) {
        if (objs == null || objs.length == 0 || isBlank(attrName)) {
            return "";
        }
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            Object value = ReflectUtils.getObjectAttrValue(objs[i], attrName);
            if (i < objs.length - 1) {
                strBuilder.append(before).append(value).append(after);
            } else {
                strBuilder.append(before).append(value);
            }
        }

        return strBuilder.toString();
    }

    /**
     * 拼接对象数组objs中每个对象的属性attrName对应的值
     * <p>（拼接方式： before + value1 + after + after1 + before + value2 + after）
     *
     * @param objs     待拼接的对象数组
     * @param attrName 对象数组中每个对象的一个属性名attrName
     * @param before   待拼接字符串（在属性值前）
     * @param after    待拼接字符串（在属性值后）
     * @param after1   待拼接字符串（在属性值后）
     * @return 拼接后的字符串
     * @since 1.0.0
     */
    public static String strCombined(Object[] objs, String attrName, String before, String after, String after1) {
        if (objs == null || objs.length == 0 || isBlank(attrName)) {
            return "";
        }
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            Object value = ReflectUtils.getObjectAttrValue(objs[i], attrName);
            if (i < objs.length - 1) {
                strBuilder.append(before).append(value).append(after).append(after1);
            } else {
                strBuilder.append(before).append(value).append(after);
            }
        }

        return strBuilder.toString();
    }

    /**
     * 判断是否满足模糊查询
     * <p>（中文模糊比较，简拼，全拼比较）
     *
     * @param matchedValue  待匹配字符串
     * @param searchedValue 搜索字符串
     * @return 如果找到，则返回true，否则，返回false
     * @since 1.0.0
     */
    public static boolean isFuzzySearch(String matchedValue, String searchedValue) {

        if (matchedValue.contains(searchedValue)) {
            return true;
        }

        String lowerJianPin = PinyinUtils.getJianPin(matchedValue, PinyinEnum.LOWER_CASE.getType());
        String upperJianPin = PinyinUtils.getJianPin(matchedValue, PinyinEnum.UPPER_CASE.getType());
        String lowerQuanPin = PinyinUtils.getQuanPin(matchedValue, PinyinEnum.LOWER_CASE.getType());
        String upperQuanPin = PinyinUtils.getQuanPin(matchedValue, PinyinEnum.UPPER_CASE.getType());

        return lowerJianPin.contains(searchedValue) || lowerJianPin.contains(searchedValue.toLowerCase())
                || upperJianPin.contains(searchedValue) || lowerQuanPin.contains(searchedValue)
                || lowerQuanPin.contains(searchedValue.toLowerCase()) || upperQuanPin.contains(searchedValue);
    }

    /**
     * 在strBuilder字符串中，用字符串value替换占位符placeholder
     *
     * @param strBuilder  待替换字符串
     * @param placeholder 占位符
     * @param value       替换字符串
     * @since 1.0.0
     */
    public static void replace(StringBuilder strBuilder, String placeholder, String value) {
        int start = strBuilder.indexOf(placeholder);
        while (start != -1) {
            // 存在占位符，才用value替换占位符，否则还是保持原样
            strBuilder.replace(start, start + placeholder.length(), value);
            // 取下一个占位符的位置
            start = strBuilder.indexOf(placeholder);
        }
    }

    /**
     * 分割字符串
     *
     * @param value       待分割字符串
     * @param placeholder 占位符
     * @return 分割后的字符串数组
     * @since 1.0.0
     */
    public static String[] split(String value, String placeholder) {
        if (isBlank(value) || isBlank(placeholder)) {
            return null;
        }
        return value.split(placeholder);
    }

    /**
     * 分割字符串数组，生成键值对Map
     *
     * @param values      待分割字符串数组
     * @param placeholder 占位符
     * @return 键值对Map
     * @since 1.0.0
     */
    public static Map<String, String> split(String[] values, String placeholder) {

        if (ArrayUtils.isEmpty(values) || isBlank(placeholder)) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        for (String str : values) {
            String[] ss = split(str, placeholder);
            if (ArrayUtils.isNotEmpty(ss) && ss.length == 2) {
                map.put(trim(ss[0]), trim(ss[1]));
            }
        }
        return map;
    }

    /**
     * 分割字符串
     *
     * @param value        待分割字符串
     * @param placeholders 多个占位符
     * @return 分割后的字符串数组
     * @since 1.0.0
     */
    public static String[] split(String value, String... placeholders) {

        if (isBlank(value) || ArrayUtils.isEmpty(placeholders)) {
            return null;
        }

        StringBuilder phs = new StringBuilder();
        for (int i = 0; i < placeholders.length; i++) {
            if (i < placeholders.length - 1) {
                phs.append(placeholders[i]).append(CommonConstants.SymbolConstants.VERTICAL_LINE);
            } else {
                phs.append(placeholders[i]);
            }
        }

        return value.split(phs.toString());
    }

    /**
     * 获取替换后的真实字符串，替换前的字符串包含{0}、
     * {1} 这样子的占位符，相关值从占位字符串中取。
     *
     * @param before       替换前的字符串
     * @param placeholders 占位字符串
     * @return 替换后的真实字符串
     * @since 2.0.0
     */
    public static String getRealValue(String before, String... placeholders) {
        String after = before;
        if (ArrayUtils.isNotEmpty(placeholders)) {
            StringBuilder builder = new StringBuilder(before);
            for (int i = 0; i < placeholders.length; i++) {
                StringUtils.replace(builder, CommonConstants.SymbolConstants.LEFT_CURLY_BRACE + i +
                        CommonConstants.SymbolConstants.RIGHT_CURLY_BRACE, placeholders[i]);
            }
            after = builder.toString();
        }
        return after;
    }
}
