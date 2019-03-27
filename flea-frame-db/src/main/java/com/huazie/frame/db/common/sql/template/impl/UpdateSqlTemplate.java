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
import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * 更新SQL模板, 用于使用原生SQL实现分表;<br/>
 * 更新模板定义配置在<b>flea-sql-template.xml</b>;<br/>
 * 节点<b>{@code<update>}</b>下即为更新SQL模板:
 * <pre>
 * (1) 模板配置信息：
 *  {@code<template id="update" name="更新模板" desc="用于原生SQL中更新语句的使用">
 *   <property key="template" value="UPDATE ##table## SET ##columns## WHERE ##conditions##"/>
 *   <property key="table" value="student"/>
 *   <property key="columns" value="para1 = :para1, para2 = :para2"/>
 *   <property key="conditions" value="para_id = :paraId and para_type = :paraType"/>
 *  </template>}<br/>
 * (2)使用示例<br/>
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new UpdateSqlTemplate<Student>();
 *  sqlTemplate.setId("update");//这个对应{@code<template id="update" >}
 *  sqlTemplate.setTableName("student");// 实体类对应的表名
 *  sqlTemplate.setEntity(student);// 实体类的实例对象
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate<Student> sqlTemplate = new UpdateSqlTemplate<Student>("update", student);
 *  sqlTemplate.initialize();// 模板初始化<br/>
 *  示例3：
 *  ITemplate<Student> sqlTemplate = new UpdateSqlTemplate<FleaParaDetail>("update", "student", student);
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
public class UpdateSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = 7689029751124562960L;

    public UpdateSqlTemplate() {
        templateType = TemplateTypeEnum.UPDATE;
    }

    public UpdateSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.UPDATE;
    }

    public UpdateSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.UPDATE;
    }

    @Override
    protected Template getSqlTemplate(String id) {
        return SqlTemplateConfig.getConfig().getUpdateTemplate(id);
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols,
                                   Map<String, Property> propMap) throws Exception {
        // 获取【key=columns】的属性， 存储SET子句的内容（ para1 = :para1, para2 = :para2）
        Property columns = propMap.get(SqlTemplateEnum.COLUMNS.getKey());
        // 获取【key=conditions】的属性，存储WHERE子句的内容 （para_id = :paraId and para_type = :paraType）
        Property conditions = propMap.get(SqlTemplateEnum.CONDITIONS.getKey());

        if (columns == null) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】的配置不存在）");
        }

        if (conditions == null) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=conditions】的配置不存在）");
        }

        String colStr = columns.getValue();
        String condStr = conditions.getValue();

        Map<String, String> setMap = StringUtils.split(StringUtils.split(colStr, DBConstants.SQLConstants.SQL_COMMA), DBConstants.SQLConstants.SQL_EQUAL);
        Map<String, String> whereMap = this.createConditionMap(condStr);

        // 校验【key=columns】 和 【key=conditions】的数据是否正确
        Column[] realEntityCols = this.check(entityCols, setMap, whereMap);
        this.createParamMap(params, realEntityCols);// 设置SQL参数

        StringUtils.replace(sql, this.createPlaceHolder(SqlTemplateEnum.COLUMNS.getKey()), colStr);
        StringUtils.replace(sql, this.createPlaceHolder(SqlTemplateEnum.CONDITIONS.getKey()), condStr);

    }

    /**
     * 校验【key=columns】和【key=conditions】的数据是否正确 <br/>
     * 注意： （1）set子句【key=columns】的属性列 和 where子句【key=columns】的属性列，一般来说应该不一样
     * （2）set子句【key=columns】的属性列和属性变量一一对应，where子句【key=conditions】的属性列和属性变量一一对应
     *
     * @param entityCols 实体类对象的属性集合
     * @param setMap     SET子句的map集合（key：属性列， map：属性列变量）
     * @param whereMap   WHERE子句的map集合（key：属性列， map：属性列变量）
     * @return
     * @throws Exception
     * @date 2018年1月30日
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, Map<String, String> setMap, Map<String, String> whereMap) throws Exception {
        if (setMap == null || setMap.isEmpty()) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】中的【value】不能为空）");
        }

        if (whereMap == null || whereMap.isEmpty()) {
            throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=conditions】中的【value】不能为空）");
        }

        // 校验SET子句中的属性列和属性变量是否一一对应，并获取SET子句相关的属性列集合
        Column[] setCols = this.checkOneByOne(entityCols, setMap, SqlTemplateEnum.COLUMNS);
        // 校验WHERE子句中的属性列和属性变量是否一一对应，并获取WHERE子句相关的属性列集合
        Column[] whereCols = this.checkOneByOne(entityCols, whereMap, SqlTemplateEnum.CONDITIONS);

        // 校验set子句集合的属性列 和 where子句集合的属性列, 两者应该各不相同
        if (!ArrayUtils.isEmpty(setCols) && !ArrayUtils.isEmpty(whereCols)) {
            for (Column setCol : setCols) {
                for (Column whereCol : whereCols) {
                    if (setCol.getTabColumnName().equals(whereCol.getTabColumnName())
                            && setCol.getAttrName().equals(whereCol.getAttrName())) {
                        throw new SqlTemplateException("请检查SQL模板【id=" + this.getId() + "】配置属性（【key=columns】 和【key=conditions】存在相同的项 【" + setCol.getTabColumnName() + "】）");
                    }
                }
            }
        }
        return (Column[]) ArrayUtils.addAll(setCols, whereCols);
    }

}
