package com.huazie.frame.auth.common.service.interfaces;

import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.frame.common.exception.CommonException;

/**
 * <p> Flea权限管理服务层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeModuleSV {

    /**
     * <p> 添加权限组关联 </p>
     *
     * @param fleaPrivilegeGroupRelPOJO Flea权限组关联POJO类
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void addPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException;
}
