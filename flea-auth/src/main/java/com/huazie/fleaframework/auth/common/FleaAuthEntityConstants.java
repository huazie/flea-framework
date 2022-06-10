package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.common.FleaEntityConstants;

/**
 * Flea Auth 实体常量
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaAuthEntityConstants extends FleaEntityConstants {

    /**
     * 用户实体常量
     *
     * @since 1.0.0
     */
    interface UserEntityConstants {
        /**
         * 操作账户编号
         */
        String E_ACCOUNT_ID = "accountId";

        /**
         * 系统账号编号
         */
        String E_SYSTEM_ACCOUNT_ID = "systemAccountId";

        /**
         * 账号
         */
        String E_ACCOUNT_CODE = "accountCode";

        /**
         * 密码
         */
        String E_ACCOUNT_PWD = "accountPwd";

        /**
         * 账户状态
         */
        String E_ACCOUNT_STATE = "accountState";

        /**
         * 用户编号
         */
        String E_USER_ID = "userId";

        /**
         * 用户昵称
         */
        String E_USER_NAME = "userName";

        /**
         * 用户组编号
         */
        String E_USER_GROUP_ID = "userGroupId";

        /**
         * 用户状态
         */
        String E_USER_STATE = "userState";

        /**
         * 实名编号
         */
        String E_REAL_NAME_ID = "realNameId";
        /**
         * 实名状态
         */
        String E_REAL_NAME_STATE = "realNameState";

        /**
         * 登录状态
         */
        String E_LOGIN_STATE = "loginState";

        /**
         * 登录时间
         */
        String E_LOGIN_TIME = "loginTime";
    }

    /**
     * 功能实体常量
     *
     * @since 1.0.0
     */
    interface FunctionEntityConstants {

        /**
         * 功能编号
         */
        String E_FUNCTION_ID = "functionId";

        /**
         * 功能类型
         */
        String E_FUNCTION_TYPE = "functionType";

        /**
         * 菜单编号
         */
        String E_MENU_ID = "menuId";

        /**
         * 菜单编码
         */
        String E_MENU_CODE = "menuCode";

        /**
         * 菜单名称
         */
        String E_MENU_NAME = "menuName";

        /**
         * 菜单FontAwesome小图标
         */
        String E_MENU_ICON = "menuIcon";

        /**
         * 同一个父菜单下的菜单展示顺序
         */
        String E_MENU_SORT = "menuSort";

        /**
         * 菜单对应页面
         */
        String E_MENU_VIEW = "menuView";

        /**
         * 菜单层级
         */
        String E_MENU_LEVEL = "menuLevel";

        /**
         * 菜单状态
         */
        String E_MENU_STATE = "menuState";

        /**
         * 父菜单编号
         */
        String E_PARENT_ID = "parentId";
    }

    /**
     * 权限实体常量
     *
     * @since 1.0.0
     */
    interface PrivilegeEntityConstants {

        /**
         * 权限编号
         */
        String E_PRIVILEGE_ID = "privilegeId";

        /**
         * 权限组编号
         */
        String E_PRIVILEGE_GROUP_ID = "privilegeGroupId";

        /**
         * 权限名称
         */
        String E_PRIVILEGE_NAME = "privilegeName";

        /**
         * 权限组名称
         */
        String E_PRIVILEGE_GROUP_NAME = "privilegeGroupName";
    }

    /**
     * 角色实体常量
     *
     * @since 1.0.0
     */
    interface RoleEntityConstants {

        /**
         * 角色编号
         */
        String E_ROLE_ID = "roleId";

        /**
         * 角色组编号
         */
        String E_ROLE_GROUP_ID = "roleGroupId";
    }

    /**
     * Flea关联表实体常量
     *
     * @since 1.0.0
     */
    interface FleaRelEntityConstants {

        /**
         * 关联编号
         */
        String E_REL_ID = "relId";

        /**
         * 关联类型
         */
        String E_REL_TYPE = "relType";

        /**
         * 关联状态
         */
        String E_REL_STATE = "relState";

    }
}
