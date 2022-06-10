package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea用户SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserSV extends IAbstractFleaJPASV<FleaUser> {

    /**
     * 新建一个Flea用户
     *
     * @param fleaUserPOJO Flea用户POJO类实例
     * @return Flea用户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUser saveFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException;

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