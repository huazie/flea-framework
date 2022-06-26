package com.huazie.fleaframework.auth.base.role.service.interfaces;

import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea角色组关联（角色）SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaRoleGroupRelSV extends IAbstractFleaJPASV<FleaRoleGroupRel> {

    /**
     * 根据角色组编号、授权关联类型，查询角色组关联数据。
     *
     * @param roleGroupId 角色组编号
     * @param authRelType 授权关联类型
     * @return 角色组关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException;

    /**
     * 保存Flea角色组关联数据
     *
     * @param fleaRoleGroupRelPOJO Flea角色组关联POJO对象
     * @return Flea角色组关联数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaRoleGroupRel saveRoleGroupRel(FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO) throws CommonException;
}