package com.huazie.frame.common.util;

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
            if (Integer.class.isInstance(value)) {
                number = Integer.valueOf(StringUtils.valueOf(value));
            } else if (Long.class.isInstance(value)) {
                number = Long.valueOf(StringUtils.valueOf(value));
            } else if (Short.class.isInstance(value)) {
                number = Short.valueOf(StringUtils.valueOf(value));
            } else if (Float.class.isInstance(value)) {
                number = Float.valueOf(StringUtils.valueOf(value));
            } else if (Double.class.isInstance(value)) {
                number = Double.valueOf(StringUtils.valueOf(value));
            } else if (Byte.class.isInstance(value)) {
                number = Byte.valueOf(StringUtils.valueOf(value));
            }
        }
        return number;
    }

}
