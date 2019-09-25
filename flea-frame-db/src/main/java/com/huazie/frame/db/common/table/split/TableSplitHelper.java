package com.huazie.frame.db.common.table.split;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.table.pojo.Column;
import com.huazie.frame.db.common.table.split.config.Split;
import com.huazie.frame.db.common.table.split.config.Splits;
import com.huazie.frame.db.common.table.split.config.Table;
import com.huazie.frame.db.common.table.split.config.TableSplitConfig;
import com.huazie.frame.db.common.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(TableSplitHelper.class);

    /**
     * <p> 获取真实的表名，如是分表，则获取分表名 </p>
     *
     * @param name 主表名
     * @return 真实的表名，如是分表，则返回相应的分表名
     * @throws Exception
     * @since 1.0.0
     */
    public static String getRealTableName(String name, Column[] entityCols) throws Exception {

        String realTableName = name;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The name of main table : {}", name);
        }

        // 获取分表信息
        Table tab = TableSplitConfig.getConfig().getTable(name);
        if (ObjectUtils.isNotEmpty(tab)) {// 当前表具有分表配置
            // 获取分表名表达式
            String exp = tab.getExp();
            if (StringUtils.isBlank(exp)) {
                // 请检查分表配置信息（分表表达式【exp】不能为空）
                throw new TableSplitException("ERROR-DB-TSP0000000009");
            }

            StringBuilder tableNameBuilder = new StringBuilder(exp);
            String tableNamePlaceholder = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS + DBConstants.TableSplitConstants.FLEA_TABLE_NAME + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;
            // 替换 分表名表达式中 (FLEA_TABLE_NAME) 内容
            StringUtils.replace(tableNameBuilder, tableNamePlaceholder, name);

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

                        if (StringUtils.isBlank(key)) {
                            // 请检查分表配置信息（分表类型关键字【key】不能为空）
                            throw new TableSplitException("ERROR-DB-TSP0000000004");
                        }

                        if (StringUtils.isBlank(column)) {
                            // 请检查分表配置信息（分表属性列【column】不能为空）
                            throw new TableSplitException("ERROR-DB-TSP0000000005");
                        }

                        if (StringUtils.isBlank(implClass)) {
                            // 请检查分表配置信息（分表后缀转换实现类【implClass】不能为空）
                            throw new TableSplitException("ERROR-DB-TSP0000000006");
                        }

                        TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(), DBConstants.TableSplitConstants.KEY, key);

                        if (null != tableSplitEnum && !implClass.equals(tableSplitEnum.getImplClass())) {
                            // 请检查分表配置信息（分表后缀转换实现类【implClass】非法）
                            throw new TableSplitException("ERROR-DB-TSP0000000007");
                        }

                        Column entityCol = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, column);
                        if (ObjectUtils.isEmpty(entityCol)) {
                            // 请检查分表配置信息（分表属性列【column】不存在）
                            throw new TableSplitException("ERROR-DB-TSP0000000008");
                        }
                        // 获取分表后缀转换实现类
                        ITableSplit tableSplit = (ITableSplit) ReflectUtils.newInstance(implClass);
                        // 获取分表后缀名
                        String suffix = tableSplit.convert(entityCol.getAttrValue());
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("The suffix name of table : {}", suffix);
                        }
                        String columnPlaceholder = DBConstants.SQLConstants.SQL_LEFT_ROUND_BRACKETS + column.toUpperCase() + DBConstants.SQLConstants.SQL_RIGHT_ROUND_BRACKETS;
                        StringUtils.replace(tableNameBuilder, columnPlaceholder, suffix);
                    }
                    realTableName = tableNameBuilder.toString();
                }

            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The real name of table : {}", realTableName);
        }
        return realTableName;
    }

}
