package com.huazie.frame.jersey.api.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p> 请求公共数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "PUBLIC")
public final class RequestPublicData implements Serializable {

    @XmlElement(name = "SYSTEM_USER_ID")
    private String systemUserId; // 系统用户编号

    @XmlElement(name = "SYSTEM_USER_PASSWORD")
    private String systemUserPassword; // 系统用户密码

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getSystemUserPassword() {
        return systemUserPassword;
    }

    public void setSystemUserPassword(String systemUserPassword) {
        this.systemUserPassword = systemUserPassword;
    }
}
