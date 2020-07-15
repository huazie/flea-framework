package com.huazie.frame.auth.common;

import com.huazie.frame.common.FleaEntityConstants;

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
         * <p> 账户编号 </p>
         */
        String E_ACCOUNT_ID = "accountId";

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
    }

    /**
     * <p> 权限实体常量 </p>
     *
     * @since 1.0.0
     */
    interface PriviilegeEntityConstants {

        /**
         * <p> 权限编号 </p>
         */
        String E_PRIVILEGE_ID = "privilegeId";
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
    }
}
