package com.huazie.frame.auth.base.privilege.service.interfaces;

import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea权限组SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeGroupSV extends IAbstractFleaJPASV<FleaPrivilegeGroup> {

    /**
     * <p> 保存Flea权限组 </p>
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO类对象
     * @return Flea权限组实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaPrivilegeGroup savePrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException;
}