package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea Jersey 过滤器链 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterChain {

    private List<IFleaJerseyFilter> beforeFilters = new ArrayList<IFleaJerseyFilter>();

    private List<IFleaJerseyFilter> serviceFilters = new ArrayList<IFleaJerseyFilter>();

    private List<IFleaJerseyFilter> afterFilters = new ArrayList<IFleaJerseyFilter>();

    private List<IFleaJerseyErrorFilter> errorFilters = new ArrayList<IFleaJerseyErrorFilter>();

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request 请求对象
     * @throws Exception
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        FleaJerseyResponse response = new FleaJerseyResponse();
        try {
            // 执行前置过滤器
            doBeforeFilter(request, response);

            // 执行业务服务过滤器
            doServiceFilter(request, response);

            // 执行后置过滤器
            doAfterFilter(request, response);
        } catch (Exception e) {
            // 执行异常过滤器
            doErrorFilter(response, e);
        }
        return response;
    }

    /**
     * <p> 添加前置过滤器 </p>
     *
     * @param filter 过滤器类
     * @since 1.0.0
     */
    public void addBeforeFilter(IFleaJerseyFilter filter) {
        if (ObjectUtils.isNotEmpty(filter)) {
            beforeFilters.add(filter);
        }
    }

    /**
     * <p> 添加业务服务过滤器 </p>
     *
     * @param filter 过滤器类
     * @since 1.0.0
     */
    public void addServiceFilter(IFleaJerseyFilter filter) {
        if (ObjectUtils.isNotEmpty(filter)) {
            serviceFilters.add(filter);
        }
    }

    /**
     * <p> 添加后置过滤器 </p>
     *
     * @param filter 过滤器类
     * @since 1.0.0
     */
    public void addAfterFilter(IFleaJerseyFilter filter) {
        if (ObjectUtils.isNotEmpty(filter)) {
            afterFilters.add(filter);
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
                if (ObjectUtils.isNotEmpty(filter)) {
                    filter.doFilter(request, response);
                }
            }
        }
    }

}
