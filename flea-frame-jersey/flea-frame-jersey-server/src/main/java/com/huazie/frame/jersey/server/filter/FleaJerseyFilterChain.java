package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.common.util.xml.JABXUtils;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.exception.FleaJerseyFilterException;
import com.huazie.frame.jersey.common.filter.config.Filter;
import com.huazie.frame.jersey.common.filter.config.FleaJerseyFilterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyFilterChain.class);

    private List<IFleaJerseyFilter> beforeFilters; // 前置过滤器链

    private List<IFleaJerseyFilter> serviceFilters; // 业务服务过滤器链

    private List<IFleaJerseyFilter> afterFilters; // 后置过滤器链

    private List<IFleaJerseyErrorFilter> errorFilters; // 异常过滤器链

    private static ThreadLocal<StringBuilder> sDoFilterStep = new ThreadLocal<StringBuilder>(); // 过滤器执行顺序

    public FleaJerseyFilterChain() {
        initFilterChain();
    }

    /**
     * <p> 初始化过滤器链 </p>
     *
     * @since 1.0.0
     */
    private void initFilterChain() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyFilterChain##initFilterChain() Start");
        }
        FleaJerseyFilterConfig config = FleaJerseyFilterConfig.getConfig();
        if (ObjectUtils.isNotEmpty(config)) {
            beforeFilters = convert(config.getBeforeFilters());
            serviceFilters = convert(config.getServiceFilters());
            afterFilters = convert(config.getAfterFilters());
            errorFilters = convertError(config.getErrorFilters());
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyFilterChain##initFilterChain() End");
        }
    }

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request 请求对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return doFilter(request, null);
    }

    /**
     * <p> 执行过滤器 </p>
     *
     * @param requestData 请求数据字符串
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(String requestData) {
        FleaJerseyResponse response = new FleaJerseyResponse();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyFilterChain##doFilter(String) RequestData = {}", requestData);
        }
        try {
            if (StringUtils.isBlank(requestData)) {
                // 请求报文不能为空
                throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000003");
            }
            FleaJerseyRequest request = JABXUtils.fromXml(requestData, FleaJerseyRequest.class);
            response = doFilter(request, response);
        } catch (Exception e) {
            // 执行异常过滤器
            doErrorFilter(null, response, e);
        }
        return response;
    }

    /**
     * <p> 执行过滤器 </p>
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
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyFilterChain##doFilter(FleaJerseyRequest, FleaJerseyResponse) Filter = {}", sDoFilterStep.get());
        }

        return response;
    }

    /**
     * <p> 执行前置过滤器 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception
     * @since 1.0.0
     */
    private void doBeforeFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
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
                    addStep(filter.getClass().getName());
                    filter.doFilter(request, response);
                }
            }
        }
    }

    /**
     * <p> 添加过滤器执行顺序 </p>
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
     * <p> 过滤器链配置集合 转化成对应过滤器链实现类集合 </p>
     *
     * @param filters 过滤器链配置
     * @return 过滤器链实现类集合
     * @since 1.0.0
     */
    private List<IFleaJerseyFilter> convert(List<Filter> filters) {
        List<IFleaJerseyFilter> jerseyFilters = null;
        if (CollectionUtils.isNotEmpty(filters)) {
            jerseyFilters = new ArrayList<IFleaJerseyFilter>();
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
     * <p> 异常过滤器链配置集合 转化成对应异常过滤器链实现类集合 </p>
     *
     * @param filters 异常过滤器链配置
     * @return 异常过滤器链实现类集合
     * @since 1.0.0
     */
    private List<IFleaJerseyErrorFilter> convertError(List<Filter> filters) {
        List<IFleaJerseyErrorFilter> jerseyErrorFilters = null;
        if (CollectionUtils.isNotEmpty(filters)) {
            jerseyErrorFilters = new ArrayList<IFleaJerseyErrorFilter>();
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
