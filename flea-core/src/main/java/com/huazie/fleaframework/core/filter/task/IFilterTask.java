package com.huazie.fleaframework.core.filter.task;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.core.filter.taskchain.IFilterTaskChain;
import com.huazie.fleaframework.core.request.FleaRequestContext;

/**
 * 过滤器任务接口类，定义了执行过滤器任务的API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFilterTask {

    /**
     * 执行过滤器任务
     *
     * @param fleaRequestContext Flea请求上下文
     * @param filterTaskChain 过滤器任务链
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void doFilterTask(FleaRequestContext fleaRequestContext, IFilterTaskChain filterTaskChain) throws CommonException;

}
