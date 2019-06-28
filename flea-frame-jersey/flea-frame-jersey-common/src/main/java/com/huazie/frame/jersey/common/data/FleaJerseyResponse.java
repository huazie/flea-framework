package com.huazie.frame.jersey.common.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> Flea Jersey 响应对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "JERSEY")
@XmlAccessorType(XmlAccessType.FIELD)
public class FleaJerseyResponse {

    @XmlElement(name = "RESPONSE")
    private FleaJerseyResponseData responseData;

    public FleaJerseyResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(FleaJerseyResponseData responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
