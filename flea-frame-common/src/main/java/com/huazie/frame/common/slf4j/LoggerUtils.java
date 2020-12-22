package com.huazie.frame.common.slf4j;

import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
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
                StringBuilder typeBuilder = new StringBuilder();
                for (int i = 0; i < types.length; i++) {
                    if (i < types.length - 1) {
                        typeBuilder.append(types[i].getName()).append(", ");
                    } else {
                        typeBuilder.append(types[i].getName());
                    }
                }
                // 方法参数名
                MDC.put(MDC_KEY_MPN, typeBuilder.toString());
            }
            // 方法名
            MDC.put(MDC_KEY_MN, method.getName());
            // 类全名
            MDC.put(MDC_KEY_CFN, method.getDeclaringClass().getName());
        }
    }
}
