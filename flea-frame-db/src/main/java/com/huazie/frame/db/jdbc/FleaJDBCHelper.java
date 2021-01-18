package com.huazie.frame.db.jdbc;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.DaoException;
import com.huazie.frame.db.common.sql.pojo.SqlParam;
import com.huazie.frame.db.common.sql.template.ITemplate;
import com.huazie.frame.db.common.sql.template.SqlTemplateFactory;
import com.huazie.frame.db.common.sql.template.TemplateTypeEnum;
import com.huazie.frame.db.common.table.pojo.Column;
import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;
import com.huazie.frame.db.jdbc.pojo.FleaDBOperationHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class FleaJDBCHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJDBCHelper.class);

    /**
     * <p> 以返回List<Map>，其中Map的键为属性名，值为相应的数据 </p>
     *
     * @param sql 数据库sql语句
     * @return 查询结果数据集合
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static List<Map<String, Object>> query(String sql) throws SQLException {
        return query(sql, new Object[]{});
    }

    /**
     * <p> 用于带参数的查询，返回List<Map>，其中Map的键为属性名，值为相应的数据 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 查询结果数据集合
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static List<Map<String, Object>> query(String sql, Object... params) throws SQLException {

        try (FleaDBOperationHandler handler = queryWithReturnResultSet(sql, params)) {
            return ResultToListMap(handler.getResultSet());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 用于带参数的查询，返回List<Map>，其中Map的键为属性名，值为相应的数据 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 查询结果数据集合
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static List<Map<String, Object>> query(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> datas = null;
        if (CollectionUtils.isNotEmpty(params)) {
            Object[] paramArr = params.toArray(new Object[0]);
            datas = query(sql, paramArr);
        }
        return datas;
    }

    /**
     * <p> 用于带参数的查询，返回结果集 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 结果集
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static FleaDBOperationHandler queryWithReturnResultSet(String sql, Object... params) throws SQLException {
        FleaDBOperationHandler handler = getDBOperationHandler(sql, params);
        queryWithReturnResultSet(handler);
        return handler;
    }

    /**
     * <p> 返回单个结果的值，如count\min\max等等 </p>
     *
     * @param sql 数据库sql语句
     * @return 单个结果
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static Object querySingle(String sql) throws SQLException {
        return querySingle(sql, new Object[]{});
    }

    /**
     * <p> 返回单个结果值，如count\min\max等 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 单个结果
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static Object querySingle(String sql, Object... params) throws SQLException {
        Object result = null;

        try (FleaDBOperationHandler handler = queryWithReturnResultSet(sql, params)) {
            ResultSet rs = handler.getResultSet();
            if (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 返回单个结果值，如count\min\max等 </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 单个结果
     * @throws SQLException 数据库操作异常
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
     * <p> 于增加数据，sql语句为insert语句 </p>用
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int insert(String sql) throws SQLException {
        return save(sql);
    }

    /**
     * <p> 用于增加数据，sql语句为insert语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int insert(String sql, Object... params) throws SQLException {
        return save(sql, params);
    }

    /**
     * <p> 用于增加数据，sql语句为insert语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int insert(String sql, List<Object> params) throws SQLException {
        return save(sql, params);
    }

    /**
     * <p> 用于更新数据，sql语句为update语句 </p>
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int update(String sql) throws SQLException {
        return save(sql);
    }

    /**
     * <p> 用于更新数据，sql语句为update语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int update(String sql, Object... params) throws SQLException {
        return save(sql, params);
    }

    /**
     * <p> 用于更新数据，sql语句为update语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int update(String sql, List<Object> params) throws SQLException {
        return save(sql, params);
    }

    /**
     * <p> 用于删除数据，sql语句为delete语句 </p>
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int delete(String sql) throws SQLException {
        return save(sql);
    }

    /**
     * <p> 用于删除数据，sql语句为delete语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int delete(String sql, Object... params) throws SQLException {
        return save(sql, params);
    }

    /**
     * <p> 用于删除数据，sql语句为delete语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int delete(String sql, List<Object> params) throws SQLException {
        return save(sql, params);
    }

    /**
     * <p> 处理INSERT, UPDATE, DELETE SQL语句 </p>
     *
     * @param sql 数据库sql语句
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static int save(String sql) throws SQLException {
        return save(sql, new Object[]{});
    }

    /**
     * <p> 处理INSERT, UPDATE, DELETE SQL语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static int save(String sql, Object... params) throws SQLException {
        try (FleaDBOperationHandler handler = getDBOperationHandler(sql, params)) {
            return save(handler);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 处理INSERT, UPDATE, DELETE SQL语句（带参数） </p>
     *
     * @param sql    数据库sql语句
     * @param params 参数列表
     * @return 影响行数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static int save(String sql, List<Object> params) throws SQLException {
        int retVal = -1;
        if (null != params && !params.isEmpty()) {
            Object[] paramArr = params.toArray(new Object[0]);
            retVal = save(sql, paramArr);
        }
        return retVal;
    }

    /**
     * <p> 处理INSERT, UPDATE, DELETE SQL语句 </p>
     *
     * @param handler 一次数据库操作处理对象
     * @return 处理记录数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static int save(FleaDBOperationHandler handler) throws SQLException {
        PreparedStatement preparedStatement = null;
        if (ObjectUtils.isNotEmpty(handler)) {
            preparedStatement = handler.getPreparedStatement();
        }
        return ObjectUtils.isNotEmpty(preparedStatement) ? preparedStatement.executeUpdate() : -1;
    }

    /**
     * <p> 获取一次数据库操作处理 </p>
     *
     * @return Flea一次数据库操作处理对象
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static FleaDBOperationHandler getDBOperationHandler(String sql, Object... params) throws SQLException {

        FleaDBOperationHandler handler = new FleaDBOperationHandler();
        Connection connection = FleaJDBCConfig.getConfig().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "SQL = {}", sql);
        }

        if (ObjectUtils.isNotEmpty(preparedStatement) && ArrayUtils.isNotEmpty(params)) {
            for (int i = 0; i < params.length; i++) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug1(obj, "PARAM{} = {}", i + 1, params[i]);
                }
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        handler.setConnection(connection);
        handler.setPreparedStatement(preparedStatement);

        return handler;
    }

    /**
     * <p> 构建并执行 SELECT SQL模板 </p>
     *
     * @param relationId 关系编号
     * @param entity     实体类对象
     * @return 查询结果Map集合
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static List<Map<String, Object>> query(String relationId, Object entity) throws SQLException {

        try (FleaDBOperationHandler handler = getDBOperationHandlerWithReturnSet(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.SELECT))) {
            return ResultToListMap(handler.getResultSet());
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 构建并执行 SELECT SQL模板 (单个查询结果) </p>
     *
     * @param relationId 关系编号
     * @param entity     实体类对象
     * @return 单个查询结果
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static Object querySingle(String relationId, Object entity) throws SQLException {
        Object result = null;

        try (FleaDBOperationHandler handler = getDBOperationHandlerWithReturnSet(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.SELECT))) {
            ResultSet rs = handler.getResultSet();
            if (rs.next()) {
                result = rs.getObject(1);
            }
            return result;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 构建并执行INSERT SQL模板 </p>
     *
     * @param relationId 关系编号
     * @param entity     实体类对象
     * @return 新增的记录数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int insert(String relationId, Object entity) throws SQLException {
        return save(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.INSERT));

    }

    /**
     * <p> 构建并执行UPDATE SQL模板 </p>
     *
     * @param relationId 关系编号
     * @param entity     实体类对象
     * @return 更新的记录数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int update(String relationId, Object entity) throws SQLException {
        return save(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.UPDATE));
    }

    /**
     * <p> 构建并执行DELETE SQL模板 </p>
     *
     * @param relationId 关系编号
     * @param entity     实体类对象
     * @return 删除的记录数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    public static int delete(String relationId, Object entity) throws SQLException {
        return save(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.DELETE));
    }

    /**
     * <p> 用于带参数的查询，返回结果集 </p>
     *
     * @param template SQL模板接口类
     * @return 一次数据库操作处理对象
     * @throws Exception
     * @since 1.0.0
     */
    private static FleaDBOperationHandler getDBOperationHandlerWithReturnSet(ITemplate<Object> template) throws Exception {
        FleaDBOperationHandler handler = null;
        if (ObjectUtils.isNotEmpty(template)) {
            template.initialize(); // 初始化SQL模板（一定要初始化）
            handler = getDBOperationHandler(template.toNativeSql(), template.toNativeParams(), template.getTemplateType().getKey());
            queryWithReturnResultSet(handler);
        }
        return handler;
    }

    /**
     * <p> 构建并执行INSERT. UPDATE. DELETE SQL模板 </p>
     *
     * @param template SQL模板
     * @return 操作记录数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static int save(ITemplate<Object> template) throws SQLException {
        try (FleaDBOperationHandler handler = getDBOperationHandler(template)) {
            return save(handler);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 构建INSERT, UPDATE, DELETE SQL模板，获取Connection 和 PreparedStatement </p>
     *
     * @param template SQL模板
     * @return 一次数据库操作处理对象
     * @throws Exception
     * @since 1.0.0
     */
    private static FleaDBOperationHandler getDBOperationHandler(ITemplate<Object> template) throws Exception {
        FleaDBOperationHandler handler = null;
        if (ObjectUtils.isNotEmpty(template)) {
            template.initialize(); // 初始化SQL模板（一定要初始化）
            handler = getDBOperationHandler(template.toNativeSql(), template.toNativeParams(), template.getTemplateType().getKey());
        }
        return handler;
    }

    /**
     * <p> 获取一次数据库操作处理 </p>
     *
     * @return 一次数据库操作处理对象
     * @throws Exception
     * @since 1.0.0
     */
    private static FleaDBOperationHandler getDBOperationHandler(String sql, List<SqlParam> sqlParams, String templateType) throws Exception {

        Connection connection = FleaJDBCConfig.getConfig().getConnection();
        // 无法获取数据库连接
        ObjectUtils.checkEmpty(connection, DaoException.class, "ERROR-DB-DAO0000000014");

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            if (TemplateTypeEnum.INSERT.getKey().equals(templateType)) {
                LOGGER.debug1(obj, "SQL = {}", sql);
            } else if (TemplateTypeEnum.UPDATE.getKey().equals(templateType)) {
                LOGGER.debug1(obj, "SQL = {}", sql);
            } else if (TemplateTypeEnum.DELETE.getKey().equals(templateType)) {
                LOGGER.debug1(obj, "SQL = {}", sql);
            } else if (TemplateTypeEnum.SELECT.getKey().equals(templateType)) {
                LOGGER.debug1(obj, "SQL = {}", sql);
            }
        }

        if (CollectionUtils.isNotEmpty(sqlParams)) {
            for (SqlParam sqlParam : sqlParams) {
                if (TemplateTypeEnum.INSERT.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JDBC, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if (TemplateTypeEnum.UPDATE.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JDBC, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if (TemplateTypeEnum.DELETE.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JDBC, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                } else if (TemplateTypeEnum.SELECT.getKey().equals(templateType)) {
                    LOGGER.debug1(obj, "JDBC, COL{} = {}, PARAM{} = {}", sqlParam.getIndex(), sqlParam.getTabColName(), sqlParam.getIndex(), sqlParam.getAttrValue());
                }
                preparedStatement.setObject(sqlParam.getIndex(), sqlParam.getAttrValue());
            }
        }
        FleaDBOperationHandler handler = new FleaDBOperationHandler();
        handler.setConnection(connection);
        handler.setPreparedStatement(preparedStatement);

        return handler;
    }

    /**
     * <p> 设置查询结果集 </p>
     *
     * @param handler 一次数据库操作处理对象（只初始化了Connection 和 PreparedStatement 对象）
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static void queryWithReturnResultSet(FleaDBOperationHandler handler) throws SQLException {
        if (ObjectUtils.isNotEmpty(handler)) {
            PreparedStatement preparedStatement = handler.getPreparedStatement();
            if (ObjectUtils.isNotEmpty(preparedStatement)) {
                handler.setResultSet(preparedStatement.executeQuery());
            }
        }
    }

    /**
     * <p> 将结果集ResultSet转换为包含Map<String,Object>的List集合 </p>
     *
     * @param rs 结果集对象
     * @return 查询数据集合
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static List<Map<String, Object>> ResultToListMap(ResultSet rs) throws SQLException {
        List<Map<String, Object>> listMaps = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(rs)) {
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                Map<String, Object> map = new HashMap<>();
                if (ObjectUtils.isNotEmpty(rsmd)) {
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        map.put(rsmd.getColumnLabel(i + 1), rs.getObject(i + 1));
                    }
                }
                listMaps.add(map);
            }
        }
        return listMaps;
    }

    /**
     * <p> 获取表结构 </p>
     *
     * @param tableName 表名
     * @return 表结构列表
     * @since 1.0.0
     */
    public static List<Column> queryTableStructure(String tableName) {
        List<Column> columnList = null;

        try (Connection connection = FleaJDBCConfig.getConfig().getConnection();
             ResultSet primaryKeyResultSet = connection.getMetaData().getPrimaryKeys(null, null, tableName.toUpperCase());
             ResultSet columnsResultSet = connection.getMetaData().getColumns(null, null, tableName.toUpperCase(), "")) {

            String pkName = "";
            while (primaryKeyResultSet.next()) {
                pkName = primaryKeyResultSet.getString("COLUMN_NAME");
            }

            columnList = new ArrayList<>();
            while (columnsResultSet.next()) {
                String columnName = columnsResultSet.getString("COLUMN_NAME");
                int columnSize = columnsResultSet.getInt("COLUMN_SIZE");
                String typeName = columnsResultSet.getString("TYPE_NAME");
                String isNullable = columnsResultSet.getString("IS_NULLABLE");
                String remarks = columnsResultSet.getString("REMARKS");

                Column column = new Column();
                column.setTabColumnName(columnName); // 表列名

                String[] columnNameArr = StringUtils.split(columnName, CommonConstants.SymbolConstants.UNDERLINE);
                if (ArrayUtils.isNotEmpty(columnNameArr)) {
                    for (int n = 0; n < columnNameArr.length; n++) {
                        if (CommonConstants.NumeralConstants.INT_ZERO == n) { // 第一个单词小写
                            columnNameArr[n] = columnNameArr[n].toLowerCase();
                        } else { // 后面单词，首字母大写，其余小写
                            columnNameArr[n] = StringUtils.toUpperCaseFirstAndLowerCaseLeft(columnNameArr[n]);
                        }
                    }
                    String attrName = StringUtils.strCat("", columnNameArr);
                    column.setAttrName(attrName); // 属性名
                }

                // 设置属性类型
                if ("INT".equals(typeName) || ("NUMBER".equals(typeName) && columnSize >= 10)) {
                    column.setAttrType(Long.class);
                }

                if ("BIT".equals(typeName) || "TINYINT".equals(typeName) || ("NUMBER".equals(typeName) && columnSize < 10)) {
                    column.setAttrType(Integer.class);
                }

                if ("VARCHAR".equals(typeName) || "VARCHAR2".equals(typeName) || "TINYTEXT".equals(typeName) || "TEXT".equals(typeName)
                        || "MEDIUMTEXT".equals(typeName) || "LONGTEXT".equals(typeName)) {
                    column.setAttrType(String.class);
                }

                if ("DATE".equals(typeName) || "DATETIME".equals(typeName)) {
                    column.setAttrType(Date.class);
                }

                // 设置是否为空
                if ("YES".equals(isNullable)) {
                    column.setNullable(true);
                } else {
                    column.setNullable(false);
                }

                // 设置字段描述
                column.setTabColumnDesc(remarks);

                if (pkName.equals(columnName)) {
                    column.setPrimaryKey(true);
                    column.setUnique(true);
                } else {
                    column.setPrimaryKey(false);
                }

                columnList.add(column);

            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("数据库操作异常：", e);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "Table Info : {}", columnList);
        }

        return columnList;
    }

}