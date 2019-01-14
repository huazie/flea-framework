package com.huazie.frame.db.common;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月23日
 *
 */
public interface DBConstants {
	
	/**
	 * <p>
	 * 		DAO层抛出的异常类型
	 * </p>
	 *  
	 * @author huazie
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
	 */
	public interface DaoConstants {
		/**
		 * @Description 主键不是正整数，即是<=0的整数
		 */
		int DAO_PRIMARY_KEY_NOT_POSITIVE_INTEGER = 1;
		/**
		 * @Description 主键为空
		 */
		int DAO_PRIMARY_KEY_IS_NULL = 2;			
		/**
		 * @Description 某实体的主键不存在
		 */
		int DAO_PRIMARY_KEY_NOT_EXIST = 3;
		/**
		 * @Description 主键不是String类型或long类型
		 */
		int DAO_PRIMARY_KEY_NOT_LONG_OR_STRING = 4;
		/**
		 * @Description 传入的参数为空
		 */
		int DAO_PARAMETER_IS_NULL = 5; 
		/**
		 * @Description 传入的对象为空
		 */
		int DAO_OBJECT_IS_NULL = 6;	
		/**
		 * @Description 实体没有被注解
		 */
		int DAO_ENTITY_NOT_BE_ANNOTATED= 7;
		/**
		 * @Description 非法的排序，应该只有降序(desc)和升序(asc)
		 */
		int DAO_INVALID_ORDER = 8;
		/**
		 * @Description 开始时间不能大于结束时间
		 */
		int DAO_INVALID_START_END_TIME = 9;
		/**
		 * @Description 非法的查询
		 */
		int DAO_INVALID_QUERY = 10;
		
	}
	
	/**
	 * <p>
	 * 		SQL语句中的常用关键字 和 相关符号
	 * </p>
	 * @author huazie
	 * @version v1.0.0
	 * @date 2018年1月23日
	 *
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
		String SQL_ORDER_BY = "ORDER BY";
		String SQL_GROUP_BY = "GROUP BY";
		String SQL_LIMIT = "LIMIT";
		String SQL_COMMA  = ",";
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
		String SQL_EQUATION = "1 = 1";
		String SQL_ORDER_ASC = "asc";
		String SQL_ORDER_DESC = "desc";
		String SQL_LEFT_ROUND_BRACKETS = "(";
		String SQL_RIGHT_ROUND_BRACKETS = ")";
		String SQL_VERTICALLINE = "|";
	}
	
	public interface DBConfigConstants{
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
