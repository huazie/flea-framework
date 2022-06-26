package com.huazie.fleaframework.auth.common.pojo.user.attr;

import com.huazie.fleaframework.auth.common.pojo.FleaAttrPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea账户扩展属性POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAccountAttrPOJO extends FleaAttrPOJO {

    private static final long serialVersionUID = -5944599796113322652L;

    private Long accountId; // 账户编号

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
