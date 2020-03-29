package com.huazie.frame.common;

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
    Long getAcctId();

    /**
     * <p> 设置账户编号 </p>
     *
     * @param acctId 账户编号
     * @since 1.0.0
     */
    void setAcctId(Long acctId);

    /**
     * <p> 获取系统账户编号 </p>
     *
     * @return 系统账户编号
     * @since 1.0.0
     */
    Long getSystemAcctId();

    /**
     * <p> 设置系统账户编号 </p>
     *
     * @param systemAcctId 系统账户编号
     * @since 1.0.0
     */
    void setSystemAcctId(Long systemAcctId);

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

}
