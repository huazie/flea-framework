package com.huazie.frame.auth.base.user.service.interfaces;

import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea用户SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserSV extends IAbstractFleaJPASV<FleaUser> {

    /**
     * <p> 新建一个Flea用户 </p>
     *
     * @param fleaUserPOJO Flea用户POJO类实例
     * @return Flea用户对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUser saveFleaUser(FleaUserPOJO fleaUserPOJO) throws CommonException;

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