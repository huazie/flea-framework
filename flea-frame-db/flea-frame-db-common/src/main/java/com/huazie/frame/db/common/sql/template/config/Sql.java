package com.huazie.frame.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SQL模板定义，参考 flea-sql-template.xml 中 {@code <sql></sql>}
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Sql {

    private Rules rules;        // 规则校验集合

    private Templates templates;// SQL模板集合

    private Params params;      // SQL模板参数集合

    private Relations relations;// SQL模板和模板参数关联关系集合

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public Templates getTemplates() {
        return templates;
    }

    public void setTemplates(Templates templates) {
        this.templates = templates;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Relations getRelations() {
        return relations;
    }

    public void setRelations(Relations relations) {
        this.relations = relations;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
