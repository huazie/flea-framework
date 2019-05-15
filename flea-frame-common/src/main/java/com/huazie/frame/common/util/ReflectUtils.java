package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * <p> 反射工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReflectUtils {

    public final static Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * <p> 获取类实例对象 </p>
     *
     * @param className 类名
     * @return 实例化的类对象
     * @since 1.0.0
     */
    public static Object newInstance(String className) {
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            obj = clazz.newInstance();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("当前类反射出错，Class={}, Exception={}", className, e);
            }
        }
        return obj;
    }

    /**
     * <p> 获取对象指定属性的值 </p>
     *
     * @param obj      待取值对象
     * @param attrName 对象的一个属性
     * @return 返回attrName属性对应的值
     * @since 1.0.0
     */
    public static Object getObjectAttrValue(Object obj, String attrName) {
        Object value = null;
        try {
            Method method = getObjectAttrMethod(obj, attrName);
            value = method.invoke(obj, new Object[]{});
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取对象指定属性值出错，Exception=", e);
            }
        }
        return value;
    }

    /**
     * <p> 获取对象<code>obj</code>中指定<code>attrName</code>属性的get方法 </p>
     *
     * @param obj      指定对象
     * @param attrName 指定属性变量名
     * @return <code>Method</code>对象
     * @since 1.0.0
     */
    public static Method getObjectAttrMethod(Object obj, String attrName) {
        Method method = null;
        try {
            String getter = CommonConstants.MethodConstants.GET + StringUtils.toUpperCaseInitial(attrName); // 属性的get方法名
            method = obj.getClass().getMethod(getter, new Class[]{});
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取对象指定属性对应get方法出错，Exception=", e);
            }
        }
        return method;
    }

}
