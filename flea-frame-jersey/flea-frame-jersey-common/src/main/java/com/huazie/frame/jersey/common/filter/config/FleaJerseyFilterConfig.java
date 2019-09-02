package com.huazie.frame.jersey.common.filter.config;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.common.JerseyXmlDigesterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p> Flea Jersey Filter 配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyFilterConfig.class);

    private static volatile FleaJerseyFilterConfig config;

    private Jersey jersey;

    private FleaJerseyFilterConfig(Jersey jersey) {
        this.jersey = jersey;
    }

    /**
     * <p> 获取Flea Jersey Filter配置类对象 </p>
     *
     * @return Flea Jersey Filter配置对象
     * @since 1.0.0
     */
    public static FleaJerseyFilterConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (FleaJerseyFilterConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    try {
                        config = new FleaJerseyFilterConfig(JerseyXmlDigesterHelper.getInstance().getJersey());
                    } catch (Exception e) {
                        if (LOGGER.isErrorEnabled()) {
                            LOGGER.error("Fail to init flea-jersey-filter.xml");
                        }
                    }
                }
            }
        }
        return config;
    }

    /**
     * <p> 获取前置过滤器链 </p>
     *
     * @return 前置过滤器链
     * @since 1.0.0
     */
    public List<Filter> getBeforeFilters() {
        List<Filter> beforeFilters = null;
        FilterChain filterChain = jersey.getFilterChain();
        if (ObjectUtils.isNotEmpty(filterChain)) {
            Before before = filterChain.getBefore();
            if (ObjectUtils.isNotEmpty(before)) {
                beforeFilters = before.getFilters();
                sort(beforeFilters);
            }
        }
        return beforeFilters;
    }

    /**
     * <p> 获取业务服务过滤器链 </p>
     *
     * @return 业务服务过滤器链
     * @since 1.0.0
     */
    public List<Filter> getServiceFilters() {
        List<Filter> serviceFilters = null;
        FilterChain filterChain = jersey.getFilterChain();
        if (ObjectUtils.isNotEmpty(filterChain)) {
            Service service = filterChain.getService();
            if (ObjectUtils.isNotEmpty(service)) {
                serviceFilters = service.getFilters();
                sort(serviceFilters);
            }
        }
        return serviceFilters;
    }

    /**
     * <p> 获取后置过滤器链 </p>
     *
     * @return 后置过滤器链
     * @since 1.0.0
     */
    public List<Filter> getAfterFilters() {
        List<Filter> afterFilters = null;
        FilterChain filterChain = jersey.getFilterChain();
        if (ObjectUtils.isNotEmpty(filterChain)) {
            After after = filterChain.getAfter();
            if (ObjectUtils.isNotEmpty(after)) {
                afterFilters = after.getFilters();
                sort(afterFilters);
            }
        }
        return afterFilters;
    }

    /**
     * <p> 获取异常过滤器链 </p>
     *
     * @return 异常过滤器链
     * @since 1.0.0
     */
    public List<Filter> getErrorFilters() {
        List<Filter> errorFilters = null;
        FilterChain filterChain = jersey.getFilterChain();
        if (ObjectUtils.isNotEmpty(filterChain)) {
            Error error = filterChain.getError();
            if (ObjectUtils.isNotEmpty(error)) {
                errorFilters = error.getFilters();
                sort(errorFilters);
            }
        }
        return errorFilters;
    }

    /**
     * <p> 重新排序 filters， 依据 Filter 中的 order 值 </p>
     *
     * @param filters 过滤器链
     * @since 1.0.0
     */
    private void sort(List<Filter> filters) {
        Collections.sort(filters, new Comparator<Filter>() {
            @Override
            public int compare(Filter o1, Filter o2) {
                // 按照 order 升序
                return o1.getOrder() - o2.getOrder();
            }
        });
    }

}
