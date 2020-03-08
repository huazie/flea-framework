package com.huazie.frame.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 其他缓存定义配置文件 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheFile {

    private String location; // 文件位置

    private List<String> executions = new ArrayList<String>(); // 需要过滤的缓存key

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getExecutions() {
        return executions;
    }

    /**
     * <p> 添加一个需要过滤的缓存key配置 </p>
     *
     * @param execution 要过滤的缓存key
     * @since 1.0.0
     */
    public void addExecution(String execution) {
        if (!contains(execution)) {
            executions.add(execution);
        }
    }

    /**
     * <p> 判断是否包含指定缓存键</p>
     *
     * @param cacheKey 指定缓存键
     * @return true：包含; false: 不包含.
     * @since 1.0.0
     */
    public boolean contains(String cacheKey) {
        return executions.contains(cacheKey);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
