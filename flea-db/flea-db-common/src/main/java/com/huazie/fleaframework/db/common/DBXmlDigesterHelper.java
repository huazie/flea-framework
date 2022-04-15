package com.huazie.fleaframework.db.common;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.xml.XmlDigesterHelper;
import com.huazie.fleaframework.db.common.exception.LibSplitException;
import com.huazie.fleaframework.db.common.exception.SqlTemplateException;
import com.huazie.fleaframework.db.common.lib.split.config.Lib;
import com.huazie.fleaframework.db.common.lib.split.config.Libs;
import com.huazie.fleaframework.db.common.lib.split.config.Transaction;
import com.huazie.fleaframework.db.common.sql.template.config.Param;
import com.huazie.fleaframework.db.common.sql.template.config.Params;
import com.huazie.fleaframework.db.common.sql.template.config.Property;
import com.huazie.fleaframework.db.common.sql.template.config.Relation;
import com.huazie.fleaframework.db.common.sql.template.config.Relations;
import com.huazie.fleaframework.db.common.sql.template.config.Rule;
import com.huazie.fleaframework.db.common.sql.template.config.Rules;
import com.huazie.fleaframework.db.common.sql.template.config.Sql;
import com.huazie.fleaframework.db.common.sql.template.config.Template;
import com.huazie.fleaframework.db.common.sql.template.config.Templates;
import com.huazie.fleaframework.db.common.table.split.config.FleaTableSplit;
import com.huazie.fleaframework.db.common.table.split.config.Split;
import com.huazie.fleaframework.db.common.table.split.config.Splits;
import com.huazie.fleaframework.db.common.table.split.config.Table;
import com.huazie.fleaframework.db.common.table.split.config.TableFile;
import com.huazie.fleaframework.db.common.table.split.config.TableFiles;
import com.huazie.fleaframework.db.common.table.split.config.Tables;
import org.apache.commons.digester.Digester;

import java.io.InputStream;
import java.util.List;

/**
 * Flea DB 模块 XML配置文件解析类（涉及SQL模板配置 flea-sql-template.xml、
 * 分表配置 flea-table-split.xml、分库配置 flea-lib-split.xml）
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class DBXmlDigesterHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(DBXmlDigesterHelper.class);

    private static volatile DBXmlDigesterHelper xmlDigester;

    /**
     * 只允许通过getInstance()获取 XML解析类
     */
    private DBXmlDigesterHelper() {
    }

    /**
     * 获取XML工具类
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static DBXmlDigesterHelper getInstance() {
        if (ObjectUtils.isEmpty(xmlDigester)) {
            synchronized (DBXmlDigesterHelper.class) {
                if (ObjectUtils.isEmpty(xmlDigester)) {
                    xmlDigester = new DBXmlDigesterHelper();
                }
            }
        }
        return xmlDigester;
    }

    /**
     * 获取分库配置集合定义
     *
     * @return 分库配置集合定义
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    public Libs getLibs() throws CommonException {
        return newLibs();
    }

    private Libs newLibs() throws CommonException {
        Libs libs = null;
        String fileName = DBConstants.LibTableSplitConstants.LIB_SPLIT_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(DBConstants.LibTableSplitConstants.LIB_SPLIT_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(DBConstants.LibTableSplitConstants.LIB_SPLIT_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Use the specified flea-lib-split.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Use the current flea-lib-split.xml :" + fileName);
            LOGGER.debug("Start to parse the flea-lib-split.xml");
        }

        try (InputStream input = IOUtils.getInputStreamFromClassPath(fileName)) {

            // 该路径下【0】找不到指定配置文件
            ObjectUtils.checkEmpty(input, LibSplitException.class, "ERROR-DB-SQT0000000030", fileName);

            libs = XmlDigesterHelper.parse(input, newFleaLibSplitFileDigester(), Libs.class);

        } catch (Exception e) {
            // XML转化异常：
            ExceptionUtils.throwCommonException(LibSplitException.class, "ERROR-DB-SQT0000000031", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End to parse the flea-lib-split.xml");
        }

        return libs;
    }

    /**
     * 解析flea-lib-split.xml的Digester对象
     *
     * @return Digester对象
     * @since 1.0.0
     */
    private Digester newFleaLibSplitFileDigester() {
        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("libs", Libs.class.getName());
        digester.addSetProperties("libs");

        digester.addObjectCreate("libs/lib", Lib.class.getName());
        digester.addSetProperties("libs/lib");

        digester.addObjectCreate("libs/lib/transaction", Transaction.class.getName());
        digester.addSetProperties("libs/lib/transaction");

        digester.addObjectCreate("libs/lib/splits", Splits.class.getName());
        digester.addSetProperties("libs/lib/splits");

        digester.addObjectCreate("libs/lib/splits/split", Split.class.getName());
        digester.addSetProperties("libs/lib/splits/split");

        digester.addSetNext("libs/lib", "addLib", Lib.class.getName());
        digester.addSetNext("libs/lib/transaction", "setTransaction", Transaction.class.getName());
        digester.addSetNext("libs/lib/splits", "setSplits", Splits.class.getName());
        digester.addSetNext("libs/lib/splits/split", "addSplit", Split.class.getName());
        return digester;
    }

    /**
     * 获取分表配置集合定义
     *
     * @return 分表配置集合定义
     * @since 2.0.0
     */
    public FleaTableSplit getFleaTableSplit() {
        return newFleaTableSplit();
    }

    private FleaTableSplit newFleaTableSplit() {
        FleaTableSplit fleaTableSplit;
        String fileName = DBConstants.LibTableSplitConstants.TABLE_SPLIT_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(DBConstants.LibTableSplitConstants.TABLE_SPLIT_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(DBConstants.LibTableSplitConstants.TABLE_SPLIT_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Use the specified flea-table-split.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Use the current flea-table-split.xml :" + fileName);
            LOGGER.debug("Start to parse the flea-table-split.xml");
        }

        Digester digester = newFleaTableSplitFileDigester();
        fleaTableSplit = XmlDigesterHelper.parse(fileName, digester, FleaTableSplit.class);

        if (ObjectUtils.isNotEmpty(fleaTableSplit)) {
            TableFiles tableFiles = fleaTableSplit.getTableFiles();
            if (ObjectUtils.isNotEmpty(tableFiles)) {
                List<TableFile> tableFileList = tableFiles.getTableFiles();
                if (CollectionUtils.isNotEmpty(tableFileList)) {
                    for (TableFile tableFile : tableFileList) {
                        if (ObjectUtils.isNotEmpty(tableFile)) {
                            // 解析其他分表配置文件
                            FleaTableSplit other = XmlDigesterHelper.parse(tableFile.getLocation(), digester, FleaTableSplit.class);
                            if (ObjectUtils.isNotEmpty(other)) {
                                Tables otherTables = other.getTables();
                                if (ObjectUtils.isNotEmpty(otherTables)) {
                                    tableFile.setTableList(otherTables.getTableList());
                                }
                            }
                        }
                    }
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End to parse the flea-table-split.xml");
        }

        return fleaTableSplit;
    }

    /**
     * 解析flea-table-split.xml的Digester对象
     *
     * @return Digester对象
     * @since 1.0.0
     */
    private Digester newFleaTableSplitFileDigester() {
        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("flea-table-split", FleaTableSplit.class.getName());
        digester.addSetProperties("flea-table-split");

        digester.addObjectCreate("flea-table-split/tables", Tables.class.getName());
        digester.addSetProperties("flea-table-split/tables");

        digester.addObjectCreate("flea-table-split/tables/table", Table.class.getName());
        digester.addSetProperties("flea-table-split/tables/table");

        digester.addObjectCreate("flea-table-split/tables/table/splits", Splits.class.getName());
        digester.addSetProperties("flea-table-split/tables/table/splits");

        digester.addObjectCreate("flea-table-split/tables/table/splits/split", Split.class.getName());
        digester.addSetProperties("flea-table-split/tables/table/splits/split");

        digester.addSetNext("flea-table-split/tables", "setTables", Tables.class.getName());
        digester.addSetNext("flea-table-split/tables/table", "addTable", Table.class.getName());
        digester.addSetNext("flea-table-split/tables/table/splits", "setSplits", Splits.class.getName());
        digester.addSetNext("flea-table-split/tables/table/splits/split", "addSplit", Split.class.getName());

        // 其他分表配置文件集
        digester.addObjectCreate("flea-table-split/table-files", TableFiles.class.getName());
        digester.addSetProperties("flea-table-split/table-files");

        digester.addObjectCreate("flea-table-split/table-files/table-file", TableFile.class.getName());
        digester.addSetProperties("flea-table-split/table-files/table-file");

        digester.addSetNext("flea-table-split/table-files", "setTableFiles", TableFiles.class.getName());
        digester.addSetNext("flea-table-split/table-files/table-file", "addTableFile", TableFile.class.getName());
        digester.addCallMethod("flea-table-split/table-files/table-file/location", "setLocation", 0);

        return digester;
    }

    /**
     * 获取Sql模板配置
     *
     * @return Sql模板对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public Sql getSqlTemplate() throws CommonException {
        return newSqlTemplate();
    }

    private Sql newSqlTemplate() throws CommonException {
        Sql sqlTemplate = null;

        String fileName = DBConstants.SqlTemplateConstants.SQL_TEMPLATE_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(DBConstants.SqlTemplateConstants.SQL_TEMPLATE_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(DBConstants.SqlTemplateConstants.SQL_TEMPLATE_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Use the specified flea-sql-template.xml :" + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Use the current flea-sql-template.xml :" + fileName);
            LOGGER.debug("Start to parse the flea-sql-template.xml");
        }

        try (InputStream input = IOUtils.getInputStreamFromClassPath(fileName)) {

            // 该路径下【0】找不到指定配置文件
            ObjectUtils.checkEmpty(input, SqlTemplateException.class, "ERROR-DB-SQT0000000030", fileName);

            sqlTemplate = XmlDigesterHelper.parse(input, newFleaSqlTemplateFileDigester(), Sql.class);

        } catch (Exception e) {
            // XML转化异常：
            ExceptionUtils.throwCommonException(SqlTemplateException.class, "ERROR-DB-SQT0000000031", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End to parse the flea-sql-template.xml");
        }

        return sqlTemplate;
    }

    /**
     * 解析flea-sql-template.xml的Digester对象
     *
     * @return Digester对象
     * @since 1.0.0
     */
    private Digester newFleaSqlTemplateFileDigester() {
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
        return digester;
    }

}
