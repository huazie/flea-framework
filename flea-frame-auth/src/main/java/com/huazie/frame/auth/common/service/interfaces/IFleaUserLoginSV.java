package com.huazie.frame.auth.common.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.exception.FleaAuthException;

/**
 * <p> Flea 用户登录服务接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserLoginSV {

    /**
     * <p> 用户登录验证 </p>
     *
     * @param accountCode 账号
     * @param accountPwd  密码
     * @return 账户信息
     * @throws FleaAuthException 服务层异常
     * @since 1.0.0
     */
    FleaAccount login(String accountCode, String accountPwd) throws FleaAuthException;

}
