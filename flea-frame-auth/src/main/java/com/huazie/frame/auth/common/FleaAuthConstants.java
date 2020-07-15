package com.huazie.frame.auth.common;

/**
 * <p> Flea Auth 常量 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaAuthConstants {

    /**
     * <p> 用户常量 </p>
     *
     * @since 1.0.0
     */
    interface UserConstants {

        Integer LOGIN_STATE_1 = 1; // 登录中

        Integer LOGIN_STATE_2 = 2; // 已退出
    }

    /**
     * <p> 功能常量 </p>
     *
     * @since 1.0.0
     */
    interface FunctionConstants {

        /**
         * <p> 正在使用中的系统 </p>
         */
        String MENU_ATTR_CODE_SYSTEM_IN_USE = "SYSTEM_IN_USE";
    }

    /**
     * <p> 权限常量 </p>
     *
     * @since 1.0.0
     */
    interface PrivilegeConstants {

    }

    /**
     * <p> 角色常量 </p>
     *
     * @since 1.0.0
     */
    interface RoleConstants {

    }

}
