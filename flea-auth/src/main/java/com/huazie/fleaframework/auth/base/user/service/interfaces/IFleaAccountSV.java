package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.common.pojo.user.FleaAccountPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea账户信息SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAccountSV extends IAbstractFleaJPASV<FleaAccount> {

    /**
     * 根据账户编号查询有效的账户信息 （账户状态 1 正常，未失效）</p>
     *
     * @param accountId 账户编号
     * @return 账户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount queryValidAccount(Long accountId) throws CommonException;

    /**
     * 根据账号和密码查询账户信息
     *
     * @param accountCode 账号
     * @param accountPwd  明文密码
     * @return Flea账户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount queryAccount(String accountCode, String accountPwd) throws CommonException;

    /**
     * 根据账号查询有效的账户信息（账户状态 1 正常 和 3 待审批, 未失效）
     *
     * @param accountCode 账号
     * @return Flea账户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount queryValidAccount(String accountCode) throws CommonException;

    /**
     * 账户密码加密
     *
     * @param originalAccountPwd 原始账户密码
     * @return 加密后的密码
     * @since 1.0.0
     */
    String encrypt(String originalAccountPwd);

    /**
     * 新建一个Flea账户
     *
     * @param fleaAccountPOJO Flea账户POJO类实例
     * @return Flea账户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount saveFleaAccount(FleaAccountPOJO fleaAccountPOJO) throws CommonException;

}