package com.huazie.frame.core.filter.task;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException {
        fleaFilterTaskChain.doFilterTask(servletRequest, servletResponse);
    }

}
