package com.huazie.frame.db.common.sql.template.impl;

import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.tab.column.Column;
import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 插入SQL模板, 用于使用原生SQL实现分表; </p>
 * <p> 插入模板定义配置在<b>flea-sql-template.xml</b>; </p>
 * <p> 节点<b>{@code<insert>}</b>下即为插入SQL模板: </p>
 * <pre>
 * (1) 模板配置信息：
 * {@code
 *  <template id="insert" name="插入模板" desc="用于原生SQL中插入语句的使用">
 *   <property key="template" value="INSERT INTO ##table## (##columns## ) VALUES(##values## )"/>
 *   <property key="table" value="student"/>
 *   <!-- 这两个不填，表示全部使用表的字段
 *   <property key="columns" value="****"/>
 *   <property key="values" value="****"/>-->
 *  </template>} <br/>
 * (2)使用示例<br/>
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new InsertSqlTemplate<Student>();
 *  sqlTemplate.setId("insert");//这个对应{@code<template id="insert" >}
 *  sqlTemplate.setTableName("student");// 实体类对应的表名
 *  sqlTemplate.setEntity(student);// 实体类的实例对象
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate sqlTemplate = new InsertSqlTemplate<Student>("insert", student);
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  示例3：
 *  ITemplate sqlTemplate = new InsertSqlTemplate<Student>("insert", "student", student);
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  (3) 模板初始化后，就可以获取原生SQL和相关参数了，如下：<br/>
 *  String sql = sqlTemplate.toNativeSql();//获取原生SQL
 *  Map<String, Object> paramMap = sqlTemplate.toNativeParams();//获取相关参数
 * </pre>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class InsertSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = 8796257687684425547L;

    public InsertSqlTemplate() {
        templateType = TemplateTypeEnum.INSERT;
    }

    public InsertSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.INSERT;
    }

    public InsertSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.INSERT;
    }

    @Override
    protected Template getSqlTemplate(String id) {
        return SqlTemplateConfig.getConfig().getInsertTemplate(id);
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, final Column[] entityCols,
                                   Map<String, Property> propMap) throws Exception {

        // 获取【key=columns】的属性, 建议将表的字段都加上，以逗号分隔(类似  id, name)
        Property columns = propMap.get(SqlTemplateEnum.COLUMNS.getKey());
        // 获取【key=values】的属性(类似  :id, :name)
        Property values = propMap.get(SqlTemplateEnum.VALUES.getKey());

        String colStr = "";
        String valStr = "";
        if (columns == null && values == null) { // 表示将实体类的全部变量替换
            colStr = StringUtils.strCombined(entityCols, Column.COLUMN_TAB_COL_NAME, DBConstants.SQLConstants.SQL_BLANK, DBConstants.SQLConstants.SQL_COMMA);
            valStr = StringUtils.strCombined(entityCols, Column.COLUMN_ATTR_NAME, DBConstants.SQLConstants.SQL_BLANK + DBConstants.SQLConstants.SQL_COLON, DBConstants.SQLConstants.SQL_COMMA);
            this.createParamMap(params, entityCols);// 设置SQL参数
        } else if (columns != null && values != null) {
            colStr = columns.getValue();
            valStr = values.getValue();

            String[] cols = StringUtils.split(StringUtils.trim(colStr), DBConstants.SQLConstants.SQL_COMMA);
            String[] vals = StringUtils.split(StringUtils.trim(valStr), DBConstants.SQLConstants.SQL_COMMA);

            // 校验表字段列和 属性值变量(是否合法, 是否一一对应)
            Column[] realEntityCols = this.check(entityCols, cols, vals);
            this.createParamMap(params, realEntityCols);// 设置SQL参数
        } else {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】和【key=values】要么都配置 或者 要么都不配置）");
        }

        StringUtils.replace(sql, this.createPlaceHolder(SqlTemplateEnum.COLUMNS.getKey()), colStr);
        StringUtils.replace(sql, this.createPlaceHolder(SqlTemplateEnum.VALUES.getKey()), valStr);
    }

    /**
     * 校验表字段列和 属性值变量是否合法(包括 是否一一对应，是否都是必填项)
     *
     * @param entityCols 实体类对象的属性集合
     * @param cols       表属性列数组
     * @param vals       表属性列对应值数组
     * @throws Exception
     * @date 2018年1月30日
     */
    private Column[] check(final Column[] entityCols, String[] cols, String[] vals) throws Exception {
        if (ArrayUtils.isEmpty(cols)) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】中的【value】不能为空）");
        }

        if (ArrayUtils.isEmpty(vals)) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=values】中的【value】不能为空）");
        }

        if (!ArrayUtils.isSameLength(cols, vals)) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】和【key=values】中表字段个数不一致）");
        }

        List<Column> entityColsList = new ArrayList<Column>();
        for (int n = 0; n < cols.length; n++) {
            String tabColumnName = StringUtils.trim(cols[n]);//表字段名
            String attrName = StringUtils.trim(vals[n]);//该表字段对应的属性变量值 (如 :paraId )

            if (StringUtils.isBlank(attrName)) {
                throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】中的表字段【" + tabColumnName + "】对应的（【key=values】中的表字段变量为空）");
            }

            Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColumnName);
            if (column == null) {
                throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】中的表字段 【" + tabColumnName + "】不存在)");
            }

            String attrN = column.getAttrName();
            if (!attrName.equals(StringUtils.strCat(DBConstants.SQLConstants.SQL_COLON, attrN))) {
                throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】中的表字段 【" + tabColumnName + "】与（【key=values】中的表字段变量【" + attrName + "】不一一对应）");
            }
            entityColsList.add(column);
        }
        return entityColsList.toArray(new Column[0]);
    }

}
