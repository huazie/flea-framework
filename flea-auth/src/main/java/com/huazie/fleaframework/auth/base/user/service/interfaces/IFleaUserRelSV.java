package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea用户关联（用户，用户组）SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserRelSV extends IAbstractFleaJPASV<FleaUserRel> {

    /**
     * 根据用户编号、授权关联类型，查询用户关联数据
     *
     * @param userId      用户编号
     * @param authRelType 授权关联类型
     * @return 用户关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserRel> getUserRelList(Long userId, String authRelType) throws CommonException;

    /**
     * 保存Flea用户关联数据
     *
     * @param fleaUserRelPOJO Flea用户关联POJO对象
     * @return Flea用户关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUserRel saveUserRel(FleaUserRelPOJO fleaUserRelPOJO) throws CommonException;
}