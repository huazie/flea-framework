package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.object.FleaObjectFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.concurrent.FleaAsyncTask;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Flea授权异步任务
 *
 * <p> 内部使用 {@link FleaAsyncTask} 实现授权模块相关的异步操作 </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaAuthAsyncTask {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static final String METHOD_INIT_USER_INFO = "initUserInfo";

    /**
     * 私有构造方法，工具类不需要实例化
     *
     * @since 2.0.0
     */
    private FleaAuthAsyncTask() {
    }

    /**
     * 异步初始化用户信息
     *
     * <p> 内部调用 {@link com.huazie.fleaframework.auth.common.service.impl.FleaUserModuleSVImpl#initUserInfo(Long, Long, Map, FleaObjectFactory)} 方法 </p>
     *
     * @param asyncTaskExecObj  异步任务执行对象（FleaUserModuleSVImpl实例）
     * @param accountId         账户编号
     * @param systemAccountId   系统账户编号
     * @param otherAttrs        其他属性信息
     * @param fleaObjectFactory 用户信息对象工厂类
     * @since 2.0.0
     */
    public static void asyncInitUserInfo(Object asyncTaskExecObj, Long accountId, Long systemAccountId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) {
        Class<?>[] paramTypes = {Long.class, Long.class, Map.class, FleaObjectFactory.class};
        Object[] params = {accountId, systemAccountId, otherAttrs, fleaObjectFactory};
        FleaAsyncTask fleaAsyncTask = new FleaAsyncTask(FleaSessionManager.getUserInfo(), asyncTaskExecObj, METHOD_INIT_USER_INFO, paramTypes, params);
        executorService.execute(fleaAsyncTask);
    }
}
