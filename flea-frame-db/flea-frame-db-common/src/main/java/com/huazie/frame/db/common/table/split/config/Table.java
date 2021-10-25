package com.huazie.frame.db.common.table.split.config;

import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分表配置定义，参考 flea-table-split.xml 中 {@code <table></table>}
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Table {

    private String name;    // 分表对应的主表名

    private String lib;     // 分表对应模板库名

    private String exp;     // 分表名表达式

    private String desc;    // 分表规则描述

    private Splits splits;  // 分表转换实现集

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
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
        if (ObjectUtils.isEmpty(splits)) {
            splits = new Splits();
        }
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
