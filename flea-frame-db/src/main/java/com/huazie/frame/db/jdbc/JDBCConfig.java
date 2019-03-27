package com.huazie.frame.db.jdbc;

import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p> 读取数据库的配置信息,该信息存在于db-config.properties中 , 并获取连接对象和释放连接对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JDBCConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(JDBCConfig.class);

    private static Map<String, JDBCConfig> configs = new HashMap<String, JDBCConfig>();

    private static Properties prop;

    private static String database; // 数据库管理系统名

    private static String name; // 数据库名 或 数据库用户名

    private String driver; // 数据库驱动名

    private String url; // 数据库连接地址

    private String user; // 数据库登录用户名

    private String password; // 数据库登录密码

    static {
        String fileName = "flea/db/flea-db-config.properties"; // 数据库配置文件名
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.db.jdbc.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.db.jdbc.config.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("JDBCConfig Use the specified flea-db-config.properties：{}", fileName);
            }
        }
        prop = PropertiesUtil.getProperties(fileName);
    }

    /**
     * <p> 使用之前先初始化 </p>
     *
     * @param mDatabase 数据库管理系统名称
     * @param mName     数据库名  或  数据库用户
     * @since 1.0.0
     */
    public static void init(String mDatabase, String mName) {
        database = mDatabase;
        name = mName;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("###### 初始化关系数据库管理系统名：{}", mDatabase);
            LOGGER.debug("###### 初始化数据库名或数据库用户：{}", mName);
        }
    }

    /**
     * <p> 读取数据库相关配置信息 </p>
     *
     * @return JDBC配置对象
     * @since 1.0.0
     */
    public static JDBCConfig getConfig() {
        String dbPrefix = database.toLowerCase() + DBConstants.SQLConstants.SQL_DOT + name.toLowerCase() + DBConstants.SQLConstants.SQL_DOT;
        JDBCConfig config;
        if (configs.isEmpty()) {
            config = getConfig(dbPrefix);
            configs.put(dbPrefix, config);
        } else {
            config = configs.get(dbPrefix);
            if (null == config) {
                config = getConfig(dbPrefix);
                configs.put(dbPrefix, config);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("###### 数据库驱动名称：{}", config.getDriver());
            LOGGER.debug("###### 数据库连接地址：{}", config.getUrl());
            LOGGER.debug("###### 数据库登录用户：{}", config.getUser());
            LOGGER.debug("###### 数据库登录密码：{}", config.getPassword());
        }

        return config;
    }

    /**
     * <p> 读取指定前缀的数据库相关配置信息 </p>
     *
     * @param dbPrefix 数据库配置前缀标识
     * @return 数据库配置信息类对象
     * @since 1.0.0
     */
    private static JDBCConfig getConfig(String dbPrefix) {
        JDBCConfig config = new JDBCConfig();
        config.setDriver(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.DBConfigConstants.DB_CONFIG_DRIVER));
        config.setUrl(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.DBConfigConstants.DB_CONFIG_URL));
        config.setUser(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.DBConfigConstants.DB_CONFIG_USER));
        config.setPassword(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.DBConfigConstants.DB_CONFIG_PASSWORD));
        return config;
    }

    /**
     * <p> 建立数据库连接 </p>
     *
     * @return 数据库连接对象
     * @since 1.0.0
     */
    public static Connection getConnection() {
        Connection conn = null;
        JDBCConfig config = JDBCConfig.getConfig();
        try {
            Class.forName(config.getDriver());
            conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        } catch (Exception e) {
            LOGGER.error("JDBCConfig##getConnection 获取数据库连接异常 ：", e);
        }
        return conn;
    }

    /**
     * <p> 释放连接Connection </p>
     *
     * @param conn 数据库连接对象
     */
    private static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JDBCConfig##closeResultSet 关闭数据库连接异常：", e);
            }
        }
    }

    /**
     * <p> 释放statement </p>
     *
     * @param statement Statement对象
     */
    private static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JDBCConfig##closeResultSet 关闭数据库statement异常：", e);
            }
        }
    }

    /**
     * <p> 释放ResultSet结果集 </p>
     *
     * @param rs 结果集对象
     */
    private static void closeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JDBCConfig##closeResultSet 关闭结果集异常：", e);
            }
        }
    }

    /**
     * <p> 释放资源 </p>
     *
     * @param conn      数据库连接对象
     * @param statement 数据库状态对象
     * @param rs        数据库结果集对象
     */
    public static void close(Connection conn, Statement statement, ResultSet rs) {
        if (null != rs) {
            closeResultSet(rs);
        }
        if (null != statement) {
            closeStatement(statement);
        }
        if (null != conn) {
            closeConnection(conn);
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}