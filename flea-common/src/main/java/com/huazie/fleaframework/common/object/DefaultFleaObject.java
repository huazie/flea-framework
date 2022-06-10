package com.huazie.fleaframework.common.object;

/**
 * 默认的 Flea 对象实现类，用于包装其实际的 T 对象实例。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultFleaObject<T> implements FleaObject<T> {

    private final T object;

    public DefaultFleaObject(T object) {
        this.object = object;
    }

    @Override
    public T getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "FleaObject[" + this.object + "]";
    }
}
