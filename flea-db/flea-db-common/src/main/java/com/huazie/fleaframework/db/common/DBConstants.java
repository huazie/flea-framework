package com.huazie.fleaframework.db.common;

/**
 * DB常量类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DBConstants {

    /**
     * Flea JPA 配置常量类
     *
     * @since 1.0.0
     */
    public interface FleaJPAConstants {
        /**
         * Flea JPA 配置
         */
        String FLEA_JPA = "flea-jpa";
        /**
         * Flea 分库分表处理者
         */
        String FLEA_SPLIT_HANDLER = "flea_split_handler";
    }

    /**
     * SQL模板常量类
     *
     * @since 1.0.0
     */
    public interface SqlTemplateConstants {
        /**
         * SQL模板文件路径
         */
        String SQL_TEMPLATE_FILE_PATH = "flea/db/flea-sql-template.xml";
        /**
         * SQL模板文件系统属性键
         */
        String SQL_TEMPLATE_FILE_SYSTEM_KEY = "fleaframework.db.sql.template.filename";
    }

    /**
     * 分库分表常量类
     *
     * @since 1.0.0
     */
    public interface LibTableSplitConstants {
        String KEY = "key";     // 分表后缀类型枚举key变量名

        String FLEA_LIB_NAME = "FLEA_LIB_NAME"; // 分库表达式库名替换变量
        String FLEA_TRANSACTION_NAME = "FLEA_TRANSACTION_NAME"; // 分库事务表达式模板事务名替换变量
        String FLEA_TABLE_NAME = "FLEA_TABLE_NAME"; // 分表表达式表名替换变量

        String SPLIT_TABLE = "SPLIT_TABLE"; // 分表信息
        String SPLIT_LIB = "SPLIT_LIB"; // 分库信息
        String IS_SPLIT_TABLE = "IS_SPLIT_TABLE"; // 是否分表 标识

        /**
         * 分表配置文件路径
         */
        String LIB_SPLIT_FILE_PATH = "flea/db/flea-lib-split.xml";
        /**
         * 分表配置文件系统属性键
         */
        String LIB_SPLIT_FILE_SYSTEM_KEY = "fleaframework.db.lib.split.filename";

        /**
         * 分表配置文件路径
         */
        String TABLE_SPLIT_FILE_PATH = "flea/db/flea-table-split.xml";
        /**
         * 分表配置文件系统属性键
         */
        String TABLE_SPLIT_FILE_SYSTEM_KEY = "fleaframework.db.table.split.filename";
    }

    /**
     * SQL语句中的常用关键字 和 相关符号
     *
     * @since 1.0.0
     */
    public interface SQLConstants {
        String SQL_SELECT = "SELECT";
        String SQL_UPDATE = "UPDATE";
        String SQL_INSERT = "INSERT";
        String SQL_INSERT_INTO = "INSERT INTO";
        String SQL_INSERT_VALUES = "VALUES";
        String SQL_DELETE = "DELETE";
        String SQL_DELETE_FROM = "DELETE FROM";
        String SQL_FROM = "FROM";
        String SQL_WHERE = "WHERE";
        String SQL_SET = "SET";
        String SQL_ORDER_BY = " ORDER BY ";
        String SQL_LOWER_ORDER_BY = " order by ";
        String SQL_GROUP_BY = " GROUP BY ";
        String SQL_LOWER_GROUP_BY = " group by ";
        String SQL_LIMIT = " LIMIT ";
        String SQL_LOWER_LIMIT = " limit ";
        String SQL_COMMA = ",";
        String SQL_DOT = ".";
        String SQL_COLON = ":";
        String SQL_ASTERISK = "*";
        String SQL_AND = " AND ";
        String SQL_LOWER_AND = " and ";
        String SQL_OR = " OR ";
        String SQL_LOWER_OR = " or ";
        String SQL_LIKE = " LIKE ";
        String SQL_LOWER_LIKE = " like ";
        String SQL_IN = " IN ";
        String SQL_LOWER_IN = " in ";
        String SQL_BLANK = " ";
        String SQL_UNDERLINE = "_";
        String SQL_LE = " <= ";
        String SQL_LT = " < ";
        String SQL_GE = " >= ";
        String SQL_GT = " > ";
        String SQL_EQUAL = " = ";
        String SQL_PERCENT = "%";
        String SQL_EQUATION = "1=1";
        String SQL_ORDER_ASC = "asc";
        String SQL_ORDER_DESC = "desc";
        String SQL_LEFT_ROUND_BRACKETS = "(";
        String SQL_RIGHT_ROUND_BRACKETS = ")";
        String SQL_PLACEHOLDER = "?";
    }

    /**
     * 数据库相关配置常量
     *
     * @since 1.0.0
     */
    public interface DBConfigConstants {
        /**
         * 驱动程序描述字符
         */
        String DB_CONFIG_DRIVER = "driver";
        /**
         * 数据库连接的地址
         */
        String DB_CONFIG_URL = "url";
        /**
         * 数据库用户
         */
        String DB_CONFIG_USER = "user";
        /**
         * 数据库用户密码
         */
        String DB_CONFIG_PASSWORD = "password";
    }

    /**
     * JPA查询对象池配置常量
     *
     * @since 1.0.0
     */
    public interface JPAQueryPoolConfigConstants {
        /**
         * Flea JPA查询配置常量
         */
        String FLEA_JPA_QUERY = "flea-jpa-query";
        /**
         * Flea JPA查询对象池最大连接数
         */
        String JPA_QUERY_POOL_MAXTOTAL = "pool.maxTotal";
        /**
         * Flea JPA查询对象池最大空闲连接数
         */
        String JPA_QUERY_POOL_MAXIDLE = "pool.maxIdle";
        /**
         * Flea JPA查询对象池最小空闲连接数
         */
        String JPA_QUERY_POOL_MINIDLE = "pool.minIdle";
        /**
         * Flea JPA查询对象池获取连接时的最大等待毫秒数
         */
        String JPA_QUERY_POOL_MAXWAITMILLIS = "pool.maxWaitMillis";
    }
}
