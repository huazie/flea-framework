package com.huazie.fleaframework.auth.base.role.dao.interfaces;

import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea角色关联（角色， 权限， 权限组）DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaRoleRelDAO extends IAbstractFleaJPADAO<FleaRoleRel> {

    /**
     * 根据角色编号和授权关联类型，查询角色关联数据
     *
     * @param roleId 角色编号
     * @param authRelType 授权关联类型
     * @return 角色关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException;
}