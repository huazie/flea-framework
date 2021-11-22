package com.huazie.fleaframework.jersey.common.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> Flea Jersey 响应数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public final class FleaJerseyResponseData {

    @XmlElement(name = "PUBLIC")
    private ResponsePublicData publicData; // 响应公共数据

    @XmlElement(name = "BUSINESS")
    private ResponseBusinessData businessData; // 响应业务数据

    public ResponsePublicData getPublicData() {
        return publicData;
    }

    public void setPublicData(ResponsePublicData publicData) {
        this.publicData = publicData;
    }

    public ResponseBusinessData getBusinessData() {
        return businessData;
    }

    public void setBusinessData(ResponseBusinessData businessData) {
        this.businessData = businessData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
