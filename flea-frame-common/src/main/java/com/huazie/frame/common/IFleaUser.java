package com.huazie.frame.common;

import java.util.Map;

/**
 * <p> 用户信息接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUser {

    /**
     * <p> 获取用户编号 </p>
     *
     * @return 用户编号
     * @since 1.0.0
     */
    Long getUserId();

    /**
     * <p> 设置用户编号 </p>
     *
     * @param userId
     * @since 1.0.0
     */
    void setUserId(Long userId);

    /**
     * <p> 获取账户编号 </p>
     *
     * @return 账户编号
     * @since 1.0.0
     */
    Long getAccountId();

    /**
     * <p> 设置账户编号 </p>
     *
     * @param accountId 账户编号
     * @since 1.0.0
     */
    void setAccountId(Long accountId);

    /**
     * <p> 获取系统账户编号 </p>
     *
     * @return 系统账户编号
     * @since 1.0.0
     */
    Long getSystemAccountId();

    /**
     * <p> 设置系统账户编号 </p>
     *
     * @param systemAccountId 系统账户编号
     * @since 1.0.0
     */
    void setSystemAccountId(Long systemAccountId);

    /**
     * <p> 获取用户其他信息 </p>
     *
     * @param key   其他信息键
     * @param clazz 其他信息Class对象
     * @param <T>   其他信息类型
     * @return 用户其他信息
     * @since 1.0.0
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * <p> 获取用户其他信息 </p>
     *
     * @param key 其他信息键
     * @return 用户其他信息
     * @since 1.0.0
     */
    Object get(String key);

    /**
     * <p> 设置用户其他信息 </p>
     *
     * @param key   其他信息键
     * @param value 其他信息值
     * @since 1.0.0
     */
    void set(String key, Object value);

    /**
     * <p> 用户信息转Map </p>
     *
     * @return 用户信息Map集合
     * @since 1.0.0
     */
    Map<String, Object> toMap();
}
