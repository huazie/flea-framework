package com.huazie.frame.db.common.sql.template;

/**
 * <p> 模板类型枚举 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public enum TemplateTypeEnum {

    INSERT("insert", "插入模板类型"),
    UPDATE("update", "更新模板类型"),
    SELECT("select", "查询模板类型"),
    DELETE("delete", "删除模板类型");

    private String key;
    private String desc;

    TemplateTypeEnum(String key, String desc) {
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
