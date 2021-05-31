package com.huazie.frame.cache;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p> 空缓存对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class NullCache implements Serializable {

    private static final long serialVersionUID = 1912136197537033304L;

    private String key; // 缓存Key

    private NullCache() {
    }

    public NullCache(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

