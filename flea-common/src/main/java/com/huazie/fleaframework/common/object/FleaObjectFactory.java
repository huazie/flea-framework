package com.huazie.fleaframework.common.object;

/**
 * Flea 对象工厂类接口，对外提供 新建 和 初始化
 * Flea 对象的 API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaObjectFactory<T> {

    /**
     * 新建一个 T 对象，并将之包装进 Flea 对象中
     *
     * @return Flea对象
     * @since 1.0.0
     */
    FleaObject<T> newObject();

    /**
     * 初始 Flea 对象相关信息（方便扩展）
     *
     * @since 1.0.0
     */
    void initObject(T t);

}
