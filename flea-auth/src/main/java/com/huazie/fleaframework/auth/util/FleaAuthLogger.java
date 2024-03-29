package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.util.concurrent.FleaAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Flea 权限日志工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthLogger {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final String METHOD_SAVE_LOGIN_LOG = "saveLoginLog";
    private static final String METHOD_SAVE_QUIT_LOG = "saveQuitLog";

    private FleaAuthLogger() {
    }

    /**
     * 异步保存登录日志
     *
     * @param asyncTaskExecObj 异步任务执行类
     * @param accountId        账户编号
     * @param request          HTTP请求对象
     * @since 1.0.0
     */
    public static void asyncSaveLoginLog(Object asyncTaskExecObj, Long accountId, HttpServletRequest request) {
        Class<?>[] paramTypes = {accountId.getClass(), HttpServletRequest.class};
        Object[] params = {accountId, request};
        FleaAsyncTask fleaAsyncTask = new FleaAsyncTask(FleaSessionManager.getUserInfo(), asyncTaskExecObj, METHOD_SAVE_LOGIN_LOG, paramTypes, params);
        executorService.execute(fleaAsyncTask);
    }

    /**
     * 异步保存登出日志
     *
     * @param asyncTaskExecObj 异步任务执行类
     * @param accountId        账户编号
     * @since 1.0.0
     */
    public static void asyncSaveQuitLog(Object asyncTaskExecObj, Long accountId) {
        Class<?>[] paramTypes = {accountId.getClass()};
        Object[] params = {accountId};
        FleaAsyncTask fleaAsyncTask = new FleaAsyncTask(FleaSessionManager.getUserInfo(), asyncTaskExecObj, METHOD_SAVE_QUIT_LOG, paramTypes, params);
        executorService.execute(fleaAsyncTask);
    }
}
