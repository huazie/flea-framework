package com.huazie.fleaframework.core.filter.taskchain;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.request.FleaRequestContext;

/**
 * 过滤器任务链接口，定义了执行过滤器任务的API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFilterTaskChain {

    /**
     * 执行过滤器任务
     *
     * @param fleaRequestContext Flea请求上下文
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void doFilterTask(FleaRequestContext fleaRequestContext) throws CommonException;
}
