package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;

import java.util.List;

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

    /**
     * 查询在用的权限数据
     *
     * @param privilegeId 权限编号
     * @return 在用的权限数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilege queryPrivilegeInUse(Long privilegeId) throws CommonException;

    /**
     * 查询在用的权限数据集合
     *
     * @param privilegeName 权限名称（可为空，为空表示不限制）
     * @param groupId       权限组编号（可为空，为空表示不限制）
     * @return 在用的权限数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilege> queryPrivilegesInUse(String privilegeName, Long groupId) throws CommonException;

    /**
     * 查询在用的权限组数据
     *
     * @param privilegeGroupId 权限组编号
     * @return 在用的权限组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaPrivilegeGroup queryPrivilegeGroupInUse(Long privilegeGroupId) throws CommonException;

    /**
     * 查询在用的权限组数据集合
     *
     * @param privilegeGroupName 权限组名称（可为空，为空表示不限制）
     * @param isMain             是否主权限组（可为空，为空表示不限制）
     * @param functionType       功能类型（可为空，为空表示不限制）
     * @return 在用的权限组数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse(String privilegeGroupName, Integer isMain, String functionType) throws CommonException;

    /**
     * 查询权限授权关联数据
     *
     * @param privilegeId 权限编号
     * @param authRelType 授权关联类型（可为空，为空表示不限制）
     * @return 权限授权关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException;

    /**
     * 撤销权限授权关联
     *
     * @param privilegeId 权限编号
     * @param relId       关联对象编号
     * @param authRelType 授权关联类型
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void removePrivilegeRel(Long privilegeId, Long relId, String authRelType) throws CommonException;

    /**
     * 查询权限组授权关联数据
     *
     * @param privilegeGroupId 权限组编号
     * @param authRelType      授权关联类型（可为空，为空表示不限制）
     * @return 权限组授权关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException;

    /**
     * 撤销权限组授权关联
     *
     * @param privilegeGroupId 权限组编号
     * @param relId            关联对象编号
     * @param authRelType      授权关联类型
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void removePrivilegeGroupRel(Long privilegeGroupId, Long relId, String authRelType) throws CommonException;

    /**
     * 权限关联菜单【REL_TYPE = PRIVILEGE_REL_MENU】
     *
     * @param privilegeId        权限编号
     * @param menuId             菜单编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void privilegeRelMenu(Long privilegeId, Long menuId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 权限关联操作【REL_TYPE = PRIVILEGE_REL_OPERATION】
     *
     * @param privilegeId        权限编号
     * @param operationId        操作编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void privilegeRelOperation(Long privilegeId, Long operationId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 权限关联元素【REL_TYPE = PRIVILEGE_REL_ELEMENT】
     *
     * @param privilegeId        权限编号
     * @param elementId          元素编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void privilegeRelElement(Long privilegeId, Long elementId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 权限关联资源【REL_TYPE = PRIVILEGE_REL_RESOURCE】
     *
     * @param privilegeId        权限编号
     * @param resourceId         资源编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void privilegeRelResource(Long privilegeId, Long resourceId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;
}
