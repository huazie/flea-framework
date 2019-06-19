package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.filter.config.Filter;
import com.huazie.frame.jersey.api.filter.config.FleaJerseyFilterConfig;
import com.huazie.frame.jersey.api.filter.impl.DataPreCheckFilter;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyFilterChain.class);

    private List<IFleaJerseyFilter> beforeFilters;

    private List<IFleaJerseyFilter> serviceFilters;

    private List<IFleaJerseyFilter> afterFilters;

    private List<IFleaJerseyErrorFilter> errorFilters;

    private StringBuilder doFilterStep;

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
     * @throws Exception
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        FleaJerseyResponse response = new FleaJerseyResponse();
        try {

            if (ObjectUtils.isEmpty(doFilterStep)) {
                doFilterStep = new StringBuilder();
            }
            // 清空过滤步骤展示的内容
            StringUtils.clear(doFilterStep);

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

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyFilterChain##doFilter(FleaJerseyRequest) Step={}", doFilterStep);
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
                    addStep(errorFilter.getClass().getName());
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
                    addStep(filter.getClass().getName());
                }
            }
        }
    }

    /**
     * <p> 添加过滤步骤 </p>
     *
     * @param filterName 过滤器全类名
     * @since 1.0.0
     */
    private void addStep(String filterName) {
        if (StringUtils.isBlank(doFilterStep.toString())) {
            doFilterStep.append(filterName);
        } else {
            doFilterStep.append(" -> ").append(filterName);
        }
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
                    String className = filter.getClazz();
                    IFleaJerseyFilter jerseyFilter = (IFleaJerseyFilter) ReflectUtils.newInstance(className);
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
                    String className = filter.getClazz();
                    IFleaJerseyErrorFilter jerseyErrorFilter = (IFleaJerseyErrorFilter) ReflectUtils.newInstance(className);
                    if (ObjectUtils.isNotEmpty(jerseyErrorFilter)) {
                        jerseyErrorFilters.add(jerseyErrorFilter);
                    }
                }
            }
        }
        return jerseyErrorFilters;
    }

}
