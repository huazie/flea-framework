package com.huazie.fleaframework.db.common.lib.split.config;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.table.split.config.Splits;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分库配置定义，参考 flea-lib-split.xml中 {@code <lib></lib>}
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class Lib {

    private String name;    // 模板库名

    private int count;      // 分库总数

    private String exp;     // 分库名表达式

    private String desc;    // 分库规则描述

    private Transaction transaction; // 分库事物配置

    private Splits splits;  // 分表后缀配置列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
