package com.huazie.fleaframework.auth.common.pojo.function.resource;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionOtherPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea资源POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaResourcePOJO extends FleaFunctionOtherPOJO {

    private static final long serialVersionUID = -1281349347306148218L;

    private String resourceCode; // 资源编码

    private String resourceName; // 操作名称

    private String resourceDesc; // 操作描述

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
