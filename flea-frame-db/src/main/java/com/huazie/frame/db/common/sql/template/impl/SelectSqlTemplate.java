package com.huazie.frame.db.common.sql.template.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.SqlTemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateEnum;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.table.column.Column;
import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * <p> 查找SQL模板, 用于使用原生SQL实现分表; </p>
 * <p> 查找模板定义配置在<b>flea-sql-template.xml</b>; </p>
 * <p> 节点<b>{@code <select>}</b>下即为查找SQL模板:</p>
 * <pre>
 * (1) 模板配置信息：
 *  {@code <template id="select" ruleId="select" name="查询模板" desc="用于原生SQL中查询语句的使用, 如需查询全部，则设置key=columns的属性值为 *，即可">
 *     <property key="template" value="SELECT ##columns## FROM ##table## WHERE ##conditions##" />
 *     <property key="table" value="student" />
 *     <property key="columns" value="*" />
 *     <property key="conditions" value="para_id = :paraId and ( para_type = :paraType or para_type = 'CERT') and para_code in ( :paraCode) or para_name like :paraName" />
 *   </template>}
 *
 * (2)使用示例
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new SelectSqlTemplate<Student>();
 *  // 这个对应{@code <template id="select" >}
 *  sqlTemplate.setId("select");
 *  // 实体类对应的表名
 *  sqlTemplate.setTableName("student");
 *  // 实体类的实例对象
 *  sqlTemplate.setEntity(student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例2：(该方式需要在实体类被@Table或者@FleaTable修饰，能获取指定的表名)
 *  ITemplate<Student> sqlTemplate = new SelectSqlTemplate<Student>("select", student);
 *  // 模板初始化
 *  sqlTemplate.initialize();
 *
 *  示例3：
 *  ITemplate<Student> sqlTemplate = new SelectSqlTemplate<FleaParaDetail>("select", "student", student);
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
public class SelectSqlTemplate<T> extends SqlTemplate<T> {

    private static final long serialVersionUID = -8355057263712328779L;

    /**
     * <p> SELECT模板构造方法, 参考示例1 </p>
     *
     * @since 1.0.0
     */
    public SelectSqlTemplate() {
        templateType = TemplateTypeEnum.SELECT;
    }

    /**
     * <p> SELECT模板构造方法，参考示例2 </p>
     *
     * @param id     模板编号
     * @param entity 实体类的实例对象
     * @since 1.0.0
     */
    public SelectSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.SELECT;
    }

    /**
     * <p> SELECT模板构造方法，参考示例3 </p>
     *
     * @param id        模板编号
     * @param tableName 表名
     * @param entity    实体类的实例对象
     * @since 1.0.0
     */
    public SelectSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.SELECT;
    }

    @Override
    protected Template getSqlTemplate(String id) {
        return SqlTemplateConfig.getConfig().getSelectTemplate(id);
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols, Map<String, Property> propMap) throws Exception {
        // 获取【key=columns】的属性， 存储SELECT子句的内容（ para_id, para_type, para_code等等）
        Property columns = propMap.get(SqlTemplateEnum.COLUMNS.getKey());
        // 获取【key=conditions】的属性，存储WHERE子句的内容 （para_id = :paraId and para_type = :paraType）
        Property conditions = propMap.get(SqlTemplateEnum.CONDITIONS.getKey());

        if (ObjectUtils.isEmpty(columns)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000015", templateType.getUpperKey(), getId());
        }

        if (ObjectUtils.isEmpty(conditions)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000016", templateType.getUpperKey(), getId());
        }

        String colStr = columns.getValue();
        String condStr = conditions.getValue();

        if (DBConstants.SQLConstants.SQL_ASTERISK.equals(StringUtils.trim(colStr))) {
            colStr = StringUtils.strCombined(entityCols, Column.COLUMN_TAB_COL_NAME, DBConstants.SQLConstants.SQL_BLANK, DBConstants.SQLConstants.SQL_COMMA);
            colStr = colStr.substring(1); // 将第一个空格去除
        }

        String[] cols = StringUtils.split(StringUtils.trim(colStr), DBConstants.SQLConstants.SQL_COMMA);
        Map<String, String> whereMap = createConditionMap(condStr);

        Column[] realEntityCols = check(entityCols, cols, whereMap);
        // 设置SQL参数
        createParamMap(params, realEntityCols);

        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.COLUMNS.getKey()), colStr);
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.CONDITIONS.getKey()), condStr);

    }

    /**
     * 校验【key=columns】和【key=conditions】的数据是否正确 <br/>
     * 注意： （1）where子句的属性列和属性变量一一对应【key=conditions】
     *
     * @param entityCols 实体类对象的属性数组
     * @param cols       查询显示列数组
     * @param whereMap   WHERE子句的map集合（key：属性列， map：属性列变量）
     * @return where子句对应的实体类对象的属性数组
     * @throws Exception
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, String[] cols, Map<String, String> whereMap) throws Exception {

        if (ArrayUtils.isEmpty(cols)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000017", templateType.getUpperKey(), getId());
        }

        if (whereMap == null || whereMap.isEmpty()) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000018", templateType.getUpperKey(), getId());
        }

        for (int n = 0; n < cols.length; n++) {
            String tabColumnName = StringUtils.trim(cols[n]);//表字段名

            Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColumnName);
            if (ObjectUtils.isEmpty(column)) {
                throw new SqlTemplateException("ERROR-DB-SQT0000000024", templateType.getUpperKey(), getId(), tabColumnName);
            }
        }

        // 校验WHERE子句中的属性列和属性变量是否一一对应，并获取WHERE子句相关的属性列集合
        Column[] whereCols = checkOneByOne(entityCols, whereMap, SqlTemplateEnum.CONDITIONS);

        return whereCols;
    }

}
