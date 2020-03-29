package com.huazie.frame.auth.base.user.dao.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * <p> Flea帐户信息DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAccountDAO extends IAbstractFleaJPADAO<FleaAccount> {

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

    /**
     * <p> 根据账号查询有效的账户信息（账户状态 1 正常 和 3 待审批, 未失效） </p>
     *
     * @param accountCode 账号
     * @return Flea账户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount queryValidAccount(String accountCode) throws CommonException;
}