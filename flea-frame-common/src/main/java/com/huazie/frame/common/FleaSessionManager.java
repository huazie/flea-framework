package com.huazie.frame.common;

import com.huazie.frame.common.util.ObjectUtils;

/**
 * <p> Flea Session Manager </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaSessionManager {

    /**
     * <p> 获取当前操作账户编号 </p>
     *
     * @return 当前操作账户编号
     * @since 1.0.0
     */
    public static Long getUserId() {
        Long userId = CommonConstants.NumeralConstants.MINUS_ONE;
        IFleaUser fleaUser = FleaFrameManager.getManager().getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            userId = fleaUser.getUserId();
            if (ObjectUtils.isEmpty(userId)) {
                userId = CommonConstants.NumeralConstants.MINUS_ONE;
            }
        }
        return userId;
    }

    /**
     * <p> 获取当前操作账户编号 </p>
     *
     * @return 当前操作账户编号
     * @since 1.0.0
     */
    public static Long getAcctId() {
        Long acctId = CommonConstants.NumeralConstants.MINUS_ONE;
        IFleaUser fleaUser = FleaFrameManager.getManager().getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            acctId = fleaUser.getAcctId();
            if (ObjectUtils.isEmpty(acctId)) {
                acctId = CommonConstants.NumeralConstants.MINUS_ONE;
            }
        }
        return acctId;
    }

    /**
     * <p> 获取当前系统账户编号 </p>
     *
     * @return 当前系统账户编号
     * @since 1.0.0
     */
    public static Long getSystemAcctId() {
        Long systemAcctId = CommonConstants.NumeralConstants.MINUS_ONE;
        IFleaUser fleaUser = FleaFrameManager.getManager().getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            systemAcctId = fleaUser.getSystemAcctId();
            if (ObjectUtils.isEmpty(systemAcctId)) {
                systemAcctId = CommonConstants.NumeralConstants.MINUS_ONE;
            }
        }
        return systemAcctId;
    }

    /**
     * <p> 获取用户其他信息 </p>
     *
     * @param key   其他信息键
     * @param clazz 其他信息Class对象
     * @param <T>   其他信息类型
     * @return 用户其他信息
     * @since 1.0.0
     */
    public static <T> T get(String key, Class<T> clazz) {
        T t = null;
        IFleaUser fleaUser = FleaFrameManager.getManager().getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            t = fleaUser.get(key, clazz);
        }
        return t;
    }

}
