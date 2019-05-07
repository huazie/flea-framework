package com.huazie.frame.db.common.sql.template.config;

import com.huazie.frame.db.common.XmlDigesterHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p> Sql模板配置工具类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class SqlTemplateConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(SqlTemplateConfig.class);

    private static volatile SqlTemplateConfig config;

    private Sql sql;

    /**
     * <p> 只允许通过getConfig() 获取Sql模板配置类对象 </p>
     *
     * @param sql
     */
    private SqlTemplateConfig(Sql sql) {
        this.sql = sql;
    }

    /**
     * <p> 获取Sql模板配置类对象 </p>
     *
     * @return Sql模板配置类对象
     */
    public static SqlTemplateConfig getConfig() {
        if (config == null) {
            synchronized (SqlTemplateConfig.class) {
                try {
                    if (null == config) {
                        config = new SqlTemplateConfig(XmlDigesterHelper.getInstance().getSqlTemplate());
                    }
                } catch (Exception e) {
                    LOGGER.error("Fail to init flea-sql-template.xml", e);
                }
            }
        }
        return config;
    }

    /**
     * <p> 根据校验规则编号，获取指定校验规则 </p>
     *
     * @param ruleId 校验规则编号
     * @return 校验规则对象
     * @since 1.0.0
     */
    public Rule getRule(String ruleId) {
        Rule rule = null;
        Map<String, Rule> rulesMap;
        if (this.sql != null) {
            Rules rules = this.sql.getRules();
            if (rules != null) {
                rulesMap = rules.toRulesMap();
                if (rulesMap != null && !rulesMap.isEmpty()) {
                    rule = rulesMap.get(ruleId);
                }
            }
        }
        return rule;
    }

    /**
     * <p> 根据新增模板编号，获取指定的新增模板 </p>
     *
     * @param tempId Sql模板编号
     * @return
     * @since 1.0.0
     */
    public Template getInsertTemplate(String tempId) {
        Template template = null;
        Map<String, Template> insertsMap = null;
        if (this.sql != null) {
            Insert insert = this.sql.getInsert();
            if (insert != null) {
                insertsMap = insert.toInsertsMap();
                if (insertsMap != null && !insertsMap.isEmpty()) {
                    template = insertsMap.get(tempId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据更新模板编号，获取指定的更新模板 </p>
     *
     * @param tempId Sql模板编号
     * @return
     * @since 1.0.0
     */
    public Template getUpdateTemplate(String tempId) {
        Template template = null;
        Map<String, Template> updatesMap;
        if (this.sql != null) {
            Update update = this.sql.getUpdate();
            if (update != null) {
                updatesMap = update.toUpdatesMap();
                if (updatesMap != null && !updatesMap.isEmpty()) {
                    template = updatesMap.get(tempId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据查询模板编号，获取指定的查询模板 </p>
     *
     * @param tempId Sql模板编号
     * @return
     * @since 1.0.0
     */
    public Template getSelectTemplate(String tempId) {
        Template template = null;
        Map<String, Template> selectsMap;
        if (this.sql != null) {
            Select select = this.sql.getSelect();
            if (select != null) {
                selectsMap = select.toSelectsMap();
                if (selectsMap != null && !selectsMap.isEmpty()) {
                    template = selectsMap.get(tempId);
                }
            }
        }
        return template;
    }

    /**
     * <p> 根据删除模板编号，获取指定的删除模板 </p>
     *
     * @param tempId Sql模板编号
     * @return
     * @since 1.0.0
     */
    public Template getDeleteTemplate(String tempId) {
        Template template = null;
        Map<String, Template> deletesMap;
        if (this.sql != null) {
            Delete delete = this.sql.getDelete();
            if (delete != null) {
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
