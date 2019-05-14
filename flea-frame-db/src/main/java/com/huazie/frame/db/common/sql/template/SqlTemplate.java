package com.huazie.frame.db.common.sql.template;

import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.sql.pojo.SqlParam;
import com.huazie.frame.db.common.sql.template.config.Param;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.Relation;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.table.pojo.Column;
import com.huazie.frame.db.common.table.split.TableSplitHelper;
import com.huazie.frame.db.common.util.EntityUtils;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private List<SqlParam> sqlParams = new ArrayList<SqlParam>(); // 原生SQL参数

    private String tableName;       // 主表名

    private String realTableName;   // 分表名

    private T entity;               // 实体类

    private String relationId;      // SQL模板和模板参数关联关系编号（以下称关系编号）

    private Relation relation;      // SQL模板和模板参数关联关系（以下简称关系）

    protected String templateId;    // 模板编号

    protected Template template;    // SQL模板

    protected String paramId;       // SQL模板参数编号

    protected Param param;          // SQL模板参数

    private Rule rule;              // 校验规则

    protected TemplateTypeEnum templateType; // 模板类型

    public SqlTemplate() {
    }

    public SqlTemplate(String id, T entity) {
        relationId = id;
        relation = getSqlRelation(id);
        if (ObjectUtils.isNotEmpty(relation)) {
            paramId = relation.getParamId();
            param = getSqlTemplateParam(paramId);
            templateId = relation.getTemplateId();
            template = getSqlTemplate(templateId);
            if (ObjectUtils.isNotEmpty(template)) {
                rule = SqlTemplateConfig.getConfig().getRule(template.getRuleId());
            }
        }
        this.entity = entity;
    }

    public SqlTemplate(String id, String tableName, T entity) {
        this(id, entity);
        this.tableName = tableName;
    }

    @Override
    public void initialize() throws Exception {
        if (ObjectUtils.isEmpty(relation)) {
            // 请检查SQL关系配置（没有找到指定关系编号【id="{0}"】的SQL关系配置信息）
            throw new SqlTemplateException("ERROR-DB-SQT0000000001", getId());
        }

        if (StringUtils.isBlank(templateId)) {
            // 请检查SQL关系【id="{0}"】配置（SQL关系配置的SQL模板编号【templateId】不能为空）
            throw new SqlTemplateException("ERROR-DB-SQT0000000002", getId());
        }

        if (StringUtils.isBlank(paramId)) {
            // 请检查SQL关系【id="{0}"】配置（SQL关系配置的SQL模板参数编号【paramId】不能为空）
            throw new SqlTemplateException("ERROR-DB-SQT0000000003", getId());
        }

        if (ObjectUtils.isEmpty(template)) {
            // 请检查SQL模板配置（没有找到指定模板编号【id="{0}"】的SQL模板配置信息）
            throw new SqlTemplateException("ERROR-DB-SQT0000000004", templateId);
        }

        if (ObjectUtils.isEmpty(param)) {
            // 请检查SQL模板参数配置（没有找到指定模板参数编号【id="{0}"】的SQL模板参数配置信息）
            throw new SqlTemplateException("ERROR-DB-SQT0000000005", paramId);
        }

        // 获取SQL模板配置的属性信息
        Map<String, Property> templatePropMap = template.toPropMap();
        if (MapUtils.isEmpty(templatePropMap)) {
            // 请检查SQL模板【id="{0}"】配置（该SQL模板没有配置相关属性）
            throw new SqlTemplateException("ERROR-DB-SQT0000000006", templateId);
        }

        // 获取key="template"的属性(即SQL模板原始值)
        Property templateProp = templatePropMap.get(SqlTemplateEnum.TEMPLATE.getKey());
        if (ObjectUtils.isEmpty(templateProp)) {
            // 请检查SQL模板【id="{0}"】配置（该SQL模板没有配置【key="{1}"】的模板属性）
            throw new SqlTemplateException("ERROR-DB-SQT0000000007", templateId, SqlTemplateEnum.TEMPLATE.getKey());
        }

        String templateValue = templateProp.getValue();
        if (StringUtils.isBlank(templateValue)) {
            // 请检查SQL模板【id="{0}"】配置（属性【key="{1}"】中的【value】不能为空）
            throw new SqlTemplateException("ERROR-DB-SQT0000000008", templateId, SqlTemplateEnum.TEMPLATE.getKey());
        }

        // SQL模板规则校验
        checkRule(templateValue);

        // 获取key="type"的属性(即SQL模板类型)
        Property typeProp = templatePropMap.get(SqlTemplateEnum.TYPE.getKey());
        if (ObjectUtils.isEmpty(typeProp)) {
            // 请检查SQL模板【id="{0}"】配置（该SQL模板没有配置【key="{1}"】的模板属性）
            throw new SqlTemplateException("ERROR-DB-SQT0000000007", templateId, SqlTemplateEnum.TYPE.getKey());
        }

        String typeValue = typeProp.getValue(); // 模板类型
        if (StringUtils.isBlank(typeValue)) {
            // 请检查SQL模板【id="{0}"】配置（属性【key="{1}"】中的【value】不能为空）
            throw new SqlTemplateException("ERROR-DB-SQT0000000008", templateId, SqlTemplateEnum.TYPE.getKey());
        }

        // 校验配置的模板类型和初始化的模板类型是否一致
        if (!templateType.getKey().equalsIgnoreCase(typeProp.getValue())) {
            // 请检查SQL模板【id="{0}"】配置（属性【key="type"】的值【value="{1}"】和初始化的模板类型【{2}】不一致）
            throw new SqlTemplateException("ERROR-DB-SQT0000000009", templateId, typeProp.getValue(), templateType.getKey());
        }

        // 获取SQL模板参数的配置属性
        Map<String, Property> paramPropMap = param.toPropMap();
        if (MapUtils.isEmpty(paramPropMap)) {
            // 请检查SQL模板参数【id="{0}"】配置（该SQL模板参数没有配置相关属性）
            throw new SqlTemplateException("ERROR-DB-SQT0000000010", paramId);
        }

        // 获取key=table的属性(即表名)
        Property tableProp = paramPropMap.get(SqlTemplateEnum.TABLE.getKey());
        if (ObjectUtils.isEmpty(tableProp)) {
            // 请检查SQL模板参数【id="{0}"】配置（该SQL模板参数没有配置【key="{1}"】的模板参数属性）
            throw new SqlTemplateException("ERROR-DB-SQT0000000011", paramId, SqlTemplateEnum.TABLE.getKey());
        }

        // 初始化模板
        sql.append(templateProp.getValue());
        // 获取SQL模板参数配置的对应表名
        String tName = tableProp.getValue();

        if (StringUtils.isBlank(tableName)) {
            tableName = EntityUtils.getTableName(entity);// 从实体类上获取表名
            if (StringUtils.isBlank(tableName)) {
                // 请检查初始实体类(其上的注解@Table或者@FleaTable对应的表名不能为空)
                throw new SqlTemplateException("ERROR-DB-SQT0000000012");
            }
        }

        if (StringUtils.isBlank(tName)) {
            // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
            throw new SqlTemplateException("ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.TABLE.getKey());
        }

        if (!tableName.equals(tName)) {
            // 请检查SQL模板参数【id="{0}"】配置(【key="table"】属性值【{1}】和初始化的表名【{2}】两者不一致)
            throw new SqlTemplateException("ERROR-DB-SQT0000000014", paramId, tName, tableName);
        }

        if (ObjectUtils.isEmpty(entity)) {
            // 请检查初始实体类（该实体类对象没有被初始化）
            throw new SqlTemplateException("ERROR-DB-SQT0000000015");
        }

        // 获取实体类T的对象的属性列相关信息
        Column[] entityCols = EntityUtils.toColumnsArray(entity);

        if (ArrayUtils.isEmpty(entityCols)) {
            // 请检查初始实体类（实体类的属性列相关信息不存在）
            throw new SqlTemplateException("ERROR-DB-SQT0000000016");
        }

        // 获取真实表名，如是分表，则获取分表名
        realTableName = TableSplitHelper.getRealTableName(tableName, entityCols);
        // 替换真实表名N
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.TABLE.getKey()), realTableName);
        Map<String, Object> params = new HashMap<String, Object>(); // SQL参数, K ：实体属性变量名 V : 参数值
        // 特殊处理，初始化由具体模板实现
        initSqlTemplate(sql, params, entityCols, paramPropMap);
        // 最终处理，生成原生SQL和对应参数
        finalSqlTemplate(params, entityCols);

    }

    /**
     * <p> SQL模板校验规则 </p>
     *
     * @param templateValue SQL模板原始值
     * @throws Exception
     * @since 1.0.0
     */
    private void checkRule(String templateValue) throws Exception {
        if (ObjectUtils.isEmpty(rule)) {
            // 请检查SQL模板【id="{0}"】配置（未找到指定的校验规则【ruleId="{1}"】）
            throw new SqlTemplateException("ERROR-DB-SQT0000000017", templateId, template.getRuleId());
        }
        // 获取校验规则的属性值
        Map<String, Property> propMap = rule.toPropMap();
        if (MapUtils.isNotEmpty(propMap)) {
            // 获取指定的校验规则配置
            Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());
            if (ObjectUtils.isEmpty(prop)) {
                // 请检查SQL模板【id="{0}"】配置（其关联的校验规则【id="{1}"】未找到【key="{2}"】的属性）
                throw new SqlTemplateException("ERROR-DB-SQT0000000018", templateId, template.getRuleId(), SqlTemplateEnum.SQL.getKey());
            }
            // 获取校验规则模式匹配正则表达式
            String regExp = prop.getValue();
            if (StringUtils.isBlank(regExp)) {
                // 请检查SQL模板【id="{0}"】配置（其关联的校验规则【id="{1}"】的属性【key="{2}"】中的【value】不能为空）
                throw new SqlTemplateException("ERROR-DB-SQT0000000019", templateId, template.getRuleId(), SqlTemplateEnum.SQL.getKey());
            }
            Pattern p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
            Matcher matcher = p.matcher(templateValue);
            if (!matcher.matches()) { // SQL模板不满足校验规则配置
                // 请检查SQL模板【id="{0}"】配置（属性【key="{1}"】中的【value】配置有误）
                throw new SqlTemplateException("ERROR-DB-SQT0000000020", templateId, SqlTemplateEnum.TEMPLATE.getKey());
            }
        }
    }

    /**
     * <p> 生成SQL模板中的占位符 </p>
     *
     * @param str 模板替换参数
     * @return SQL模板中的占位符
     * @since 1.0.0
     */
    protected String createPlaceHolder(String str) {
        return SqlTemplateEnum.PLACEHOLDER.getKey() + str + SqlTemplateEnum.PLACEHOLDER.getKey();
    }

    /**
     * <p> 获取SQL语句中的参数Map集合 </p>
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
     * <p> 判断指定关键字的模板参数属性配置是否存在，如果存在，返回相应的值 </p>
     *
     * @param propMap         模板参数属性Map集合
     * @param sqlTemplateEnum SQL模板关键字枚举
     * @return 指定关键字的模板参数属性值
     * @throws Exception
     * @since 1.0.0
     */
    protected String checkProperty(Map<String, Property> propMap, SqlTemplateEnum sqlTemplateEnum) throws Exception {
        Property property = propMap.get(sqlTemplateEnum.getKey());

        if (ObjectUtils.isEmpty(property)) {
            // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
            throw new SqlTemplateException("ERROR-DB-SQT0000000013", paramId, sqlTemplateEnum.getKey());
        }

        return property.getValue();
    }

    /**
     * <p> 校验是否存在指定属性列名<code>tabColumnName</code>的属性列对象<code>Column</code>, 如果存在则返回指定的属性列对象<code>Column</code> </p>
     *
     * @param entityCols    属性列数组
     * @param tabColumnName 属性列名
     * @return 指定的属性列对象
     * @throws Exception
     */
    protected Column checkColumn(final Column[] entityCols, String tabColumnName) throws Exception {
        Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColumnName);
        if (ObjectUtils.isEmpty(column)) {
            // 请检查SQL模板参数【id="{0}"】配置（属性【key="columns"】中的字段【{1}】在实体类【{2}】中不存在)
            throw new SqlTemplateException("ERROR-DB-SQT0000000027", paramId, tabColumnName, entity.getClass().getName());
        }
        return column;
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

        if (MapUtils.isEmpty(map)) {
            return null;
        }

        List<Column> cols = new ArrayList<Column>();

        Set<String> tabColNameSet = map.keySet();
        Iterator<String> tabColNameIt = tabColNameSet.iterator();
        while (tabColNameIt.hasNext()) {
            String tabColName = tabColNameIt.next();
            String attrName = map.get(tabColName);

            if (StringUtils.isBlank(attrName)) {
                // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的字段{2}对应的属性变量为空）
                throw new SqlTemplateException("ERROR-DB-SQT0000000021", paramId, sqlTemplateEnum.getKey(), tabColName);
            }

            Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColName);
            if (ObjectUtils.isEmpty(column)) {
                // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的字段【{2}】在实体类【{3}】中不存在）
                throw new SqlTemplateException("ERROR-DB-SQT0000000022", paramId, sqlTemplateEnum.getKey(), tabColName, entity.getClass().getName());
            }

            String attrN = column.getAttrName();
            if (!attrName.equals(StringUtils.strCat(DBConstants.SQLConstants.SQL_COLON, attrN))) {
                // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的属性列{2}与属性变量{3}不一一对应）
                throw new SqlTemplateException("ERROR-DB-SQT0000000023", paramId, sqlTemplateEnum.getKey(), tabColName, attrName);
            }
            cols.add(column);
        }
        return cols.toArray(new Column[0]);
    }

    /**
     * <p> 原生SQL和对应参数的二次处理 </p>
     *
     * @param params SQL参数Map集合(K ：实体属性变量名 V : 参数值)
     * @since 1.0.0
     */
    protected void finalSqlTemplate(Map<String, Object> params, Column[] entityCols) throws Exception {
        // 根据SQL参数Map集合从SQL模板上sql获取自定义SQL模板参数List<SqlParam>集合
        if (MapUtils.isNotEmpty(params)) {
            Set<String> keySet = params.keySet();
            Iterator<String> keyIt = keySet.iterator();
            while (keyIt.hasNext()) {
                String key = keyIt.next();

                // 从sql中获取指定实体属性变量名 所在的起始位置
                int index = sql.indexOf(DBConstants.SQLConstants.SQL_COLON + key);
                Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_ATTR_NAME, key);
                if (ObjectUtils.isNotEmpty(column)) {
                    SqlParam sqlParam = new SqlParam();
                    sqlParam.setAttrName(column.getAttrName());
                    sqlParam.setAttrValue(column.getAttrValue());
                    sqlParam.setTabColName(column.getTabColumnName());
                    // 设置参数索引为起始位置，暂时这样处理，后面需要重新根据这个排序确定
                    sqlParam.setIndex(index);
                    // 添加SQL参数
                    sqlParams.add(sqlParam);
                    // 将sql中的 :实体属性变量名 替换为 ?
                    StringUtils.replace(sql, DBConstants.SQLConstants.SQL_COLON + key, DBConstants.SQLConstants.SQL_PLACEHOLDER);
                }
            }
        }

        // 重新排序sqlParams， 依据SqlParam中的index值
        Collections.sort(sqlParams, new Comparator<SqlParam>() {
            @Override
            public int compare(SqlParam o1, SqlParam o2) {
                // 按照index升序
                return o1.getIndex() - o2.getIndex();
            }
        });

        // 重新设置 参数索引
        for (int i = 0; i < sqlParams.size(); i++) {
            SqlParam sqlParam = sqlParams.get(i);
            if (ObjectUtils.isNotEmpty(sqlParam)) {
                sqlParam.setIndex(i + 1);
            }
        }

    }

    @Override
    public String getId() {
        return relationId;
    }

    public void setId(String id) {
        relationId = id;
        relation = getSqlRelation(id);
        if (ObjectUtils.isNotEmpty(relation)) {
            paramId = relation.getParamId();
            param = getSqlTemplateParam(paramId);
            templateId = relation.getTemplateId();
            template = getSqlTemplate(templateId);
            if (ObjectUtils.isNotEmpty(template)) {
                rule = SqlTemplateConfig.getConfig().getRule(template.getRuleId());
            }
        }
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
     * <p> 根据关系编号，获取对应关系配置信息 </p>
     *
     * @param relationId 关系编号
     * @return 关系配置信息
     * @since 1.0.0
     */
    protected Relation getSqlRelation(String relationId) {
        return SqlTemplateConfig.getConfig().getRelation(relationId);
    }

    /**
     * <p> 根据模板编号，获取对应SQL模板 </p>
     *
     * @param templateId SQL模板编号
     * @return SQL模板
     * @since 1.0.0
     */
    protected Template getSqlTemplate(String templateId) {
        return SqlTemplateConfig.getConfig().getTemplate(templateId);
    }

    /**
     * <p> 根据模板参数编号，获取对应SQL模板参数配置信息 </p>
     *
     * @param paramId SQL模板参数编号
     * @return SQL模板参数配置
     * @since 1.0.0
     */
    protected Param getSqlTemplateParam(String paramId) {
        return SqlTemplateConfig.getConfig().getParam(paramId);
    }

    /**
     * <p> 用于特殊处理SQL模板的初始化工作 </p>
     *
     * @param sql        原声SQL
     * @param params     SQL参数
     * @param entityCols 实体类T的对象的属性列相关信息
     * @param propMap    模板属性配置信息
     * @throws Exception
     * @since 1.0.0
     */
    protected abstract void initSqlTemplate(StringBuilder sql, Map<String, Object> params, final Column[] entityCols, Map<String, Property> propMap) throws Exception;

    @Override
    public String toNativeSql() {
        return sql.toString();
    }

    @Override
    public List<SqlParam> toNativeParams() {
        return sqlParams;
    }

}
