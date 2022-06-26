package com.huazie.fleaframework.jersey.common.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Flea Jersey 请求对象
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "JERSEY")
@XmlAccessorType(XmlAccessType.FIELD)
public final class FleaJerseyRequest {

    @XmlElement(name = "REQUEST")
    private FleaJerseyRequestData requestData;

    public FleaJerseyRequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(FleaJerseyRequestData requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
