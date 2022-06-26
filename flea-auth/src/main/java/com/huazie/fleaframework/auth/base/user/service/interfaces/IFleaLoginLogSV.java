package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaLoginLogPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea登录日志SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaLoginLogSV extends IAbstractFleaJPASV<FleaLoginLog> {

    /**
     * 查询当月用户最近一次的登录日志
     *
     * @param accountId 账户编号
     * @return 当月用户最近一次的登录日志
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaLoginLog queryLastUserLoginLog(Long accountId) throws CommonException;

    /**
     * 保存Flea登录日志
     *
     * @param fleaLoginLogPOJO Flea登录日志POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void saveLoginLog(FleaLoginLogPOJO fleaLoginLogPOJO) throws CommonException;

    /**
     * 保存指定账户最后一次登录的退出日志
     *
     * @param accountId 账户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void saveQuitLog(Long accountId) throws CommonException;
}