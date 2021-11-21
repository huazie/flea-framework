package com.huazie.fleaframework.auth.base.user.dao.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * <p> Flea登录日志DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaLoginLogDAO extends IAbstractFleaJPADAO<FleaLoginLog> {

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