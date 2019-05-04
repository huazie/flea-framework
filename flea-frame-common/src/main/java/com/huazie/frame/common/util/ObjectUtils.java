package com.huazie.frame.common.util;

/**
 * <p> Object工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class ObjectUtils {
    /**
     * <p> 判断value是否为空 </p>
     *
     * @param value 校验的对象
     * @return 如果为空或null，返回true；否则返回false
     * @since 1.0.0
     */
    public static boolean isEmpty(Object value) {
        if (value instanceof String) {
            return null == value || "".equals(value);
        }
        return null == value;
    }

    /**
     * <p> 判断value是否为空 </p>
     *
     * @param value 校验的对象
     * @return 如果不为空或null，返回true；否则返回false
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }
}
