package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.exceptions.FleaException;
import com.huazie.fleaframework.common.exceptions.MethodSignatureNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Flea切面工具类
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaAspectUtils {

    /**
     * 从连接点中获取对应的方法对象，如果当前连接点的签名不是方法签名，则抛出异常
     *
     * @param joinPoint 连接点对象
     * @return 方法连接点对应的Method对象
     * @throws FleaException         Flea异常
     * @throws NoSuchMethodException 方法找不到异常
     * @since 1.2.0
     */
    public static Method getTargetMethod(ProceedingJoinPoint joinPoint) throws FleaException, NoSuchMethodException {
        // 获取目标方法所在类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 获取连接点处的签名
        Signature signature = joinPoint.getSignature();
        Method method = null;
        if (ObjectUtils.isNotEmpty(signature) && signature instanceof MethodSignature) {
            method = targetClass.getMethod(signature.getName(), ((MethodSignature) signature).getParameterTypes());
        } else {
            ExceptionUtils.throwFleaException(MethodSignatureNotFoundException.class, "当前连接点的签名不是方法签名！");
        }
        return method;
    }
}
