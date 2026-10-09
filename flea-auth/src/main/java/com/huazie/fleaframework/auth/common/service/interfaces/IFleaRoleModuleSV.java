package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;

import java.util.List;

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

    /**
     * 查询在用的角色数据
     *
     * @param roleId 角色编号
     * @return 在用的角色数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaRole queryRoleInUse(Long roleId) throws CommonException;

    /**
     * 查询在用的角色数据集合
     *
     * @param roleName 角色名称（可为空，为空表示不限制）
     * @param groupId  角色组编号（可为空，为空表示不限制）
     * @return 在用的角色数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaRole> queryRolesInUse(String roleName, Long groupId) throws CommonException;

    /**
     * 查询在用的角色组数据
     *
     * @param roleGroupId 角色组编号
     * @return 在用的角色组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaRoleGroup queryRoleGroupInUse(Long roleGroupId) throws CommonException;

    /**
     * 查询在用的角色组数据集合
     *
     * @param roleGroupName 角色组名称（可为空，为空表示不限制）
     * @return 在用的角色组数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaRoleGroup> queryRoleGroupsInUse(String roleGroupName) throws CommonException;

    /**
     * 查询角色授权关联数据
     *
     * @param roleId      角色编号
     * @param authRelType 授权关联类型（可为空，为空表示不限制）
     * @return 角色授权关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException;

    /**
     * 撤销角色授权关联
     *
     * @param roleId      角色编号
     * @param relId       关联对象编号
     * @param authRelType 授权关联类型
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void removeRoleRel(Long roleId, Long relId, String authRelType) throws CommonException;

    /**
     * 查询角色组授权关联数据
     *
     * @param roleGroupId 角色组编号
     * @param authRelType 授权关联类型（可为空，为空表示不限制）
     * @return 角色组授权关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException;

    /**
     * 撤销角色组授权关联
     *
     * @param roleGroupId 角色组编号
     * @param relId       关联对象编号
     * @param authRelType 授权关联类型
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void removeRoleGroupRel(Long roleGroupId, Long relId, String authRelType) throws CommonException;

}
