package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.util.pojo.FleaFunctionModulePOJOUtils;
import com.huazie.fleaframework.auth.util.pojo.FleaPrivilegeModulePOJOUtils;
import com.huazie.fleaframework.auth.util.pojo.FleaRoleModulePOJOUtils;
import com.huazie.fleaframework.auth.util.pojo.FleaUserModulePOJOUtils;

/**
 * Flea授权POJO工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaAuthPOJOUtils {

    private FleaAuthPOJOUtils() {
    }

    /**
     * 新建Flea用户登录POJO对象
     *
     * @param accountCode 账号
     * @param accountPwd  密码
     * @return Flea用户登录POJO对象
     * @since 2.0.0
     */
    public static FleaUserLoginPOJO newFleaUserLoginPOJO(String accountCode, String accountPwd) {
        return FleaUserModulePOJOUtils.newFleaUserLoginPOJO(accountCode, accountPwd);
    }

    /**
     * 新建用户扩展属性POJO对象
     *
     * @param attrCode  属性码
     * @param attrValue 属性值
     * @param attrDesc  属性描述
     * @return 用户扩展属性POJO对象
     * @since 2.0.0
     */
    public static FleaUserAttrPOJO newFleaUserAttrPOJO(String attrCode, String attrValue, String attrDesc) {
        return FleaUserModulePOJOUtils.newFleaUserAttrPOJO(attrCode, attrValue, attrDesc);
    }

    /**
     * 新建账户扩展属性POJO对象
     *
     * @param attrCode  属性码
     * @param attrValue 属性值
     * @param attrDesc  属性描述
     * @return 账户扩展属性POJO对象
     * @since 2.0.0
     */
    public static FleaAccountAttrPOJO newFleaAccountAttrPOJO(String attrCode, String attrValue, String attrDesc) {
        return FleaUserModulePOJOUtils.newFleaAccountAttrPOJO(attrCode, attrValue, attrDesc);
    }

    /**
     * 新建用户扩展属性POJO对象【用户类型=操作用户】
     *
     * @return 用户扩展属性POJO对象【用户类型=操作用户】
     * @since 2.0.0
     */
    public static FleaUserAttrPOJO newOperatorUserAttr() {
        return FleaUserModulePOJOUtils.newOperatorUserAttr();
    }

    /**
     * 新建账户扩展属性POJO对象【账户类型=操作账户】
     *
     * @return 账户扩展属性POJO对象【账户类型=操作账户】
     * @since 2.0.0
     */
    public static FleaAccountAttrPOJO newOperatorAccountAttr() {
        return FleaUserModulePOJOUtils.newOperatorAccountAttr();
    }

    /**
     * 新建用户扩展属性POJO对象【用户类型=系统用户】
     *
     * @return 用户扩展属性POJO对象【用户类型=系统用户】
     * @since 2.0.0
     */
    public static FleaUserAttrPOJO newSystemUserAttr() {
        return FleaUserModulePOJOUtils.newSystemUserAttr();
    }

    /**
     * 新建账户扩展属性POJO对象【账户类型=系统账户】
     *
     * @return 账户扩展属性POJO对象【账户类型=系统账户】
     * @since 2.0.0
     */
    public static FleaAccountAttrPOJO newSystemAccountAttr() {
        return FleaUserModulePOJOUtils.newSystemAccountAttr();
    }

    /**
     * 新建用户关联角色POJO对象
     *
     * @param userId   用户编号
     * @param userName 用户名称
     * @param roleId   角色编号
     * @param roleName 角色名称
     * @return 用户关联角色POJO对象
     * @since 2.0.0
     */
    public static FleaUserRelPOJO newUserRelRolePOJO(Long userId, String userName, Long roleId, String roleName) {
        return FleaUserModulePOJOUtils.newUserRelRolePOJO(userId, userName, roleId, roleName);
    }

    /**
     * 新建用户关联角色组POJO对象
     *
     * @param userId        用户编号
     * @param userName      用户名称
     * @param roleGroupId   角色组编号
     * @param roleGroupName 角色组名称
     * @return 用户关联角色组POJO对象
     * @since 2.0.0
     */
    public static FleaUserRelPOJO newUserRelRoleGroupPOJO(Long userId, String userName, Long roleGroupId, String roleGroupName) {
        return FleaUserModulePOJOUtils.newUserRelRoleGroupPOJO(userId, userName, roleGroupId, roleGroupName);
    }

    /**
     * 新建用户组关联角色POJO对象
     *
     * @param userGroupId   用户组编号
     * @param userGroupName 用户组名称
     * @param roleId        角色编号
     * @param roleName      角色名称
     * @return 用户组关联角色POJO对象
     * @since 2.0.0
     */
    public static FleaUserGroupRelPOJO newUserGroupRelRolePOJO(Long userGroupId, String userGroupName, Long roleId, String roleName) {
        return FleaUserModulePOJOUtils.newUserGroupRelRolePOJO(userGroupId, userGroupName, roleId, roleName);
    }

    /**
     * 新建用户组关联角色组POJO对象
     *
     * @param userGroupId   用户组编号
     * @param userGroupName 用户组名称
     * @param roleGroupId   角色组编号
     * @param roleGroupName 角色组名称
     * @return 用户组关联角色组POJO对象
     * @since 2.0.0
     */
    public static FleaUserGroupRelPOJO newUserGroupRelRoleGroupPOJO(Long userGroupId, String userGroupName, Long roleGroupId, String roleGroupName) {
        return FleaUserModulePOJOUtils.newUserGroupRelRoleGroupPOJO(userGroupId, userGroupName, roleGroupId, roleGroupName);
    }

    /**
     * 新建用户组关联用户POJO对象
     *
     * @param userGroupId   用户组编号
     * @param userGroupName 用户组名称
     * @param userId        用户编号
     * @param userName      用户名称
     * @return 用户组关联用户POJO对象
     * @since 2.0.0
     */
    public static FleaUserGroupRelPOJO newUserGroupRelUserPOJO(Long userGroupId, String userGroupName, Long userId, String userName) {
        return FleaUserModulePOJOUtils.newUserGroupRelUserPOJO(userGroupId, userGroupName, userId, userName);
    }

    /**
     * 新建角色关联权限POJO对象
     *
     * @param roleId        角色编号
     * @param roleName      角色名称
     * @param privilegeId   权限编号
     * @param privilegeName 权限名称
     * @return 角色关联权限POJO对象
     * @since 2.0.0
     */
    public static FleaRoleRelPOJO newRoleRelPrivilegePOJO(Long roleId, String roleName, Long privilegeId, String privilegeName) {
        return FleaRoleModulePOJOUtils.newRoleRelPrivilegePOJO(roleId, roleName, privilegeId, privilegeName);
    }

    /**
     * 新建角色关联权限组POJO对象
     *
     * @param roleId             角色编号
     * @param roleName           角色名称
     * @param privilegeGroupId   权限组编号
     * @param privilegeGroupName 权限组名称
     * @return 角色关联权限组POJO对象
     * @since 2.0.0
     */
    public static FleaRoleRelPOJO newRoleRelPrivilegeGroupPOJO(Long roleId, String roleName, Long privilegeGroupId, String privilegeGroupName) {
        return FleaRoleModulePOJOUtils.newRoleRelPrivilegeGroupPOJO(roleId, roleName, privilegeGroupId, privilegeGroupName);
    }

    /**
     * 新建角色关联角色POJO对象
     *
     * @param roleId      角色编号
     * @param roleName    角色名称
     * @param relRoleId   关联角色编号
     * @param relRoleName 关联角色名称
     * @return 角色关联角色POJO对象
     * @since 2.0.0
     */
    public static FleaRoleRelPOJO newRoleRelRolePOJO(Long roleId, String roleName, Long relRoleId, String relRoleName) {
        return FleaRoleModulePOJOUtils.newRoleRelRolePOJO(roleId, roleName, relRoleId, relRoleName);
    }

    /**
     * 新建角色组关联角色POJO对象
     *
     * @param roleGroupId   角色组编号
     * @param roleGroupName 角色组名称
     * @param roleId        角色编号
     * @param roleName      角色名称
     * @return 角色组关联角色POJO对象
     * @since 2.0.0
     */
    public static FleaRoleGroupRelPOJO newRoleGroupRelRolePOJO(Long roleGroupId, String roleGroupName, Long roleId, String roleName) {
        return FleaRoleModulePOJOUtils.newRoleGroupRelRolePOJO(roleGroupId, roleGroupName, roleId, roleName);
    }

    /**
     * 新建Flea权限POJO对象
     *
     * @param privilegeName 权限名称
     * @param privilegeDesc 权限描述
     * @param remarks       备注
     * @return Flea权限POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJO(String privilegeName, String privilegeDesc, Long groupId, String remarks) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegePOJO(privilegeName, privilegeDesc, groupId, remarks);
    }

    /**
     * 新建Flea权限组POJO对象
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param remarks            备注
     * @return Flea权限组POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJO(String privilegeGroupName, String privilegeGroupDesc, String remarks) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
    }

    /**
     * 新建Flea权限组POJO对象【与菜单访问相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与菜单访问相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForMenu(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeGroupPOJOForMenu(privilegeGroupName, privilegeGroupDesc, isMain, remarks);
    }

    /**
     * 新建Flea权限组POJO对象【与执行操作相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForOperation(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeGroupPOJOForOperation(privilegeGroupName, privilegeGroupDesc, isMain, remarks);
    }

    /**
     * 新建Flea权限组POJO对象【与展示元素相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForElement(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeGroupPOJOForElement(privilegeGroupName, privilegeGroupDesc, isMain, remarks);
    }

    /**
     * 新建Flea权限组POJO对象【与调用资源相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForResource(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeGroupPOJOForResource(privilegeGroupName, privilegeGroupDesc, isMain, remarks);
    }

    /**
     * 新建权限组关联权限POJO对象
     *
     * @param privilegeGroupId   权限组编号
     * @param privilegeGroupName 权限组名称
     * @param privilegeId        权限编号
     * @param privilegeName      权限名称
     * @return 权限组关联权限POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupRelPOJO newPrivilegeGroupRelPrivilegePOJO(Long privilegeGroupId, String privilegeGroupName, Long privilegeId, String privilegeName) {
        return FleaPrivilegeModulePOJOUtils.newPrivilegeGroupRelPrivilegePOJO(privilegeGroupId, privilegeGroupName, privilegeId, privilegeName);
    }

    /**
     * 新建权限组关联权限POJO对象
     *
     * @param fleaPrivilegeGroup Flea权限组数据
     * @param fleaPrivilege      Flea权限数据
     * @return 权限组关联权限POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupRelPOJO newPrivilegeGroupRelPrivilegePOJO(FleaPrivilegeGroup fleaPrivilegeGroup, FleaPrivilege fleaPrivilege) {
        return FleaPrivilegeModulePOJOUtils.newPrivilegeGroupRelPrivilegePOJO(fleaPrivilegeGroup, fleaPrivilege);
    }

    /**
     * 新建Flea权限POJO对象【与访问菜单相关】
     *
     * @param menuName 菜单名称
     * @return Flea权限POJO对象【与访问菜单相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForMenu(String menuName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegePOJOForMenu(menuName, fleaPrivilegeGroup);
    }

    /**
     * 新建Flea权限关联POJO对象【与访问菜单相关】
     *
     * @param privilegeId 权限编号
     * @param relId       关联编号【这里是菜单编号】
     * @param menuName    菜单名称
     * @return Flea权限关联POJO对象【与访问菜单相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelMenuPOJO(Long privilegeId, Long relId, String menuName) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeRelMenuPOJO(privilegeId, relId, menuName);
    }

    /**
     * 新建Flea权限POJO对象【与执行操作相关】
     *
     * @param operationName 操作名称
     * @return Flea权限POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForOperation(String operationName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegePOJOForOperation(operationName, fleaPrivilegeGroup);
    }

    /**
     * 新建Flea权限关联POJO对象【与执行操作相关】
     *
     * @param privilegeId   权限编号
     * @param relId         关联编号【这里是操作编号】
     * @param operationName 操作名称
     * @return Flea权限关联POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelOperationPOJO(Long privilegeId, Long relId, String operationName) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeRelOperationPOJO(privilegeId, relId, operationName);
    }

    /**
     * 新建Flea权限POJO对象【与展示元素相关】
     *
     * @param elementName 元素名称
     * @return Flea权限POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForElement(String elementName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegePOJOForElement(elementName, fleaPrivilegeGroup);
    }

    /**
     * 新建Flea权限关联POJO对象【与展示元素相关】
     *
     * @param privilegeId 权限编号
     * @param relId       关联编号【这里是元素编号】
     * @param elementName 元素名称
     * @return Flea权限关联POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelElementPOJO(Long privilegeId, Long relId, String elementName) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeRelElementPOJO(privilegeId, relId, elementName);
    }

    /**
     * 新建Flea权限POJO对象【与调用资源相关】
     *
     * @param resourceName 资源名称
     * @return Flea权限POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForResource(String resourceName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegePOJOForResource(resourceName, fleaPrivilegeGroup);
    }

    /**
     * 新建Flea权限关联POJO对象【与调用资源相关】
     *
     * @param privilegeId  权限编号
     * @param relId        关联编号【这里是资源编号】
     * @param resourceName 资源名称
     * @return Flea权限关联POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelResourcePOJO(Long privilegeId, Long relId, String resourceName) {
        return FleaPrivilegeModulePOJOUtils.newFleaPrivilegeRelResourcePOJO(privilegeId, relId, resourceName);
    }

    /**
     * 新建功能扩展属性POJO对象
     *
     * @param attrCode  属性码
     * @param attrValue 属性值
     * @param attrDesc  属性描述
     * @param remarks   备注
     * @return 功能扩展属性POJO对象
     * @since 2.0.0
     */
    public static FleaFunctionAttrPOJO newFleaFunctionAttrPOJO(String attrCode, String attrValue, String attrDesc, String remarks) {
        return FleaFunctionModulePOJOUtils.newFleaFunctionAttrPOJO(attrCode, attrValue, attrDesc, remarks);
    }

    /**
     * 新建功能扩展属性【归属系统】
     *
     * @param systemId   系统编号
     * @param systemName 系统名称
     * @return 功能扩展属性【归属系统】
     * @since 2.0.0
     */
    public static FleaFunctionAttrPOJO newSystemInUseAttr(String systemId, String systemName) {
        return FleaFunctionModulePOJOUtils.newSystemInUseAttr(systemId, systemName);
    }

    /**
     * 新建功能扩展属性【操作类型】
     *
     * @param attrValue 属性值
     * @param attrDesc  属性描述
     * @return 功能扩展属性【操作类型】
     * @since 2.0.0
     */
    public static FleaFunctionAttrPOJO newOperationTypeAttr(String attrValue, String attrDesc) {
        return FleaFunctionModulePOJOUtils.newOperationTypeAttr(attrValue, attrDesc);
    }
}
