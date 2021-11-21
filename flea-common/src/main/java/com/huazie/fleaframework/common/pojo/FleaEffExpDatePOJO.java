package com.huazie.fleaframework.common.pojo;

import java.util.Date;

/**
 * <p> Flea生失效时间POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaEffExpDatePOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -4484145062215147463L;

    private Date effectiveDate; // 生效时间

    private Date expiryDate; // 失效时间

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

}
