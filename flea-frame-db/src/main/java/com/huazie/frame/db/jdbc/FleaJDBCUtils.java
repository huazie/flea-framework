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
 * <p> 数据库工具类，主要进行数据库中数据的增删改查操作 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJDBCUtils {

    private static Connection conn;
    private static PreparedStatement preparedStatement;

    /**
     * <p> 以返回List<Map>，其中Map的键为属性名，值为相应的数据 </p>
     *
     * @param sql 数据库sql语句
     * @return 查询结果数据集合
     * @throws SQLException
     * @since 1.0.0
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
     * <p> 用于带参数的查询，返回List<Map>，其中Map的键为属性名，值为相应的数据 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 查询结果数据集合
     * @throws SQLException
     * @since 1.0.0
     */
    public static List<Map<String, Object>> query(String sql, Object... params) throws SQLException {
        ResultSet rs = null;
        try {
            rs = queryWithReturnResultSet(sql, params);
            return ResultToListMap(rs);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            close(rs);
        }

    }

    /**
     * <p> 用于带参数的查询，返回List<Map>，其中Map的键为属性名，值为相应的数据 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return
     * @throws SQLException
     * @since 1.0.0
     */
    public static List<Map<String, Object>> query(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> data = null;
        if (null != params && !params.isEmpty()) {
            Object[] paramArr = params.toArray(new Object[0]);
            data = query(sql, paramArr);
        }
        return data;
    }

    /**
     * <p> 用于查询，返回结果集ResultSet </p>
     *
     * @param sql 数据库sql语句
     * @return 结果集
     * @throws SQLException
     * @since 1.0.0
     */
    public static ResultSet queryWithReturnResultSet(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            getPreparedStatement(sql);
            rs = preparedStatement.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            close(rs);
        }
    }

    /**
     * <p> 用于带参数的查询，返回结果集 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 结果集
     * @throws SQLException
     * @since 1.0.0
     */
    public static ResultSet queryWithReturnResultSet(String sql, Object... params) throws SQLException {
        ResultSet rs = null;
        try {
            getPreparedStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
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
     * <p> 用于带参数的查询，返回结果集 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 结果集
     * @throws SQLException
     * @since 1.0.0
     */
    public static ResultSet queryWithReturnResultSet(String sql, List<Object> params) throws SQLException {
        ResultSet resultSet = null;
        if (null != params && !params.isEmpty()) {
            Object[] paramArr = params.toArray(new Object[0]);
            resultSet = queryWithReturnResultSet(sql, paramArr);
        }
        return resultSet;
    }

    /**
     * <p> 返回单个结果的值，如count\min\max等等 </p>
     *
     * @param sql 数据库sql语句
     * @return 单个结果
     * @throws SQLException
     * @since 1.0.0
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
     * <p> 返回单个结果值，如count\min\max等 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 单个结果
     * @throws SQLException
     * @since 1.0.0
     */
    public static Object querySingle(String sql, Object... params) throws SQLException {
        Object result = null;
        ResultSet rs = null;
        try {
            rs = queryWithReturnResultSet(sql, params);
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
     * <p> 返回单个结果值，如count\min\max等 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 单个结果
     * @throws SQLException
     * @since 1.0.0
     */
    public static Object querySingle(String sql, List<Object> params) throws SQLException {
        Object result = null;
        if (null != params && !params.isEmpty()) {
            Object[] paramArr = params.toArray(new Object[0]);
            result = querySingle(sql, paramArr);
        }
        return result;
    }

    /**
     * <p> 用于更新数据 sql语句为update语句 </p>
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
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
     * <p> 用于更新数据 sql语句为update语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int update(String sql, Object... params) throws SQLException {
        try {
            getPreparedStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            close();
        }
    }

    /**
     * <p> 用于更新数据 sql语句为update语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int update(String sql, List<Object> params) throws SQLException {
        int retVal = -1;
        if (null != params && !params.isEmpty()) {
            Object[] paramArr = params.toArray(new Object[0]);
            retVal = update(sql, paramArr);
        }
        return retVal;
    }

    /**
     * <p> 于增加数据 sql语句为insert语句 </p>用
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int insert(String sql) throws SQLException {
        return update(sql);
    }

    /**
     * <p> 用于增加数据 sql语句为insert语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int insert(String sql, Object... params) throws SQLException {
        return update(sql, params);
    }

    /**
     * <p> 用于增加数据 sql语句为insert语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int insert(String sql, List<Object> params) throws SQLException {
        return update(sql, params);
    }

    /**
     * <p> 用于删除数据 sql语句为delete语句 </p>
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int delete(String sql) throws SQLException {
        return update(sql);
    }

    /**
     * <p> 用于删除数据 sql语句为delete语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int delete(String sql, Object... params) throws SQLException {
        return update(sql, params);
    }

    /**
     * <p> 用于删除数据 sql语句为delete语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException
     * @since 1.0.0
     */
    public static int delete(String sql, List<Object> params) throws SQLException {
        return update(sql, params);
    }

    /**
     * <p> 将结果集ResultSet转换为包含Map<String,Object>的List集合 </p>
     *
     * @param rs 结果集对象
     * @return 包含Map<String   ,   Object>的List数据集合
     * @throws SQLException
     * @since 1.0.0
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
     * <p> 获取PreparedStatement </p>
     *
     * @param sql 数据库sql语句
     * @throws SQLException
     * @since 1.0.0
     */
    private static void getPreparedStatement(String sql) throws SQLException {
        conn = FleaJDBCConfig.getConfig().getConnection();
        preparedStatement = conn.prepareStatement(sql);
    }

    /**
     * <p> 释放资源connection,statement,ResultSet </p>
     *
     * @param rs 结果集
     * @since 1.0.0
     */
    public static void close(ResultSet rs) {
        FleaJDBCConfig.close(conn, preparedStatement, rs);
    }

    /**
     * <p> 释放资源connection和statement </p>
     *
     * @since 1.0.0
     */
    public static void close() {
        close(null);
    }

}