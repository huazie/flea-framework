package com.huazie.frame.common.util;

import java.util.Collection;

/**
 * <p> Collection集合处理工具类  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CollectionUtils {

    /**
     * <p> 判断Collection集合是否空（Collection集合为null 或 无数据） </p>
     *
     * @param collection Collection集合
     * @return true : Collection集合为空; false : Collection集合非空
     * @since 1.0.0
     */
    public static boolean isEmpty(Collection collection) {
        if (null == collection || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * <p> 判断Collection集合是否非空（Collection集合存在数据） </p>
     *
     * @param collection Collection集合
     * @return true : Collection集合非空; false : Collection集合为空
     * @since 1.0.0
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

}
