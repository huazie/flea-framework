package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.common.FleaEntityConstants;

/**
 * <p> Flea Auth 实体常量  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaAuthEntityConstants extends FleaEntityConstants {

    /**
     * <p> 用户实体常量 </p>
     *
     * @since 1.0.0
     */
    interface UserEntityConstants {
        /**
         * <p> 操作账户编号 </p>
         */
        String E_ACCOUNT_ID = "accountId";

        /**
         * <p> 系统账号编号 </p>
         */
        String E_SYSTEM_ACCOUNT_ID = "systemAccountId";

        /**
         * <p> 账号 </p>
         */
        String E_ACCOUNT_CODE = "accountCode";

        /**
         * <p> 密码 </p>
         */
        String E_ACCOUNT_PWD = "accountPwd";

        /**
         * <p> 账户状态 </p>
         */
        String E_ACCOUNT_STATE = "accountState";

        /**
         * <p> 用户编号 </p>
         */
        String E_USER_ID = "userId";

        /**
         * <p> 用户昵称 </p>
         */
        String E_USER_NAME = "userName";

        /**
         * <p> 用户组编号 </p>
         */
        String E_USER_GROUP_ID = "userGroupId";

        /**
         * <p> 用户状态 </p>
         */
        String E_USER_STATE = "userState";

        /**
         * <p> 实名编号 </p>
         */
        String E_REAL_NAME_ID = "realNameId";
        /**
         * <p> 实名状态 </p>
         */
        String E_REAL_NAME_STATE = "realNameState";

        /**
         * <p> 登录状态 </p>
         */
        String E_LOGIN_STATE = "loginState";

        /**
         * <p> 登录时间 </p>
         */
        String E_LOGIN_TIME = "loginTime";
    }

    /**
     * <p> 功能实体常量 </p>
     *
     * @since 1.0.0
     */
    interface FunctionEntityConstants {

        /**
         * <p> 功能编号 </p>
         */
        String E_FUNCTION_ID = "functionId";

        /**
         * <p> 功能类型 </p>
         */
        String E_FUNCTION_TYPE = "functionType";

        /**
         * <p> 菜单编号 </p>
         */
        String E_MENU_ID = "menuId";

        /**
         * <p> 菜单编码 </p>
         */
        String E_MENU_CODE = "menuCode";

        /**
         * <p> 菜单名称 </p>
         */
        String E_MENU_NAME = "menuName";

        /**
         * <p> 菜单FontAwesome小图标 </p>
         */
        String E_MENU_ICON = "menuIcon";

        /**
         * <p> 同一个父菜单下的菜单展示顺序 </p>
         */
        String E_MENU_SORT = "menuSort";

        /**
         * <p> 菜单对应页面 </p>
         */
        String E_MENU_VIEW = "menuView";

        /**
         * <p> 菜单层级 </p>
         */
        String E_MENU_LEVEL = "menuLevel";

        /**
         * <p> 菜单状态 </p>
         */
        String E_MENU_STATE = "menuState";

        /**
         * <P> 父菜单编号 </P>
         */
        String E_PARENT_ID = "parentId";
    }

    /**
     * <p> 权限实体常量 </p>
     *
     * @since 1.0.0
     */
    interface PrivilegeEntityConstants {

        /**
         * <p> 权限编号 </p>
         */
        String E_PRIVILEGE_ID = "privilegeId";

        /**
         * <p> 权限组编号 </p>
         */
        String E_PRIVILEGE_GROUP_ID = "privilegeGroupId";

        /**
         * <p> 权限名称 </p>
         */
        String E_PRIVILEGE_NAME = "privilegeName";

        /**
         * <p> 权限组名称 </p>
         */
        String E_PRIVILEGE_GROUP_NAME = "privilegeGroupName";
    }

    /**
     * <p> 角色实体常量 </p>
     *
     * @since 1.0.0
     */
    interface RoleEntityConstants {

        /**
         * <p> 角色编号 </p>
         */
        String E_ROLE_ID = "roleId";

        /**
         * <p> 角色组编号 </p>
         */
        String E_ROLE_GROUP_ID = "roleGroupId";
    }

    /**
     * <p> Flea关联表实体常量 </p>
     *
     * @since 1.0.0
     */
    interface FleaRelEntityConstants {

        /**
         * <p> 关联编号 </p>
         */
        String E_REL_ID = "relId";

        /**
         * <p> 关联类型 </p>
         */
        String E_REL_TYPE = "relType";

        /**
         * <p> 关联状态 </p>
         */
        String E_REL_STATE = "relState";

    }
}
