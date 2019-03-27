package com.huazie.frame.db.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

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
import com.huazie.frame.db.common.tab.split.config.Split;
import com.huazie.frame.db.common.tab.split.config.Table;
import com.huazie.frame.db.common.tab.split.config.Tables;

/**
 * XML解析类（涉及SQL模板配置flea-sql-template.xml, 分表配置flea-table-split.xml）
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class XmlDigesterHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(XmlDigesterHelper.class);

    private static XmlDigesterHelper xmlDigester = null;

    private static Boolean isInit = Boolean.FALSE;
    private static Boolean isTablesInit = Boolean.FALSE;
    private static Boolean isSqlTemplateInit = Boolean.FALSE;

    private static Tables tables = null;

    private static Sql sql = null;

    /**
     * 只允许通过getInstance()获取 XML解析类
     */
    private XmlDigesterHelper() {
    }

    /**
     * 获取XML工具类
     *
     * @return XML解析工具类对象
     * @throws Exception
     * @date 2018年1月26日
     */
    public static XmlDigesterHelper getInstance() throws Exception {
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
     * 获取分表信息
     *
     * @return 分表信息对象
     * @date 2018年1月26日
     */
    public Tables getTables() {
        if (tables == null) {
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
        Tables tabs = null;
        String fileName = "flea/db/flea-table-split.xml";
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.db.table.split.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.db.table.split.filename"));
            if (XmlDigesterHelper.LOGGER.isDebugEnabled()) {
                XmlDigesterHelper.LOGGER.debug("XmlDigesterHelper##newTables Use the specified flea-table-split.xml :" + fileName);
            }
        }

        if (XmlDigesterHelper.LOGGER.isDebugEnabled()) {
            XmlDigesterHelper.LOGGER.debug("XmlDigesterHelper##newTables Start parse the flea-table-split.xml");
        }

        InputStream input = ResourcesUtil.getInputStreamFromClassPath(fileName);
        if (input == null) {
            throw new TableSplitException("该路径下找不到指定配置文件");
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
        } catch (IOException e) {
            throw new TableSplitException("XML转化异常：", e);
        } catch (SAXException e) {
            throw new TableSplitException("XML转化异常：", e);
        }

        if (XmlDigesterHelper.LOGGER.isDebugEnabled()) {
            XmlDigesterHelper.LOGGER.debug("XmlDigesterHelper##newTables End parse the flea-table-split.xml");
        }

        return tabs;
    }

    /**
     * 获取Sql模板配置
     *
     * @return Sql模板对象
     * @date 2018年1月26日
     */
    public Sql getSqlTemplate() {
        if (sql == null) {
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
        Sql sqlTemplate = null;

        String fileName = "flea/db/flea-sql-template.xml";
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.db.sql.template.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.db.sql.template.filename"));
            if (XmlDigesterHelper.LOGGER.isDebugEnabled()) {
                XmlDigesterHelper.LOGGER.debug("XmlDigesterHelper##newSqlTemplate Use the specified flea-sql-template.xml :" + fileName);
            }
        }

        if (XmlDigesterHelper.LOGGER.isDebugEnabled()) {
            XmlDigesterHelper.LOGGER.debug("XmlDigesterHelper##newTables Start parse the flea-sql-template.xml");
        }

        InputStream input = ResourcesUtil.getInputStreamFromClassPath(fileName);
        if (input == null) {
            throw new SqlTemplateException("该路径下找不到指定配置文件");
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
        } catch (IOException e) {
            throw new SqlTemplateException("XML转化异常：", e);
        } catch (SAXException e) {
            throw new SqlTemplateException("XML转化异常：", e);
        }

        if (XmlDigesterHelper.LOGGER.isDebugEnabled()) {
            XmlDigesterHelper.LOGGER.debug("XmlDigesterHelper##newTables End parse the flea-sql-template.xml");
        }

        return sqlTemplate;
    }

}
