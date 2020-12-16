package com.huazie.frame.db.jdbc.config;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.FleaConfigManager;
import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.jdbc.pojo.FleaDBUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> 读取数据库的配置信息,该信息存在于db-config.properties中 , 并获取连接对象和释放连接对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJDBCConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJDBCConfig.class);

    private static volatile FleaJDBCConfig config;

    private static ConcurrentMap<String, FleaDBUnit> fleaDBUnits = new ConcurrentHashMap<>();

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
        FleaFrameManager.getManager().setDBConfigKey(mDatabase, mName);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("关系数据库管理系统名：{}", mDatabase);
            LOGGER.debug("数据库名或数据库用户：{}", mName);
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

        String dbConfigKey = FleaFrameManager.getManager().getDBConfigKey();

        if (!fleaDBUnits.containsKey(dbConfigKey)) {
            synchronized (fleaDBUnits) {
                if (!fleaDBUnits.containsKey(dbConfigKey)) {
                    fleaDBUnits.put(dbConfigKey, getFleaDBUnit(dbConfigKey));
                }
            }
        }

        fleaDBUnit = fleaDBUnits.get(dbConfigKey);

        try {

            // 请正确初始化数据库管理系统和数据库（或数据库用户）
            ObjectUtils.checkEmpty(fleaDBUnit, DaoException.class, "ERROR-DB-DAO0000000013");

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("数据库配置键名：{}", dbConfigKey);
                LOGGER.debug("数据库驱动名称：{}", fleaDBUnit.getDriver());
                LOGGER.debug("数据库连接地址：{}", fleaDBUnit.getUrl());
                LOGGER.debug("数据库登录用户：{}", fleaDBUnit.getUser());
                LOGGER.debug("数据库登录密码：{}", fleaDBUnit.getPassword());
            }

            Class.forName(fleaDBUnit.getDriver());
            conn = DriverManager.getConnection(fleaDBUnit.getUrl(), fleaDBUnit.getUser(), fleaDBUnit.getPassword());
        } catch (Exception e) {
            LOGGER.error("获取数据库连接异常 ：{}", e.getMessage());
        }
        return conn;
    }

    /**
     * <p> 读取指定配置键的数据库相关配置信息 </p>
     *
     * @param dbConfigKey 数据库配置键
     * @return 数据库配置信息类对象
     * @since 1.0.0
     */
    private FleaDBUnit getFleaDBUnit(String dbConfigKey) {
        FleaDBUnit fDBUnit = null;
        if (StringUtils.isNotBlank(dbConfigKey)) {
            fDBUnit = new FleaDBUnit();
            String[] dbConfigKeyArr = StringUtils.split(dbConfigKey, CommonConstants.SymbolConstants.HYPHEN);
            if (ArrayUtils.isNotEmpty(dbConfigKeyArr) && CommonConstants.NumeralConstants.INT_TWO == dbConfigKeyArr.length) {
                fDBUnit.setDatabase(dbConfigKeyArr[0]);
                fDBUnit.setName(dbConfigKeyArr[1]);
            }
            fDBUnit.setDriver(FleaConfigManager.getConfigItemValue(dbConfigKey, DBConstants.DBConfigConstants.DB_CONFIG_DRIVER));
            fDBUnit.setUrl(FleaConfigManager.getConfigItemValue(dbConfigKey, DBConstants.DBConfigConstants.DB_CONFIG_URL));
            fDBUnit.setUser(FleaConfigManager.getConfigItemValue(dbConfigKey, DBConstants.DBConfigConstants.DB_CONFIG_USER));
            fDBUnit.setPassword(FleaConfigManager.getConfigItemValue(dbConfigKey, DBConstants.DBConfigConstants.DB_CONFIG_PASSWORD));
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
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Connection已关闭。");
                }
            }
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("关闭数据库连接异常：", e);
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
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Statement已关闭。");
                }
            }
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("关闭数据库statement异常：", e);
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
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("ResultSet已关闭。");
                }
            }
        } catch (SQLException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("关闭结果集异常：", e);
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
        closeResultSet(rs);
        closeStatement(statement);
        closeConnection(conn);
    }

    /**
     * <p> 释放连接 </p>
     *
     * @param conn 数据库连接对象
     */
    public static void close(Connection conn) {
        closeConnection(conn);
    }

    /**
     * <p> 释放状态 </p>
     *
     * @param statement 数据库状态对象
     */
    public static void close(Statement statement) {
        closeStatement(statement);
    }

    /**
     * <p> 释放结果集 </p>
     *
     * @param rs 数据库结果集对象
     */
    public static void close(ResultSet rs) {
        closeResultSet(rs);
    }

}