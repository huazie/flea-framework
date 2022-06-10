package com.huazie.fleaframework.db.common.sql.template;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.PatternMatcherUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.DBConstants.SQLConstants;
import com.huazie.fleaframework.db.common.exception.SqlTemplateException;
import com.huazie.fleaframework.db.common.sql.pojo.SqlParam;
import com.huazie.fleaframework.db.common.sql.template.config.Param;
import com.huazie.fleaframework.db.common.sql.template.config.Property;
import com.huazie.fleaframework.db.common.sql.template.config.Relation;
import com.huazie.fleaframework.db.common.sql.template.config.Rule;
import com.huazie.fleaframework.db.common.sql.template.config.SqlTemplateConfig;
import com.huazie.fleaframework.db.common.sql.template.config.Template;
import com.huazie.fleaframework.db.common.table.pojo.Column;
import com.huazie.fleaframework.db.common.util.EntityUtils;
import com.huazie.fleaframework.db.common.util.FleaSplitUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Sql模板抽象类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public abstract class SqlTemplate<T> implements ITemplate<T> {

    private StringBuilder sql = new StringBuilder();

    private List<SqlParam> sqlParams = new ArrayList<>(); // 原生SQL参数

    private String tableName;       // 模板表名

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

    // 实体属性变量和表字段对应关系（主要是应对where子句中表字段对应多个实体属性变量的场景）， K ：实体属性变量名 V ：表字段名
    private Map<String, String> paramTabCols = new HashMap<>();

    protected static final Class<? extends CommonException> SQT_CLASS = SqlTemplateException.class;

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
    public void initialize() throws CommonException {

        // 请检查SQL关系配置（没有找到指定关系编号【id="{0}"】的SQL关系配置信息）
        ObjectUtils.checkEmpty(relation, SQT_CLASS, "ERROR-DB-SQT0000000001", getId());

        // 请检查SQL关系【id="{0}"】配置（SQL关系配置的SQL模板编号【templateId】不能为空）
        StringUtils.checkBlank(templateId, SQT_CLASS, "ERROR-DB-SQT0000000002", getId());

        // 请检查SQL关系【id="{0}"】配置（SQL关系配置的SQL模板参数编号【paramId】不能为空）
        StringUtils.checkBlank(paramId, SQT_CLASS, "ERROR-DB-SQT0000000003", getId());

        // 请检查SQL模板配置（没有找到指定模板编号【id="{0}"】的SQL模板配置信息）
        ObjectUtils.checkEmpty(template, SQT_CLASS, "ERROR-DB-SQT0000000004", templateId);

        // 请检查SQL模板参数配置（没有找到指定模板参数编号【id="{0}"】的SQL模板参数配置信息）
        ObjectUtils.checkEmpty(param, SQT_CLASS, "ERROR-DB-SQT0000000005", paramId);

        // 获取SQL模板配置的属性信息
        Map<String, Property> templatePropMap = template.toPropMap();
        // 请检查SQL模板【id="{0}"】配置（该SQL模板没有配置相关属性）
        MapUtils.checkEmpty(templatePropMap, SQT_CLASS, "ERROR-DB-SQT0000000006", templateId);

        // 获取key="template"的属性(即SQL模板原始值)
        Property templateProp = templatePropMap.get(SqlTemplateEnum.TEMPLATE.getKey());
        // 请检查SQL模板【id="{0}"】配置（该SQL模板没有配置【key="{1}"】的模板属性）
        ObjectUtils.checkEmpty(templateProp, SQT_CLASS, "ERROR-DB-SQT0000000007", templateId, SqlTemplateEnum.TEMPLATE.getKey());

        String templateValue = templateProp.getValue();
        // 请检查SQL模板【id="{0}"】配置（属性【key="{1}"】中的【value】不能为空）
        StringUtils.checkBlank(templateValue, SQT_CLASS, "ERROR-DB-SQT0000000008", templateId, SqlTemplateEnum.TEMPLATE.getKey());

        // SQL模板规则校验
        checkRule(templateValue);

        // 获取key="type"的属性(即SQL模板类型)
        Property typeProp = templatePropMap.get(SqlTemplateEnum.TYPE.getKey());
        // 请检查SQL模板【id="{0}"】配置（该SQL模板没有配置【key="{1}"】的模板属性）
        ObjectUtils.checkEmpty(typeProp, SQT_CLASS, "ERROR-DB-SQT0000000007", templateId, SqlTemplateEnum.TYPE.getKey());

        String typeValue = typeProp.getValue(); // 模板类型
        // 请检查SQL模板【id="{0}"】配置（属性【key="{1}"】中的【value】不能为空）
        StringUtils.checkBlank(typeValue, SQT_CLASS, "ERROR-DB-SQT0000000008", templateId, SqlTemplateEnum.TYPE.getKey());

        // 校验配置的模板类型和初始化的模板类型是否一致
        if (!templateType.getKey().equalsIgnoreCase(typeProp.getValue())) {
            // 请检查SQL模板【id="{0}"】配置（属性【key="type"】的值【value="{1}"】和初始化的模板类型【{2}】不一致）
            ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000009", templateId, typeProp.getValue(), templateType.getKey());
        }

        // 获取SQL模板参数的配置属性
        Map<String, Property> paramPropMap = param.toPropMap();
        // 请检查SQL模板参数【id="{0}"】配置（该SQL模板参数没有配置相关属性）
        MapUtils.checkEmpty(paramPropMap, SQT_CLASS, "ERROR-DB-SQT0000000010", paramId);

        // 获取key=table的属性(即表名)
        Property tableProp = paramPropMap.get(SqlTemplateEnum.TABLE.getKey());
        // 请检查SQL模板参数【id="{0}"】配置（该SQL模板参数没有配置【key="{1}"】的模板参数属性）
        ObjectUtils.checkEmpty(tableProp, SQT_CLASS, "ERROR-DB-SQT0000000011", paramId, SqlTemplateEnum.TABLE.getKey());

        // 初始化模板
        sql.append(templateProp.getValue());
        // 获取SQL模板参数配置的对应表名
        String tName = tableProp.getValue();

        if (StringUtils.isBlank(tableName)) {
            tableName = EntityUtils.getTableName(entity);// 从实体类上获取表名
            // 请检查初始实体类(其上的注解@Table或者@FleaTable对应的表名不能为空)
            StringUtils.checkBlank(tableName, SQT_CLASS, "ERROR-DB-SQT0000000012");
        }

        // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
        StringUtils.checkBlank(tName, SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, SqlTemplateEnum.TABLE.getKey());

        if (!tableName.equals(tName)) {
            // 请检查SQL模板参数【id="{0}"】配置(【key="table"】属性值【{1}】和初始化的表名【{2}】两者不一致)
            ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000014", paramId, tName, tableName);
        }

        // 请检查初始实体类（该实体类对象没有被初始化）
        ObjectUtils.checkEmpty(entity, SQT_CLASS, "ERROR-DB-SQT0000000015");

        // 获取实体类T的对象的属性列相关信息
        Column[] entityCols = EntityUtils.toColumnsArray(entity);
        // 请检查初始实体类（实体类的属性列相关信息不存在）
        ArrayUtils.checkEmpty(entityCols, SQT_CLASS, "ERROR-DB-SQT0000000016");

        // 获取真实表名，如是分表，则获取分表名
        realTableName = FleaSplitUtils.getRealTableName(tableName, entityCols);
        // 替换真实表名N
        StringUtils.replace(sql, createPlaceHolder(SqlTemplateEnum.TABLE.getKey()), realTableName);
        Map<String, Object> params = new HashMap<>(); // SQL参数, K ：实体属性变量名 V : 参数值
        // 特殊处理，初始化由具体模板实现
        initSqlTemplate(sql, params, entityCols, paramPropMap);
        // 最终处理，生成原生SQL和对应参数
        finalSqlTemplate(params, entityCols);

    }

    /**
     * SQL模板校验规则
     *
     * @param templateValue SQL模板原始值
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private void checkRule(String templateValue) throws CommonException {

        // 请检查SQL模板【id="{0}"】配置（未找到指定的校验规则【ruleId="{1}"】）
        ObjectUtils.checkEmpty(rule, SQT_CLASS, "ERROR-DB-SQT0000000017", templateId, template.getRuleId());

        // 获取校验规则的属性值
        Map<String, Property> propMap = rule.toPropMap();
        if (MapUtils.isNotEmpty(propMap)) {
            // 获取指定的校验规则配置
            Property prop = propMap.get(SqlTemplateEnum.SQL.getKey());

            // 请检查SQL模板【id="{0}"】配置（其关联的校验规则【id="{1}"】未找到【key="{2}"】的属性）
            ObjectUtils.checkEmpty(prop, SQT_CLASS, "ERROR-DB-SQT0000000018", templateId, template.getRuleId(), SqlTemplateEnum.SQL.getKey());

            // 获取校验规则模式匹配正则表达式
            String regExp = prop.getValue();

            // 请检查SQL模板【id="{0}"】配置（其关联的校验规则【id="{1}"】的属性【key="{2}"】中的【value】不能为空）
            StringUtils.checkBlank(regExp, SQT_CLASS, "ERROR-DB-SQT0000000019", templateId, template.getRuleId(), SqlTemplateEnum.SQL.getKey());

            if (!PatternMatcherUtils.matches(regExp, templateValue, Pattern.CASE_INSENSITIVE)) { // SQL模板不满足校验规则配置
                // 请检查SQL模板【id="{0}"】配置（属性【key="{1}"】中的【value】配置有误）
                ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000020", templateId, SqlTemplateEnum.TEMPLATE.getKey());
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
            paramMap = new HashMap<>();
        }
        for (Column column : entityCols) {
            paramMap.put(column.getAttrName(), column.getAttrValue());
            paramTabCols.put(column.getAttrName(), column.getTabColumnName());
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
                .replace(SQLConstants.SQL_LEFT_ROUND_BRACKETS, SQLConstants.SQL_BLANK)
                .replace(SQLConstants.SQL_RIGHT_ROUND_BRACKETS, SQLConstants.SQL_BLANK);

        // 根据 and , or , order by , group by , limit 进行分组
        String[] singleConnAttr = StringUtils.split(newConn,
                SQLConstants.SQL_AND, SQLConstants.SQL_LOWER_AND,
                SQLConstants.SQL_OR, SQLConstants.SQL_LOWER_OR,
                SQLConstants.SQL_ORDER_BY, SQLConstants.SQL_LOWER_ORDER_BY,
                SQLConstants.SQL_GROUP_BY, SQLConstants.SQL_LOWER_GROUP_BY,
                SQLConstants.SQL_LIMIT, SQLConstants.SQL_LOWER_LIMIT);

        if (!ArrayUtils.isEmpty(singleConnAttr)) {

            map = new HashMap<>();

            for (String singleConn : singleConnAttr) {
                String[] connAttr = StringUtils.split(singleConn, SQLConstants.SQL_EQUAL,
                        SQLConstants.SQL_GE, SQLConstants.SQL_GT,
                        SQLConstants.SQL_LE, SQLConstants.SQL_LT,
                        SQLConstants.SQL_LIKE, SQLConstants.SQL_LOWER_LIKE,
                        SQLConstants.SQL_IN, SQLConstants.SQL_LOWER_IN);
                if (ArrayUtils.isEmpty(connAttr)) {
                    continue;
                }
                if (connAttr.length == CommonConstants.NumeralConstants.INT_ONE) {
                    if (connAttr[0].contains(SQLConstants.SQL_COLON)) { // 目前这里就只有 limit
                        map.put(StringUtils.trim(SQLConstants.SQL_LIMIT), connAttr[0]);
                    }
                } else if (connAttr.length == CommonConstants.NumeralConstants.INT_TWO) {
                    String key = connAttr[0];
                    String value = connAttr[1];

                    if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                        continue;
                    }

                    if (value.contains(SQLConstants.SQL_COLON)) {
                        // 存在重复的key，value中的值以逗号分隔
                        String val = map.get(StringUtils.trim(key));
                        if (StringUtils.isNotBlank(val)) {
                            value = StringUtils.trim(val) + SQLConstants.SQL_COMMA + StringUtils.trim(value);
                        }
                        map.put(StringUtils.trim(key), StringUtils.trim(value));
                    }
                }
            }
        }

        return map;
    }

    /**
     * 判断指定关键字的模板参数属性配置是否存在，如果存在，返回相应的值
     *
     * @param propMap         模板参数属性Map集合
     * @param sqlTemplateEnum SQL模板关键字枚举
     * @return 指定关键字的模板参数属性值
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected String checkProperty(Map<String, Property> propMap, SqlTemplateEnum sqlTemplateEnum) throws CommonException {
        Property property = propMap.get(sqlTemplateEnum.getKey());

        // 请检查SQL模板参数【id="{0}"】配置(属性【key="{1}"】中的【value】不能为空)
        ObjectUtils.checkEmpty(property, SQT_CLASS, "ERROR-DB-SQT0000000013", paramId, sqlTemplateEnum.getKey());

        return property.getValue();
    }

    /**
     * 校验是否存在指定属性列名<code>tabColumnName</code>的属性列对象<code>Column</code>,
     * 如果存在则返回指定的属性列对象<code>Column</code>。
     *
     * @param entityCols    属性列数组
     * @param tabColumnName 属性列名
     * @return 指定的属性列对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected Column checkColumn(final Column[] entityCols, String tabColumnName) throws CommonException {
        Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColumnName);

        // 请检查SQL模板参数【id="{0}"】配置（属性【key="columns"】中的字段【{1}】在实体类【{2}】中不存在)
        ObjectUtils.checkEmpty(column, SQT_CLASS, "ERROR-DB-SQT0000000027", paramId, tabColumnName, entity.getClass().getName());

        return column;
    }

    /**
     * 校验 表字段列和变量是否一一对应（如para_id = :paraId）</p>
     *
     * @param entityCols 实体类的属性列数组
     * @param map        map的key存表字段列，value存变量
     * @return 校验通过的属性列数组
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected Column[] checkOneByOne(final Column[] entityCols, Map<String, String> map, SqlTemplateEnum sqlTemplateEnum) throws CommonException {

        if (MapUtils.isEmpty(map)) {
            return null;
        }

        List<Column> cols = new ArrayList<>();

        Set<String> tabColNameSet = map.keySet();
        for (String tabColName : tabColNameSet) {
            String attrName = map.get(tabColName);

            // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的字段{2}对应的属性变量为空）
            StringUtils.checkBlank(attrName, SQT_CLASS, "ERROR-DB-SQT0000000021", paramId, sqlTemplateEnum.getKey(), tabColName);

            Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, tabColName);
            if (ObjectUtils.isEmpty(column) && !StringUtils.trim(SQLConstants.SQL_LIMIT).equals(tabColName)) {
                // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的字段【{2}】在实体类【{3}】中不存在）
                ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000022", paramId, sqlTemplateEnum.getKey(), tabColName, entity.getClass().getName());
            }

            if (ObjectUtils.isNotEmpty(column)) {
                String attrN = column.getAttrName();
                if (attrName.equals(StringUtils.strCat(SQLConstants.SQL_COLON, attrN, SQLConstants.SQL_COLON))) {
                    cols.add(column);
                } else {
                    if (attrNameCheck(attrName, cols, tabColName)) {
                        // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的属性列{2}与属性变量{3}不一一对应）
                        ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000023", paramId, sqlTemplateEnum.getKey(), tabColName, attrName);
                    }
                }
            } else {
                if (StringUtils.trim(SQLConstants.SQL_LIMIT).equals(tabColName)) {
                    if (attrNameCheck(attrName, cols, tabColName)) {
                        // 请检查SQL模板参数【id="{0}"】配置（属性【key="{1}"】中的LIMIT子句里的变量{2}）
                        ExceptionUtils.throwCommonException(SQT_CLASS, "ERROR-DB-SQT0000000032", paramId, sqlTemplateEnum.getKey(), attrName);
                    }
                }
            }
        }
        return cols.toArray(new Column[0]);
    }

    /**
     * 校验属性名, 存在多个以逗号分隔
     *
     * @param attrName 属性名
     * @return true：属性名合法；false：属性名不合法
     * @since 1.0.0
     */
    private boolean attrNameCheck(String attrName, List<Column> cols, String tabColName) {
        boolean isValid = true;
        FleaEntity fleaEntity = null;
        if (ObjectUtils.isNotEmpty(entity) && entity instanceof FleaEntity) {
            fleaEntity = (FleaEntity) entity;
        }
        String[] aNameArr = StringUtils.split(attrName, SQLConstants.SQL_COMMA);
        if (ArrayUtils.isNotEmpty(aNameArr)) {
            for (String aName : aNameArr) {
                aName = StringUtils.trim(aName);
                String aNameVar = StringUtils.subStrLast(aName, aName.length() - 1);
                aNameVar = StringUtils.subStrBefore(aNameVar, aNameVar.length() - 1);
                if (ObjectUtils.isNotEmpty(fleaEntity) && !fleaEntity.contains(aNameVar)) {
                    isValid = false;
                    break;
                }
                if (ObjectUtils.isNotEmpty(fleaEntity) && fleaEntity.contains(aNameVar)) {
                    Column column = new Column();
                    column.setAttrName(aNameVar);
                    column.setAttrValue(fleaEntity.get(aNameVar));
                    column.setTabColumnName(tabColName);
                    cols.add(column);
                }
            }
        }
        return !isValid;
    }

    /**
     * 原生SQL和对应参数的二次处理
     *
     * @param params SQL参数Map集合(K ：实体属性变量名 V : 参数值)
     * @since 1.0.0
     */
    private void finalSqlTemplate(Map<String, Object> params, Column[] entityCols) {
        // 根据SQL参数Map集合从SQL模板上sql获取自定义SQL模板参数List<SqlParam>集合
        if (MapUtils.isNotEmpty(params)) {
            Set<String> keySet = params.keySet();
            Iterator<String> keyIt = keySet.iterator();

            // 这边需要暂存， 因为下面替换了指定的元素 为 "?", 导致字段所在位置发生变化
            String sqlStr = sql.toString();

            while (keyIt.hasNext()) {
                String key = keyIt.next();

                // 从sql中获取指定实体属性变量名 所在的起始位置
                int index = sqlStr.indexOf(SQLConstants.SQL_COLON + key);

                Column column = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_ATTR_NAME, key);
                SqlParam sqlParam = new SqlParam();
                if (ObjectUtils.isNotEmpty(column)) {
                    sqlParam.setAttrName(column.getAttrName());
                    sqlParam.setAttrValue(column.getAttrValue());
                    sqlParam.setTabColName(column.getTabColumnName());
                } else {
                    sqlParam.setAttrName(key);
                    sqlParam.setAttrValue(params.get(key));
                    sqlParam.setTabColName(paramTabCols.get(key));
                }
                // 设置参数索引为起始位置，后面需要重新根据这个排序确定
                sqlParam.setIndex(index);
                // 添加SQL参数
                sqlParams.add(sqlParam);
                // 将sql中的 :实体属性变量名 替换为 ?
                StringUtils.replace(sql, SQLConstants.SQL_COLON + key + SQLConstants.SQL_COLON, SQLConstants.SQL_PLACEHOLDER);
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

    /**
     * 根据关系编号，获取对应关系配置信息
     *
     * @param relationId 关系编号
     * @return 关系配置信息
     * @since 1.0.0
     */
    protected Relation getSqlRelation(String relationId) {
        return SqlTemplateConfig.getConfig().getRelation(relationId);
    }

    /**
     * 根据模板编号，获取对应SQL模板
     *
     * @param templateId SQL模板编号
     * @return SQL模板
     * @since 1.0.0
     */
    protected Template getSqlTemplate(String templateId) {
        return SqlTemplateConfig.getConfig().getTemplate(templateId);
    }

    /**
     * 根据模板参数编号，获取对应SQL模板参数配置信息
     *
     * @param paramId SQL模板参数编号
     * @return SQL模板参数配置
     * @since 1.0.0
     */
    protected Param getSqlTemplateParam(String paramId) {
        return SqlTemplateConfig.getConfig().getParam(paramId);
    }

    /**
     * 特殊处理，初始化由具体模板实现
     *
     * @param sql        原声SQL
     * @param params     SQL参数
     * @param entityCols 实体类T的对象的属性列相关信息
     * @param propMap    模板属性配置信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected abstract void initSqlTemplate(StringBuilder sql, Map<String, Object> params, final Column[] entityCols, Map<String, Property> propMap) throws CommonException;

    @Override
    public String getId() {
        return relationId;
    }

    /**
     * 初始化关系配置（SQL模板和模板参数）
     *
     * @param id 关系编号
     * @since 1.0.0
     */
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

    @Override
    public String toNativeSql() {
        return sql.toString();
    }

    @Override
    public List<SqlParam> toNativeParams() {
        return sqlParams;
    }

    @Override
    public TemplateTypeEnum getTemplateType() {
        return templateType;
    }
}
