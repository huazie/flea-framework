package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * URL 前缀，在配置文件 <b>flea-request.xml</b>
 * 中查看 {@code <url-prefix>} 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UrlPrefix {

    private List<Property> urlPrefixList = new ArrayList<>(); // URL前缀列表

    public List<Property> getUrlPrefixList() {
        return urlPrefixList;
    }

    /**
     * <p> 添加一个属性 </p>
     *
     * @param property 属性
     * @since 1.0.0
     */
    public void addProperty(Property property) {
        urlPrefixList.add(property);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
