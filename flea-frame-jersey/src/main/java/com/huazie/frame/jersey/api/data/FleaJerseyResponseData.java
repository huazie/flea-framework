package com.huazie.frame.jersey.api.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p> Flea Jersey 响应数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "RESPONSE")
public final class FleaJerseyResponseData implements Serializable {

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
}
