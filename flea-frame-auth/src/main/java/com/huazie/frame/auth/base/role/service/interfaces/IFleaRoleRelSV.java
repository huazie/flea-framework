package com.huazie.frame.auth.base.role.service.interfaces;

import com.huazie.frame.auth.base.role.entity.FleaRoleRel;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea角色关联（角色， 权限， 权限组）SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaRoleRelSV extends IAbstractFleaJPASV<FleaRoleRel> {

    /**
     * <p> 获取指定角色编号【roleId】关联的指定授权关联类型【authRelType】的角色关联信息 </p>
     *
     * @param roleId 角色编号
     * @param authRelType 授权关联类型
     * @return 角色关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException;
}