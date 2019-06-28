package com.huazie.frame.jersey.common.data;

import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import org.apache.commons.lang.builder.ToStringBuilder;

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

    private FleaConfigDataSpringBean fleaConfigDataSpringBean; // Flea Config 配置数据Bean

    public FleaJerseyRequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(FleaJerseyRequestData requestData) {
        this.requestData = requestData;
    }

    public FleaConfigDataSpringBean getFleaConfigDataSpringBean() {
        return fleaConfigDataSpringBean;
    }

    public void setFleaConfigDataSpringBean(FleaConfigDataSpringBean fleaConfigDataSpringBean) {
        this.fleaConfigDataSpringBean = fleaConfigDataSpringBean;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
