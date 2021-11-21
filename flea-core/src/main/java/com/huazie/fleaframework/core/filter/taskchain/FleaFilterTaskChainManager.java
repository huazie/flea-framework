package com.huazie.fleaframework.core.filter.taskchain;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.core.filter.taskchain.impl.FleaFilterTaskChain;
import com.huazie.fleaframework.core.request.FleaRequestContext;

/**
 * <p> 过滤器任务链管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFilterTaskChainManager {

    private static volatile FleaFilterTaskChainManager manager;

    private FleaFilterTaskChain fleaFilterTaskChain;

    private FleaFilterTaskChainManager(FleaFilterTaskChain filterTaskChain) {
        this.fleaFilterTaskChain = filterTaskChain;
    }

    /**
     * <p> 获取过滤器任务链管理类 </p>
     *
     * @return 过滤器任务链管理类对象
     * @since 1.0.0
     */
    public static FleaFilterTaskChainManager getManager() {
        if (ObjectUtils.isEmpty(manager)) {
            synchronized (FleaFilterTaskChainManager.class) {
                if (ObjectUtils.isEmpty(manager)) {
                    manager = new FleaFilterTaskChainManager(new FleaFilterTaskChain());
                }
            }
        }
        return manager;
    }

    /**
     * <p> 执行过滤器任务链 </p>
     *
     * @param fleaRequestContext Flea请求上下文
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public void doFilterTask(FleaRequestContext fleaRequestContext) throws CommonException {
        fleaFilterTaskChain.doFilterTask(fleaRequestContext);
        // 过滤器任务链执行完毕，当前线程重置过滤器任务执行位置
        fleaFilterTaskChain.reset();
    }

}
