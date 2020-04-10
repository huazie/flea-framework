package com.huazie.frame.core.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> flea-request-filter.xml 配置 {@code <flea-request-filter> </flea-request-filter>} 节点 </p>
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
