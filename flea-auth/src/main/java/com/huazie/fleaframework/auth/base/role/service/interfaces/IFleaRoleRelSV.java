package com.huazie.fleaframework.auth.base.role.service.interfaces;

import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea角色关联（角色， 权限， 权限组）SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaRoleRelSV extends IAbstractFleaJPASV<FleaRoleRel> {

    /**
     * 根据角色编号和授权关联类型，查询角色关联数据
     *
     * @param roleId      角色编号
     * @param authRelType 授权关联类型
     * @return 角色关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException;

    /**
     * 保存Flea角色关联
     *
     * @param fleaRoleRelPOJO Flea角色关联POJO对象
     * @return Flea角色关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaRoleRel saveRoleRel(FleaRoleRelPOJO fleaRoleRelPOJO) throws CommonException;
}