package com.huazie.fleaframework.cache;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 空缓存数据，代指了一类数据值为【{@code null}】的缓存数据。
 *
 * <p> flea-cache 允许部分数据值为【{@code null}】的缓存，
 * 以【{@code NullCache}】为值的形式存储到缓存系统中，默认有效期
 * 为300s，可通过配置修改。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class NullCache implements Serializable {

    private static final long serialVersionUID = 1912136197537033304L;

    private String key; // 缓存键

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

