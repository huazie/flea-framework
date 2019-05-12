package com.huazie.frame.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p> 分表后缀配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Split {

    private String key;         // 表类型关键字

    private String column;      // 分表列名

    private String implClass;   // 分表转换实现类

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getImplClass() {
        return implClass;
    }

    public void setImplClass(String implClass) {
        this.implClass = implClass;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
