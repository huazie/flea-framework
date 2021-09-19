package com.huazie.frame.common.util;

/**
 * <p> POJO工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class POJOUtils {

    private POJOUtils() {
    }

    /**
     * <p> 将POJO类数据拷贝到实体类中 </p>
     *
     * @param pojo   POJO类
     * @param entity 实体类
     * @since 1.0.0
     */
    public static void copyPOJOToEntity(Object pojo, Object entity) {
        if (ObjectUtils.isEmpty(pojo) || ObjectUtils.isEmpty(entity)) {
            return;
        }

        // 获取POJO类的变量
        

    }

}
