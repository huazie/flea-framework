package com.huazie.frame.jersey.api.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> Flea Jersey 请求对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "JERSEY")
@XmlAccessorType(XmlAccessType.FIELD)
public class FleaJerseyRequest {

    @XmlElement(name = "REQUEST")
    private FleaJerseyRequestData requestData;

    public FleaJerseyRequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(FleaJerseyRequestData requestData) {
        this.requestData = requestData;
    }
}
