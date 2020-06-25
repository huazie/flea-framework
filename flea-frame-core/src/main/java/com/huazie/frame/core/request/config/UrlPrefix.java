package com.huazie.frame.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> URL 前缀，flea-request.xml 配置 {@code <url-prefix> </url-prefix>} 节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UrlPrefix {

    private List<Property> urlPrefixList = new ArrayList<Property>(); // URL前缀列表

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
