package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.pojo.account.FleaAccountPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea账户信息SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAccountSV extends IAbstractFleaJPASV<FleaAccount> {

    /**
     * <p> 新建一个Flea账户 </p>
     *
     * @param fleaAccountPOJO Flea账户POJO类实例
     * @return Flea账户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount saveFleaAccount(FleaAccountPOJO fleaAccountPOJO) throws CommonException;

    /**
     * <p> 根据账号和密码查询账户信息 </p>
     *
     * @param accountCode 账号
     * @param accountPwd  密码
     * @return Flea账户对象
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

    /**
     * <p> 账户密码加密 </p>
     *
     * @param originalAccountPwd 原始账户密码
     * @return 加密后的密码
     * @since 1.0.0
     */
    String encrypt(String originalAccountPwd);

}