package com.huazie.frame.jersey.common;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.FleaCommonConfig;
import com.huazie.frame.common.IFleaUser;

/**
 * <p> 用户信息实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserImpl extends FleaCommonConfig implements IFleaUser {

    @Override
    public Long getUserId() {
        return get(CommonConstants.FleaUserInfoConstants.USER_ID, Long.class);
    }

    @Override
    public void setUserId(Long userId) {
        put(CommonConstants.FleaUserInfoConstants.USER_ID, userId);
    }

    @Override
    public Long getAcctId() {
        return get(FleaJerseyConstants.RequestPublicDataConstants.ACCT_ID, Long.class);
    }

    @Override
    public void setAcctId(Long acctId) {
        put(FleaJerseyConstants.RequestPublicDataConstants.ACCT_ID, acctId);
    }

    @Override
    public void set(String key, Object value) {
        put(key, value);
    }
}
