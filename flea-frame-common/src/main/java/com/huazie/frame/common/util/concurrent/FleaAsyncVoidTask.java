package com.huazie.frame.common.util.concurrent;

import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * <p> Flea异步任务 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAsyncVoidTask extends FleaRecursiveAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAsyncVoidTask.class);

    private Object asyncTaskExecObj; // 异步任务执行对象

    private String methodName; // 方法名

    private Class<?>[] paramTypes; // 法参数类型数组

    private Object[] params; // 方法参数数组

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param fleaUser    Flea用户信息
     * @param asyncTaskExecObj 异步任务执行对象
     * @param methodName 方法名
     * @param paramTypes 方法参数类型数组
     * @param params  方法参数数组
     * @since 1.0.0
     */
    public FleaAsyncVoidTask(IFleaUser fleaUser, Object asyncTaskExecObj, String methodName, Class<?>[] paramTypes, Object[] params) {
        super(fleaUser);
        this.asyncTaskExecObj = asyncTaskExecObj;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    @Override
    protected void subCompute() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthLogTask##subCompute() start");
        }

        if (ObjectUtils.isEmpty(asyncTaskExecObj)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AuthLogTask##subCompute() asyncTaskExecObj is null");
            }
            return;
        }

        if (StringUtils.isBlank(methodName)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AuthLogTask##subCompute() methodName is empty");
            }
            return;
        }

        if (ArrayUtils.isEmpty(paramTypes)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AuthLogTask##subCompute() paramTypes is empty");
            }
            return;
        }

        if (ArrayUtils.isEmpty(params)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AuthLogTask##subCompute() params is empty");
            }
            return;
        }

        try {
            Method method = asyncTaskExecObj.getClass().getDeclaredMethod(methodName, paramTypes);
            LOGGER.debug("IFleaAuthSV = {}", method);
            LOGGER.debug("Object = {}", asyncTaskExecObj);
            LOGGER.debug("Params = {}", params);
            method.invoke(asyncTaskExecObj, params);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("AuthLogTask##subCompute() AsyncTask Execute Exception : ", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthLogTask##subCompute() end");
        }
    }
}
