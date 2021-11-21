package com.huazie.fleaframework.common.pool;

/**
 * <p> Flea对象池构建者 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaObjectPoolBuilder {

    /**
     * <p> 构建Flea对象池 </p>
     *
     * @param poolName 对象池名
     * @return Flea对象池实例
     * @since 1.0.0
     */
    FleaObjectPool build(String poolName);

}
