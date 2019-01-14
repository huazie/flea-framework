package com.huazie.frame.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库工具类，主要进行数据库中数据的增删改查操作
 * 
 * @author LGH
 * 
 */
public class JDBCUtils {

	private static Connection conn = null;
	private static PreparedStatement preparedStatement = null;
	
	/**
	 * 用于查询，返回结果集
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @return 结果集list还有各个数据的map
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> query(String sql) throws SQLException {

		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			rs = preparedStatement.executeQuery();
			return ResultToListMap(rs);

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 用于带参数的查询，返回List<Map>，其中Map的键为属性名，值为相应的数据
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 结果集
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> query(String sql, Object... paramters) throws SQLException {

		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			return ResultToListMap(rs);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}

	}

	/**
	 * 用于带参数的查询，返回List<Map>，其中Map的键为属性名，值为相应的数据
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> query(String sql, List<Object> paramters) throws SQLException {

		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.size(); i++) {
				preparedStatement.setObject(i + 1, paramters.get(i));
			}
			rs = preparedStatement.executeQuery();
			return ResultToListMap(rs);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}

	}

	/**
	 * 用于查询，返回结果集ResultSet
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @return 结果集
	 * @throws SQLException
	 */
	public static ResultSet queryWithReturnResultSet(String sql) throws SQLException {
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 用于带参数的查询，返回结果集
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 结果集
	 * @throws SQLException
	 */
	public static ResultSet queryWithReturnResultSet(String sql, Object... paramters) throws SQLException {
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 用于带参数的查询，返回结果集
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 结果集
	 * @throws SQLException
	 */
	public static ResultSet queryWithReturnResultSet(String sql, List<Object> paramters) throws SQLException {
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.size(); i++) {
				preparedStatement.setObject(i + 1, paramters.get(i));
			}
			rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 返回单个结果的值，如count\min\max等等
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @return
	 * @throws SQLException
	 */
	public static Object querySingle(String sql) throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 返回单个结果值，如count\min\max等
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 结果
	 * @throws SQLException
	 */
	public static Object querySingle(String sql, Object... paramters) throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 返回单个结果值，如count\min\max等
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 结果
	 * @throws SQLException
	 */
	public static Object querySingle(String sql, List<Object> paramters) throws SQLException {
		Object result = null;
		ResultSet rs = null;
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.size(); i++) {
				preparedStatement.setObject(i + 1, paramters.get(i));
			}
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}
			return result;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close(rs);
		}
	}

	/**
	 * 用于更新数据 sql语句为update语句
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int update(String sql) throws SQLException {

		try {
			getPreparedStatement(sql);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close();
		}
	}

	/**
	 * 用于更新数据 sql语句为update语句（带参数）
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int update(String sql, Object... paramters) throws SQLException {
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close();
		}
	}

	/**
	 * 用于更新数据 sql语句为update语句（带参数）
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int update(String sql, List<Object> paramters) throws SQLException {
		try {
			getPreparedStatement(sql);
			for (int i = 0; i < paramters.size(); i++) {
				preparedStatement.setObject(i + 1, paramters.get(i));
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			close();
		}
	}

	/**
	 * 用于增加数据 sql语句为insert语句
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int insert(String sql) throws SQLException {
		return update(sql);
	}

	/**
	 * 用于增加数据 sql语句为insert语句（带参数）
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int insert(String sql, Object... paramters) throws SQLException {
		return update(sql, paramters);
	}

	/**
	 * 用于增加数据 sql语句为insert语句（带参数）
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int insert(String sql, List<Object> paramters) throws SQLException {
		return update(sql, paramters);
	}

	/**
	 * 用于删除数据 sql语句为delete语句
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int delete(String sql) throws SQLException {
		return update(sql);
	}

	/**
	 * 用于删除数据 sql语句为delete语句（带参数）
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int delete(String sql, Object... paramters) throws SQLException {
		return update(sql, paramters);
	}

	/**
	 * 用于删除数据 sql语句为delete语句（带参数）
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @param paramters
	 *            参数列表
	 * @return 影响行数
	 * @throws SQLException
	 */
	public static int delete(String sql, List<Object> paramters) throws SQLException {
		return update(sql, paramters);
	}

	/**
	 * 将结果集ResultSet转换为包含Map<String,Object>的List集合
	 * 
	 * @param rs
	 *            结果集对象
	 * @return
	 * @throws SQLException
	 */
	private static List<Map<String, Object>> ResultToListMap(ResultSet rs) throws SQLException {
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
			}
			listMaps.add(map);
		}
		return listMaps;
	}

	/**
	 * 获取PreparedStatement
	 * 
	 * @param sql
	 *            数据库sql语句
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static void getPreparedStatement(String sql) throws SQLException {
		conn = JDBCConfig.getConnection();
		preparedStatement = conn.prepareStatement(sql);
	}

	/**
	 * 释放资源connection,statement,ResultSet
	 * 
	 * @param rs
	 *            结果集
	 * @throws SQLException
	 */
	public static void close(ResultSet rs) {
		JDBCConfig.close(conn, preparedStatement, rs);
	}

	/**
	 * 释放资源connection和statement
	 * 
	 * @throws SQLException
	 */
	public static void close() {
		close(null);
	}
	
}