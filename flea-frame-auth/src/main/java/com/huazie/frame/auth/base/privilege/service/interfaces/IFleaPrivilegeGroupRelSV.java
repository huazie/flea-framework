package com.huazie.frame.auth.base.privilege.service.interfaces;

import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea权限组关联（权限）SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupRelSV extends IAbstractFleaJPASV<FleaPrivilegeGroupRel> {

    /**
     * <p> 获取指定权限组编号【privilegeGroupId】关联的指定授权关联类型【authRelType】的权限组关联信息 </p>
     *
     * @param privilegeGroupId 权限组编号
     * @param authRelType      授权关联类型
     * @return 权限组关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException;
}