package com.huazie.frame.db.common;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ResourcesUtil;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.sql.template.config.Param;
import com.huazie.frame.db.common.sql.template.config.Params;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.Relation;
import com.huazie.frame.db.common.sql.template.config.Relations;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.Rules;
import com.huazie.frame.db.common.sql.template.config.Sql;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.sql.template.config.Templates;
import com.huazie.frame.db.common.table.split.config.Split;
import com.huazie.frame.db.common.table.split.config.Splits;
import com.huazie.frame.db.common.table.split.config.Table;
import com.huazie.frame.db.common.table.split.config.Tables;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * XML解析类（涉及SQL模板配置flea-sql-template.xml, 分表配置flea-table-split.xml）
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DBXmlDigesterHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(DBXmlDigesterHelper.class);

    private static volatile DBXmlDigesterHelper xmlDigester;

    private static Boolean isInit = Boolean.FALSE;
    private static Boolean isTablesInit = Boolean.FALSE;
    private static Boolean isSqlTemplateInit = Boolean.FALSE;

    private static Tables tables;

    private static Sql sql;

    /**
     * <p> 只允许通过getInstance()获取 XML解析类 </p>
     */
    private DBXmlDigesterHelper() {
    }

    /**
     * <p> 获取XML工具类 </p>
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static DBXmlDigesterHelper getInstance() {
        if (isInit.equals(Boolean.FALSE)) {
            synchronized (isInit) {
                if (isInit.equals(Boolean.FALSE)) {
                    isInit = Boolean.TRUE;
                    xmlDigester = new DBXmlDigesterHelper();
                }
            }
        }
        return xmlDigester;
    }

    /**
     * <p> 获取分表信息 </p>
     *
     * @return 分表信息对象
     * @since 1.0.0
     */
    public Tables getTables() {
        if (ObjectUtils.isEmpty(tables)) {
            synchronized (isTablesInit) {
                if (isTablesInit.equals(Boolean.FALSE)) {
                    try {
                        tables = newTables();
                        isTablesInit = Boolean.TRUE;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return tables;
    }

    private Tables newTables() throws TableSplitException {
        Tables tabs;
        String fileName = "flea/db/flea-table-split.xml";
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.db.table.split.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.db.table.split.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("DBXmlDigesterHelper##newTables Use the specified flea-table-split.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DBXmlDigesterHelper##newTables() Use the current flea-sql-template.xml :" + fileName);
            LOGGER.debug("DBXmlDigesterHelper##newTables() Start to parse the flea-table-split.xml");
        }

        InputStream input = ResourcesUtil.getInputStreamFromClassPath(fileName);
        if (ObjectUtils.isEmpty(input)) {
            throw new TableSplitException("ERROR-DB-SQT0000000030", fileName);
        }

        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("tables", Tables.class.getName());
        digester.addSetProperties("tables");

        digester.addObjectCreate("tables/table", Table.class.getName());
        digester.addSetProperties("tables/table");

        digester.addObjectCreate("tables/table/splits", Splits.class.getName());
        digester.addSetProperties("tables/table/splits");

        digester.addObjectCreate("tables/table/splits/split", Split.class.getName());
        digester.addSetProperties("tables/table/splits/split");

        digester.addSetNext("tables/table", "addTable", Table.class.getName());
        digester.addSetNext("tables/table/splits", "setSplits", Splits.class.getName());
        digester.addSetNext("tables/table/splits/split", "addSplit", Split.class.getName());

        try {
            tabs = (Tables) digester.parse(input);
        } catch (Exception e) {
            throw new TableSplitException("ERROR-DB-SQT0000000031", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DBXmlDigesterHelper##newTables() End to parse the flea-table-split.xml");
        }

        return tabs;
    }

    /**
     * <p> 获取Sql模板配置 </p>
     *
     * @return Sql模板对象
     * @since 1.0.0
     */
    public Sql getSqlTemplate() {
        if (ObjectUtils.isEmpty(sql)) {
            synchronized (isSqlTemplateInit) {
                if (isSqlTemplateInit.equals(Boolean.FALSE)) {
                    try {
                        sql = newSqlTemplate();
                        isSqlTemplateInit = Boolean.TRUE;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return sql;
    }

    private Sql newSqlTemplate() throws SqlTemplateException {
        Sql sqlTemplate;

        String fileName = "flea/db/flea-sql-template.xml";
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.db.sql.template.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.db.sql.template.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("DBXmlDigesterHelper##newSqlTemplate Use the specified flea-sql-template.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DBXmlDigesterHelper##newSqlTemplate() Use the current flea-sql-template.xml :" + fileName);
            LOGGER.debug("DBXmlDigesterHelper##newSqlTemplate() Start to parse the flea-sql-template.xml");
        }

        InputStream input = ResourcesUtil.getInputStreamFromClassPath(fileName);
        if (ObjectUtils.isEmpty(input)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000030", fileName);
        }

        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("sql", Sql.class.getName());
        digester.addSetProperties("sql");
        // SQL模板校验规则
        digester.addObjectCreate("sql/rules", Rules.class.getName());
        digester.addSetProperties("sql/rules");

        digester.addObjectCreate("sql/rules/rule", Rule.class.getName());
        digester.addSetProperties("sql/rules/rule");

        digester.addObjectCreate("sql/rules/rule/property", Property.class.getName());
        digester.addSetProperties("sql/rules/rule/property");
        // SQL模板
        digester.addObjectCreate("sql/templates", Templates.class.getName());
        digester.addSetProperties("sql/templates");

        digester.addObjectCreate("sql/templates/template", Template.class.getName());
        digester.addSetProperties("sql/templates/template");

        digester.addObjectCreate("sql/templates/template/property", Property.class.getName());
        digester.addSetProperties("sql/templates/template/property");
        // SQL模板参数
        digester.addObjectCreate("sql/params", Params.class.getName());
        digester.addSetProperties("sql/params");

        digester.addObjectCreate("sql/params/param", Param.class.getName());
        digester.addSetProperties("sql/params/param");

        digester.addObjectCreate("sql/params/param/property", Property.class.getName());
        digester.addSetProperties("sql/params/param/property");
        // SQL关系
        digester.addObjectCreate("sql/relations", Relations.class.getName());
        digester.addSetProperties("sql/relations");

        digester.addObjectCreate("sql/relations/relation", Relation.class.getName());
        digester.addSetProperties("sql/relations/relation");

        digester.addObjectCreate("sql/relations/relation/property", Property.class.getName());
        digester.addSetProperties("sql/relations/relation/property");

        digester.addSetNext("sql/rules", "setRules", Rules.class.getName());
        digester.addSetNext("sql/rules/rule", "addRule", Rule.class.getName());
        digester.addSetNext("sql/rules/rule/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/templates", "setTemplates", Templates.class.getName());
        digester.addSetNext("sql/templates/template", "addTemplate", Template.class.getName());
        digester.addSetNext("sql/templates/template/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/params", "setParams", Params.class.getName());
        digester.addSetNext("sql/params/param", "addParam", Param.class.getName());
        digester.addSetNext("sql/params/param/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/relations", "setRelations", Relations.class.getName());
        digester.addSetNext("sql/relations/relation", "addRelation", Relation.class.getName());
        digester.addSetNext("sql/relations/relation/property", "addProperty", Property.class.getName());

        try {
            sqlTemplate = (Sql) digester.parse(input);
        } catch (Exception e) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000031", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DBXmlDigesterHelper##newSqlTemplate() End to parse the flea-sql-template.xml");
        }

        return sqlTemplate;
    }

}
