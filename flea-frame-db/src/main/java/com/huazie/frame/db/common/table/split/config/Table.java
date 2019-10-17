package com.huazie.frame.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 分表定义类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Table {

    private String name;    // 分表对应的主表名

    private String exp;     // 分表名表达式

    private String desc;    // 分表规则描述

    private Splits splits;  // 分表后缀配置列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Splits getSplits() {
        return splits;
    }

    public void setSplits(Splits splits) {
        this.splits = splits;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
