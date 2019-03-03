package com.huazie.frame.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> SQL模板 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Sql {

    private Rules rules;    // 规则校验集合

    private Insert insert;    // 插入模板集合

    private Update update;    // 更新模板集合

    private Select select;    // 查询模板集合

    private Delete delete;    // 删除模板集合

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public Insert getInsert() {
        return insert;
    }

    public void setInsert(Insert insert) {
        this.insert = insert;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

    public Delete getDelete() {
        return delete;
    }

    public void setDelete(Delete delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
