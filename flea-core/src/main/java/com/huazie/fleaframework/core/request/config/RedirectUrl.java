package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 重定向URL，在配置文件 <b>flea-request.xml</b>
 * 中查看 {@code <redirect-url> } 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedirectUrl {

    private List<Property> redirectUrlList = new ArrayList<>(); // 重定向URL列表

    public List<Property> getRedirectUrlList() {
        return redirectUrlList;
    }

    /**
     * <p> 添加一个属性 </p>
     *
     * @param property 属性
     * @since 1.0.0
     */
    public void addProperty(Property property) {
        redirectUrlList.add(property);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}