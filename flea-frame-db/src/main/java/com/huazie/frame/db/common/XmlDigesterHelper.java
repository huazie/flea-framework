package com.huazie.frame.db.common;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ResourcesUtil;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.SqlTemplateException;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.sql.template.config.Delete;
import com.huazie.frame.db.common.sql.template.config.Insert;
import com.huazie.frame.db.common.sql.template.config.Property;
import com.huazie.frame.db.common.sql.template.config.Rule;
import com.huazie.frame.db.common.sql.template.config.Rules;
import com.huazie.frame.db.common.sql.template.config.Select;
import com.huazie.frame.db.common.sql.template.config.Sql;
import com.huazie.frame.db.common.sql.template.config.Template;
import com.huazie.frame.db.common.sql.template.config.Update;
import com.huazie.frame.db.common.table.split.config.Split;
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
public class XmlDigesterHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(XmlDigesterHelper.class);

    private static volatile XmlDigesterHelper xmlDigester;

    private static Boolean isInit = Boolean.FALSE;
    private static Boolean isTablesInit = Boolean.FALSE;
    private static Boolean isSqlTemplateInit = Boolean.FALSE;

    private static Tables tables;

    private static Sql sql;

    /**
     * <p> 只允许通过getInstance()获取 XML解析类 </p>
     */
    private XmlDigesterHelper() {
    }

    /**
     * <p> 获取XML工具类 </p>
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static XmlDigesterHelper getInstance() {
        if (isInit.equals(Boolean.FALSE)) {
            synchronized (isInit) {
                if (isInit.equals(Boolean.FALSE)) {
                    isInit = Boolean.TRUE;
                    xmlDigester = new XmlDigesterHelper();
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
                LOGGER.debug("XmlDigesterHelper##newTables Use the specified flea-table-split.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("XmlDigesterHelper##newTables Start parse the flea-table-split.xml");
        }

        InputStream input = ResourcesUtil.getInputStreamFromClassPath(fileName);
        if (ObjectUtils.isEmpty(input)) {
            throw new TableSplitException("ERROR-DB-SQT0000000026", fileName);
        }

        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("tables", Tables.class.getName());
        digester.addSetProperties("tables");

        digester.addObjectCreate("tables/table", Table.class.getName());
        digester.addSetProperties("tables/table");

        digester.addObjectCreate("tables/table/split", Split.class.getName());
        digester.addSetProperties("tables/table/split");

        digester.addSetNext("tables/table", "addTable", Table.class.getName());
        digester.addSetNext("tables/table/split", "setSplit", Split.class.getName());

        try {
            tabs = (Tables) digester.parse(input);
        } catch (Exception e) {
            throw new TableSplitException("ERROR-DB-SQT0000000027", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("XmlDigesterHelper##newTables End parse the flea-table-split.xml");
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
                LOGGER.debug("XmlDigesterHelper##newSqlTemplate Use the specified flea-sql-template.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("XmlDigesterHelper##newSqlTemplate Use the current flea-sql-template.xml :" + fileName);
            LOGGER.debug("XmlDigesterHelper##newTables Start parse the flea-sql-template.xml");
        }

        InputStream input = ResourcesUtil.getInputStreamFromClassPath(fileName);
        if (ObjectUtils.isEmpty(input)) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000026", fileName);
        }

        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("sql", Sql.class.getName());
        digester.addSetProperties("sql");

        digester.addObjectCreate("sql/rules", Rules.class.getName());
        digester.addSetProperties("sql/rules");

        digester.addObjectCreate("sql/rules/rule", Rule.class.getName());
        digester.addSetProperties("sql/rules/rule");

        digester.addObjectCreate("sql/rules/rule/property", Property.class.getName());
        digester.addSetProperties("sql/rules/rule/property");

        digester.addObjectCreate("sql/insert", Insert.class.getName());
        digester.addSetProperties("sql/insert");

        digester.addObjectCreate("sql/insert/template", Template.class.getName());
        digester.addSetProperties("sql/insert/template");

        digester.addObjectCreate("sql/insert/template/property", Property.class.getName());
        digester.addSetProperties("sql/insert/template/property");

        digester.addObjectCreate("sql/update", Update.class.getName());
        digester.addSetProperties("sql/update");

        digester.addObjectCreate("sql/update/template", Template.class.getName());
        digester.addSetProperties("sql/update/template");

        digester.addObjectCreate("sql/update/template/property", Property.class.getName());
        digester.addSetProperties("sql/update/template/property");

        digester.addObjectCreate("sql/select", Select.class.getName());
        digester.addSetProperties("sql/select");

        digester.addObjectCreate("sql/select/template", Template.class.getName());
        digester.addSetProperties("sql/select/template");

        digester.addObjectCreate("sql/select/template/property", Property.class.getName());
        digester.addSetProperties("sql/select/template/property");

        digester.addObjectCreate("sql/delete", Delete.class.getName());
        digester.addSetProperties("sql/delete");

        digester.addObjectCreate("sql/delete/template", Template.class.getName());
        digester.addSetProperties("sql/delete/template");

        digester.addObjectCreate("sql/delete/template/property", Property.class.getName());
        digester.addSetProperties("sql/delete/template/property");

        digester.addSetNext("sql/rules", "setRules", Rules.class.getName());
        digester.addSetNext("sql/rules/rule", "addRule", Rule.class.getName());
        digester.addSetNext("sql/rules/rule/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/insert", "setInsert", Insert.class.getName());
        digester.addSetNext("sql/insert/template", "addTemplate", Template.class.getName());
        digester.addSetNext("sql/insert/template/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/update", "setUpdate", Update.class.getName());
        digester.addSetNext("sql/update/template", "addTemplate", Template.class.getName());
        digester.addSetNext("sql/update/template/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/select", "setSelect", Select.class.getName());
        digester.addSetNext("sql/select/template", "addTemplate", Template.class.getName());
        digester.addSetNext("sql/select/template/property", "addProperty", Property.class.getName());

        digester.addSetNext("sql/delete", "setDelete", Delete.class.getName());
        digester.addSetNext("sql/delete/template", "addTemplate", Template.class.getName());
        digester.addSetNext("sql/delete/template/property", "addProperty", Property.class.getName());

        try {
            sqlTemplate = (Sql) digester.parse(input);
        } catch (Exception e) {
            throw new SqlTemplateException("ERROR-DB-SQT0000000027", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("XmlDigesterHelper##newTables End parse the flea-sql-template.xml");
        }

        return sqlTemplate;
    }

}
