package com.huazie.frame.core.filter.taskchain;

import com.huazie.frame.common.exception.CommonException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException;
}
