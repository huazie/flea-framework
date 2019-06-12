package com.huazie.frame.jersey.api.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p> Flea Jersey 请求数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "REQUEST")
@XmlAccessorType(XmlAccessType.FIELD)
public final class FleaJerseyRequestData implements Serializable {

    @XmlElement(name = "PUBLIC")
    private RequestPublicData publicData;       // 请求公共数据

    @XmlElement(name = "BUSINESS")
    private RequestBusinessData businessData;   // 请求业务数据

    public RequestPublicData getPublicData() {
        return publicData;
    }

    public void setPublicData(RequestPublicData publicData) {
        this.publicData = publicData;
    }

    public RequestBusinessData getBusinessData() {
        return businessData;
    }

    public void setBusinessData(RequestBusinessData businessData) {
        this.businessData = businessData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
