package com.huazie.frame.cache;

/**
 * <p> Flea缓存建造者接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCacheBuilder {

    /**
     * <p> 构建Flea缓存对象 </p>
     *
     * @return Flea缓存对象
     * @since 1.0.0
     */
    AbstractFleaCache build();

}
