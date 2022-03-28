package com.huazie.fleaframework.auth.base.privilege.dao.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea权限组关联（权限）DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupRelDAO extends IAbstractFleaJPADAO<FleaPrivilegeGroupRel> {

    /**
     * 获取指定权限组编号【privilegeGroupId】关联的指定授权关联类型【authRelType】的权限组关联信息
     *
     * @param privilegeGroupId 权限组编号
     * @param authRelType      授权关联类型
     * @return 权限组关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException;
}