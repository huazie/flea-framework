package com.huazie.frame.auth.util;

import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.FleaAuthRelPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;

/**
 * <p> 权限校验工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthCheck {

    private FleaAuthCheck() {
    }

    /**
     * <p> 校验权限关联POJO类对象 </p>
     *
     * @param fleaAuthRelPOJO 权限关联POJO类对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkAuthRelPOJO(FleaAuthRelPOJO fleaAuthRelPOJO) throws CommonException {
        // 校验关联编号不能为空
        Long relId = fleaAuthRelPOJO.getRelId();
        ObjectUtils.checkEmpty(relId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_ID);

        // 校验关联类型不能为空
        String relType = fleaAuthRelPOJO.getRelType();
        StringUtils.checkBlank(relType, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE);
    }
}
