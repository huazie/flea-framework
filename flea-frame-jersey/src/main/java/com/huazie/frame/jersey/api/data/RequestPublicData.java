package com.huazie.frame.jersey.api.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@XmlAccessorType(XmlAccessType.FIELD)
public final class RequestPublicData implements Serializable {

    @XmlElement(name = "SYSTEM_USER_ID")
    private String systemUserId; // 系统用户编号

    @XmlElement(name = "SYSTEM_USER_PASSWORD")
    private String systemUserPassword; // 系统用户密码

    @XmlElement(name = "RESOURCE_PATH")
    private String resourcePath; // 资源路径

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

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
