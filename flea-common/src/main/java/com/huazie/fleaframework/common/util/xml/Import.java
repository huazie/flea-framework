package com.huazie.fleaframework.common.util.xml;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 资源导入的XML节点，可查看各配置文件中
 * {@code <import resource=""/> }
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class Import {

    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
