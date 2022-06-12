package com.huazie.fleaframework.common.slf4j;

import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import org.slf4j.MDC;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoggerUtils {

    private LoggerUtils() {
    }

    /**
     * 清理日志上下文数据
     *
     * @since 1.0.0
     */
    public static void clearMethodMDC() {
        // 方法名
        MDC.remove(FleaLogger.MDC_KEY_MN);
        // 方法参数
        MDC.remove(FleaLogger.MDC_KEY_MPN);
        // 类全名
        MDC.remove(FleaLogger.MDC_KEY_CFN);
        // 代码行数
        MDC.remove(FleaLogger.MDC_KEY_LN);
        // 源文件名
        MDC.remove(FleaLogger.MDC_KEY_FN);
        // 输出日志事件的发生位置
        MDC.remove(FleaLogger.MDC_KEY_LOC);
    }

    /**
     * 添加当前日志打印的所在类的方法相关信息至日志上下文MDC中
     *
     * @param elements 当前线程的堆栈元素
     * @param obj      Object对象实例, obj = new Object() {}
     * @since 1.0.0
     */
    public static void addMethodMDC(StackTraceElement[] elements, int position, Object obj) {
        // 默认用指定堆栈元素的信息初始化 当前日志打印的所在类的方法相关信息【方法名，方法参数名，类名，代码行数】
        addMethodMDC(elements, position);
        if (ObjectUtils.isNotEmpty(obj)) {
            Method method = obj.getClass().getEnclosingMethod();
            if (ObjectUtils.isNotEmpty(method)) {
                // 处理调用方基础类的立即封闭方法的对象，获取该方法相关信息，并设置到日志上下文MDC中
                addMethodMDC(obj.getClass().getEnclosingMethod());
            }
        }
        // 输出日志事件的发生位置
        addLocationMDC();
    }

    /**
     * 添加当前日志打印的所在类的方法相关信息至日志上下文MDC中
     *
     * @param elements 当前线程的堆栈元素
     * @param position 堆栈元素指定的位置
     * @since 1.0.0
     */
    public static void addMethodMDC(StackTraceElement[] elements, int position) {
        if (position < 0 || position >= elements.length) {
            return;
        }
        addMethodMDC(elements[position]);
        // 输出日志事件的发生位置
        addLocationMDC();
    }

    /**
     * 获取调用方基础类的立即封闭方法的对象的相关信息，并设置到日志上下文MDC中
     *
     * @param method 调用方基础类的立即封闭方法的对象
     * @since 1.0.0
     */
    private static void addMethodMDC(Method method) {
        Class<?>[] types = method.getParameterTypes();
        if (ArrayUtils.isNotEmpty(types)) {
            // 方法参数名
            MDC.put(FleaLogger.MDC_KEY_MPN, StringUtils.strCombined(getParameterTypeNames(types), ", "));
        }
    }

    /**
     * 处理指定堆栈元素，从中获取堆栈元素所属类相关信息，并设置到日志上下文MDC中
     *
     * @param element 堆栈元素
     */
    private static void addMethodMDC(StackTraceElement element) {
        if (ObjectUtils.isNotEmpty(element)) {
            // 方法名
            MDC.put(FleaLogger.MDC_KEY_MN, element.getMethodName());
            // 类全名
            MDC.put(FleaLogger.MDC_KEY_CFN, element.getClassName());
            // 源文件名
            MDC.put(FleaLogger.MDC_KEY_FN, element.getFileName());
            // 代码行数
            MDC.put(FleaLogger.MDC_KEY_LN, StringUtils.valueOf(element.getLineNumber()));
        }
    }

    /**
     * 输出日志事件的发生位置
     *
     * @since 1.0.0
     */
    private static void addLocationMDC() {
        // 输出日志事件的发生位置
        String builder = MDC.get(FleaLogger.MDC_KEY_CFN) + "##" + MDC.get(FleaLogger.MDC_KEY_MN) + "(" +
                (MDC.get(FleaLogger.MDC_KEY_MPN) == null ? "" : MDC.get(FleaLogger.MDC_KEY_MPN)) + ")(" +
                MDC.get(FleaLogger.MDC_KEY_FN) + ":" + MDC.get(FleaLogger.MDC_KEY_LN) + ")";
        MDC.put(FleaLogger.MDC_KEY_LOC, builder);
    }

    /**
     * 获取参数类型全名字符串数组
     *
     * @param types 参数类型Class对象
     * @return 参数类型全名字符串数组
     * @since 1.0.0
     */
    private static String[] getParameterTypeNames(Class<?>[] types) {
        if (ArrayUtils.isNotEmpty(types)) {
            List<String> paramTypeList = new ArrayList<>();
            for (Class<?> type : types) {
                paramTypeList.add(type.getSimpleName());
            }
            return paramTypeList.toArray(new String[0]);
        }
        return null;
    }
}
