package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea 请求过滤器，在配置文件 <b>flea-request.xml</b>
 * 中查看 {@code <flea-request-filter> } 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestFilter {

    private FilterTaskChain filterTaskChain; // 过滤器任务链

    public FilterTaskChain getFilterTaskChain() {
        return filterTaskChain;
    }

    public void setFilterTaskChain(FilterTaskChain filterTaskChain) {
        this.filterTaskChain = filterTaskChain;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
