package com.huazie.fleaframework.auth.base.privilege.service.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea权限组SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupSV extends IAbstractFleaJPASV<FleaPrivilegeGroup> {

    /**
     * 保存Flea权限组
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO类对象
     * @return Flea权限组实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeGroup savePrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException;
}