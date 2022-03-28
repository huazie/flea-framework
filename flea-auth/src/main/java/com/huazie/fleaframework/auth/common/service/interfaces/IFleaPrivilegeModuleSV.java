package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * Flea权限管理服务层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeModuleSV {

    /**
     * 添加权限组关联
     *
     * @param fleaPrivilegeGroupRelPOJO Flea权限组关联POJO类
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void addPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException;
}
