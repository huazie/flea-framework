package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaAccountPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * 权限校验工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthCheck {

    private FleaAuthCheck() {
    }

    /**
     * 校验权限关联POJO类对象
     *
     * @param fleaAuthRelPOJO 权限关联POJO类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkAuthRelPOJO(FleaAuthRelPOJO fleaAuthRelPOJO) throws CommonException {
        // 校验关联编号不能为空
        Long relId = fleaAuthRelPOJO.getRelId();
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(relId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_ID);

        // 校验关联类型不能为空
        String relType = fleaAuthRelPOJO.getRelType();
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        StringUtils.checkBlank(relType, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE);
    }

    /**
     * 校验Flea用户模块数据
     *
     * @param fleaUserModuleData Flea用户模块数据
     * @param accountId          账户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserModuleData(FleaUserModuleData fleaUserModuleData, String accountId) throws CommonException {
        // 校验Flea用户模块数据不能为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserModuleData, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAccountPOJO.class.getSimpleName());

        FleaAccount fleaAccount = fleaUserModuleData.getFleaAccount();
        // ERROR-AUTH-COMMON0000000006 账户【account_id = {0}】不存在！
        ObjectUtils.checkEmpty(fleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000006", accountId);

        FleaUser fleaUser = fleaUserModuleData.getFleaUser();
        // ERROR-AUTH-COMMON0000000007 用户【user_id = {0}】不存在！
        ObjectUtils.checkEmpty(fleaUser, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000007", fleaAccount.getUserId());
    }

    /**
     * 校验账户信息
     *
     * @param fleaAccount 账户信息
     * @param accountPwd  密码
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkAccountPwd(FleaAccount fleaAccount, String accountPwd) throws CommonException {
        if (ObjectUtils.isEmpty(fleaAccount) || StringUtils.isBlank(accountPwd) || !accountPwd.equals(fleaAccount.getAccountPwd())) {
            // ERROR-AUTH-COMMON0000000004 账号或者密码错误！
            ExceptionUtils.throwCommonException(FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000004");
        }
    }
}
