package com.huazie.fleaframework.auth.base.audit.util;

import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Flea 授权审计日志字段对比工具
 *
 * <p> 用于生成变更类审计日志的备注内容：对比变更前实体与变更入参 POJO，
 * 输出「字段标签：旧值→新值」形式的变更描述，如
 * {@code 昵称：张三→李四；账户状态：1→2}。 </p>
 *
 * <p> null 视为未提交（跳过），空串视为显式清空（参与对比，展示为「→空」），
 * 与框架 {@code POJOUtils.copyNonNull} 的合并非 null 字段语义保持一致；
 * 无差异时返回 {@code null}，可直接作为审计日志备注。 </p>
 *
 * <p> 敏感字段（如密码）不应参与对比，由调用方单独体现「是否修改」，
 * 避免具体值落入审计日志。 </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public final class FleaAuthAuditDiffUtils {

    private FleaAuthAuditDiffUtils() {
    }

    /**
     * <p> 对比新旧对象在指定字段上的差异，生成变更描述 </p>
     *
     * @param oldObj      变更前对象（实体）
     * @param newObj      变更入参对象（POJO），字段值为空视为未修改
     * @param fieldLabels 参与对比的字段标签（key：属性名，value：展示名，有序）
     * @return 变更描述，如 {@code 昵称：张三→李四；账户状态：1→2}；无差异时返回 {@code null}
     * @since 2.0.0
     */
    public static String diff(Object oldObj, Object newObj, Map<String, String> fieldLabels) {
        if (oldObj == null || newObj == null || fieldLabels == null || fieldLabels.isEmpty()) {
            return null;
        }

        Map<String, PropertyDescriptor> oldDescriptors = getPropertyDescriptors(oldObj.getClass());
        Map<String, PropertyDescriptor> newDescriptors = getPropertyDescriptors(newObj.getClass());

        StringBuilder changeDesc = new StringBuilder();
        for (Map.Entry<String, String> labelEntry : fieldLabels.entrySet()) {
            String propertyName = labelEntry.getKey();

            Object newVal = readProperty(newDescriptors, propertyName, newObj);
            // null 视为未提交，跳过；空串为显式清空，参与对比
            if (newVal == null) {
                continue;
            }

            Object oldVal = readProperty(oldDescriptors, propertyName, oldObj);
            if (newVal.equals(oldVal)) {
                continue;
            }
            // 库中本就为空（null），提交空串清空不算变更，避免「空→空」噪音
            if (oldVal == null && newVal instanceof String && StringUtils.isBlank((String) newVal)) {
                continue;
            }

            if (changeDesc.length() > 0) {
                changeDesc.append("；");
            }
            changeDesc.append(labelEntry.getValue()).append("：")
                    .append(formatValue(oldVal)).append("→").append(formatValue(newVal));
        }

        return changeDesc.length() > 0 ? changeDesc.toString() : null;
    }

    /**
     * <p> 解析指定类的可读属性描述器（按声明顺序） </p>
     *
     * @param clazz 目标类型
     * @return 属性名与属性描述器的映射；解析失败时返回已解析部分（可能为空）
     * @since 2.0.0
     */
    private static Map<String, PropertyDescriptor> getPropertyDescriptors(Class<?> clazz) {
        Map<String, PropertyDescriptor> descriptors = new LinkedHashMap<String, PropertyDescriptor>();
        try {
            for (PropertyDescriptor descriptor : Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors()) {
                if (descriptor.getReadMethod() != null) {
                    descriptors.put(descriptor.getName(), descriptor);
                }
            }
        } catch (IntrospectionException e) {
            // 解析失败时返回已解析部分，调用方按无差异处理
        }
        return descriptors;
    }

    /**
     * <p> 读取对象指定属性值，读取失败视为空 </p>
     *
     * @param descriptors  属性描述器映射
     * @param propertyName 属性名
     * @param obj          目标对象
     * @return 属性值；不可读取时返回 {@code null}
     * @since 2.0.0
     */
    private static Object readProperty(Map<String, PropertyDescriptor> descriptors, String propertyName, Object obj) {
        PropertyDescriptor descriptor = descriptors.get(propertyName);
        if (descriptor == null) {
            return null;
        }

        Method readMethod = descriptor.getReadMethod();
        if (readMethod == null) {
            return null;
        }

        try {
            return readMethod.invoke(obj);
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
    }

    /**
     * <p> 格式化属性值用于展示，日期使用默认格式，空值展示为「空」 </p>
     *
     * @param value 属性值
     * @return 展示值
     * @since 2.0.0
     */
    private static String formatValue(Object value) {
        if (value == null) {
            return "空";
        }
        if (value instanceof Date) {
            return DateUtils.date2String((Date) value);
        }
        if (value instanceof String && StringUtils.isBlank((String) value)) {
            return "空";
        }
        return String.valueOf(value);
    }
}
