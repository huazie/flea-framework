package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea登录日志SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaLoginLogSV extends IAbstractFleaJPASV<FleaLoginLog> {

    /**
     * <p> 查询当月用户最近一次的登录日志 </p>
     *
     * @param accountId 账户编号
     * @return 当月用户最近一次的登录日志
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaLoginLog queryLastUserLoginLog(Long accountId) throws CommonException;
}