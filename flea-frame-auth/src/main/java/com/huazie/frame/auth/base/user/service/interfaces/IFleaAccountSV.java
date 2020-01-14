package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea帐户信息SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAccountSV extends IAbstractFleaJPASV<FleaAccount> {

    /**
     * <p> 根据账号和密码查询账户信息 </p>
     *
     * @param accountCode 账号
     * @param accountPwd  密码
     * @return Flea账户信息对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount queryAccount(String accountCode, String accountPwd) throws CommonException;

}