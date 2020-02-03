package com.huazie.frame.auth.common.pojo;

import com.huazie.frame.common.pojo.FleaCommonPOJO;

import java.util.Date;

/**
 * <p> Flea属性POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAttrInfo implements FleaCommonPOJO {

    private static final long serialVersionUID = -3403056323551571404L;
    
    private String attrCode; // 属性码

    private String attrValue; // 属性值

    private Date effectiveDate; // 生效时间

    private Date expiryDate; // 失效时间

    private String remarks; // 备注

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
