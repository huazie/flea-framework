package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class ReflectUtils {

    public static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(ReflectUtils.class);

    private ReflectUtils() {
    }

    /**
     * 获取类Class
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
            LOGGER.error1(new Object() {}, "Exception : \n", e);
        }
        return clazz;
    }

    /**
     * 获取类实例化后的对象
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
            LOGGER.error1(new Object() {}, "Exception : \n", e);
        }
        return obj;
    }

    /**
     * 获取类实例化后的对象
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
     * 获取类实例化后的对象
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
            LOGGER.error1(new Object() {}, "当前类反射出错，Class={}, Exception={}", className, e);
        }
        return obj;
    }

    /**
     * 获取类实例化后的对象
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
     * 获取类实例化后的对象
     *
     * @param className  类名
     * @param params     构造方法参数
     * @param paramTypes 构造方法参数类型
     * @return 类实例化后的对象
     * @since 1.0.0
     */
    public static Object newInstance(String className, Object[] params, Class<?>[] paramTypes) {
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            if (ArrayUtils.isNotEmpty(params) && ArrayUtils.isNotEmpty(paramTypes) && params.length == paramTypes.length) {
                Constructor<?> constructor = clazz.getConstructor(paramTypes);
                obj = constructor.newInstance(params);
            }
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "当前类反射出错，Class={}, Exception={}", className, e);
        }
        return obj;
    }

    /**
     * 获取类实例化后的对象
     *
     * @param clazz      类对象
     * @param params     构造方法参数
     * @param paramTypes 构造方法参数类型
     * @return 类实例化后的对象
     * @since 1.0.0
     */
    public static Object newInstance(Class<?> clazz, Object[] params, Class<?>[] paramTypes) {
        Object obj = null;
        if (ObjectUtils.isNotEmpty(clazz)) {
            obj = newInstance(clazz.getName(), params, paramTypes);
        }
        return obj;
    }

    /**
     * 设置指定对象的指定私有成员变量的值
     *
     * @param obj   待处理对象
     * @param name  成员变量名
     * @param value 成员变量值
     * @since 1.0.0
     */
    public static void setValue(Object obj, String name, Object value) {
        try {
            // 获取指定的成员变量Field
            Field field = obj.getClass().getDeclaredField(name);
            // 设置true，可以访问私有变量
            field.setAccessible(true);
            // 设置对应成员变量的值
            field.set(obj, value);
        } catch (NoSuchFieldException ex) {
            LOGGER.error1(new Object() {}, "当前对象[{}]无【{}】字段，跳过处理！", obj.getClass().getSimpleName(), ex.getMessage());
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "设置对象指定属性【" + name + "】对应的值出错，Exception=", e);
        }
    }

    /**
     * 获取对象指定属性的值
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
            LOGGER.error1(new Object() {}, "获取对象指定属性【" + attrName + "】对应的值出错，Exception=", e);
        }
        return value;
    }

    /**
     * 获取对象<code>obj</code>中指定<code>attrName</code>属性的get方法
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
            LOGGER.error1(new Object() {}, "获取对象指定属性【" + attrName + "】对应get方法出错，Exception=", e);
        }
        return method;
    }

    /**
     * 反射调用指定对象的指定方法并返回
     *
     * @param method 指定方法
     * @param obj    指定对象
     * @param args   参数列表
     * @return 指定对象的指定方法调用后的返回值
     * @since 2.0.0
     */
    public static Object invoke(Method method, Object obj, Object[] args) {
        Object result = null;
        try {
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "反射调用对象指定方法【" + method.getName() + "】、指定参数列表【" + Arrays.toString(method.getParameterTypes()) + "】出错，Exception = ", e);
            ExceptionUtils.throwException("反射调用对象指定方法【" + method.getName() + "】、指定参数列表【" + Arrays.toString(method.getParameterTypes()) + "】出错", e);
        }
        return result;
    }

    /**
     * 反射调用指定对象的指定方法并返回
     *
     * @param obj        对象实例
     * @param methodName 方法名
     * @param args       参数列表
     * @param types      参数Class类型列表
     * @return 指定对象的指定方法调用后的返回值
     * @since 1.0.0
     */
    public static Object invoke(Object obj, String methodName, Object[] args, Class<?>[] types) {
        Object result = null;
        try {
            Method method = obj.getClass().getMethod(methodName, types);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "反射调用对象指定方法【" + methodName + "】、指定参数列表【" + Arrays.toString(types) + "】出错，Exception = ", e);
            ExceptionUtils.throwException("反射调用对象指定方法【" + methodName + "】、指定参数列表【" + Arrays.toString(types) + "】出错", e);
        }
        return result;
    }

    /**
     * 反射调用指定对象指定方法并返回
     *
     * @param obj        对象实例
     * @param methodName 方法名
     * @param inputObj   入参对象
     * @param inputClazz 入参对象Class
     * @return 指定对象的指定方法调用后的返回值
     * @since 1.0.0
     */
    public static Object invoke(Object obj, String methodName, Object inputObj, Class inputClazz) {
        Object outputObj = null;
        try {
            Method method = obj.getClass().getMethod(methodName, inputClazz);
            outputObj = method.invoke(obj, inputObj);
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "反射调用对象指定方法【" + methodName + "】出错，Exception = ", e);
            ExceptionUtils.throwException("反射调用对象指定方法【" + methodName + "】出错", e);
        }
        return outputObj;
    }

}
