package com.huazie.frame.jersey.api.data;

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
public final class ResponsePublicData implements Serializable {

    @XmlElement(name = "RESULT_CODE")
    private String resultCode; // 返回码

    @XmlElement(name = "RESULT_MESS")
    private String resultMess; // 返回信息

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
}
