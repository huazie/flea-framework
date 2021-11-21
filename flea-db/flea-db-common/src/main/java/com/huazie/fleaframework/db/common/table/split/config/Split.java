package com.huazie.fleaframework.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分表转换或分库转换配置定义，参考 flea-table-split.xml 或
 * flea-lib-split.xml 中 {@code <split />}
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Split {

    private String key;         // 分库或分表转换类型关键字

    private String column;      // 分表列名

    private String seq;         // 分库序列键

    private String implClass;   // 分表或分库转换实现类

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

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
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
