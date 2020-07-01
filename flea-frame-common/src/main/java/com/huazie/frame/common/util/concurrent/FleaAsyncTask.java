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
public class FleaAsyncTask extends FleaRunnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAsyncTask.class);

    private Object asyncTaskExecObj; // 异步任务执行对象

    private String methodName; // 方法名

    private Class<?>[] paramTypes; // 法参数类型数组

    private Object[] params; // 方法参数数组

    /**
     * <p> 带参数的构造方法 </p>
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAsyncTask##subRun() start");
        }

        try {
            if (isParamCheck()) {
                Method method = asyncTaskExecObj.getClass().getDeclaredMethod(methodName, paramTypes);
                method.invoke(asyncTaskExecObj, params);
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("FleaAsyncTask##subRun() AsyncTask Execute Exception : ", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAsyncTask##subRun() end");
        }
    }

    /**
     * <p> 参数校验 </p>
     *
     * @return true: 校验通过，false: 校验不通过
     */
    private boolean isParamCheck() {

        boolean isCheck = true;

        if (ObjectUtils.isEmpty(asyncTaskExecObj)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("FleaAsyncTask##subRun() asyncTaskExecObj is null, is not allowed");
            }
            isCheck = false;
        }

        if (StringUtils.isBlank(methodName)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("FleaAsyncTask##subRun() methodName is empty, is not allowed");
            }
            isCheck = false;
        }

        if (ArrayUtils.isEmpty(paramTypes)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("FleaAsyncTask##subRun() paramTypes is empty");
            }
        }

        if (ArrayUtils.isEmpty(params)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("FleaAsyncTask##subRun() params is empty");
            }
        }

        return isCheck;
    }

}
