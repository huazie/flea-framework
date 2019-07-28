package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.PinyinEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 字符串相关的工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringUtils {

    /**
     * <p> 判断字符串是否为空 </p>
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
     * <p> 判断字符串是否不为空 </p>
     *
     * @param value 待检查字符串
     * @return 如果不为空且不是空白字符，返回true; 否则返回false
     * @since 1.0.0
     */
    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    /**
     * <p> 将对象值转换为字符串 </p>
     *
     * @param value 指定对象
     * @return 对象值对应的字符串
     * @since 1.0.0
     */
    public static String valueOf(Object value) {
        return (value == null) ? "" : value.toString();
    }

    /**
     * <p> 将str字符串两边空格去掉 </p>
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
     * <p> 清除字符串内容 </p>
     *
     * @param value {@code StringBuilder}对象
     * @since 1.0.0
     */
    public static void clear(StringBuilder value) {
        if (ObjectUtils.isNotEmpty(value) && StringUtils.isNotBlank(value.toString())) {
            value.delete(0, value.length());
        }
    }

    /**
     * <p> 首字母转小写 </p>
     *
     * @param value 待处理的字符串
     * @return 首字母转小写的字符串
     * @since 1.0.0
     */
    public static String toLowerCaseInitial(String value) {
        if (Character.isLowerCase(value.charAt(0)))
            return value;
        else
            return (new StringBuilder()).append(Character.toLowerCase(value.charAt(0))).append(value.substring(1)).toString();
    }

    /**
     * <p> 首字母转大写 </p>
     *
     * @param value 待处理的字符串
     * @return 首字母转大写的字符串
     * @since 1.0.0
     */
    public static String toUpperCaseInitial(String value) {
        if (Character.isUpperCase(value.charAt(0)))
            return value;
        else
            return (new StringBuilder()).append(Character.toUpperCase(value.charAt(0))).append(value.substring(1)).toString();
    }

    /**
     * <p> 截取字符串 value中字符串start 和 字符串 end 之间的字符串 </p>
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
     * <p> 获取字符串value的后length位字符串 </p>
     * <p>（注意：length若大于value的长度，则返回原字符串value的拷贝值） </p>
     *
     * @param value  待截取的字符串
     * @param length 待截取的长度
     * @return 截取后的字符串
     * @since 1.0.0
     */
    public static String subStrLast(String value, int length) {
        return subStr(value, length, Boolean.FALSE.booleanValue());
    }

    /**
     * <p>获取字符串value的前length位字符串</p>
     * <p>（注意：length若大于字符串value的长度，则返回原字符串value的拷贝值）</p>
     *
     * @param value  待截取的字符串
     * @param length 待截取的长度
     * @return 截取后的字符串
     * @since 1.0.0
     */
    public static String subStrBefore(String value, int length) {
        return subStr(value, length, Boolean.TRUE.booleanValue());
    }

    /**
     * <p> 从头部或从尾部截取字符串 </p>
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
     * <p> 字符串连接函数 </p>
     * <p>（注意：支持多个字符串之间的连接）</p>
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
     * <p> 拼接对象数组objs中每个对象的属性attrName对应的值 </p>
     * <p>（拼接方式： before + value1 + after + before + value2）</p>
     *
     * @param objs     待拼接的对象数组
     * @param attrName 对象数组中每个对象的一个属性名attrName
     * @param before   待拼接字符串（在属性值前）
     * @param after    待拼接字符串（在属性值后）
     * @return 拼接后的字符串
     * @since 1.0.0
     */
    public static String strCombined(Object[] objs, String attrName, String before, String after) {
        StringBuilder strBuilder = new StringBuilder();
        if (objs == null || objs.length == 0 || isBlank(attrName)) {
            return null;
        }
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
     * <p>判断是否满足模糊查询/p>
     * <p>（中文模糊比较，简拼，全拼比较）</p>
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

        if (lowerJianPin.contains(searchedValue) || lowerJianPin.contains(searchedValue.toLowerCase())
                || upperJianPin.contains(searchedValue) || lowerQuanPin.contains(searchedValue)
                || lowerQuanPin.contains(searchedValue.toLowerCase()) || upperQuanPin.contains(searchedValue)) {
            return true;
        }

        return false;
    }

    /**
     * <p> 在strBuilder字符串中，用字符串value替换占位符placeholder </p>
     *
     * @param strBuilder  待替换字符串
     * @param placeholder 占位符
     * @param value       替换字符串
     * @since 1.0.0
     */
    public static void replace(StringBuilder strBuilder, String placeholder, String value) {
        int start = strBuilder.indexOf(placeholder);
        if (start != -1) {// 存在占位符，才用value替换占位符，否则还是保持原样
            strBuilder.replace(start, start + placeholder.length(), value);
        }
    }

    /**
     * <p> 分割字符串 </p>
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
     * <p> 分割字符串数组，生成键值对Map </p>
     *
     * @param values      待分割字符串数组
     * @param placeholder 占位符
     * @return 键值对Map
     * @since 1.0.0
     */
    public static Map<String, String> split(String[] values, String placeholder) {
        Map<String, String> map = null;
        if (ArrayUtils.isEmpty(values) || isBlank(placeholder)) {
            return map;
        }
        map = new HashMap<>();
        for (String str : values) {
            String[] ss = split(str, placeholder);
            if (ss.length == 2) {
                map.put(trim(ss[0]), trim(ss[1]));
            }
        }
        return map;
    }

    /**
     * <p> 分割字符串 </p>
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
}
