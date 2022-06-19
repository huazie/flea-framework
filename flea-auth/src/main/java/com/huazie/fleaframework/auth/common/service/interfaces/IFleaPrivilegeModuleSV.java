package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * Flea权限管理服务层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaPrivilegeModuleSV {

    /**
     * 添加Flea权限数据
     *
     * @param fleaPrivilegePOJO Flea权限POJO对象
     * @return 权限编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaPrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException;

    /**
     * 修改Flea权限数据
     *
     * @param privilegeId       权限编号
     * @param fleaPrivilegePOJO Flea权限POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaPrivilege(Long privilegeId, FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException;

    /**
     * 添加Flea权限组数据
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO对象
     * @return 权限组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaPrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException;

    /**
     * 修改Flea权限组数据
     *
     * @param privilegeGroupId       权限组编号
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaPrivilegeGroup(Long privilegeGroupId, FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException;

    /**
     * 权限组关联权限数据【REL_TYPE = PRIVILEGE_GROUP_REL_PRIVILEGE】
     *
     * @param privilegeGroupId   权限组编号
     * @param privilegeId        权限编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void privilegeGroupRelPrivilege(Long privilegeGroupId, Long privilegeId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;
}
