package com.huazie.frame.cache;

/**
 * <p> 自定义Cache接口类（主要定义了一些增删改查的方法） </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaCache {

    Object get(String key);

    void put(String key, Object value);

    void clear();

    void delete(String key);

}
