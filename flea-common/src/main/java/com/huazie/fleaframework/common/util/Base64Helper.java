package com.huazie.fleaframework.common.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Base64Helper {

    private Base64Helper() {
    }

    /**
     * 获取Base64对象实例 (仅当第一次调用该方法，加载内部类，初始化Base64对象)
     *
     * @return Base64对象实例
     * @since 1.0.0
     */
    public static Base64 getInstance() {
        return Base64Holder.INSTANCE;
    }

    /**
     * 内部类，用于延迟初始化Base64对象
     *
     * @since 1.0.0
     */
    private static class Base64Holder {
        private static Base64 INSTANCE = new Base64();
    }
}
