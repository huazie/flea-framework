package com.huazie.fleaframework.common.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class POJOUtils {

    private POJOUtils() {
    }

    /**
     * 将源对象所有数据拷贝到目标对象中
     *
     * @param source 源对象
     * @param target 目标对象
     * @since 2.0.0
     */
    public static void copyAll(Object source, Object target) {
        if (ObjectUtils.isEmpty(source) || ObjectUtils.isEmpty(target)) return;
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 将源对象非空的数据拷贝到目标对象中
     *
     * @param source 源对象
     * @param target 目标对象
     * @since 2.0.0
     */
    public static void copyNotEmpty(Object source, Object target) {
        if (ObjectUtils.isEmpty(source) || ObjectUtils.isEmpty(target)) return;
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());
        List<String> ignoreProperties = null;
        for (PropertyDescriptor sourcePd : sourcePds) {
            Method readMethod = sourcePd.getReadMethod();
            if (ObjectUtils.isEmpty(ReflectUtils.invoke(readMethod, source, null))) {
                if (ObjectUtils.isEmpty(ignoreProperties)) {
                    ignoreProperties = new ArrayList<>();
                }
                ignoreProperties.add(sourcePd.getName());
            }
        }
        if (ObjectUtils.isEmpty(ignoreProperties))
            BeanUtils.copyProperties(source, target);
        else
            BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[0]));
    }

}
