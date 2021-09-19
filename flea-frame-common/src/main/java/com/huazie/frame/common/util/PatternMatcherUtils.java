package com.huazie.frame.common.util;

import java.util.regex.Pattern;

/**
 * <p> 模式匹配工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PatternMatcherUtils {

    private PatternMatcherUtils() {
    }

    /**
     * <p> 正则匹配 </p>
     *
     * @param regex 正则表达式
     * @param input 匹配对象
     * @param flags 匹配标识
     * @return true：匹配; false：不匹配
     */
    public static boolean matches(String regex, CharSequence input, int flags) {
        return Pattern.compile(regex, flags).matcher(input).matches();
    }

}
