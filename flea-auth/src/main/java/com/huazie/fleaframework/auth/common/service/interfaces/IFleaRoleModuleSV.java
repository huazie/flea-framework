package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * Flea角色管理服务接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaRoleModuleSV {

    /**
     * 添加Flea角色数据
     *
     * @param fleaRolePOJO Flea角色POJO对象
     * @return 角色编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaRole(FleaRolePOJO fleaRolePOJO) throws CommonException;

    /**
     * 修改Flea角色数据
     *
     * @param roleId       角色编号
     * @param fleaRolePOJO Flea角色POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaRole(Long roleId, FleaRolePOJO fleaRolePOJO) throws CommonException;

    /**
     * 添加Flea角色组数据
     *
     * @param fleaRoleGroupPOJO Flea角色组POJO对象
     * @return 角色组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addFleaRoleGroup(FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException;

    /**
     * 修改Flea角色组数据
     *
     * @param roleGroupId       角色组编号
     * @param fleaRoleGroupPOJO Flea角色组POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaRoleGroup(Long roleGroupId, FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException;

    /**
     * 角色关联角色【REL_TYPE = ROLE_REL_ROLE】
     *
     * @param roleId             角色编号
     * @param relRoleId          关联角色编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void roleRelRole(Long roleId, Long relRoleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 角色关联权限【REL_TYPE = ROLE_REL_PRIVILEGE】
     *
     * @param roleId             角色编号
     * @param privilegeId        权限编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void roleRelPrivilege(Long roleId, Long privilegeId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 角色关联权限组【REL_TYPE = ROLE_REL_PRIVILEGE_GROUP】
     *
     * @param roleId             角色编号
     * @param privilegeGroupId   权限组编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void roleRelPrivilegeGroup(Long roleId, Long privilegeGroupId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 角色组关联角色【REL_TYPE = ROLE_GROUP_REL_ROLE】
     *
     * @param roleGroupId        角色组编号
     * @param roleId             角色编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void roleGroupRelRole(Long roleGroupId, Long roleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

}
