package com.huazie.fleaframework.auth.base.privilege.service.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea权限组关联（权限）SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupRelSV extends IAbstractFleaJPASV<FleaPrivilegeGroupRel> {

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

    /**
     * 保存Flea权限组关联
     *
     * @param fleaPrivilegeGroupRelPOJO Flea权限组关联POJO类对象
     * @return Flea权限组关联实体类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeGroupRel saveFleaPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException;
}