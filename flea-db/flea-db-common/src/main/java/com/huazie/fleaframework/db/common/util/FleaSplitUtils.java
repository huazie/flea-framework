package com.huazie.fleaframework.db.common.util;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.exceptions.LibSplitException;
import com.huazie.fleaframework.db.common.exceptions.TableSplitException;
import com.huazie.fleaframework.db.common.lib.pojo.SplitLib;
import com.huazie.fleaframework.db.common.lib.split.ILibSplit;
import com.huazie.fleaframework.db.common.lib.split.LibSplitEnum;
import com.huazie.fleaframework.db.common.lib.split.config.Lib;
import com.huazie.fleaframework.db.common.lib.split.config.LibSplitConfig;
import com.huazie.fleaframework.db.common.lib.split.config.Transaction;
import com.huazie.fleaframework.db.common.table.pojo.Column;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.common.table.split.ITableSplit;
import com.huazie.fleaframework.db.common.table.split.TableSplitEnum;
import com.huazie.fleaframework.db.common.table.split.config.Split;
import com.huazie.fleaframework.db.common.table.split.config.Table;
import com.huazie.fleaframework.db.common.table.split.config.TableSplitConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分库分表工具类
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaSplitUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaSplitUtils.class);

    private static final String TABLE_NAME_PLACEHOLDER = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS +
            DBConstants.LibTableSplitConstants.FLEA_TABLE_NAME + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;

    private static final String LIB_NAME_PLACEHOLDER = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS +
            DBConstants.LibTableSplitConstants.FLEA_LIB_NAME + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;

    private static final String TRANSACTION_NAME_PLACEHOLDER = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS +
            DBConstants.LibTableSplitConstants.FLEA_TRANSACTION_NAME + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;

    private FleaSplitUtils() {
    }

    /**
     * 获取分表信息
     *
     * @param tableName  模板表名
     * @param entityCols 实体类属性列集合
     * @return 分表对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static SplitTable getSplitTable(String tableName, Column[] entityCols) throws CommonException {

        String pkColumnValue = "";
        boolean generatorFlag = true;
        if (!ArrayUtils.isEmpty(entityCols)) {
            for (Column entityCol : entityCols) {
                if (entityCol.isPrimaryKey()) {
                    pkColumnValue = entityCol.getPkColumnValue();
                    generatorFlag = entityCol.isGeneratorFlag();
                    break;
                }
            }
        }

        SplitTable splitTable = new SplitTable();
        splitTable.setTableName(tableName); // 设置模板表名
        splitTable.setSplitTableName(tableName); // 设置分表名默认为模板表名
        splitTable.setPkColumnValue(pkColumnValue); // 生成器表中的主键值，为主键中@TableGenerator中的pkColumnValue
        splitTable.setSplitTablePkColumnValue(pkColumnValue); // 生成器表中分表的主键值，默认为主键中@TableGenerator中的pkColumnValue
        splitTable.setExistSplitTable(false); // 默认没有分表
        splitTable.setExistSplitTablePkColumn(false); // 默认没有ID生成器表中分表的主键值
        splitTable.setGeneratorFlag(generatorFlag); // 默认主键生成器表在模板库中

        Object obj = new Object() {};
        LOGGER.debug1(obj, "The name of main table = {}", tableName);

        // 获取分表信息
        Table tab = TableSplitConfig.getConfig().getTable(tableName);
        if (ObjectUtils.isNotEmpty(tab)) { // 当前表具有分表配置
            // 获取分表名表达式
            String exp = tab.getExp();
            // 请检查分表配置信息（分表名表达式【exp】不能为空）
            StringUtils.checkBlank(exp, TableSplitException.class, "ERROR-DB-TSP0000000009");

            StringBuilder tableNameBuilder = new StringBuilder(exp);
            // 替换 分表名表达式中 (FLEA_TABLE_NAME) 内容
            StringUtils.replace(tableNameBuilder, TABLE_NAME_PLACEHOLDER, tableName);

            StringBuilder pkColumnValueBuilder = new StringBuilder(splitTable.getPkColumnValue());

            Map<String, Object> splitLibObjMap = null; // 分库对象集合，键为分库序列键

            List<Split> splitList = tab.getSplits().getSplitList();
            for (Split split : splitList) {
                // 分表转换类型关键字
                String key = split.getKey();
                // 分表属性列
                String column = split.getColumn();
                // 分表转换实现类
                String implClass = split.getImplClass();

                if (StringUtils.isBlank(implClass)) { // 分表转换实现类【implClass】为空，则分表转换类型关键字【key】不能为空
                    // 请检查分表配置信息（分表转换类型关键字【key】不能为空）
                    StringUtils.checkBlank(key, TableSplitException.class, "ERROR-DB-TSP0000000004");
                }

                if (StringUtils.isBlank(key)) { // 分表转换类型关键字【key】为空，则 分表转换实现类【implClass】不能为空
                    // 请检查分表配置信息（分表转换实现类【implClass】不能为空）
                    StringUtils.checkBlank(implClass, TableSplitException.class, "ERROR-DB-TSP0000000006");
                } else {
                    // 根据分表转换类型关键字【key】，获取对应的分表枚举
                    TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(), DBConstants.LibTableSplitConstants.KEY, key);
                    if (ObjectUtils.isEmpty(tableSplitEnum)) {
                        // 请检查分表配置信息（分表转换类型关键字【key】非法）
                        ExceptionUtils.throwCommonException(TableSplitException.class, "ERROR-DB-TSP0000000010");
                    } else if (StringUtils.isNotBlank(implClass) && !implClass.equals(tableSplitEnum.getImplClass())) {
                        // 请检查分表配置信息（分表转换实现类【implClass】非法）
                        ExceptionUtils.throwCommonException(TableSplitException.class, "ERROR-DB-TSP0000000007");
                    }
                    // 取枚举类中的分表转换实现类
                    implClass = tableSplitEnum.getImplClass();
                }

                // 请检查分表配置信息（分表属性列【column】不能为空）
                StringUtils.checkBlank(column, TableSplitException.class, "ERROR-DB-TSP0000000005");

                Column entityCol = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, column);
                // 请检查分表配置信息（分表属性列【column】不存在）
                ObjectUtils.checkEmpty(entityCol, TableSplitException.class, "ERROR-DB-TSP0000000008");

                // 获取分表转换实现类
                ITableSplit tableSplit = (ITableSplit) ReflectUtils.newInstance(implClass);
                // 获取分表列的属性值
                Object attrValue = entityCol.getAttrValue();
                // 获取分表字段值【即分表规则转换后的值】
                String splitTableFieldValue = tableSplit.convert(attrValue);
                LOGGER.debug1(obj, "The field value of split table = {}", splitTableFieldValue);
                String columnPlaceholder = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS + column.toUpperCase()
                        + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;
                StringUtils.replace(tableNameBuilder, columnPlaceholder, splitTableFieldValue);
                // 分表场景下，替换生成器表中的主键值模板，为指定分表
                StringUtils.replace(pkColumnValueBuilder, columnPlaceholder, splitTableFieldValue);

                // 获取分库序列键
                String seq = split.getSeq();
                if (ObjectUtils.isNotEmpty(seq)) {
                    if (ObjectUtils.isEmpty(splitLibObjMap)) {
                        splitLibObjMap = new HashMap<>();
                    }
                    // 添加分库对象，键为分库序列键，值为分表字段值
                    splitLibObjMap.put(seq, attrValue);
                }
            }
            // 设置分表名
            splitTable.setSplitTableName(tableNameBuilder.toString());
            // 设置生成器表中分表的主键值
            splitTable.setSplitTablePkColumnValue(pkColumnValueBuilder.toString());
            // 存在分表
            splitTable.setExistSplitTable(true);
            // 存在ID生成器表中分表的主键值
            if (!pkColumnValue.equals(splitTable.getSplitTablePkColumnValue())) {
                splitTable.setExistSplitTablePkColumn(true);
            }
            // 添加分库信息
            splitTable.setSplitLib(getSplitLib(tab.getLib(), splitLibObjMap));
        } else {
            SplitLib splitLib = new SplitLib();
            splitLib.setExistSplitLib(false);
            splitTable.setSplitLib(splitLib);
        }

        LOGGER.debug1(obj, "The real name of table = {}", splitTable.getSplitTableName());
        LOGGER.debug1(obj, "The origin of pkColumnValue of table = {}", splitTable.getPkColumnValue());
        LOGGER.debug1(obj, "The pkColumnValue of split table = {}", splitTable.getSplitTablePkColumnValue());

        return splitTable;
    }

    /**
     * 获取真实的表名，如是分表，则获取分表名
     *
     * @param tableName  模板表名
     * @param entityCols 实体类属性列集合
     * @return 真实的表名，如是分表，则返回相应的分表名
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static String getRealTableName(String tableName, Column[] entityCols) throws CommonException {
        return getSplitTable(tableName, entityCols).getSplitTableName();
    }

    /**
     * 获取分库信息
     *
     * @param libName        模板库名
     * @param splitLibObjMap 分库对象集合
     * @return 分库信息
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    public static SplitLib getSplitLib(String libName, Map<String, Object> splitLibObjMap) throws CommonException {

        SplitLib splitLib = new SplitLib();
        splitLib.setExistSplitLib(false);
        splitLib.setLibName(libName);
        splitLib.setSplitLibName(libName);

        if (StringUtils.isNotBlank(libName) && MapUtils.isNotEmpty(splitLibObjMap)) {

            Object obj = new Object() {};
            LOGGER.debug1(obj, "The name of template library = {}", libName);

            // 分库信息
            Lib lib = LibSplitConfig.getConfig().getLib(libName);
            if (ObjectUtils.isNotEmpty(lib)) {
                // 分库总数
                int count = lib.getCount();
                // 分库名表达式
                String libExp = lib.getExp();

                // 请检查分库配置信息（分库表达式【exp】不能为空）
                StringUtils.checkBlank(libExp, LibSplitException.class, "ERROR-DB-LSP0000000002");

                StringBuilder libNameBuilder = new StringBuilder(libExp);
                // 替换 分库名表达式中 (FLEA_LIB_NAME) 内容
                StringUtils.replace(libNameBuilder, LIB_NAME_PLACEHOLDER, libName);

                Transaction tx = lib.getTransaction();
                // 请检查分库配置信息（分库事务配置【transaction】不能为空）
                ObjectUtils.checkEmpty(tx, LibSplitException.class, "ERROR-DB-LSP0000000008");

                String txName = tx.getName();
                // 请检查分库配置信息（模板事务名【name】不能为空）
                StringUtils.checkBlank(txName, LibSplitException.class, "ERROR-DB-LSP0000000009");

                String txExp = tx.getExp();
                // 请检查分库配置信息（分库事务名表达式【exp】不能为空）
                StringUtils.checkBlank(txExp, LibSplitException.class, "ERROR-DB-LSP0000000010");

                StringBuilder txNameBuilder = new StringBuilder(txExp);
                // 替换 分库事务名表达式中 (FLEA_TRANSACTION_NAME) 内容
                StringUtils.replace(txNameBuilder, TRANSACTION_NAME_PLACEHOLDER, txName);

                List<Split> splitList = lib.getSplits().getSplitList();
                for (Split split : splitList) {
                    // 分库转换类型关键字
                    String key = split.getKey();
                    // 分库序列键
                    String seq = split.getSeq();
                    // 分库转换实现类
                    String implClass = split.getImplClass();

                    if (StringUtils.isBlank(implClass)) { // 分库转换实现类【implClass】为空，则分库转换类型关键字【key】不能为空
                        // 请检查分库配置信息（分库转换类型关键字【key】不能为空）
                        StringUtils.checkBlank(key, LibSplitException.class, "ERROR-DB-LSP0000000003");
                    }

                    if (StringUtils.isBlank(key)) { // 分库转换类型关键字【key】为空，则 分库转换实现类【implClass】不能为空
                        // 请检查分库配置信息（分库转换实现类【implClass】不能为空）
                        StringUtils.checkBlank(implClass, LibSplitException.class, "ERROR-DB-LSP0000000004");
                    } else {
                        // 根据分库转换类型关键字【key】，获取对应的分库转换类型枚举
                        LibSplitEnum libSplitEnum = (LibSplitEnum) EntityUtils.getEntity(LibSplitEnum.values(), DBConstants.LibTableSplitConstants.KEY, key);
                        if (ObjectUtils.isEmpty(libSplitEnum)) {
                            // 请检查分库配置信息（分库转换类型关键字【key】非法）
                            ExceptionUtils.throwCommonException(LibSplitException.class, "ERROR-DB-LSP0000000005");
                        } else if (StringUtils.isNotBlank(implClass) && !implClass.equals(libSplitEnum.getImplClass())) {
                            // 请检查分库配置信息（分库转换实现类【implClass】非法）
                            ExceptionUtils.throwCommonException(LibSplitException.class, "ERROR-DB-LSP0000000006");
                        }
                        // 取枚举类中的分表转换实现类
                        implClass = libSplitEnum.getImplClass();
                    }

                    // 请检查分库配置信息（分库序列键【seq】不能为空）
                    StringUtils.checkBlank(seq, LibSplitException.class, "ERROR-DB-LSP0000000007");

                    // 根据分库序列键从分库对象集合中获取分库对象
                    Object splitLibObj = splitLibObjMap.get(seq);

                    // 获取分库转换实现类
                    ILibSplit libSplit = (ILibSplit) ReflectUtils.newInstance(implClass);
                    if (ObjectUtils.isEmpty(libSplit)) {
                        // 请检查分库配置信息（分库转换实现类【implClass】非法）
                        ExceptionUtils.throwCommonException(LibSplitException.class, "ERROR-DB-LSP0000000006");
                    }
                    // 获取分库序列键【即分库规则转换后的值】
                    String splitLibSeq = libSplit.convert(splitLibObj, count);
                    LOGGER.debug1(obj, "The sequence key of split lib = {}", splitLibSeq);

                    // 分库序列键
                    String seqPlaceholder = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS +
                            seq + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;
                    // 分库名表达式替换分库序列键
                    StringUtils.replace(libNameBuilder, seqPlaceholder, splitLibSeq);
                    // 分库事务名表达式替换分库序列键
                    StringUtils.replace(txNameBuilder, seqPlaceholder, splitLibSeq);
                }
                // 设置分库名
                splitLib.setSplitLibName(libNameBuilder.toString());
                // 设置模板库名
                splitLib.setTransactionName(txName);
                // 设置分库事务名
                splitLib.setSplitLibTxName(txNameBuilder.toString());
                // 存在分库
                splitLib.setExistSplitLib(true);
            }

            LOGGER.debug1(obj, "The real name of lib = {}", splitLib.getSplitLibName());
            LOGGER.debug1(obj, "The name of template transaction = {}", splitLib.getTransactionName());
            LOGGER.debug1(obj, "The real name of transaction = {}", splitLib.getSplitLibTxName());
        }

        return splitLib;
    }
}
