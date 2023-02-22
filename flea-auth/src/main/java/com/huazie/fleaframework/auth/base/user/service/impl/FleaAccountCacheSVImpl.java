package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Flea账户信息SV层缓存实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaAccountCacheSV")
public class FleaAccountCacheSVImpl extends FleaAccountSVImpl {

    @Override
    @Cacheable(value = "fleaauthaccount", key = "'accountId_' + #accountId")
    public FleaAccount queryValidAccount(Long accountId) throws CommonException {
        return super.queryValidAccount(accountId);
    }

    @Override
    @Cacheable(value = "fleaauthaccount", key = "'accountCode_' + #accountCode")
    public FleaAccount queryValidAccount(String accountCode) throws CommonException {
        return super.queryValidAccount(accountCode);
    }
}