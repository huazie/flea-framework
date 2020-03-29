package com.huazie.frame.common.util;

import com.huazie.frame.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * <p> 反射工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReflectUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    /**
     * <p> 获取类Class </p>
     *
     * @param className 类全名字符串
     * @return 类Class
     * @since 1.0.0
     */
    public static Class forName(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("ReflectUtils##forName(String) Class={}, Exception={}", className, e.getMessage());
            }
        }
        return clazz;
    }

    /**
     * <p> 获取类实例化后的对象 </p>
     *
     * @param className 类全名
     * @return 类实例化后的对象
     * @since 1.0.0
     */
    public static Object newInstance(String className) {
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            obj = clazz.newInstance();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("ReflectUtils##forName(String) Class={}, Exception={}", className, e.getMessage());
            }
        }
        return obj;
    }

    /**
     * <p> 获取类实例化后的对象 </p>
     *
     * @param clazz 类对象
     * @return 类实例化后的对象
     * @since 1.0.0
     */
    public static Object newInstance(Class<?> clazz) {
        Object obj = null;
        if (ObjectUtils.isNotEmpty(clazz)) {
            obj = newInstance(clazz.getName());
        }
        return obj;
    }

    /**
     * <p> 获取类实例化后的对象 </p>
     *
     * @param className 类名
     * @param params    构造方法参数
     * @return 类实例化后的对象
     * @since 1.0.0
     */
    public static Object newInstance(String className, Object... params) {
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            if (ArrayUtils.isNotEmpty(params)) {
                Class<?>[] paramClazzArr = new Class<?>[params.length];
                for (int i = 0; i < params.length; i++) {
                    paramClazzArr[i] = params[i].getClass();
                }
                Constructor<?> constructor = clazz.getConstructor(paramClazzArr);
                obj = constructor.newInstance(params);
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("当前类反射出错，Class={}, Exception={}", className, e.getMessage());
            }
        }
        return obj;
    }

    /**
     * <p> 获取类实例化后的对象 </p>
     *
     * @param clazz  类对象
     * @param params 构造方法参数
     * @return 类实例化后的对象
     * @since 1.0.0
     */
    public static Object newInstance(Class<?> clazz, Object... params) {
        Object obj = null;
        if (ObjectUtils.isNotEmpty(clazz)) {
            obj = newInstance(clazz.getName(), params);
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
            value = method.invoke(obj);
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
            method = obj.getClass().getMethod(getter);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取对象指定属性对应get方法出错，Exception=", e);
            }
        }
        return method;
    }

}
