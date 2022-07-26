package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.common.AccountTypeEnum;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.UserTypeEnum;
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
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * Flea授权POJO工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaAuthPOJOUtils {

    /**
     * 新建Flea用户登录POJO对象
     *
     * @param accountCode 账号
     * @param accountPwd  密码
     * @return Flea用户登录POJO对象
     * @since 2.0.0
     */
    public static FleaUserLoginPOJO newFleaUserLoginPOJO(String accountCode, String accountPwd) {
        FleaUserLoginPOJO fleaUserLoginInfo = new FleaUserLoginPOJO();
        fleaUserLoginInfo.setAccountCode(accountCode);
        fleaUserLoginInfo.setAccountPwd(accountPwd);
        return fleaUserLoginInfo;
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
        FleaUserAttrPOJO fleaUserAttrPOJO = new FleaUserAttrPOJO();
        fleaUserAttrPOJO.setAttrCode(attrCode);
        fleaUserAttrPOJO.setAttrValue(attrValue);
        fleaUserAttrPOJO.setAttrDesc(attrDesc);
        return fleaUserAttrPOJO;
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
        FleaAccountAttrPOJO fleaAccountAttrPOJO = new FleaAccountAttrPOJO();
        fleaAccountAttrPOJO.setAttrCode(attrCode);
        fleaAccountAttrPOJO.setAttrValue(attrValue);
        fleaAccountAttrPOJO.setAttrDesc(attrDesc);
        return fleaAccountAttrPOJO;
    }

    /**
     * 新建用户扩展属性POJO对象【用户类型=操作用户】
     *
     * @return 用户扩展属性POJO对象【用户类型=操作用户】
     * @since 2.0.0
     */
    public static FleaUserAttrPOJO newOperatorUserAttr() {
        String userAttrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_USER_TYPE;
        String userAttrValue = UserTypeEnum.OPERATOR_USER.getType();
        String userAttrDesc = UserTypeEnum.OPERATOR_USER.getDesc();
        return newFleaUserAttrPOJO(userAttrCode, userAttrValue, userAttrDesc);
    }

    /**
     * 新建账户扩展属性POJO对象【账户类型=操作账户】
     *
     * @return 账户扩展属性POJO对象【账户类型=操作账户】
     * @since 2.0.0
     */
    public static FleaAccountAttrPOJO newOperatorAccountAttr() {
        String accountAttrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_ACCOUNT_TYPE;
        String accountAttrValue = AccountTypeEnum.OPERATOR_ACCOUNT.getType();
        String accountAttrDesc = AccountTypeEnum.OPERATOR_ACCOUNT.getDesc();
        return newFleaAccountAttrPOJO(accountAttrCode, accountAttrValue, accountAttrDesc);
    }

    /**
     * 新建用户扩展属性POJO对象【用户类型=系统用户】
     *
     * @return 用户扩展属性POJO对象【用户类型=系统用户】
     * @since 2.0.0
     */
    public static FleaUserAttrPOJO newSystemUserAttr() {
        String userAttrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_USER_TYPE;
        String userAttrValue = UserTypeEnum.SYSTEM_USER.getType();
        String userAttrDesc = UserTypeEnum.SYSTEM_USER.getDesc();
        return newFleaUserAttrPOJO(userAttrCode, userAttrValue, userAttrDesc);
    }

    /**
     * 新建账户扩展属性POJO对象【账户类型=系统账户】
     *
     * @return 账户扩展属性POJO对象【账户类型=系统账户】
     * @since 2.0.0
     */
    public static FleaAccountAttrPOJO newSystemAccountAttr() {
        String accountAttrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_ACCOUNT_TYPE;
        String accountAttrValue = AccountTypeEnum.SYSTEM_ACCOUNT.getType();
        String accountAttrDesc = AccountTypeEnum.SYSTEM_ACCOUNT.getDesc();
        return newFleaAccountAttrPOJO(accountAttrCode, accountAttrValue, accountAttrDesc);
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
        FleaUserRelPOJO fleaUserRelPOJO = new FleaUserRelPOJO();
        fleaUserRelPOJO.setUserId(userId);
        fleaUserRelPOJO.setRelId(roleId);
        fleaUserRelPOJO.setRelType(AuthRelTypeEnum.USER_REL_ROLE.getRelType());
        // 用户【{0}】绑定【{1}】角色
        fleaUserRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-USER0000000001", new String[]{userName, roleName}));
        return fleaUserRelPOJO;
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
        FleaUserRelPOJO fleaUserRelPOJO = new FleaUserRelPOJO();
        fleaUserRelPOJO.setUserId(userId);
        fleaUserRelPOJO.setRelId(roleGroupId);
        fleaUserRelPOJO.setRelType(AuthRelTypeEnum.USER_REL_ROLE_GROUP.getRelType());
        // 用户【{0}】绑定【{1}】角色组
        fleaUserRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-USER0000000002", new String[]{userName, roleGroupName}));
        return fleaUserRelPOJO;
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
        FleaUserGroupRelPOJO fleaUserGroupRelPOJO = new FleaUserGroupRelPOJO();
        fleaUserGroupRelPOJO.setUserGroupId(userGroupId);
        fleaUserGroupRelPOJO.setRelId(roleId);
        fleaUserGroupRelPOJO.setRelType(AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType()); // 用户组关联角色
        // 用户组【{0}】绑定【{1}】角色
        fleaUserGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-USER0000000003", new String[]{userGroupName, roleName}));
        return fleaUserGroupRelPOJO;
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
        FleaUserGroupRelPOJO fleaUserGroupRelPOJO = new FleaUserGroupRelPOJO();
        fleaUserGroupRelPOJO.setUserGroupId(userGroupId);
        fleaUserGroupRelPOJO.setRelId(roleGroupId);
        fleaUserGroupRelPOJO.setRelType(AuthRelTypeEnum.USER_GROUP_REL_ROLE_GROUP.getRelType()); // 用户组关联角色组
        // 用户组【{0}】绑定【{1}】角色组
        fleaUserGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-USER0000000004", new String[]{userGroupName, roleGroupName}));
        return fleaUserGroupRelPOJO;
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
        FleaUserGroupRelPOJO fleaUserGroupRelPOJO = new FleaUserGroupRelPOJO();
        fleaUserGroupRelPOJO.setUserGroupId(userGroupId);
        fleaUserGroupRelPOJO.setRelId(userId);
        fleaUserGroupRelPOJO.setRelType(AuthRelTypeEnum.USER_GROUP_REL_USER.getRelType()); // 用户组关联用户
        // 用户组【{0}】绑定【{1}】用户
        fleaUserGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-USER0000000005", new String[]{userGroupName, userName}));
        return fleaUserGroupRelPOJO;
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
        FleaRoleRelPOJO fleaRoleRelPOJO = new FleaRoleRelPOJO();
        fleaRoleRelPOJO.setRoleId(roleId);
        fleaRoleRelPOJO.setRelId(privilegeId);
        fleaRoleRelPOJO.setRelType(AuthRelTypeEnum.ROLE_REL_PRIVILEGE.getRelType()); // 角色关联权限
        // 【{0}】角色绑定【{1}】权限
        fleaRoleRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000001", new String[]{roleName, privilegeName}));
        return fleaRoleRelPOJO;
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
        FleaRoleRelPOJO fleaRoleRelPOJO = new FleaRoleRelPOJO();
        fleaRoleRelPOJO.setRoleId(roleId);
        fleaRoleRelPOJO.setRelId(privilegeGroupId);
        fleaRoleRelPOJO.setRelType(AuthRelTypeEnum.ROLE_REL_PRIVILEGE_GROUP.getRelType()); // 角色关联权限组
        // 【{0}】角色绑定【{1}】权限组
        fleaRoleRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000002", new String[]{roleName, privilegeGroupName}));
        return fleaRoleRelPOJO;
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
        FleaRoleRelPOJO fleaRoleRelPOJO = new FleaRoleRelPOJO();
        fleaRoleRelPOJO.setRoleId(roleId);
        fleaRoleRelPOJO.setRelId(relRoleId);
        fleaRoleRelPOJO.setRelType(AuthRelTypeEnum.ROLE_REL_ROLE.getRelType()); // 角色关联角色
        // 【{0}】角色关联【{1}】角色
        fleaRoleRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000003", new String[]{roleName, relRoleName}));
        return fleaRoleRelPOJO;
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
        FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO = new FleaRoleGroupRelPOJO();
        fleaRoleGroupRelPOJO.setRoleGroupId(roleGroupId);
        fleaRoleGroupRelPOJO.setRelId(roleId);
        fleaRoleGroupRelPOJO.setRelType(AuthRelTypeEnum.ROLE_GROUP_REL_ROLE.getRelType()); // 角色组关联角色
        // 【{0}】角色组关联【{1}】角色
        fleaRoleGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000004", new String[]{roleGroupName, roleName}));
        return fleaRoleGroupRelPOJO;
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
        FleaPrivilegePOJO fleaPrivilegePOJO = new FleaPrivilegePOJO();
        fleaPrivilegePOJO.setPrivilegeName(privilegeName);
        String[] values = new String[]{privilegeName};
        if (StringUtils.isBlank(privilegeDesc)) {
            // 与【{0}】相关的权限
            privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000001", values);
        }
        fleaPrivilegePOJO.setPrivilegeDesc(privilegeDesc);
        if (StringUtils.isBlank(remarks)) {
            // 该权限可以用来【{0}】
            remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000002", values);
        }
        fleaPrivilegePOJO.setGroupId(groupId);
        fleaPrivilegePOJO.setRemarks(remarks);
        return fleaPrivilegePOJO;
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
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = new FleaPrivilegeGroupPOJO();
        fleaPrivilegeGroupPOJO.setPrivilegeGroupName(privilegeGroupName);
        String[] values = new String[]{privilegeGroupName};
        if (StringUtils.isBlank(privilegeGroupDesc)) {
            // 与【{0}】相关的权限归属的权限组
            privilegeGroupDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000003", values);
        }
        fleaPrivilegeGroupPOJO.setPrivilegeGroupDesc(privilegeGroupDesc);
        if (StringUtils.isBlank(remarks)) {
            // 该权限组包含了【{0}】相关的权限
            remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000004", values);
        }
        fleaPrivilegeGroupPOJO.setRemarks(remarks);
        return fleaPrivilegeGroupPOJO;
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
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        return fleaPrivilegeGroupPOJO;
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
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.OPERATION.getType());
        return fleaPrivilegeGroupPOJO;
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
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.ELEMENT.getType());
        return fleaPrivilegeGroupPOJO;
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
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.RESOURCE.getType());
        return fleaPrivilegeGroupPOJO;
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
        FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO = new FleaPrivilegeGroupRelPOJO();
        fleaPrivilegeGroupRelPOJO.setPrivilegeGroupId(privilegeGroupId);
        fleaPrivilegeGroupRelPOJO.setRelId(privilegeId);
        fleaPrivilegeGroupRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType()); // 权限组关联权限
        // 【{0}】权限组关联【{1}】权限
        fleaPrivilegeGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000005", new String[]{privilegeGroupName, privilegeName}));
        return fleaPrivilegeGroupRelPOJO;
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
        FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO = null;
        // 添加权限组关联权限
        if (ObjectUtils.isNotEmpty(fleaPrivilegeGroup) && ObjectUtils.isNotEmpty(fleaPrivilege)) {
            // 新建权限组关联权限POJO对象
            Long privilegeGroupId = fleaPrivilegeGroup.getPrivilegeGroupId();
            String privilegeGroupName = fleaPrivilegeGroup.getPrivilegeGroupName();
            Long privilegeId = fleaPrivilege.getPrivilegeId();
            String privilegeName = fleaPrivilege.getPrivilegeName();
            fleaPrivilegeGroupRelPOJO = newPrivilegeGroupRelPrivilegePOJO(privilegeGroupId, privilegeGroupName, privilegeId, privilegeName);
        }
        return fleaPrivilegeGroupRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与访问菜单相关】
     *
     * @param menuName 菜单名称
     * @return Flea权限POJO对象【与访问菜单相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForMenu(String menuName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{menuName};
        // 访问《{0}》菜单
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000006", values);
        // 拥有可以访问《{0}》菜单的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000007", values);
        // 【访问《{0}》菜单】权限对应【{0}】菜单，新增菜单时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000008", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
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
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
        // 【{0}】菜单绑定【访问《{0}》菜单】权限, 新增菜单时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000009", new String[]{menuName}));
        return fleaPrivilegeRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与执行操作相关】
     *
     * @param operationName 操作名称
     * @return Flea权限POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForOperation(String operationName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{operationName};
        // 执行《{0}》操作
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000010", values);
        // 拥有可以执行《{0}》操作的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000011", values);
        // 【执行《{0}》操作】权限对应【{0}】操作，新增操作时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000012", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
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
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_OPERATION.getRelType());
        // 【{0}】操作绑定【执行《{0}》操作】权限，新增操作时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000013", new String[]{operationName}));
        return fleaPrivilegeRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与展示元素相关】
     *
     * @param elementName 元素名称
     * @return Flea权限POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForElement(String elementName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{elementName};
        // 展示《{0}》元素
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000014", values);
        // 拥有可以展示《{0}》元素的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000015", values);
        //【展示《{0}》元素】权限对应【{0}】元素，新增元素时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000016", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
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
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_ELEMENT.getRelType());
        //【{0}】元素绑定【展示《{0}》元素】权限，新增元素时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000017", new String[]{elementName}));
        return fleaPrivilegeRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与调用资源相关】
     *
     * @param resourceName 资源名称
     * @return Flea权限POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForResource(String resourceName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{resourceName};
        // 调用《{0}》资源
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000018", values);
        // 拥有可以调用《{0}》资源的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000019", values);
        //【调用《{0}》资源】权限对应【{0}】资源，新增资源时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000020", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
    }

    /**
     * 获取权限组编号
     *
     * @param fleaPrivilegeGroup Flea权限组数据
     * @return 权限组编号
     * @since 2.0.0
     */
    private static Long getPrivilegeGroupId(FleaPrivilegeGroup fleaPrivilegeGroup) {
        if (ObjectUtils.isNotEmpty(fleaPrivilegeGroup))
            return fleaPrivilegeGroup.getPrivilegeGroupId();
        else
            return null;
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
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_RESOURCE.getRelType());
        //【{0}】资源绑定【调用《{0}》资源】权限，新增资源时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000021", new String[]{resourceName}));
        return fleaPrivilegeRelPOJO;
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
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setAttrCode(attrCode);
        fleaFunctionAttrPOJO.setAttrValue(attrValue);
        fleaFunctionAttrPOJO.setAttrDesc(attrDesc);
        if (StringUtils.isBlank(remarks)) {
            remarks = FleaI18nHelper.i18nForAuth("AUTH-FUNCTION0000000003", new String[]{attrDesc});
        }
        fleaFunctionAttrPOJO.setRemarks(remarks);
        return fleaFunctionAttrPOJO;
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
        String[] values = new String[]{systemName};
        String attrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE;
        // 归属系统【{0}】
        String attrDesc = FleaI18nHelper.i18nForAuth("AUTH-FUNCTION0000000001", values);
        // 【{0}】正在使用中
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-FUNCTION0000000002", values);
        return newFleaFunctionAttrPOJO(attrCode, systemId, attrDesc, remarks);
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
        String attrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_OPERATION_TYPE;
        return newFleaFunctionAttrPOJO(attrCode, attrValue, attrDesc, null);
    }
}
