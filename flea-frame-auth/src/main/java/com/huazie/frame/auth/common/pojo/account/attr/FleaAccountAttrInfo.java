package com.huazie.frame.auth.common.pojo.account.attr;

import com.huazie.frame.auth.common.pojo.FleaAttrInfo;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea账户属性POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAccountAttrInfo extends FleaAttrInfo {

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
