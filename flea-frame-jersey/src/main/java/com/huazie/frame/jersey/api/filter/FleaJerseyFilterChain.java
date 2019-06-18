package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;

import java.util.List;

/**
 * <p> Flea Jersey 过滤器链 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterChain {

    private List<IFleaJerseyFilter> beforeFilters;

    private List<IFleaJerseyFilter> serviceFilters;

    private List<IFleaJerseyFilter> afterFilters;

    private List<IFleaJerseyErrorFilter> errorFilters;

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception
     * @since 1.0.0
     */
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        try {
            // 执行前置过滤器
            doBeforeFilter(request, response);

            // 执行服务过滤器
            doServiceFilter(request, response);

            // 执行后置过滤器
            doAfterFilter(request, response);
        } catch (Exception e) {
            // 执行异常过滤器
            doErrorFilter(response, e);
        }
    }

    /**
     * <p> 执行前置过滤器 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception
     * @since 1.0.0
     */
    public void doBeforeFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        doFilter(beforeFilters, request, response);
    }

    /**
     * <p> 执行服务过滤器 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception
     * @since 1.0.0
     */
    private void doServiceFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        doFilter(serviceFilters, request, response);
    }

    /**
     * <p> 执行后置过滤器 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception
     * @since 1.0.0
     */
    private void doAfterFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        doFilter(afterFilters, request, response);
    }

    /**
     * <p> 执行异常过滤器 </p>
     *
     * @param response  响应对象
     * @param throwable 异常对象
     * @throws Exception
     * @since 1.0.0
     */
    private void doErrorFilter(FleaJerseyResponse response, Throwable throwable) {
        if (CollectionUtils.isNotEmpty(errorFilters)) {
            for (IFleaJerseyErrorFilter errorFilter : errorFilters) {
                if (ObjectUtils.isNotEmpty(errorFilter)) {
                    errorFilter.doFilter(response, throwable);
                }
            }
        }
    }

    /**
     * <p> 执行过滤器链 </p>
     *
     * @param filters  过滤器集合
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception
     * @since 1.0.0
     */
    private void doFilter(List<IFleaJerseyFilter> filters, FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        if (CollectionUtils.isNotEmpty(filters)) {
            for (IFleaJerseyFilter filter : filters) {
                filter.doFilter(request, response);
            }
        }
    }

}
