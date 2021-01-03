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
     * <p> 获取当前线程的用户信息 </p>
     *
     * @return 当前线程的用户信息
     * @since 1.0.0
     */
    public static IFleaUser getUserInfo() {
        return FleaFrameManager.getManager().getUserInfo();
    }

    /**
     * <p> 设置当前线程的用户信息 </p>
     *
     * @param fleaUser 用户信息
     * @since 1.0.0
     */
    public static void setUserInfo(IFleaUser fleaUser) {
        FleaFrameManager.getManager().setUserInfo(fleaUser);
    }

    /**
     * <p> 获取当前操作账户编号 </p>
     *
     * @return 当前操作账户编号
     * @since 1.0.0
     */
    public static Long getUserId() {
        Long userId = CommonConstants.NumeralConstants.MINUS_ONE;
        IFleaUser fleaUser = getUserInfo();
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
    public static Long getAccountId() {
        Long accountId = CommonConstants.NumeralConstants.MINUS_ONE;
        IFleaUser fleaUser = getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            accountId = fleaUser.getAccountId();
            if (ObjectUtils.isEmpty(accountId)) {
                accountId = CommonConstants.NumeralConstants.MINUS_ONE;
            }
        }
        return accountId;
    }

    /**
     * <p> 获取当前系统账户编号 </p>
     *
     * @return 当前系统账户编号
     * @since 1.0.0
     */
    public static Long getSystemAccountId() {
        Long systemAccountId = CommonConstants.NumeralConstants.MINUS_ONE;
        IFleaUser fleaUser = getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            systemAccountId = fleaUser.getSystemAccountId();
            if (ObjectUtils.isEmpty(systemAccountId)) {
                systemAccountId = CommonConstants.NumeralConstants.MINUS_ONE;
            }
        }
        return systemAccountId;
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
        IFleaUser fleaUser = getUserInfo();
        if (ObjectUtils.isNotEmpty(fleaUser)) {
            t = fleaUser.get(key, clazz);
        }
        return t;
    }

}
