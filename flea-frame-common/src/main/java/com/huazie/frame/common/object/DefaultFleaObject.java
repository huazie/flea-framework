package com.huazie.frame.common.object;

/**
 * <p> 默认的Flea对象实现类 </p>
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
        final StringBuilder result = new StringBuilder();
        result.append("FleaObject[");
        result.append(this.object);
        result.append("]");
        return result.toString();
    }
}
