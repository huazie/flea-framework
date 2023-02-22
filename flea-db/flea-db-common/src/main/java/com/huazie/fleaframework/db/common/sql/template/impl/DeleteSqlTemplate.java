package com.huazie.fleaframework.db.common.sql.template.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplateEnum;
import com.huazie.fleaframework.db.common.sql.template.TemplateTypeEnum;
import com.huazie.fleaframework.db.common.sql.template.config.Property;
import com.huazie.fleaframework.db.common.table.pojo.Column;

import java.util.Map;

/**
 * 删除SQL模板, 用于使用原生SQL实现分表。
 * 删除SQL模板定义配置在<b>flea-sql-template.xml</b>。
 * <pre>
 * (1) 模板配置信息：
 * {@code
 *   <!-- SQL模板配置 -->
 *   <templates>
 *     <template id="delete" ruleId="delete" name="删除模板" desc="用于原生SQL中删除语句的使用">
 *       <property key="template" value="DELETE FROM ##table## WHERE ##conditions##" />
 *       <property key="type" value="delete"/>
 *     </template>
 *   </templates>
 *   <!-- SQL模板参数配置 -->
 *   <params>
 *     <param id="delete" name="SQL模板參數" desc="用于定义SQL模板中的替换参数">
 *       <!-- 表名 -->
 *       <property key="table" value="flea_config_data" />
 *       <!-- WHERE 子句 -->
 *       <property key="conditions" value="config_id = :configId: and config_state = :configState:" />
 *     </param>
 *   </params>
 *   <!-- SQL模板和模板参数关联关系配置（简称 SQL关系配置）-->
 *   <relations>
 *     <relation id="delete" templateId="delete" paramId="delete" name="SQL关系"/>
 *   </relations>
 * 	}
 *
 * (2)使用示例
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new DeleteSqlTemplate<Student>();
 *  // 这个对应{@code <relation id="delete" .../>}
 *  sqlTemplate.setId("delete");
 *  // 实体类对应的表名
 *  sqlTemplate.setTableName("student");
 *  // 实体类的实例对象
 *  sqlTemplate.setEntity(student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate<Student> sqlTemplate = new DeleteSqlTemplate<Student>("delete", student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例3：
 *  ITemplate<Student> sqlTemplate = new DeleteSqlTemplate<Student>("delete", "student", student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  (3) 模板初始化后，就可以获取原生SQL和相关参数了，如下：
 *  // 获取原生SQL
 *  String sql = sqlTemplate.toNativeSql();
 *  // 获取相关参数
 *  Map<String, Object> paramMap = sqlTemplate.toNativeParams();
 * </pre>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeleteSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = 1270557406520049504L;

    /**
     * DELETE模板构造方法, 参考示例1
     *
     * @since 1.0.0
     */
    public DeleteSqlTemplate() {
        templateType = TemplateTypeEnum.DELETE;
    }

    /**
     * DELETE模板构造方法，参考示例2
     *
     * @param id     关系编号
     * @param entity 实体类的实例对象
     * @since 1.0.0
     */
    public DeleteSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.DELETE;
    }

    /**
     * DELETE模板构造方法，参考示例3
     *
     * @param id        关系编号
     * @param tableName 表名
     * @param entity    实体类的实例对象
     * @since 1.0.0
     */
    public DeleteSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.DELETE;
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols, Map<String, Property> propMap) throws CommonException {
        // 获取【key=conditions】的属性，存储WHERE子句部分的内容 （para_id = :paraId and para_type = :paraType）
        String condStr = checkProperty(propMap, SqlTemplateEnum.CONDITIONS);
        // 构建条件Map集合
        Map<String, String> whereMap = createConditionMap(condStr);
        // 校验【key=conditions】的数据是否正确
        Column[] realEntityCols = check(entityCols, whereMap);
        // 设置SQL参数
        createParamMap(params, realEntityCols);
        // 替换SQL模板中的conditions关键字内容
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.CONDITIONS.getKey()), condStr);
    }

    /**
     * 校验【key=conditions】的数据是否正确 <br/>
     * 注意： （1） where子句的属性列和属性变量一一对应
     *
     * @param entityCols 实体类对象的属性数组
     * @param whereMap   WHERE子句的map集合（key：属性列， map：属性列变量）
     * @return where子句对应的实体类对象的属性数组
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, Map<String, String> whereMap) throws CommonException {

        // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
        MapUtils.checkEmpty(whereMap, SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.CONDITIONS.getKey());

        // 校验WHERE子句中的属性列和属性变量是否一一对应，并获取WHERE子句相关的属性列集合
        return checkOneByOne(entityCols, whereMap, SqlTemplateEnum.CONDITIONS);
    }

}
