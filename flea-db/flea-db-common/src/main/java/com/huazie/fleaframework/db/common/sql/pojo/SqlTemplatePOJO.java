package com.huazie.fleaframework.db.common.sql.pojo;

/**
 * SQL模板POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class SqlTemplatePOJO<T> {

    private String relationId;

    private T entity;

    public SqlTemplatePOJO() {
    }

    public SqlTemplatePOJO(String relationId, T entity) {
        this.relationId = relationId;
        this.entity = entity;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
