package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea用户关联（角色，角色组）SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserRelSV extends IAbstractFleaJPASV<FleaUserRel> {

    /**
     * 获取指定用户编号【userId】关联的指定授权关联类型【authRelType】的用户组关联信息
     *
     * @param userId 用户编号
     * @param authRelType 授权关联类型
     * @return 用户关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserRel> getUserRelList(Long userId, String authRelType) throws CommonException;
}