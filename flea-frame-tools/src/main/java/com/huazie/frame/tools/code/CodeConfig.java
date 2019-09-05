package com.huazie.frame.tools.code;

import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.tools.common.ToolsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * <p> 代码生成器配置 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CodeConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeConfig.class);

    private static volatile CodeConfig config;

    private String dbName; // 数据库名

    private String tableName; // 表名

    private String tableNameDesc; // 表名描述

    private String author; // 作者

    private String version; // 版本

    private String rootPath; // 根目录

    private String codePackage; // 代码包名

    private String puDaoCodePackage; // 持久化单元DAO层实现类代码包名

    private String puDaoClassName; // 持久化单元DAO层实现类名

    private String puName; // 持久化单元名

    private String puAliasName; // 持久化单元别名

    public static CodeConfig getConfig() {

        String fileName = ToolsConstants.CodeConfigConstants.CODE_CONFIG_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(ToolsConstants.CodeConfigConstants.CODE_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(ToolsConstants.CodeConfigConstants.CODE_CONFIG_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("MemCachedConfig Use the specified memcached.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedConfig Use the current memcached.properties：{}", fileName);
        }
        // 获取配置文件
        Properties prop = PropertiesUtil.getProperties(fileName);

        config = new CodeConfig();

        config.setDbName(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.DB_NAME));
        config.setTableName(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.TABLE_NAME));
        config.setTableNameDesc(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.TABLE_NAME_DESC));
        config.setAuthor(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.AUTHOR));
        config.setVersion(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.VERSION));
        config.setRootPath(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.ROOT_PATH));
        config.setCodePackage(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.CODE_PACKAGE));
        config.setPuDaoCodePackage(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.PU_DAO_CODE_PACKAGE));
        config.setPuDaoClassName(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.PU_DAO_CLASS_NAME));
        config.setPuName(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.PU_NAME));
        config.setPuAliasName(PropertiesUtil.getStringValue(prop, ToolsConstants.CodeConfigConstants.PU_ALIAS_NAME));

        return config;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableNameDesc() {
        return tableNameDesc;
    }

    public void setTableNameDesc(String tableNameDesc) {
        this.tableNameDesc = tableNameDesc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getCodePackage() {
        return codePackage;
    }

    public void setCodePackage(String codePackage) {
        this.codePackage = codePackage;
    }

    public String getPuDaoCodePackage() {
        return puDaoCodePackage;
    }

    public void setPuDaoCodePackage(String puDaoCodePackage) {
        this.puDaoCodePackage = puDaoCodePackage;
    }

    public String getPuDaoClassName() {
        return puDaoClassName;
    }

    public void setPuDaoClassName(String puDaoClassName) {
        this.puDaoClassName = puDaoClassName;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

    public String getPuAliasName() {
        return puAliasName;
    }

    public void setPuAliasName(String puAliasName) {
        this.puAliasName = puAliasName;
    }
}
