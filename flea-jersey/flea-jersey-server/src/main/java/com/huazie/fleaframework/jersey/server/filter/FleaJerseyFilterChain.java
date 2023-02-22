package com.huazie.fleaframework.jersey.server.filter;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.xml.JABXUtils;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.exceptions.FleaJerseyFilterException;
import com.huazie.fleaframework.jersey.common.filter.config.Filter;
import com.huazie.fleaframework.jersey.common.filter.config.FleaJerseyFilterConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea Jersey 接口过滤器链，定义了前置过滤器链、业务服务过滤器链、
 * 后置过滤器链 和 异常过滤器链。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterChain {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyFilterChain.class);

    private List<IFleaJerseyFilter> beforeFilters; // 前置过滤器链

    private List<IFleaJerseyFilter> serviceFilters; // 业务服务过滤器链

    private List<IFleaJerseyFilter> afterFilters; // 后置过滤器链

    private List<IFleaJerseyErrorFilter> errorFilters; // 异常过滤器链

    private static ThreadLocal<StringBuilder> sDoFilterStep = new ThreadLocal<>(); // 过滤器执行顺序

    public FleaJerseyFilterChain() {
        init();
    }

    /**
     * 初始化过滤器链
     *
     * @since 1.0.0
     */
    private void init() {
        beforeFilters = convert(FleaJerseyFilterConfig.getBeforeFilters());
        serviceFilters = convert(FleaJerseyFilterConfig.getServiceFilters());
        afterFilters = convert(FleaJerseyFilterConfig.getAfterFilters());
        errorFilters = convertError(FleaJerseyFilterConfig.getErrorFilters());
    }

    /**
     * 执行过滤器
     *
     * @param request 请求对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return doFilter(request, null);
    }

    /**
     * 执行过滤器
     *
     * @param requestData 请求数据字符串
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(String requestData) {
        FleaJerseyResponse response = new FleaJerseyResponse();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "RequestData = {}", requestData);
        }
        FleaJerseyRequest request = null;
        try {
            // 请求报文不能为空
            StringUtils.checkBlank(requestData, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000003");
            request= JABXUtils.fromXml(requestData, FleaJerseyRequest.class);
            response = doFilter(request, response);
        } catch (Exception e) {
            // 执行异常过滤器
            doErrorFilter(request, response, e);
        }
        return response;
    }

    /**
     * 执行过滤器
     *
     * @param request  请求对象
     * @param response 响应对象
     * @since 1.0.0
     */
    private FleaJerseyResponse doFilter(FleaJerseyRequest request, FleaJerseyResponse response) {
        if (ObjectUtils.isEmpty(response)) {
            response = new FleaJerseyResponse();
        }
        try {
            // 执行前置过滤器
            doBeforeFilter(request, response);

            // 执行业务服务过滤器
            doServiceFilter(request, response);

            // 执行后置过滤器
            doAfterFilter(request, response);
        } catch (Exception e) {
            // 执行异常过滤器
            doErrorFilter(request, response, e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(new Object() {}, "Filter = {}", showStep());
            }
            resetStep();
        }

        return response;
    }

    /**
     * 执行前置过滤器
     *
     * @param request  请求对象
     * @param response 响应对象
     * @since 1.0.0
     */
    private void doBeforeFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        doFilter(beforeFilters, request, response);
    }

    /**
     * 执行服务过滤器
     *
     * @param request  请求对象
     * @param response 响应对象
     * @since 1.0.0
     */
    private void doServiceFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        doFilter(serviceFilters, request, response);
    }

    /**
     * 执行后置过滤器
     *
     * @param request  请求对象
     * @param response 响应对象
     * @since 1.0.0
     */
    private void doAfterFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        doFilter(afterFilters, request, response);
    }

    /**
     * 执行异常过滤器
     *
     * @param response  响应对象
     * @param throwable 异常对象
     * @since 1.0.0
     */
    private void doErrorFilter(FleaJerseyRequest request, FleaJerseyResponse response, Throwable throwable) {
        if (CollectionUtils.isNotEmpty(errorFilters)) {
            for (IFleaJerseyErrorFilter errorFilter : errorFilters) {
                if (ObjectUtils.isNotEmpty(errorFilter)) {
                    addStep(errorFilter.getClass().getName());
                    errorFilter.doFilter(request, response, throwable);
                }
            }
        }
    }

    /**
     * 执行过滤器链
     *
     * @param filters  过滤器集合
     * @param request  请求对象
     * @param response 响应对象
     * @since 1.0.0
     */
    private void doFilter(List<IFleaJerseyFilter> filters, FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        if (CollectionUtils.isNotEmpty(filters)) {
            for (IFleaJerseyFilter filter : filters) {
                if (ObjectUtils.isNotEmpty(filter)) {
                    addStep(filter.getClass().getName());
                    filter.doFilter(request, response);
                }
            }
        }
    }

    /**
     * 添加过滤器执行顺序
     *
     * @param filterName 过滤器全类名
     * @since 1.0.0
     */
    private void addStep(String filterName) {
        // 从线程变量中获取 过滤器执行顺序
        StringBuilder doFilterStep = sDoFilterStep.get();
        if (ObjectUtils.isEmpty(doFilterStep)) {
            doFilterStep = new StringBuilder();
        }
        if (StringUtils.isBlank(doFilterStep.toString())) {
            doFilterStep.append(filterName);
        } else {
            doFilterStep.append(" -> ").append(filterName);
        }
        sDoFilterStep.set(doFilterStep);
    }

    /**
     * 展示当前线程的过滤器执行顺序
     *
     * @return 过滤器执行顺序字符串
     * @since 1.0.0
     */
    private String showStep() {
        String step = "";
        // 从线程变量中获取 过滤器执行顺序
        StringBuilder doFilterStep = sDoFilterStep.get();
        if (ObjectUtils.isNotEmpty(doFilterStep)) {
            step = doFilterStep.toString();
        }
        return step;
    }

    /**
     * 重置当前线程的 过滤器执行顺序
     *
     * @since 1.0.0
     */
    private void resetStep() {
        // 从线程变量中获取 过滤器执行顺序
        StringBuilder doFilterStep = sDoFilterStep.get();
        if (ObjectUtils.isNotEmpty(doFilterStep)) {
            doFilterStep.delete(0, doFilterStep.length());
        }
    }

    /**
     * 过滤器链配置集合 转化成对应过滤器链实现类集合
     *
     * @param filters 过滤器链配置
     * @return 过滤器链实现类集合
     * @since 1.0.0
     */
    private List<IFleaJerseyFilter> convert(List<Filter> filters) {
        List<IFleaJerseyFilter> jerseyFilters = null;
        if (CollectionUtils.isNotEmpty(filters)) {
            jerseyFilters = new ArrayList<>();
            for (Filter filter : filters) {
                if (ObjectUtils.isNotEmpty(filter)) {
                    IFleaJerseyFilter jerseyFilter = (IFleaJerseyFilter) ReflectUtils.newInstance(filter.getClazz());
                    if (ObjectUtils.isNotEmpty(jerseyFilter)) {
                        jerseyFilters.add(jerseyFilter);
                    }
                }
            }
        }
        return jerseyFilters;
    }

    /**
     * 异常过滤器链配置集合 转化成对应异常过滤器链实现类集合
     *
     * @param filters 异常过滤器链配置
     * @return 异常过滤器链实现类集合
     * @since 1.0.0
     */
    private List<IFleaJerseyErrorFilter> convertError(List<Filter> filters) {
        List<IFleaJerseyErrorFilter> jerseyErrorFilters = null;
        if (CollectionUtils.isNotEmpty(filters)) {
            jerseyErrorFilters = new ArrayList<>();
            for (Filter filter : filters) {
                if (ObjectUtils.isNotEmpty(filter)) {
                    IFleaJerseyErrorFilter jerseyErrorFilter = (IFleaJerseyErrorFilter) ReflectUtils.newInstance(filter.getClazz());
                    if (ObjectUtils.isNotEmpty(jerseyErrorFilter)) {
                        jerseyErrorFilters.add(jerseyErrorFilter);
                    }
                }
            }
        }
        return jerseyErrorFilters;
    }

}
