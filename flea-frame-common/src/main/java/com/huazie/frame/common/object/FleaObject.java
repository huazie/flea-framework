package com.huazie.frame.common.object;

/**
 * <p> Flea对象包装类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaObject<T> {

    /**
     * <p> 获取Flea对象实例 {@link FleaObject} 包装的对象 </p>
     *
     * @return 被包装的对象
     * @since 1.0.0
     */
    T getObject();

    /**
     * <p> 自定义调式输出Flea对象包装类 </p>
     *
     * @return 自定义调式输出Flea对象包装类
     */
    @Override
    String toString();

}
