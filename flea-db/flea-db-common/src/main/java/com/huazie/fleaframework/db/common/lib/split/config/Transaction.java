package com.huazie.fleaframework.db.common.lib.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分库事务配置定义，参考 flea-lib-split.xml中 {@code <transaction />}
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class Transaction {

    private String name; // 模板事务名

    private String exp; // 分库事务名表达式

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
