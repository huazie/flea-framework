package com.huazie.frame.db.common;

/**
 * <p> DB常量类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DBConstants {

    /**
     * <p> 分表常量类 </p>
     *
     * @since 1.0.0
     */
    interface TableSplitConstants {
        int TWO = 2;            // 两位分表
        int THREE = 3;          // 三位分表
        String KEY = "key";     // 分表后缀类型枚举key变量名
        String FLEA_TABLE_NAME = "FLEA_TABLE_NAME"; // 分表表达式表名替换变量
    }

    /**
     * <p> SQL语句中的常用关键字 和 相关符号 </p>
     *
     * @since 1.0.0
     */
    interface SQLConstants {
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
        String SQL_ORDER_BY = "ORDER BY";
        String SQL_GROUP_BY = "GROUP BY";
        String SQL_LIMIT = "LIMIT";
        String SQL_COMMA = ",";
        String SQL_DOT = ".";
        String SQL_COLON = ":";
        String SQL_ASTERISK = "*";
        String SQL_AND = "AND";
        String SQL_LOWER_AND = "and";
        String SQL_OR = "OR";
        String SQL_LOWER_OR = "or";
        String SQL_LIKE = "LIKE";
        String SQL_LOWER_LIKE = "like";
        String SQL_IN = "IN";
        String SQL_LOWER_IN = "in";
        String SQL_BLANK = " ";
        String SQL_UNDERLINE = "_";
        String SQL_LE = "<=";
        String SQL_LT = "<";
        String SQL_GE = ">=";
        String SQL_GT = ">";
        String SQL_EQUAL = "=";
        String SQL_PERCENT = "%";
        String SQL_EQUATION = "1=1";
        String SQL_ORDER_ASC = "asc";
        String SQL_ORDER_DESC = "desc";
        String SQL_LEFT_ROUND_BRACKETS = "(";
        String SQL_RIGHT_ROUND_BRACKETS = ")";
        String SQL_VERTICALLINE = "|";
        String SQL_PLACEHOLDER = "?";
    }

    /**
     * <p> JDBC数据库配置常量 </p>
     *
     * @since 1.0.0
     */
    interface DBConfigConstants {
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
}
