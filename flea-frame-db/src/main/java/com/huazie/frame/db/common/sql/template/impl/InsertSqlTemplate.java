package com.huazie.frame.db.common.sql.template.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.table.pojo.Column;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 插入SQL模板, 用于使用原生SQL实现分表; </p>
 * <p> 插入模板定义配置在<b>flea-sql-template.xml</b>; </p>
 * <p> 节点<b>{@code <insert>}</b>下即为插入SQL模板: </p>
 * <pre>
 * (1) 模板配置信息：
 * {@code
 *   <!-- SQL模板配置 -->
 *   <templates>
 *     <template id="insert" ruleId="insert" name="INSERT SQL模板" desc="用于原生SQL中INSERT语句的使用">
 *       <property key="template" value="INSERT INTO ##table## (##columns## ) VALUES (##values## )" />
 *       <property key="type" value="insert"/>
 *     </template>
 *   </templates>
 *   <!-- SQL模板参数配置 -->
 *   <params>
 *     <param id="insert" name="SQL模板參數" desc="用于定义SQL模板中的替换参数">
 *       <property key="table" value="flea_para_detail" />
 *       <!-- 这两个不填，表示表的字段全部使用
 *       <property key="columns" value="para_id, para_type, para_code, para_name, para1, para_state" />
 *       <property key="values" value=":paraId, :paraType, :paraCode, :paraName, :para1, :paraState" />-->
 *     </param>
 *   </params>
 *   <!-- SQL模板和模板参数关联关系配置（简称 SQL关系配置）-->
 *   <relations>
 *     <relation id="insert" templateId="insert" paramId="insert" name="SQL关系"/>
 *   </relations>
 * 	}
 *
 * (2)使用示例
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new InsertSqlTemplate<Student>();
 *  // 这个对应{@code <relation id="insert" .../>}
 *  sqlTemplate.setId("insert");
 *  // 实体类对应的表名
 *  sqlTemplate.setTableName("student");
 *  // 实体类的实例对象
 *  sqlTemplate.setEntity(student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate<Student> sqlTemplate = new InsertSqlTemplate<Student>("insert", student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例3：
 *  ITemplate<Student> sqlTemplate = new InsertSqlTemplate<Student>("insert", "student", student);
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
public class InsertSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = 8796257687684425547L;

    /**
     * <p> INSERT模板构造方法, 参考示例1 </p>
     *
     * @since 1.0.0
     */
    public InsertSqlTemplate() {
        templateType = TemplateTypeEnum.INSERT;
    }

    /**
     * <p> INSERT模板构造方法，参考示例2 </p>
     *
     * @param id     关系编号
     * @param entity 实体类的实例对象
     * @since 1.0.0
     */
    public InsertSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.INSERT;
    }

    /**
     * <p> INSERT模板构造方法，参考示例3 </p>
     *
     * @param id        关系编号
     * @param tableName 表名
     * @param entity    实体类的实例对象
     * @since 1.0.0
     */
    public InsertSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.INSERT;
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, final Column[] entityCols, Map<String, Property> propMap) throws SqlTemplateException {

        // 获取【key=columns】的属性, 建议将表的字段都加上，以逗号分隔(类似  id, name)
        Property columns = propMap.get(SqlTemplateEnum.COLUMNS.getKey());
        // 获取【key=values】的属性(类似  :id, :name)
        Property values = propMap.get(SqlTemplateEnum.VALUES.getKey());

        String colStr;
        String valStr;
        if (ObjectUtils.isEmpty(columns) && ObjectUtils.isEmpty(values)) { // 表示将实体类的全部变量替换
            colStr = StringUtils.strCombined(entityCols, Column.COLUMN_TAB_COL_NAME, DBConstants.SQLConstants.SQL_BLANK, DBConstants.SQLConstants.SQL_COMMA);
            valStr = StringUtils.strCombined(entityCols, Column.COLUMN_ATTR_NAME, DBConstants.SQLConstants.SQL_BLANK + DBConstants.SQLConstants.SQL_COLON, DBConstants.SQLConstants.SQL_COMMA);
            createParamMap(params, entityCols);// 设置SQL参数
        } else if (ObjectUtils.isNotEmpty(columns) && ObjectUtils.isNotEmpty(values)) {
            colStr = columns.getValue();
            valStr = values.getValue();

            String[] colArr = StringUtils.split(StringUtils.trim(colStr), DBConstants.SQLConstants.SQL_COMMA);
            String[] valArr = StringUtils.split(StringUtils.trim(valStr), DBConstants.SQLConstants.SQL_COMMA);

            // 校验表字段列和 属性值变量(是否合法, 是否一一对应)
            Column[] realEntityCols = check(entityCols, colArr, valArr);
            // 设置SQL参数
            createParamMap(params, realEntityCols);
        } else {
            // 请检查SQL模板参数【id="{0}"】配置（属性【key="columns"】和【key="values"】要么都配置 或者 要么都不配置）
            throw new SqlTemplateException("ERROR-DB-SQT0000000024", paramId);
        }

        // 替换SQL模板中的columns关键字内容
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.COLUMNS.getKey()), colStr);
        // 替换SQL模板中的values关键字内容
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.VALUES.getKey()), valStr);
    }

    /**
     * 校验表字段列和 属性值变量是否合法(包括 是否一一对应，是否都是必填项)
     *
     * @param entityCols 实体类对象的属性数组
     * @param cols       表属性列数组
     * @param values     表属性列对应值数组
     * @return 模板配置中对应的实体类对象的属性数组
     * @throws SqlTemplateException SQL模板异常类
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, String[] cols, String[] values) throws SqlTemplateException {
        if (ArrayUtils.isEmpty(cols)) {
            // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
            throw new SqlTemplateException("ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.COLUMNS.getKey());
        }

        if (ArrayUtils.isEmpty(values)) {
            // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
            throw new SqlTemplateException("ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.VALUES.getKey());
        }

        if (!ArrayUtils.isSameLength(cols, values)) {
            // 请检查SQL模板参数【id="{0}"】配置（属性【key="columns"】和【key="values"】中配置字段个数不一致）
            throw new SqlTemplateException("ERROR-DB-SQT0000000025", paramId);
        }

        List<Column> entityColsList = new ArrayList<Column>();
        for (int n = 0; n < cols.length; n++) {
            String tabColumnName = StringUtils.trim(cols[n]);//表字段名
            String attrName = StringUtils.trim(values[n]);//该表字段对应的属性变量值 (如 :paraId )

            if (StringUtils.isBlank(attrName)) {
                // 请检查SQL模板参数【id="{1}"】配置（属性【key="columns"】中的字段【{1}】对应属性【key="values"】中的字段不存在）
                throw new SqlTemplateException("ERROR-DB-SQT0000000026", templateType.getUpperKey(), getId(), tabColumnName);
            }
            // 校验是否存在指定属性列名的属性列对象
            Column column = checkColumn(entityCols, tabColumnName);
            // 取属性列对应的实体类中的变量名
            String attrN = column.getAttrName();
            if (!attrName.equals(StringUtils.strCat(DBConstants.SQLConstants.SQL_COLON, attrN))) {
                // 请检查SQL模板参数【id="{0}"】配置（属性【key="columns"】中的字段【{1}】与属性【key="values"】中的字段【{2}】不一一对应）
                throw new SqlTemplateException("ERROR-DB-SQT0000000028", paramId, tabColumnName, attrName);
            }
            entityColsList.add(column);
        }
        return entityColsList.toArray(new Column[0]);
    }

}
