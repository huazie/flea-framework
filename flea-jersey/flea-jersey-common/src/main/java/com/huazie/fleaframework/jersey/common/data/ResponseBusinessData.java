package com.huazie.fleaframework.jersey.common.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 响应业务数据
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "BUSINESS")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ResponseBusinessData {

    @XmlElement(name = "OUTPUT")
    private String output; // 业务出参

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
