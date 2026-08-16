package com.huazie.fleaframework.auth.util.pojo;

import com.huazie.fleaframework.auth.common.AccountTypeEnum;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.UserTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;

/**
 * Flea用户相关POJO工具类
 *
 * <p>该类包含用户、账户相关的POJO创建方法。</p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaUserModulePOJOUtils {

    private FleaUserModulePOJOUtils() {
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
}
