package com.huazie.fleaframework.auth.common;

/**
 * Flea Auth 常量
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaAuthConstants {

    /**
     * 属性编码常量
     *
     * @since 1.0.0
     */
    interface AttrCodeConstants {
        // 用户类型属性编码
        String ATTR_CODE_USER_TYPE = "USER_TYPE";

        // 账户类型属性编码
        String ATTR_CODE_ACCOUNT_TYPE = "ACCOUNT_TYPE";

        // 正在使用中的系统
        String ATTR_CODE_SYSTEM_IN_USE = "SYSTEM_IN_USE";

        // 用户关联的实名编号
        String ATTR_CODE_REAL_NAME_ID = "REAL_NAME_ID";
    }

    /**
     * 用户模块常量
     *
     * @since 1.0.0
     */
    interface UserModuleConstants {

        Integer LOGIN_STATE_1 = 1; // 登录中

        Integer LOGIN_STATE_2 = 2; // 已退出

        String USER_ID = "USER_ID";

        String USER_NAME = "USER_NAME";

        String SYSTEM_USER_NAME = "SYSTEM_USER_NAME";

        String USER_SEX = "USER_SEX";

        String USER_BIRTHDAY = "USER_BIRTHDAY";

        String USER_ADDRESS = "USER_ADDRESS";

        String USER_EMAIL = "USER_EMAIL";

        String USER_PHONE = "USER_PHONE";

        String ACCOUNT_CODE = "ACCOUNT_CODE";

        String SYSTEM_ACCOUNT_CODE = "SYSTEM_ACCOUNT_CODE";

        String USER_ATTR = "USER_ATTR";

        String SYSTEM_USER_ATTR = "SYSTEM_USER_ATTR";

        String ACCOUNT_ATTR = "ACCOUNT_ATTR";

        String SYSTEM_ACCOUNT_ATTR = "SYSTEM_ACCOUNT_ATTR";

    }

    /**
     * 功能模块常量
     *
     * @since 1.0.0
     */
    interface FunctionModuleConstants {

    }

    /**
     * 权限模块常量
     *
     * @since 1.0.0
     */
    interface PrivilegeModuleConstants {

    }

    /**
     * 角色模块常量
     *
     * @since 1.0.0
     */
    interface RoleModuleConstants {

    }

}
