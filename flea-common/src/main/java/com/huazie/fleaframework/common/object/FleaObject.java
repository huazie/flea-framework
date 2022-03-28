package com.huazie.fleaframework.common.object;

/**
 * Flea 对象接口，可参考 {@code DefaultFleaObject}
 *
 * @author huazie
 * @version 1.0.0
 * @see DefaultFleaObject
 * @since 1.0.0
 */
public interface FleaObject<T> {

    /**
     * 获取 Flea 对象实例包装的对象T
     *
     * @return 被包装的对象T
     * @since 1.0.0
     */
    T getObject();

    /**
     * 自定义输出 Flea 对象实例
     *
     * @return 自定义输出 Flea 对象实例
     * @since 1.0.0
     */
    String toString();

}
