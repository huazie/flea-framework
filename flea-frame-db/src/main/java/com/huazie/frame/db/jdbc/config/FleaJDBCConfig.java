package com.huazie.frame.db.jdbc.config;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.jdbc.pojo.FleaDBUnit;
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
public class FleaJDBCConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJDBCConfig.class);

    private static volatile FleaJDBCConfig config;

    private static Map<String, FleaDBUnit> fleaDBUnits = new HashMap<String, FleaDBUnit>();

    private static Properties prop;

    static {
        String fileName = "flea/db/flea-db-config.properties"; // 数据库配置文件名
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.db.jdbc.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.db.jdbc.config.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("JDBCConfig Use the specified flea-db-config.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JDBCConfig Use the current memcached.properties：{}", fileName);
        }
        prop = PropertiesUtil.getProperties(fileName);
    }

    private FleaJDBCConfig() {
    }

    /**
     * <p> 读取数据库相关配置信息 </p>
     *
     * @return JDBC配置对象
     * @since 1.0.0
     */
    public static FleaJDBCConfig getConfig() {

        if (ObjectUtils.isEmpty(config)) {
            synchronized (FleaJDBCConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new FleaJDBCConfig();
                }
            }
        }
        return config;
    }

    /**
     * <p> 使用之前先初始化 </p>
     *
     * @param mDatabase 数据库管理系统名称
     * @param mName     数据库名  或  数据库用户
     * @since 1.0.0
     */
    public static void init(String mDatabase, String mName) {
        FleaFrameManager.getManager().setDBPrefix(mDatabase, mName);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JDBCConfig##init(String, String) 关系数据库管理系统名：{}", mDatabase);
            LOGGER.debug("JDBCConfig##init(String, String) 数据库名或数据库用户：{}", mName);
        }
    }

    /**
     * <p> 建立数据库连接 </p>
     *
     * @return 数据库连接对象
     * @since 1.0.0
     */
    public Connection getConnection() {
        Connection conn = null;
        FleaDBUnit fleaDBUnit;
        String dbPrefix = FleaFrameManager.getManager().getDBPrefix();
        if (fleaDBUnits.isEmpty()) {
            fleaDBUnit = getFleaDBUnit(dbPrefix);
            fleaDBUnits.put(dbPrefix, fleaDBUnit);
        } else {
            fleaDBUnit = fleaDBUnits.get(dbPrefix);
            if (null == fleaDBUnit) {
                fleaDBUnit = getFleaDBUnit(dbPrefix);
                fleaDBUnits.put(dbPrefix, fleaDBUnit);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JDBCConfig##getConnection() 数据库驱动名称：{}", fleaDBUnit.getDriver());
            LOGGER.debug("JDBCConfig##getConnection() 数据库连接地址：{}", fleaDBUnit.getUrl());
            LOGGER.debug("JDBCConfig##getConnection() 数据库登录用户：{}", fleaDBUnit.getUser());
            LOGGER.debug("JDBCConfig##getConnection() 数据库登录密码：{}", fleaDBUnit.getPassword());
        }

        try {
            Class.forName(fleaDBUnit.getDriver());
            conn = DriverManager.getConnection(fleaDBUnit.getUrl(), fleaDBUnit.getUser(), fleaDBUnit.getPassword());
        } catch (Exception e) {
            LOGGER.error("JDBCConfig##getConnection 获取数据库连接异常 ：", e);
        }
        return conn;
    }

    /**
     * <p> 读取指定前缀的数据库相关配置信息 </p>
     *
     * @param dbPrefix 数据库配置前缀标识
     * @return 数据库配置信息类对象
     * @since 1.0.0
     */
    private FleaDBUnit getFleaDBUnit(String dbPrefix) {
        FleaDBUnit fDBUnit = null;
        if (StringUtils.isNotBlank(dbPrefix)) {
            fDBUnit = new FleaDBUnit();
            String[] strs = StringUtils.split(dbPrefix, DBConstants.SQLConstants.SQL_DOT);
            if (ArrayUtils.isNotEmpty(strs) && strs.length == 2) {
                fDBUnit.setDatabase(strs[0]);
                fDBUnit.setName(strs[1]);
            }
            fDBUnit.setDriver(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.SQLConstants.SQL_DOT + DBConstants.DBConfigConstants.DB_CONFIG_DRIVER));
            fDBUnit.setUrl(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.SQLConstants.SQL_DOT + DBConstants.DBConfigConstants.DB_CONFIG_URL));
            fDBUnit.setUser(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.SQLConstants.SQL_DOT + DBConstants.DBConfigConstants.DB_CONFIG_USER));
            fDBUnit.setPassword(PropertiesUtil.getStringValue(prop, dbPrefix + DBConstants.SQLConstants.SQL_DOT + DBConstants.DBConfigConstants.DB_CONFIG_PASSWORD));
        }

        return fDBUnit;
    }

    /**
     * <p> 释放连接Connection </p>
     *
     * @param conn 数据库连接对象
     */
    private static void closeConnection(Connection conn) {
        try {
            if (ObjectUtils.isNotEmpty(conn)) {
                conn.close();
            }
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JDBCConfig##closeConnection(Connection) 关闭数据库连接异常：", e);
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
            if (ObjectUtils.isNotEmpty(statement)) {
                statement.close();
            }
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JDBCConfig##closeStatement(Statement) 关闭数据库statement异常：", e);
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
            if (ObjectUtils.isNotEmpty(rs)) {
                rs.close();
            }
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JDBCConfig##closeResultSet(ResultSet) 关闭结果集异常：", e);
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
        closeConnection(conn);
        closeStatement(statement);
        closeResultSet(rs);
    }

}