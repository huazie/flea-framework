package com.huazie.fleaframework.jersey.common.data;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p> 请求公共数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@XmlRootElement(name = "PUBLIC")
@XmlAccessorType(XmlAccessType.FIELD)
public final class RequestPublicData {

    @XmlElement(name = "SYSTEM_ACCOUNT_ID")
    private String systemAccountId; // 系统账户编号

    @XmlElement(name = "SYSTEM_ACCOUNT_PWD")
    private String systemAccountPassword; // 系统账户密码

    @XmlElement(name = "ACCOUNT_ID")
    private String accountId; // 账户编号

    @XmlElement(name = "RESOURCE_CODE")
    private String resourceCode; // 资源编码

    @XmlElement(name = "SERVICE_CODE")
    private String serviceCode; // 服务编码

    public String getSystemAccountId() {
        return systemAccountId;
    }

    public void setSystemAccountId(String systemAccountId) {
        this.systemAccountId = systemAccountId;
    }

    public String getSystemAccountPassword() {
        return systemAccountPassword;
    }

    public void setSystemAccountPassword(String systemAccountPassword) {
        this.systemAccountPassword = systemAccountPassword;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
