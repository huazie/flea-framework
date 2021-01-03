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
    public Long getAccountId() {
        return get(FleaJerseyConstants.RequestPublicDataConstants.ACCOUNT_ID, Long.class);
    }

    @Override
    public void setAccountId(Long accountId) {
        put(FleaJerseyConstants.RequestPublicDataConstants.ACCOUNT_ID, accountId);
    }

    @Override
    public Long getSystemAccountId() {
        return get(FleaJerseyConstants.RequestPublicDataConstants.SYSTEM_ACCOUNT_ID, Long.class);
    }

    @Override
    public void setSystemAccountId(Long systemAccountId) {
        put(FleaJerseyConstants.RequestPublicDataConstants.SYSTEM_ACCOUNT_ID, systemAccountId);
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
