package com.huazie.frame.db.common.sql.template;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.table.column.Column;
import com.huazie.frame.db.common.table.split.TableSplitHelper;
import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> Sql模板抽象类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class SqlTemplate<T> implements ITemplate<T> {

    private StringBuilder sql = new StringBuilder();

    private Map<String, Object> params = new HashMap<String, Object>(); // SQL参数

    private String tableName; // 主表名

    private String realTableName; // 分表名

    private T entity; // 实体类

    private String tempId; // 模板编号

    private Template template; // SQL模板

    private Rule rule; // 校验规则

    protected TemplateTypeEnum templateType; // 模板类型

    public SqlTemplate() {
    }

    public SqlTemplate(String id, T entity) {
        tempId = id;
        template = getSqlTemplate(id);
        rule = SqlTemplateConfig.getConfig().getRule(template.getRuleId());
        this.entity = entity;
    }

    public SqlTemplate(String id, String tableName, T entity) {
        this(id, entity);
        this.tableName = tableName;
    }

    @Override
    public void initialize() throws Exception {
        if (ObjectUtils.isEmpty(template)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000001", getId(), tempId);
        }
        Map<String, Property> propMap = template.toPropMap();
        if (propMap == null || propMap.isEmpty()) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000002", getId());
        }
        // 获取key=template的属性(即SQL模板)
        Property template = propMap.get(SqlTemplateEnum.TEMPLATE.getKey());
        // 获取key=table的属性(即表名)
        Property table = propMap.get(SqlTemplateEnum.TABLE.getKey());

        if (ObjectUtils.isEmpty(template)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000003", getId(), SqlTemplateEnum.TEMPLATE.getKey());
        }

        if (ObjectUtils.isEmpty(table)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000003", getId(), SqlTemplateEnum.TABLE.getKey());
        }

        // SQL模板规则校验
        checkRule(template.getValue());
        // 初始化模板
        sql.append(template.getValue());
        // 获取配置的SQL模板的表名
        String tName = table.getValue();

        if (StringUtils.isBlank(tableName)) {
            tableName = EntityUtils.getTableName(entity);// 从实体类上获取表名
            if (StringUtils.isBlank(tableName)) {
                throw new SqlTemplateException("ERROR-DB-SQT0000000004");
            }
        }

        if (StringUtils.isBlank(tName)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000005", getId());
        }

        if (!tableName.equals(tName)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000006", getId());
        }

        if (ObjectUtils.isEmpty(entity)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000007");
        }

        // 获取实体类T的对象的属性列相关信息
        Column[] entityCols = EntityUtils.toColumnsArray(entity);

        if (ArrayUtils.isEmpty(entityCols)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000008");
        }

        // 获取真实表名，如是分表，则获取分表名
        realTableName = TableSplitHelper.getRealTableName(tableName, entityCols);
        // 替换真实表名N
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.TABLE.getKey()), realTableName);
        // 特殊处理初始化
        initSqlTemplate(sql, params, entityCols, propMap);

    }

    /**
     * SQL模板校验规则
     *
     * @param template
     * @throws Exception
     * @date 2018年6月24日
     */
    private void checkRule(String template) throws Exception {
        if (ObjectUtils.isEmpty(rule)) {// 需要校验
            throw new SqlTemplateException("ERROR-DB-SQT0000000009", getId());
        }
        // 获取其属性值
        Map<String, Property> propMap = rule.toPropMap();
        if (propMap != null && !propMap.isEmpty()) {
            // 获取指定的校验规则配置
            Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());
            if (ObjectUtils.isEmpty(prop)) {
                throw new SqlTemplateException("ERROR-DB-SQT0000000010", getId());
            }
            String regExp = prop.getValue();

            Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
            Matcher matcher = p.matcher(template);
            if (!matcher.matches()) { // SQL模板不满足校验规则配置
                throw new SqlTemplateException("ERROR-DB-SQT0000000011", getId());
            }
        }
    }

    /**
     * 生成SQL模板中的占位符
     *
     * @param str 模板替换参数
     * @return SQL模板中的占位符
     * @since 1.0.0
     */
    protected String createPlaceHolder(String str) {
        return SqlTemplateEnum.PLACEHOLDER.getKey() + str + SqlTemplateEnum.PLACEHOLDER.getKey();
    }

    /**
     * 获取SQL语句中的参数Map集合
     *
     * @param paramMap   SQL语句中的参数Map集合
     * @param entityCols 实体类中属性列集合
     * @since 1.0.0
     */
    protected void createParamMap(Map<String, Object> paramMap, Column[] entityCols) {
        if (ArrayUtils.isEmpty(entityCols)) {
            return;
        }
        if (ObjectUtils.isEmpty(paramMap)) {
            paramMap = new HashMap<String, Object>();
        }
        for (int i = 0; i < entityCols.length; i++) {
            Column column = entityCols[i];
            paramMap.put(column.getAttrName(), column.getAttrValue());
        }
    }

    /**
     * 获取SQL条件语句的各个条件的Map集合
     *
     * @param condition SQL条件语句字符串
     * @return SQL条件语句的各个条件的Map集合
     * @since 1.0.0
     */
    protected Map<String, String> createConditionMap(String condition) {
        Map<String, String> map = null;
        // 首先需要将条件语句中的 圆括号 全部替换成空格
        String newConn = condition
                .replace(DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS, DBConstants.SQLConstants.SQL_BLANK)
                .replace(DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS, DBConstants.SQLConstants.SQL_BLANK);

        // 根据 and 和 or 进行分组
        String[] singleConnAttr = StringUtils.split(newConn, DBConstants.SQLConstants.SQL_AND, DBConstants.SQLConstants.SQL_LOWER_AND, DBConstants.SQLConstants.SQL_OR, DBConstants.SQLConstants.SQL_LOWER_OR);
        if (!ArrayUtils.isEmpty(singleConnAttr)) {

            map = new HashMap<String, String>();

            for (String singleConn : singleConnAttr) {
                String[] connAttr = StringUtils.split(singleConn, DBConstants.SQLConstants.SQL_EQUAL,
                        DBConstants.SQLConstants.SQL_GE, DBConstants.SQLConstants.SQL_GT,
                        DBConstants.SQLConstants.SQL_LE, DBConstants.SQLConstants.SQL_LT,
                        DBConstants.SQLConstants.SQL_LIKE, DBConstants.SQLConstants.SQL_LOWER_LIKE,
                        DBConstants.SQLConstants.SQL_IN, DBConstants.SQLConstants.SQL_LOWER_IN);
                if (connAttr.length == 2) {
                    String key = connAttr[0];
                    String value = connAttr[1];

                    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                        continue;
                    }

                    if (value.contains(DBConstants.SQLConstants.SQL_COLON)) {
                        map.put(StringUtils.trim(key), StringUtils.trim(value));
                    }
                }
            }
        }

        return map;
    }

    /**
     * <p> 校验 表字段列和变量是否一一对应（如para_id = :paraId）</p>
     *
     * @param entityCols 实体类的属性列数组
     * @param map        map的key存表字段列，value存变量
     * @throws Exception
     * @date 2018年1月31日
     */
    protected Column[] checkOneByOne(final Column[] entityCols, Map<String, String> map, SqlTemplateEnum sqlTemplateEnum) throws Exception {

        if (null == map || map.isEmpty()) {
            return null;
        }

        List<Column> cols = new ArrayList<Column>();

        Set<String> tabColNameSet = map.keySet();
        Iterator<String> tabColNameIt = tabColNameSet.iterator();
        while (tabColNameIt.hasNext()) {
            String tabColName = tabColNameIt.next();
            String attrName = map.get(tabColName);

            if (StringUtils.isBlank(attrName)) {
                throw new SqlTemplateException("ERROR-DB-SQT0000000012", getId(), sqlTemplateEnum.getKey(), tabColName);
            }

            Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColName);
            if (ObjectUtils.isEmpty(column)) {
                throw new SqlTemplateException("ERROR-DB-SQT0000000013", getId(), sqlTemplateEnum.getKey(), tabColName);
            }

            String attrN = column.getAttrName();
            if (!attrName.equals(StringUtils.strCat(DBConstants.SQLConstants.SQL_COLON, attrN))) {
                throw new SqlTemplateException("ERROR-DB-SQT0000000014", getId(), sqlTemplateEnum.getKey(), tabColName, attrName);
            }
            cols.add(column);
        }
        return cols.toArray(new Column[0]);
    }

    @Override
    public String getId() {
        return tempId;
    }

    public void setId(String id) {
        tempId = id;
        template = getSqlTemplate(id);
        rule = SqlTemplateConfig.getConfig().getRule(template.getRuleId());
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public String getSplitTableName() {
        return realTableName;
    }

    public Template getTemplate() {
        return template;
    }

    @Override
    public Rule getRule() {
        return rule;
    }

    /**
     * <p> 根据模板编号，获取对应SQL模板 </p>
     *
     * @param id 模板编号
     * @return SQL模板
     * @since 1.0.0
     */
    protected abstract Template getSqlTemplate(String id);

    /**
     * <p> 用于特殊处理SQL模板的初始化工作 </p>
     *
     * @param sql        原声SQL
     * @param params     SQL参数
     * @param entityCols 实体类T的对象的属性列相关信息
     * @param propMap    模板属性配置信息
     * @throws Exception
     */
    protected abstract void initSqlTemplate(StringBuilder sql, Map<String, Object> params, final Column[] entityCols, Map<String, Property> propMap) throws Exception;

    @Override
    public String toNativeSql() {
        return sql.toString();
    }

    @Override
    public Map<String, Object> toNativeParams() {
        return params;
    }

}
