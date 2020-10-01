package com.huazie.frame.jersey.common;

import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.common.FleaCommonConfig;
import com.huazie.frame.common.IFleaUser;

import java.util.Map;

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
        return get(FleaAuthConstants.UserConstants.USER_ID, Long.class);
    }

    @Override
    public void setUserId(Long userId) {
        put(FleaAuthConstants.UserConstants.USER_ID, userId);
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
    public Long getSystemAcctId() {
        return get(FleaJerseyConstants.RequestPublicDataConstants.SYSTEM_ACCT_ID, Long.class);
    }

    @Override
    public void setSystemAcctId(Long systemAcctId) {
        put(FleaJerseyConstants.RequestPublicDataConstants.SYSTEM_ACCT_ID, systemAcctId);
    }

    @Override
    public void set(String key, Object value) {
        put(key, value);
    }

    @Override
    public Map<String, Object> toMap() {
        return getConfig();
    }
}
