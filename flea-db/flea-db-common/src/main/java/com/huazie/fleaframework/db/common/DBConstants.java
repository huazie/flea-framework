package com.huazie.fleaframework.db.common;

/**
 * DB常量类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public final class DBConstants {

    private DBConstants() {
    }

    /**
     * Flea JPA 配置常量类
     *
     * @since 1.0.0
     */
    public static final class FleaJPAConstants {
        /**
         * Flea JPA 配置
         */
        public static final String FLEA_JPA = "flea-jpa";
        /**
         * Flea 分库分表处理者
         */
        public static final String FLEA_SPLIT_HANDLER = "flea_split_handler";
    }

    /**
     * SQL模板常量类
     *
     * @since 1.0.0
     */
    public static final class SqlTemplateConstants {
        /**
         * SQL模板文件路径
         */
        public static final String SQL_TEMPLATE_FILE_PATH = "flea/db/flea-sql-template.xml";
        /**
         * SQL模板文件系统属性键
         */
        public static final String SQL_TEMPLATE_FILE_SYSTEM_KEY = "fleaframework.db.sql.template.filename";
    }

    /**
     * 分库分表常量类
     *
     * @since 1.0.0
     */
    public static final class LibTableSplitConstants {
        public static final String KEY = "key";     // 分表后缀类型枚举key变量名

        public static final String FLEA_LIB_NAME = "FLEA_LIB_NAME"; // 分库表达式库名替换变量
        public static final String FLEA_TRANSACTION_NAME = "FLEA_TRANSACTION_NAME"; // 分库事务表达式模板事务名替换变量
        public static final String FLEA_TABLE_NAME = "FLEA_TABLE_NAME"; // 分表表达式表名替换变量

        public static final String SPLIT_TABLE = "SPLIT_TABLE"; // 分表信息
        public static final String SPLIT_LIB = "SPLIT_LIB"; // 分库信息
        public static final String IS_SPLIT_TABLE = "IS_SPLIT_TABLE"; // 是否分表 标识

        /**
         * 分表配置文件路径
         */
        public static final String LIB_SPLIT_FILE_PATH = "flea/db/flea-lib-split.xml";
        /**
         * 分表配置文件系统属性键
         */
        public static final String LIB_SPLIT_FILE_SYSTEM_KEY = "fleaframework.db.lib.split.filename";

        /**
         * 分表配置文件路径
         */
        public static final String TABLE_SPLIT_FILE_PATH = "flea/db/flea-table-split.xml";
        /**
         * 分表配置文件系统属性键
         */
        public static final String TABLE_SPLIT_FILE_SYSTEM_KEY = "fleaframework.db.table.split.filename";
    }

    /**
     * SQL语句中的常用关键字 和 相关符号
     *
     * @since 1.0.0
     */
    public static final class SQLConstants {
        public static final String SQL_SELECT = "SELECT";
        public static final String SQL_UPDATE = "UPDATE";
        public static final String SQL_INSERT = "INSERT";
        public static final String SQL_INSERT_INTO = "INSERT INTO";
        public static final String SQL_INSERT_VALUES = "VALUES";
        public static final String SQL_DELETE = "DELETE";
        public static final String SQL_DELETE_FROM = "DELETE FROM";
        public static final String SQL_FROM = "FROM";
        public static final String SQL_WHERE = "WHERE";
        public static final String SQL_SET = "SET";
        public static final String SQL_ORDER_BY = " ORDER BY ";
        public static final String SQL_LOWER_ORDER_BY = " order by ";
        public static final String SQL_GROUP_BY = " GROUP BY ";
        public static final String SQL_LOWER_GROUP_BY = " group by ";
        public static final String SQL_LIMIT = " LIMIT ";
        public static final String SQL_LOWER_LIMIT = " limit ";
        public static final String SQL_COMMA = ",";
        public static final String SQL_DOT = ".";
        public static final String SQL_COLON = ":";
        public static final String SQL_ASTERISK = "*";
        public static final String SQL_AND = " AND ";
        public static final String SQL_LOWER_AND = " and ";
        public static final String SQL_OR = " OR ";
        public static final String SQL_LOWER_OR = " or ";
        public static final String SQL_LIKE = " LIKE ";
        public static final String SQL_LOWER_LIKE = " like ";
        public static final String SQL_IN = " IN ";
        public static final String SQL_LOWER_IN = " in ";
        public static final String SQL_BLANK = " ";
        public static final String SQL_UNDERLINE = "_";
        public static final String SQL_LE = " <= ";
        public static final String SQL_LT = " < ";
        public static final String SQL_GE = " >= ";
        public static final String SQL_GT = " > ";
        public static final String SQL_EQUAL = " = ";
        public static final String SQL_PERCENT = "%";
        public static final String SQL_EQUATION = "1=1";
        public static final String SQL_ORDER_ASC = "asc";
        public static final String SQL_ORDER_DESC = "desc";
        public static final String SQL_LEFT_ROUND_BRACKETS = "(";
        public static final String SQL_RIGHT_ROUND_BRACKETS = ")";
        public static final String SQL_PLACEHOLDER = "?";
    }

    /**
     * 数据库相关配置常量
     *
     * @since 1.0.0
     */
    public static final class DBConfigConstants {
        /**
         * 驱动程序描述字符
         */
        public static final String DB_CONFIG_DRIVER = "driver";
        /**
         * 数据库连接的地址
         */
        public static final String DB_CONFIG_URL = "url";
        /**
         * 数据库用户
         */
        public static final String DB_CONFIG_USER = "user";
        /**
         * 数据库用户密码
         */
        public static final String DB_CONFIG_PASSWORD = "password";
    }

    /**
     * JPA查询对象池配置常量
     *
     * @since 1.0.0
     */
    public static final class JPAQueryPoolConfigConstants {
        /**
         * Flea JPA查询配置常量
         */
        public static final String FLEA_JPA_QUERY = "flea-jpa-query";
        /**
         * Flea JPA查询对象池最大连接数
         */
        public static final String JPA_QUERY_POOL_MAXTOTAL = "pool.maxTotal";
        /**
         * Flea JPA查询对象池最大空闲连接数
         */
        public static final String JPA_QUERY_POOL_MAXIDLE = "pool.maxIdle";
        /**
         * Flea JPA查询对象池最小空闲连接数
         */
        public static final String JPA_QUERY_POOL_MINIDLE = "pool.minIdle";
        /**
         * Flea JPA查询对象池获取连接时的最大等待毫秒数
         */
        public static final String JPA_QUERY_POOL_MAXWAITMILLIS = "pool.maxWaitMillis";
    }
}
