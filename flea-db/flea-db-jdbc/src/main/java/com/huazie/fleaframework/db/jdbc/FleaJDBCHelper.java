package com.huazie.fleaframework.db.jdbc;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.exception.DaoException;
import com.huazie.fleaframework.db.common.sql.pojo.SqlParam;
import com.huazie.fleaframework.db.common.sql.template.ITemplate;
import com.huazie.fleaframework.db.common.sql.template.SqlTemplateFactory;
import com.huazie.fleaframework.db.common.sql.template.TemplateTypeEnum;
import com.huazie.fleaframework.db.common.table.pojo.Column;
import com.huazie.fleaframework.db.jdbc.config.FleaJDBCConfig;
import com.huazie.fleaframework.db.jdbc.pojo.FleaDBOperation;

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
 * <p> JDBC数据库工具类，包含数据的增删改查操作 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJDBCHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJDBCHelper.class);

    private FleaJDBCHelper() {
    }

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

        try (FleaDBOperation operation = queryWithReturnResultSet(sql, params)) {
            return ResultToListMap(operation.getResultSet());
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
    public static FleaDBOperation queryWithReturnResultSet(String sql, Object... params) throws SQLException {
        FleaDBOperation operation = getDBOperation(sql, params);
        queryWithReturnResultSet(operation);
        return operation;
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

        try (FleaDBOperation operation = queryWithReturnResultSet(sql, params)) {
            ResultSet rs = operation.getResultSet();
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
        try (FleaDBOperation operation = getDBOperation(sql, params)) {
            return save(operation);
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
     * @param operation Flea数据库操作
     * @return 处理记录数
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static int save(FleaDBOperation operation) throws SQLException {
        PreparedStatement preparedStatement = null;
        if (ObjectUtils.isNotEmpty(operation)) {
            preparedStatement = operation.getPreparedStatement();
        }
        return ObjectUtils.isNotEmpty(preparedStatement) ? preparedStatement.executeUpdate() : -1;
    }

    /**
     * <p> 获取数据库操作 </p>
     *
     * @return Flea数据库操作
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static FleaDBOperation getDBOperation(String sql, Object... params) throws SQLException {

        FleaDBOperation operation = new FleaDBOperation();
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
        operation.setConnection(connection);
        operation.setPreparedStatement(preparedStatement);

        return operation;
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

        try (FleaDBOperation operation = getDBOperationHandlerWithReturnSet(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.SELECT))) {
            return ResultToListMap(operation.getResultSet());
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

        try (FleaDBOperation operation = getDBOperationHandlerWithReturnSet(SqlTemplateFactory.newSqlTemplate(relationId, entity, TemplateTypeEnum.SELECT))) {
            ResultSet rs = operation.getResultSet();
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
     * @return Flea数据库操作
     * @since 1.0.0
     */
    private static FleaDBOperation getDBOperationHandlerWithReturnSet(ITemplate<Object> template) throws Exception {
        FleaDBOperation operation = null;
        if (ObjectUtils.isNotEmpty(template)) {
            template.initialize(); // 初始化SQL模板（一定要初始化）
            operation = getDBOperation(template.toNativeSql(), template.toNativeParams(), template.getTemplateType().getKey());
            queryWithReturnResultSet(operation);
        }
        return operation;
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
        try (FleaDBOperation operation = getDBOperation(template)) {
            return save(operation);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /**
     * <p> 构建INSERT, UPDATE, DELETE SQL模板，获取Connection 和 PreparedStatement </p>
     *
     * @param template SQL模板
     * @return Flea数据库操作
     * @since 1.0.0
     */
    private static FleaDBOperation getDBOperation(ITemplate<Object> template) throws Exception {
        FleaDBOperation operation = null;
        if (ObjectUtils.isNotEmpty(template)) {
            template.initialize(); // 初始化SQL模板（一定要初始化）
            operation = getDBOperation(template.toNativeSql(), template.toNativeParams(), template.getTemplateType().getKey());
        }
        return operation;
    }

    /**
     * <p> 获取数据库操作 </p>
     *
     * @return Flea数据库操作
     * @since 1.0.0
     */
    private static FleaDBOperation getDBOperation(String sql, List<SqlParam> sqlParams, String templateType) throws Exception {

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
        FleaDBOperation operation = new FleaDBOperation();
        operation.setConnection(connection);
        operation.setPreparedStatement(preparedStatement);

        return operation;
    }

    /**
     * <p> 设置查询结果集 </p>
     *
     * @param operation Flea数据库操作（只初始化了Connection 和 PreparedStatement 对象）
     * @throws SQLException 数据库操作异常
     * @since 1.0.0
     */
    private static void queryWithReturnResultSet(FleaDBOperation operation) throws SQLException {
        if (ObjectUtils.isNotEmpty(operation)) {
            PreparedStatement preparedStatement = operation.getPreparedStatement();
            if (ObjectUtils.isNotEmpty(preparedStatement)) {
                operation.setResultSet(preparedStatement.executeQuery());
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
                if ("INT".equals(typeName) || "BIGINT".equals(typeName) || ("NUMBER".equals(typeName) && columnSize >= 10)) {
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