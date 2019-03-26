package com.huazie.frame.db.common.sql.template.impl;

import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.tab.column.Column;

import java.util.Map;

/**
 * 刪除SQL模板, 用于使用原生SQL实现分表;<br/>
 * 刪除模板定义配置在<b>flea-sql-template.xml</b>;<br/>
 * 节点<b>{@code<delete>}</b>下即为刪除SQL模板
 * <pre>
 * (1) 模板配置信息：
 *
 *  {@code<template id="delete" ruleId="delete" name="删除模板" desc="用于原生SQL中删除语句的使用">
 *   <property key="template" value="DELETE FROM ##table## WHERE ##conditions##" />
 *   <property key="table" value="student"/>
 *   <property key="conditions" value="para_id = :paraId" />
 *  </template>}<br/>
 * (2)使用示例<br/>
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new DeleteSqlTemplate<Student>();
 *  sqlTemplate.setId("delete");//这个对应{@code<template id="delete" >}
 *  sqlTemplate.setTableName("student");// 实体类对应的表名
 *  sqlTemplate.setEntity(student);// 实体类的实例对象
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate<Student> sqlTemplate = new DeleteSqlTemplate<Student>("delete", student);
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  示例3：
 *  ITemplate<Student> sqlTemplate = new DeleteSqlTemplate<Student>("delete", "student", student);
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  (3) 模板初始化后，就可以获取原生SQL和相关参数了，如下：<br/>
 *  String sql = sqlTemplate.toNativeSql();//获取原生SQL
 *  Map<String, Object> paramMap = sqlTemplate.toNativeParams();//获取相关参数
 * </pre>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeleteSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = 1270557406520049504L;

    public DeleteSqlTemplate() {
        templateType = TemplateTypeEnum.DELETE;
    }

    public DeleteSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.DELETE;
    }

    public DeleteSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.DELETE;
    }

    @Override
    protected Template getSqlTemplate(String id) {
        return SqlTemplateConfig.getConfig().getDeleteTemplate(id);
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols,
                                   Map<String, Property> propMap) throws Exception {
        // 获取【key=conditions】的属性，存储WHERE子句部分的内容 （para_id = :paraId and para_type = :paraType）
        Property conditions = propMap.get(SqlTemplateEnum.CONDITIONS.getKey());

        if (conditions == null) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=conditions】的配置不存在）");
        }

        String condStr = conditions.getValue();
        Map<String, String> whereMap = this.createConditionMap(condStr);

        // 校验【key=conditions】的数据是否正确
        Column[] realEntityCols = this.check(entityCols, whereMap);
        this.createParamMap(params, realEntityCols);// 设置SQL参数

        StringUtils.replace(sql, this.createPlaceHolder(SqlTemplateEnum.CONDITIONS.getKey()), condStr);

    }

    /**
     * 校验【key=conditions】的数据是否正确 <br/>
     * 注意： （1） where子句的属性列和属性变量一一对应
     *
     * @param entityCols 实体类对象的属性集合
     * @param whereMap   WHERE子句的map集合（key：属性列， map：属性列变量）
     * @return
     * @throws Exception
     * @date 2018年6月2日
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, Map<String, String> whereMap) throws Exception {

        if (whereMap == null || whereMap.isEmpty()) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=conditions】中的【value】不能为空）");
        }

        // 校验WHERE子句中的属性列和属性变量是否一一对应，并获取WHERE子句相关的属性列集合
        Column[] whereCols = this.checkOneByOne(entityCols, whereMap, SqlTemplateEnum.CONDITIONS);

        return whereCols;
    }

}
