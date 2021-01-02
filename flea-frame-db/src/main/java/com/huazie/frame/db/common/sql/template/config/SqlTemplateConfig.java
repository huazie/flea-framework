package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.db.common.DBXmlDigesterHelper;

import java.util.Map;

/**
 * <p> SQL模板配置工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SqlTemplateConfig.class);

    private static volatile SqlTemplateConfig config;

    private Sql sql;

    /**
     * <p> 只允许通过getConfig() 获取SQL模板配置类对象 </p>
     *
     * @param sql SQL模板配置父节点对象
     */
    private SqlTemplateConfig(Sql sql) {
        this.sql = sql;
    }

    /**
     * <p> 获取Sql模板配置类对象 </p>
     *
     * @return SQL模板配置类对象
     */
    public static SqlTemplateConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (SqlTemplateConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    try {
                        config = new SqlTemplateConfig(DBXmlDigesterHelper.getInstance().getSqlTemplate());
                    } catch (Exception e) {
                        LOGGER.error1(new Object() {}, "Fail to init flea-sql-template.xml ：", e);
                    }
                }
            }
        }
        return config;
    }

    /**
     * <p> 根据校验规则编号，获取指定校验规则配置信息 </p>
     *
     * @param ruleId 校验规则编号
     * @return 校验规则对象配置信息
     * @since 1.0.0
     */
    public Rule getRule(String ruleId) {
        Rule rule = null;
        Map<String, Rule> rulesMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Rules rules = sql.getRules();
            if (ObjectUtils.isNotEmpty(rules)) {
                rulesMap = rules.toRulesMap();
                if (MapUtils.isNotEmpty(rulesMap)) {
                    rule = rulesMap.get(ruleId);
                }
            }
        }
        return rule;
    }

    /**
     * <p> 根据SQL模板编号，获取指定的SQL模板配置信息 </p>
     *
     * @param templateId SQL模板编号
     * @return SQL模板配置信息
     * @since 1.0.0
     */
    public Template getTemplate(String templateId) {
        Template template = null;
        Map<String, Template> templatesMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Templates templates = sql.getTemplates();
            if (ObjectUtils.isNotEmpty(templates)) {
                templatesMap = templates.toTemplatesMap();
                if (MapUtils.isNotEmpty(templatesMap)) {
                    template = templatesMap.get(templateId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据SQL模板参数编号，获取指定的SQL模板参数配置信息 </p>
     *
     * @param paramId SQL模板参数编号
     * @return SQL模板参数配置信息
     * @since 1.0.0
     */
    public Param getParam(String paramId) {
        Param param = null;
        Map<String, Param> paramsMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Params params = sql.getParams();
            if (ObjectUtils.isNotEmpty(params)) {
                paramsMap = params.toParamsMap();
                if (MapUtils.isNotEmpty(paramsMap)) {
                    param = paramsMap.get(paramId);
                }
            }
        }
        return param;
    }

    /**
     * <p> 根据关系编号，获取指定的关系配置信息 </p>
     * <p> SQL模板和模板参数关联关系（简称：关系）</p>
     *
     * @param relationId 关系编号
     * @return 关系配置信息
     * @since 1.0.0
     */
    public Relation getRelation(String relationId) {
        Relation relation = null;
        Map<String, Relation> relationsMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Relations relations = sql.getRelations();
            if (ObjectUtils.isNotEmpty(relations)) {
                relationsMap = relations.toRelationsMap();
                if (MapUtils.isNotEmpty(relationsMap)) {
                    relation = relationsMap.get(relationId);
                }
            }
        }
        return relation;
    }

    public Sql getSql() {
        return sql;
    }

    public void setSql(Sql sql) {
        this.sql = sql;
    }

}
