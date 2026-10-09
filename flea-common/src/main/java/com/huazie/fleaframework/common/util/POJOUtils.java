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

    /**
     * 将源对象非 null 的数据拷贝到目标对象中
     *
     * <p> 与 {@link #copyNotEmpty(Object, Object)} 的区别：空字符串 {@code ""} 不再被跳过，
     * 而是作为显式提交的「清空」操作覆盖目标对象对应属性；仅 {@code null} 视为未提交。 </p>
     *
     * <p> 适用于变更场景需要支持「原值清空」的链路，如数据修改。 </p>
     *
     * @param source 源对象
     * @param target 目标对象
     * @since 2.0.0
     */
    public static void copyNonNull(Object source, Object target) {
        if (ObjectUtils.isEmpty(source) || ObjectUtils.isEmpty(target)) return;
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());
        List<String> ignoreProperties = null;
        for (PropertyDescriptor sourcePd : sourcePds) {
            Method readMethod = sourcePd.getReadMethod();
            if (ReflectUtils.invoke(readMethod, source, null) == null) {
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
