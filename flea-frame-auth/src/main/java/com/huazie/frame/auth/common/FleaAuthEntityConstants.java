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
     * <p> 账户实体常量 </p>
     *
     * @since 1.0.0
     */
    interface AccountEntityConstants {
        /**
         * <p> 账户编号 </p>
         */
        String ACCOUNT_ID = "accountId";

        /**
         * <p> 账号 </p>
         */
        String ACCOUNT_CODE = "accountCode";

        /**
         * <p> 密码 </p>
         */
        String ACCOUNT_PWD = "accountPwd";

        /**
         * <p> 账户状态 </p>
         */
        String ACCOUNT_STATE = "accountState";
    }

    /**
     * <p> 用户实体常量 </p>
     *
     * @since 1.0.0
     */
    interface UserEntityConstants {
        /**
         * <p> 用户编号 </p>
         */
        String USER_ID = "userId";
    }


}
