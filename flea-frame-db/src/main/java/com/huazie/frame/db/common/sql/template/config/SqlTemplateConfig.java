package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.db.common.XmlDigesterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p> SQL模板配置工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlTemplateConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(SqlTemplateConfig.class);

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
                        config = new SqlTemplateConfig(XmlDigesterHelper.getInstance().getSqlTemplate());
                    } catch (Exception e) {
                        LOGGER.error("Fail to init flea-sql-template.xml ：", e);
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
                if (rulesMap != null && !rulesMap.isEmpty()) {
                    rule = rulesMap.get(ruleId);
                }
            }
        }
        return rule;
    }

    /**
     * <p> 根据新增模板编号，获取指定的新增模板配置信息 </p>
     *
     * @param tempId SQL模板编号
     * @return SQL新增模板配置信息（INSERT）
     * @since 1.0.0
     */
    public Template getInsertTemplate(String tempId) {
        Template template = null;
        Map<String, Template> insertsMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Insert insert = sql.getInsert();
            if (ObjectUtils.isNotEmpty(insert)) {
                insertsMap = insert.toInsertsMap();
                if (insertsMap != null && !insertsMap.isEmpty()) {
                    template = insertsMap.get(tempId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据更新模板编号，获取指定的更新模板配置信息 </p>
     *
     * @param tempId SQL模板编号
     * @return SQL更新模板配置信息（UPDATE）
     * @since 1.0.0
     */
    public Template getUpdateTemplate(String tempId) {
        Template template = null;
        Map<String, Template> updatesMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Update update = sql.getUpdate();
            if (ObjectUtils.isNotEmpty(update)) {
                updatesMap = update.toUpdatesMap();
                if (updatesMap != null && !updatesMap.isEmpty()) {
                    template = updatesMap.get(tempId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据查询模板编号，获取指定的查询模板配置信息 </p>
     *
     * @param tempId SQL模板编号
     * @return SQL查询模板配置信息（SELECT）
     * @since 1.0.0
     */
    public Template getSelectTemplate(String tempId) {
        Template template = null;
        Map<String, Template> selectsMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Select select = sql.getSelect();
            if (ObjectUtils.isNotEmpty(select)) {
                selectsMap = select.toSelectsMap();
                if (selectsMap != null && !selectsMap.isEmpty()) {
                    template = selectsMap.get(tempId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据删除模板编号，获取指定的删除模板配置信息 </p>
     *
     * @param tempId SQL模板编号
     * @return SQL删除模板配置信息（DELETE）
     * @since 1.0.0
     */
    public Template getDeleteTemplate(String tempId) {
        Template template = null;
        Map<String, Template> deletesMap;
        if (ObjectUtils.isNotEmpty(sql)) {
            Delete delete = sql.getDelete();
            if (ObjectUtils.isNotEmpty(delete)) {
                deletesMap = delete.toDeletesMap();
                if (deletesMap != null && !deletesMap.isEmpty()) {
                    template = deletesMap.get(tempId);
                }
            }
        }
        return template;
    }

    public Sql getSql() {
        return sql;
    }

    public void setSql(Sql sql) {
        this.sql = sql;
    }

}
