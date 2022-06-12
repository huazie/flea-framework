package com.huazie.fleaframework.common.pool;

/**
 * Flea对象池构建者
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaObjectPoolBuilder {

    /**
     * 构建Flea对象池
     *
     * @param poolName 对象池名
     * @return Flea对象池实例
     * @since 1.0.0
     */
    FleaObjectPool build(String poolName);

}
