package com.huazie.fleaframework.auth.base.user.dao.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * Flea用户DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserDAO extends IAbstractFleaJPADAO<FleaUser> {

    /**
     * 根据用户编号获取用户信息（用户状态 1 正常，未失效）</p>
     *
     * @param userId 用户编号
     * @return 用户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUser queryValidUser(Long userId) throws CommonException;
}