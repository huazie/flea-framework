package com.huazie.fleaframework.jersey.common;

import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.common.FleaCommonConfig;
import com.huazie.fleaframework.common.IFleaUser;

import java.util.Map;

/**
 * 用户信息实现
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaUserImpl extends FleaCommonConfig implements IFleaUser {

    private static final long serialVersionUID = 4478158086769800361L;

    @Override
    public Long getUserId() {
        return get(FleaAuthConstants.UserModuleConstants.USER_ID, Long.class);
    }

    @Override
    public void setUserId(Long userId) {
        put(FleaAuthConstants.UserModuleConstants.USER_ID, userId);
    }

    @Override
    public Long getSystemUserId() {
        return get(FleaAuthConstants.UserModuleConstants.SYSTEM_USER_ID, Long.class);
    }

    @Override
    public void setSystemUserId(Long systemUserId) {
        put(FleaAuthConstants.UserModuleConstants.SYSTEM_USER_ID, systemUserId);
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
    public void addAll(Map<String, Object> otherMap) {
        putAll(otherMap);
    }

    @Override
    public Map<String, Object> toMap() {
        return getConfig();
    }
}
