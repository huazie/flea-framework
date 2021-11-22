package com.huazie.fleaframework.jersey.common.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 请求业务数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "BUSINESS")
@XmlAccessorType(XmlAccessType.FIELD)
public final class RequestBusinessData {

    @XmlElement(name = "INPUT")
    private String input; // 业务入参

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
