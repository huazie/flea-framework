package com.huazie.fleaframework.common.util.concurrent;

import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Flea异步任务
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAsyncTask extends FleaRunnable {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAsyncTask.class);

    private Object asyncTaskExecObj; // 异步任务执行对象

    private String methodName; // 方法名

    private Class<?>[] paramTypes; // 法参数类型数组

    private Object[] params; // 方法参数数组

    /**
     * 带参数的构造方法
     *
     * @param fleaUser         Flea用户信息
     * @param asyncTaskExecObj 异步任务执行对象
     * @param methodName       方法名
     * @param paramTypes       方法参数类型数组
     * @param params           方法参数数组
     * @since 1.0.0
     */
    public FleaAsyncTask(IFleaUser fleaUser, Object asyncTaskExecObj, String methodName, Class<?>[] paramTypes, Object[] params) {
        super(fleaUser);
        this.asyncTaskExecObj = asyncTaskExecObj;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    @Override
    protected void subRun() {
        LOGGER.debug("Start");

        try {
            if (isParamCheck()) {
                Method method = asyncTaskExecObj.getClass().getDeclaredMethod(methodName, paramTypes);
                method.invoke(asyncTaskExecObj, params);
            }
        } catch (Exception e) {
            LOGGER.error("AsyncTask Execute Exception : ", e);
        }

        LOGGER.debug("End");
    }

    /**
     * 参数校验
     *
     * @return true: 校验通过，false: 校验不通过
     */
    private boolean isParamCheck() {

        boolean isCheck = true;

        if (ObjectUtils.isEmpty(asyncTaskExecObj)) {
            LOGGER.debug("asyncTaskExecObj is null, is not allowed");
            isCheck = false;
        }

        if (StringUtils.isBlank(methodName)) {
            LOGGER.debug("methodName is empty, is not allowed");
            isCheck = false;
        }

        if (ArrayUtils.isEmpty(paramTypes)) {
            LOGGER.debug("paramTypes is empty");
        }

        if (ArrayUtils.isEmpty(params)) {
            LOGGER.debug("params is empty");
        }

        return isCheck;
    }

}
