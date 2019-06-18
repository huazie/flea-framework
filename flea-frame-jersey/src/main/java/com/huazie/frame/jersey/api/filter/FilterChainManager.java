package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 过滤器链管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterChainManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(FilterChainManager.class);

    private static volatile FilterChainManager manager;

    private FleaJerseyFilterChain filterChain;

    private FilterChainManager() {
    }

    /**
     * <p> 获取过滤器链管理类 </p>
     *
     * @return 过滤器链管理类对象
     * @since 1.0.0
     */
    public static FilterChainManager getManager() {
        if (ObjectUtils.isEmpty(manager)) {
            synchronized (FilterChainManager.class) {
                if (ObjectUtils.isEmpty(manager)) {
                    manager = new FilterChainManager();
                    initFilterChain();
                }
            }
        }
        return manager;
    }

    private static void initFilterChain() {
        manager.filterChain = new FleaJerseyFilterChain();
        // 读取配置获取

        // 设置前置过滤器

        // 设置业务服务过滤器

        // 设置后置过滤器

        // 设置异常过滤器

    }

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request 请求对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return filterChain.doFilter(request);
    }

}
