package com.huazie.frame.auth.base.role.dao.interfaces;

import com.huazie.frame.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> Flea角色组关联（角色）DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaRoleGroupRelDAO extends IAbstractFleaJPADAO<FleaRoleGroupRel> {

    /**
     * <p> 获取指定角色组编号【roleGroupId】关联的指定授权关联类型【authRelType】的角色组关联信息 </p>
     *
     * @param roleGroupId 角色组编号
     * @param authRelType 授权关联类型
     * @return 角色组关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException;
}