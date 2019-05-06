package com.huazie.frame.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p> 分表定义类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Table implements Serializable {

    private static final long serialVersionUID = 6269688704177325112L;

    private String id;          //分表定义编码

    private String name;        //分表定义名

    private String entityClass; //主表实体类

    private Split split;        //分表后缀配置类

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
