package com.huazie.frame.db.common.table.split;

import com.huazie.frame.common.FleaConfigManager;
import com.huazie.frame.common.config.ConfigItem;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ExceptionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.table.pojo.Column;
import com.huazie.frame.db.common.table.pojo.SplitTable;
import com.huazie.frame.db.common.table.split.config.Split;
import com.huazie.frame.db.common.table.split.config.Splits;
import com.huazie.frame.db.common.table.split.config.Table;
import com.huazie.frame.db.common.table.split.config.TableSplitConfig;
import com.huazie.frame.db.common.util.EntityUtils;
import com.huazie.frame.db.jpa.persistence.IFleaJPATableSplitHandler;

import java.util.Iterator;
import java.util.List;

/**
 * <p> 分表工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TableSplitHelper.class);

    private static volatile IFleaJPATableSplitHandler fleaJPATableSplitHandler;

    private static Boolean isTableSplitHandlerInit = Boolean.FALSE;

    private TableSplitHelper() {
    }

    /**
     * <p> 获取分表对象 </p>
     *
     * @param tableName  主表名
     * @param entityCols 实体类属性列集合
     * @return 分表对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static SplitTable getSplitTable(String tableName, Column[] entityCols) throws CommonException {

        String pkColumnValue = "";
        if (!ArrayUtils.isEmpty(entityCols)) {
            for (Column entityCol : entityCols) {
                if (entityCol.isPrimaryKey()) {
                    pkColumnValue = entityCol.getPkColumnValue();
                    break;
                }
            }
        }

        SplitTable splitTable = new SplitTable();
        splitTable.setTableName(tableName); // 设置主表名
        splitTable.setSplitTableName(tableName); // 设置分表名默认为主表名
        splitTable.setPkColumnValue(pkColumnValue); // 生成器表中的主键值，为主键中@TableGenerator中的pkColumnValue
        splitTable.setSplitTablePkColumnValue(pkColumnValue); // 生成器表中分表的主键值，默认为主键中@TableGenerator中的pkColumnValue
        splitTable.setExistSplitTable(false);
        
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "The name of main table = {}", tableName);
        }

        TableSplitConfig config = TableSplitConfig.getConfig();

        if (ObjectUtils.isNotEmpty(config)) {
            // 获取分表信息
            Table tab = config.getTable(tableName);
            if (ObjectUtils.isNotEmpty(tab)) {// 当前表具有分表配置
                // 获取分表名表达式
                String exp = tab.getExp();
                // 请检查分表配置信息（分表表达式【exp】不能为空）
                StringUtils.checkBlank(exp, TableSplitException.class, "ERROR-DB-TSP0000000009");

                StringBuilder tableNameBuilder = new StringBuilder(exp);
                String tableNamePlaceholder = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS +
                        DBConstants.TableSplitConstants.FLEA_TABLE_NAME + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;
                // 替换 分表名表达式中 (FLEA_TABLE_NAME) 内容
                StringUtils.replace(tableNameBuilder, tableNamePlaceholder, tableName);

                StringBuilder pkColumnValueBuilder = new StringBuilder(splitTable.getPkColumnValue());

                Splits splits = tab.getSplits();
                if (ObjectUtils.isNotEmpty(splits)) {
                    List<Split> splitList = splits.getSplitList();
                    if (CollectionUtils.isNotEmpty(splitList)) {
                        Iterator<Split> splitIt = splitList.iterator();
                        while (splitIt.hasNext()) {
                            Split split = splitIt.next();
                            // 分表类型关键字
                            String key = split.getKey();
                            // 分表属性列
                            String column = split.getColumn();
                            // 分表后缀转换实现类
                            String implClass = split.getImplClass();

                            // 请检查分表配置信息（分表类型关键字【key】不能为空）
                            StringUtils.checkBlank(key, TableSplitException.class, "ERROR-DB-TSP0000000004");

                            // 请检查分表配置信息（分表属性列【column】不能为空）
                            StringUtils.checkBlank(column, TableSplitException.class, "ERROR-DB-TSP0000000005");

                            // 请检查分表配置信息（分表后缀转换实现类【implClass】不能为空）
                            StringUtils.checkBlank(implClass, TableSplitException.class, "ERROR-DB-TSP0000000006");

                            TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(),
                                    DBConstants.TableSplitConstants.KEY, key);

                            if (ObjectUtils.isNotEmpty(tableSplitEnum) && !implClass.equals(tableSplitEnum.getImplClass())) {
                                // 请检查分表配置信息（分表后缀转换实现类【implClass】非法）
                                ExceptionUtils.throwCommonException(TableSplitException.class, "ERROR-DB-TSP0000000007");
                            }

                            Column entityCol = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, column);
                            // 请检查分表配置信息（分表属性列【column】不存在）
                            ObjectUtils.checkEmpty(entityCol, TableSplitException.class, "ERROR-DB-TSP0000000008");

                            // 获取分表后缀转换实现类
                            ITableSplit tableSplit = (ITableSplit) ReflectUtils.newInstance(implClass);
                            // 获取分表字段值【即分表规则转换后的值】
                            String splitTableFieldValue = tableSplit.convert(entityCol.getAttrValue());
                            if (LOGGER.isDebugEnabled()) {
                                LOGGER.debug1(obj, "The field value of split table = {}", splitTableFieldValue);
                            }
                            String columnPlaceholder = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS +
                                    column.toUpperCase() + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;
                            StringUtils.replace(tableNameBuilder, columnPlaceholder, splitTableFieldValue);
                            // 分表场景下，替换生成器表中的主键值模板，为指定分表
                            StringUtils.replace(pkColumnValueBuilder, columnPlaceholder, splitTableFieldValue);
                        }
                        // 设置分表名
                        splitTable.setSplitTableName(tableNameBuilder.toString());
                        // 设置生成器表中分表的主键值
                        splitTable.setSplitTablePkColumnValue(pkColumnValueBuilder.toString());
                        splitTable.setExistSplitTable(true); // 存在分表
                    }
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "The real name of table = {}", splitTable.getSplitTableName());
            LOGGER.debug1(obj, "The origin of pkColumnValue of table = {}", splitTable.getPkColumnValue());
            LOGGER.debug1(obj, "The pkColumnValue of split table = {}", splitTable.getSplitTablePkColumnValue());
        }

        return splitTable;
    }

    /**
     * <p> 获取真实的表名，如是分表，则获取分表名 </p>
     *
     * @param tableName  主表名
     * @param entityCols 实体类属性列集合
     * @return 真实的表名，如是分表，则返回相应的分表名
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static String getRealTableName(String tableName, Column[] entityCols) throws CommonException {
        return getSplitTable(tableName, entityCols).getSplitTableName();
    }

    /**
     * <p> 获取分表处理者实现类 </p>
     *
     * @return Flea JPA分表处理者实现类
     * @since 1.0.0
     */
    public static IFleaJPATableSplitHandler findTableSplitHandle() {

        if (isTableSplitHandlerInit.equals(Boolean.FALSE)) {
            synchronized (isTableSplitHandlerInit) {
                if (isTableSplitHandlerInit.equals(Boolean.FALSE)) {
                    try {
                        fleaJPATableSplitHandler = newTableSplitHandle();
                        isTableSplitHandlerInit = Boolean.TRUE;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return fleaJPATableSplitHandler;
    }

    /**
     * <p> 新生成一个Flea JPA 分表处理者实现类 </p>
     *
     * @return 分表处理者实现类
     * @since 1.0.0
     */
    private static IFleaJPATableSplitHandler newTableSplitHandle() {

        ConfigItem configItem = FleaConfigManager.getConfigItem(DBConstants.FleaJPAConstants.FLEA_JPA, DBConstants.FleaJPAConstants.TABLE_SPLIT_HANDLER);

        if (ObjectUtils.isEmpty(configItem) || StringUtils.isBlank(configItem.getValue())) {
            throw new NullPointerException("请检查flea-config.xml中配置项【<config-items key=\"flea-jpa\" > <config-item key=\"table_split_handler\" >】");
        }
        // 获取分表处理者实现类配置
        String handlerClassStr = configItem.getValue();
        // 获取分表处理者实现类实例
        IFleaJPATableSplitHandler tableSplitHandler = (IFleaJPATableSplitHandler) ReflectUtils.newInstance(handlerClassStr);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Table Split Handler = {}", tableSplitHandler);
        }

        return tableSplitHandler;
    }

}
