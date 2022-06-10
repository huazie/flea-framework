package com.huazie.fleaframework.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 定义SQL模板和模板参数关联关系
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Relation {

    private String id;          // 关系编号

    private String templateId;  // SQL模板编号

    private String paramId;     // SQL模板参数编号

    private String name;        // 关系名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
