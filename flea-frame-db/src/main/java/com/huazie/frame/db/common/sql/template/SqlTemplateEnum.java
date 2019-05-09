package com.huazie.frame.db.common.sql.template;

/**
 * <p> SQL模板枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @dsince 1.0.0
 */
public enum SqlTemplateEnum {

    SQL("sql", "rule校验规则关键字"),
    TEMPLATE("template", "SQL模板定义关键字"),
    TABLE("table", "表名定义关键字"),
    COLUMNS("columns", "列定义关键字"),
    VALUES("values", "值定义关键字"),
    SETS("sets", "SET定义关键字"),
    CONDITIONS("conditions", "条件定义关键字"),
    PLACEHOLDER("##", "替换符号");

    private String key;  // 模板关键字
    private String desc; // 模板关键字描述

    SqlTemplateEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

}
