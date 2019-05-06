package com.huazie.frame.db.common.table.split;

import com.huazie.frame.db.common.DBConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.table.column.Column;
import com.huazie.frame.db.common.table.split.config.Split;
import com.huazie.frame.db.common.table.split.config.Table;
import com.huazie.frame.db.common.table.split.config.TableSplitConfig;
import com.huazie.frame.db.common.util.EntityUtils;

/**
 * <p> 分表工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(TableSplitHelper.class);

    /**
     * <p>
     * 获取真实的表名，如是分表，则获取分表名
     *
     * @param name 主表名
     * @return
     * @throws Exception
     * @date 2018年6月3日
     */
    public static String getRealTableName(String name, Column[] entityCols) throws Exception {
        String realTableName = name;

        // 获取分表信息
        Table tab = TableSplitConfig.getConfig().getTable(name);
        if (ObjectUtils.isNotEmpty(tab)) {// 当前表具有分表配置
            Split split = tab.getSplit();
            if (ObjectUtils.isNotEmpty(split)) {
                String key = split.getKey();// 分表关键字
                String column = split.getColumn();//分表列名
                String implClass = split.getImplClass();// 分表转换实现类

                if (StringUtils.isBlank(key)) {
                    throw new TableSplitException("请检查分表配置信息（分表关键字key不能为空）");
                }

                if (StringUtils.isBlank(column)) {
                    throw new TableSplitException("请检查分表配置信息（分表列名column不能为空）");
                }

                if (StringUtils.isBlank(implClass)) {
                    throw new TableSplitException("请检查分表配置信息（分表分表转换实现类implClass不能为空）");
                }

                TableSplitEnum tableSplitEnum = (TableSplitEnum) EntityUtils.getEntity(TableSplitEnum.values(), DBConstants.TableSplitConstants.KEY, key);

                if (null != tableSplitEnum && !implClass.equals(tableSplitEnum.getImplClass())) {
                    throw new TableSplitException("请检查分表配置信息（分表转换实现类implClass非法）");
                }

                Column entityCol = (Column) EntityUtils.getEntity(entityCols, Column.COLUMN_TAB_COL_NAME, column);
                if (ObjectUtils.isEmpty(entityCol)) {
                    throw new TableSplitException("请检查分表配置信息（分表列名column不存在）");
                }
                // 获取分表转换实现类
                ITableSplit tableSplit = (ITableSplit) ReflectUtils.newInstance(implClass);
                // 设置转换后的表名
                realTableName = tableSplit.convert(name, entityCol.getAttrValue());
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The name of main table : {}", name);
            LOGGER.debug("The real name of table : {}", realTableName);
        }
        return realTableName;
    }
}
