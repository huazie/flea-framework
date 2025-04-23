package com.huazie.fleaframework.auth.common;

/**
 * Flea Auth 常量
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public final class FleaAuthConstants {

    private FleaAuthConstants() {
    }

    /**
     * 属性编码常量
     *
     * @since 1.0.0
     */
    public static final class AttrCodeConstants {
        // 用户类型属性编码
        public static final String ATTR_CODE_USER_TYPE = "USER_TYPE";

        // 账户类型属性编码
        public static final String ATTR_CODE_ACCOUNT_TYPE = "ACCOUNT_TYPE";

        // 正在使用中的系统
        public static final String ATTR_CODE_SYSTEM_IN_USE = "SYSTEM_IN_USE";

        // 操作类型
        public static final String ATTR_CODE_OPERATION_TYPE = "OPERATION_TYPE";

        // 用户关联的实名编号
        public static final String ATTR_CODE_REAL_NAME_ID = "REAL_NAME_ID";
    }

    /**
     * 用户模块常量
     *
     * @since 1.0.0
     */
    public static final class UserModuleConstants {

        public static final String USER_ID = "USER_ID";

        public static final String SYSTEM_USER_ID = "SYSTEM_USER_ID";

        public static final String USER_NAME = "USER_NAME";

        public static final String SYSTEM_USER_NAME = "SYSTEM_USER_NAME";

        public static final String USER_SEX = "USER_SEX";

        public static final String USER_BIRTHDAY = "USER_BIRTHDAY";

        public static final String USER_ADDRESS = "USER_ADDRESS";

        public static final String USER_EMAIL = "USER_EMAIL";

        public static final String USER_PHONE = "USER_PHONE";

        public static final String ACCOUNT_CODE = "ACCOUNT_CODE";

        public static final String SYSTEM_ACCOUNT_CODE = "SYSTEM_ACCOUNT_CODE";

        public static final String USER_ATTR = "USER_ATTR";

        public static final String SYSTEM_USER_ATTR = "SYSTEM_USER_ATTR";

        public static final String ACCOUNT_ATTR = "ACCOUNT_ATTR";

        public static final String SYSTEM_ACCOUNT_ATTR = "SYSTEM_ACCOUNT_ATTR";

    }

    /**
     * 功能模块常量
     *
     * @since 1.0.0
     */
    public static final class FunctionModuleConstants {

    }

    /**
     * 权限模块常量
     *
     * @since 1.0.0
     */
    public static final class PrivilegeModuleConstants {

        public static final int MAIN = 1; // 主权限组

        public static final int NOT_MAIN = 0; // 非主权限组
    }

    /**
     * 角色模块常量
     *
     * @since 1.0.0
     */
    public static final class RoleModuleConstants {

    }

}
