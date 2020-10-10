package com.huazie.frame.auth.base.user.dao.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * <p> Flea用户DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserDAO extends IAbstractFleaJPADAO<FleaUser> {

    /**
     * <p> 根据用户编号获取用户信息（用户状态 1 正常，未失效）</p>
     *
     * @param userId 用户编号
     * @return 用户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUser queryValidUser(Long userId) throws CommonException;
}