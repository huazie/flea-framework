package com.huazie.fleaframework.common.i18n.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Flea I18N 数据
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaI18nData implements Serializable {

    private static final long serialVersionUID = 5073383168723394410L;

    private String key; // 国际化编码

    private String value; // 国际化数据

    public FleaI18nData() {
    }

    public FleaI18nData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
