package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea实名信息POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRealNameInfoPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = 8584802903639886247L;

    private Integer certType; // 证件类型（1：身份证）

    private String certCode; // 证件号码

    private String certName; // 证件名称

    private String certAddress; // 证件地址

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertAddress() {
        return certAddress;
    }

    public void setCertAddress(String certAddress) {
        this.certAddress = certAddress;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
