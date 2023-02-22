package com.huazie.fleaframework.db.common.sql.template.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.PatternMatcherUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplate;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplateEnum;
import com.huazie.fleaframework.db.common.sql.template.TemplateTypeEnum;
import com.huazie.fleaframework.db.common.sql.template.config.Property;
import com.huazie.fleaframework.db.common.table.pojo.Column;
import com.huazie.fleaframework.db.common.util.EntityUtils;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * 查找SQL模板, 用于使用原生SQL实现分表。
 * 查找SQL模板定义配置在<b>flea-sql-template.xml</b>。
 * <pre>
 * (1) 模板配置信息：
 * {@code
 *   <!-- SQL模板配置 -->
 *   <templates>
 *     <template id="select" ruleId="select" name="SELECT SQL模板" desc="用于原生SQL中SELECT语句的使用">
 *       <property key="template" value="SELECT ##columns## FROM ##table## WHERE ##conditions##" />
 *       <property key="type" value="select"/>
 *     </template>
 *   </templates>
 *   <!-- SQL模板参数配置 -->
 *   <params>
 *     <param id="select" name="SQL模板參數" desc="用于定义SQL模板中的替换参数; 如需查询全部，则设置key=columns的属性值为 *，即可">
 *       <!-- 表名 -->
 *       <property key="table" value="flea_config_data" />
 *       <!-- SELECT 显示列 -->
 *       <property key="columns" value="*" />
 *       <!-- WHERE 子句 -->
 *       <property key="conditions" value="para_id = :paraId and ( para_type = :paraType or para_type = 'CERT')" />
 *     </param>
 *   </params>
 *   <!-- SQL模板和模板参数关联关系配置（简称 SQL关系配置）-->
 *   <relations>
 *     <relation id="select" templateId="select" paramId="select" name="SQL关系"/>
 *   </relations>
 * 	}
 *
 * (2)使用示例
 *  示例1：
 *  SqlTemplate<Student> sqlTemplate = new SelectSqlTemplate<Student>();
 *  // 这个对应{@code <relation id="select" .../>}
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

    private static final String SINGLE_SELECT_RESULT_EXP = "AVG\\([([\\s\\S]*)]*\\)|COUNT\\([([\\s\\S]*)]*\\)|SUM\\([([\\s\\S]*)]*\\)|MAX\\([([\\s\\S]*)]*\\)|MIN\\([([\\s\\S]*)]*\\)";

    private static final String EQUAL_EXP = "[ ]*1[ ]*=[ ]*1[ ]*";

    /**
     * SELECT模板构造方法, 参考示例1
     *
     * @since 1.0.0
     */
    public SelectSqlTemplate() {
        templateType = TemplateTypeEnum.SELECT;
    }

    /**
     * SELECT模板构造方法，参考示例2
     *
     * @param id     关系编号
     * @param entity 实体类的实例对象
     * @since 1.0.0
     */
    public SelectSqlTemplate(String id, T entity) {
        super(id, entity);
        templateType = TemplateTypeEnum.SELECT;
    }

    /**
     * SELECT模板构造方法，参考示例3
     *
     * @param id        关系编号
     * @param tableName 表名
     * @param entity    实体类的实例对象
     * @since 1.0.0
     */
    public SelectSqlTemplate(String id, String tableName, T entity) {
        super(id, tableName, entity);
        templateType = TemplateTypeEnum.SELECT;
    }

    @Override
    protected void initSqlTemplate(StringBuilder sql, Map<String, Object> params, Column[] entityCols, Map<String, Property> propMap) throws CommonException {

        // 获取【key="columns"】的属性， 存储SELECT子句的内容（ para_id, para_type, para_code等等）
        String colStr = checkProperty(propMap, SqlTemplateEnum.COLUMNS);

        // 如果【key="columns"】配置为 *，则默认查询全部
        if (DBConstants.SQLConstants.SQL_ASTERISK.equals(StringUtils.trim(colStr))) {
            colStr = StringUtils.strCombined(entityCols, Column.COLUMN_TAB_COL_NAME, DBConstants.SQLConstants.SQL_BLANK, DBConstants.SQLConstants.SQL_COMMA);
            colStr = colStr.substring(1); // 将第一个空格去除
        }
        // 获取查询列信息
        String[] cols = StringUtils.split(StringUtils.trim(colStr), DBConstants.SQLConstants.SQL_COMMA);

        // 获取【key="conditions"】的属性，存储WHERE子句的内容 （para_id = :paraId and para_type = :paraType）
        String condStr = checkProperty(propMap, SqlTemplateEnum.CONDITIONS);

        // 构建条件Map集合
        Map<String, String> whereMap = createConditionMap(condStr);

        // 校验【key=columns】和【key=conditions】的数据是否正确
        Column[] realEntityCols = check(entityCols, cols, whereMap, condStr);

        // 设置SQL参数
        createParamMap(params, realEntityCols);

        // 替换SQL模板中的columns关键字内容
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.COLUMNS.getKey()), colStr);
        // 替换SQL模板中的conditions关键字内容
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
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private Column[] check(final Column[] entityCols, String[] cols, Map<String, String> whereMap, String condStr) throws CommonException {

        // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
        ArrayUtils.checkEmpty(cols, SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.COLUMNS.getKey());

        if (MapUtils.isEmpty(whereMap) && !PatternMatcherUtils.matches(EQUAL_EXP, condStr, Pattern.CASE_INSENSITIVE)) {
            // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
            ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.CONDITIONS.getKey());
        }

        for (String columnName : cols) {
            String tabColumnName = StringUtils.trim(columnName); // 表字段名
            Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColumnName);
            if (ObjectUtils.isEmpty(column) && !PatternMatcherUtils.matches(SINGLE_SELECT_RESULT_EXP, tabColumnName, Pattern.CASE_INSENSITIVE)) {
                // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的字段【{2}】在实体类【{3}】中不存在）
                ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000022", paramId, SqlTemplateEnum.COLUMNS.getKey(), tabColumnName, getEntity().getClass().getName());
            }
        }

        // 校验WHERE子句中的属性列和属性变量是否一一对应，并获取WHERE子句相关的属性列集合
        return checkOneByOne(entityCols, whereMap, SqlTemplateEnum.CONDITIONS);
    }

}
