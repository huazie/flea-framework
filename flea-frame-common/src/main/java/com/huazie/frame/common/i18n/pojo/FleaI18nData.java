package com.huazie.frame.common.i18n.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p>Flea I18n 数据</p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class FleaI18nData implements Serializable {

    private String key;

    private String value;

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

}
