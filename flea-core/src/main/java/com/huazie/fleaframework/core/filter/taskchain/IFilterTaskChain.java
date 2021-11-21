package com.huazie.fleaframework.core.filter.taskchain;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.request.FleaRequestContext;

/**
 * <p> 过滤器任务链接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFilterTaskChain {

    /**
     * <p> 执行过滤器任务 </p>
     *
     * @param fleaRequestContext Flea请求上下文
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void doFilterTask(FleaRequestContext fleaRequestContext) throws CommonException;
}
