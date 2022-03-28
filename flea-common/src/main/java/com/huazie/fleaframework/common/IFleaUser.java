package com.huazie.fleaframework.common;

import java.util.Map;

/**
 * Flea 用户信息接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUser {

    /**
     * 获取用户编号
     *
     * @return 用户编号
     * @since 1.0.0
     */
    Long getUserId();

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     * @since 1.0.0
     */
    void setUserId(Long userId);

    /**
     * 获取账户编号
     *
     * @return 账户编号
     * @since 1.0.0
     */
    Long getAccountId();

    /**
     * 设置账户编号
     *
     * @param accountId 账户编号
     * @since 1.0.0
     */
    void setAccountId(Long accountId);

    /**
     * 获取系统账户编号
     *
     * @return 系统账户编号
     * @since 1.0.0
     */
    Long getSystemAccountId();

    /**
     * 设置系统账户编号
     *
     * @param systemAccountId 系统账户编号
     * @since 1.0.0
     */
    void setSystemAccountId(Long systemAccountId);

    /**
     * 获取用户其他属性信息
     *
     * @param key   其他属性信息键
     * @param clazz 其他属性信息Class对象
     * @param <T>   其他属性信息类型
     * @return 用户其他属性信息
     * @since 1.0.0
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 获取用户其他属性信息
     *
     * @param key 其他属性信息键
     * @return 用户其他属性信息
     * @since 1.0.0
     */
    Object get(String key);

    /**
     * 设置用户其他属性信息
     *
     * @param key   其他属性信息键
     * @param value 其他属性信息值
     * @since 1.0.0
     */
    void set(String key, Object value);

    /**
     * 添加多个用户属性信息
     *
     * @param otherMap 其他属性信息Map
     * @since 2.0.0
     */
    void addAll(Map<String, Object> otherMap);

    /**
     * 用户信息转Map
     *
     * @return 用户信息Map集合
     * @since 1.0.0
     */
    Map<String, Object> toMap();
}
