package com.huazie.fleaframework.db.common;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.xml.Import;
import com.huazie.fleaframework.common.util.xml.XmlDigesterHelper;
import com.huazie.fleaframework.db.common.lib.split.config.FleaLibSplit;
import com.huazie.fleaframework.db.common.lib.split.config.Lib;
import com.huazie.fleaframework.db.common.lib.split.config.LibFile;
import com.huazie.fleaframework.db.common.lib.split.config.LibFiles;
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
     * 获取Flea分库定义
     *
     * @return Flea分表定义
     * @since 1.1.0
     */
    public FleaLibSplit getFleaLibSplit() {
        return newFleaLibSplit();
    }

    private FleaLibSplit newFleaLibSplit() {
        String fileName = DBConstants.LibTableSplitConstants.LIB_SPLIT_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(DBConstants.LibTableSplitConstants.LIB_SPLIT_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(DBConstants.LibTableSplitConstants.LIB_SPLIT_FILE_SYSTEM_KEY));
            LOGGER.debug("Use the specified flea-lib-split.xml :" + fileName);
        }

        LOGGER.debug("Use the current flea-lib-split.xml :" + fileName);
        LOGGER.debug("Start to parse the flea-lib-split.xml");

        Digester digester = newFleaLibSplitFileDigester();
        FleaLibSplit fleaLibSplit = XmlDigesterHelper.parse(fileName, digester, FleaLibSplit.class);

        if (ObjectUtils.isNotEmpty(fleaLibSplit)) {
            List<Import> importList = fleaLibSplit.getImportList();
            if (CollectionUtils.isNotEmpty(importList)) {
                LibFiles libFiles = new LibFiles();
                for (Import mImport : importList) {
                    if (ObjectUtils.isEmpty(mImport)) continue;
                    String resource = mImport.getResource();
                    // 解析其他分库配置文件
                    FleaLibSplit other = XmlDigesterHelper.parse(resource, digester, FleaLibSplit.class);
                    if (ObjectUtils.isEmpty(other)) continue;
                    LibFile libFile = new LibFile();
                    libFile.setLocation(resource);
                    Libs otherLibs = other.getLibs();
                    if (ObjectUtils.isNotEmpty(otherLibs)) {
                        libFile.setLibList(otherLibs.getLibList());
                    }
                    libFiles.addLibFile(libFile);
                }
                fleaLibSplit.setLibFiles(libFiles);
            }
        }

        LOGGER.debug("End to parse the flea-lib-split.xml");

        return fleaLibSplit;
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

        digester.addObjectCreate("flea-lib-split", FleaLibSplit.class.getName());
        digester.addSetProperties("flea-lib-split");

        digester.addObjectCreate("flea-lib-split/libs", Libs.class.getName());
        digester.addSetProperties("flea-lib-split/libs");

        digester.addObjectCreate("flea-lib-split/libs/lib", Lib.class.getName());
        digester.addSetProperties("flea-lib-split/libs/lib");

        digester.addObjectCreate("flea-lib-split/libs/lib/transaction", Transaction.class.getName());
        digester.addSetProperties("flea-lib-split/libs/lib/transaction");

        digester.addObjectCreate("flea-lib-split/libs/lib/splits", Splits.class.getName());
        digester.addSetProperties("flea-lib-split/libs/lib/splits");

        digester.addObjectCreate("flea-lib-split/libs/lib/splits/split", Split.class.getName());
        digester.addSetProperties("flea-lib-split/libs/lib/splits/split");

        digester.addSetNext("flea-lib-split/libs", "setLibs", Libs.class.getName());
        digester.addSetNext("flea-lib-split/libs/lib", "addLib", Lib.class.getName());
        digester.addSetNext("flea-lib-split/libs/lib/transaction", "setTransaction", Transaction.class.getName());
        digester.addSetNext("flea-lib-split/libs/lib/splits", "setSplits", Splits.class.getName());
        digester.addSetNext("flea-lib-split/libs/lib/splits/split", "addSplit", Split.class.getName());

        // 其他分表配置文件资源导入
        digester.addObjectCreate("flea-lib-split/import", Import.class.getName());
        digester.addSetProperties("flea-lib-split/import");
        digester.addSetNext("flea-lib-split/import", "addImport", Import.class.getName());

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
        String fileName = DBConstants.LibTableSplitConstants.TABLE_SPLIT_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(DBConstants.LibTableSplitConstants.TABLE_SPLIT_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(DBConstants.LibTableSplitConstants.TABLE_SPLIT_FILE_SYSTEM_KEY));
            LOGGER.debug("Use the specified flea-table-split.xml :" + fileName);
        }

        LOGGER.debug("Use the current flea-table-split.xml :" + fileName);
        LOGGER.debug("Start to parse the flea-table-split.xml");

        Digester digester = newFleaTableSplitFileDigester();
        FleaTableSplit fleaTableSplit = XmlDigesterHelper.parse(fileName, digester, FleaTableSplit.class);

        if (ObjectUtils.isNotEmpty(fleaTableSplit)) {
            List<Import> importList = fleaTableSplit.getImportList();
            if (CollectionUtils.isNotEmpty(importList)) {
                TableFiles tableFiles = new TableFiles();
                for (Import mImport : importList) {
                    if (ObjectUtils.isEmpty(mImport)) continue;
                    String resource = mImport.getResource();
                    // 解析其他分表配置文件
                    FleaTableSplit other = XmlDigesterHelper.parse(resource, digester, FleaTableSplit.class);
                    if (ObjectUtils.isEmpty(other)) continue;
                    TableFile tableFile = new TableFile();
                    tableFile.setLocation(resource);
                    Tables otherTables = other.getTables();
                    if (ObjectUtils.isNotEmpty(otherTables)) {
                        tableFile.setTableList(otherTables.getTableList());
                    }
                    tableFiles.addTableFile(tableFile);
                }
                fleaTableSplit.setTableFiles(tableFiles);
            }
        }

        LOGGER.debug("End to parse the flea-table-split.xml");

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

        // 其他分表配置文件资源导入
        digester.addObjectCreate("flea-table-split/import", Import.class.getName());
        digester.addSetProperties("flea-table-split/import");
        digester.addSetNext("flea-table-split/import", "addImport", Import.class.getName());

        return digester;
    }

    /**
     * 获取Sql模板配置
     *
     * @return Sql模板对象
     * @since 1.0.0
     */
    public Sql getSqlTemplate() {
        return newSqlTemplate();
    }

    private Sql newSqlTemplate() {
        String fileName = DBConstants.SqlTemplateConstants.SQL_TEMPLATE_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(DBConstants.SqlTemplateConstants.SQL_TEMPLATE_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(DBConstants.SqlTemplateConstants.SQL_TEMPLATE_FILE_SYSTEM_KEY));
            LOGGER.debug("Use the specified flea-sql-template.xml :" + fileName);
        }

        LOGGER.debug("Use the current flea-sql-template.xml :" + fileName);
        LOGGER.debug("Start to parse the flea-sql-template.xml");

        Sql sqlTemplate = XmlDigesterHelper.parse(fileName, newFleaSqlTemplateFileDigester(), Sql.class);

        LOGGER.debug("End to parse the flea-sql-template.xml");

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
