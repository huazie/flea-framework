package com.huazie.frame.jersey.api.data;

import com.huazie.frame.jersey.common.FleaJerseyConstants;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p> 响应公共数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "PUBLIC")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ResponsePublicData implements Serializable {

    @XmlElement(name = "RESULT_CODE")
    private String resultCode; // 返回码

    @XmlElement(name = "RESULT_MESS")
    private String resultMess; // 返回信息

    /**
     * <p> 默认构造函数，初始化响应成功的公共数据 </p>
     *
     * @since 1.0.0
     */
    public ResponsePublicData() {
        resultCode = FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_SUCCESS;
        resultMess = FleaJerseyConstants.ResponseResultConstants.RESULT_MESS_SUCCESS;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMess() {
        return resultMess;
    }

    public void setResultMess(String resultMess) {
        this.resultMess = resultMess;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
