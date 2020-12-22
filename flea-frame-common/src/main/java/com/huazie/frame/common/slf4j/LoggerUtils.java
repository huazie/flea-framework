package com.huazie.frame.common.slf4j;

import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.slf4j.MDC;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 日志工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoggerUtils {

    public static final String MDC_KEY_MN = "METHOD_NAME";

    public static final String MDC_KEY_MPN = "METHOD_PARAM_NAME";

    public static final String MDC_KEY_CFN = "CLASS_FULL_NAME";

    /**
     * <p> 添加当前日志打印的所在类的方法相关信息【方法名和方法参数名】至日志上下文MDC中 </p>
     *
     * @param obj Object对象实例, obj = new Object() {}
     * @since 1.0.0
     */
    public static void addMethodMDC(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return;
        }
        Method method = obj.getClass().getEnclosingMethod();
        if (ObjectUtils.isNotEmpty(method)) {
            Class<?>[] types = method.getParameterTypes();
            if (ArrayUtils.isNotEmpty(types)) {
                // 方法参数名
                MDC.put(MDC_KEY_MPN, StringUtils.strCombined(getParameterTypeNames(types), ", "));
            }
            // 方法名
            MDC.put(MDC_KEY_MN, method.getName());
            // 类全名
            MDC.put(MDC_KEY_CFN, method.getDeclaringClass().getName());
        }
    }

    /**
     * <p> 获取参数类型全名字符串数组 </p>
     *
     * @param types 参数类型Class对象
     * @return 参数类型全名字符串数组
     * @since 1.0.0
     */
    private static String[] getParameterTypeNames(Class<?>[] types) {
        if (ArrayUtils.isNotEmpty(types)) {
            List<String> paramTypeList = new ArrayList<>();
            for (Class<?> type : types) {
                paramTypeList.add(type.getName());
            }
            return paramTypeList.toArray(new String[0]);
        }
        return null;
    }
}
