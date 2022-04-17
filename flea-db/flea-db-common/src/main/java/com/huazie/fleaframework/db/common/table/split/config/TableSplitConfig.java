package com.huazie.fleaframework.db.common.table.split.config;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBXmlDigesterHelper;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分表配置类，参考分表配置文件 flea-table-split.xml
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class TableSplitConfig {

    private static volatile TableSplitConfig config;

    private FleaTableSplit fleaTableSplit;    // Flea分表定义类

    private TableSplitConfig() {
        this.fleaTableSplit = DBXmlDigesterHelper.getInstance().getFleaTableSplit();
    }

    /**
     * 获取分表配置信息实例对象
     *
     * @return 分表配置信息实例对象
     * @since 1.0.0
     */
    public static TableSplitConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (TableSplitConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new TableSplitConfig();
                }
            }
        }
        return config;
    }

    /**
     * 根据name获取指定的分表配置信息
     *
     * @param name 模板表名
     * @return 分表配置信息
     * @since 1.0.0
     */
    public Table getTable(String name) {
        Table table = null;
        if (ObjectUtils.isNotEmpty(fleaTableSplit)) {
            Tables tables = fleaTableSplit.getTables();
            if (ObjectUtils.isNotEmpty(tables)) {
                // 从主分表配置文件中获取指定模板表名的分表配置
                table = tables.getFleaTable(name);
                if (ObjectUtils.isEmpty(table)) {
                    // 从其他配置文件中获取指定模板表名的分表配置
                    TableFiles tableFiles = fleaTableSplit.getTableFiles();
                    if (ObjectUtils.isNotEmpty(tableFiles)) {
                        table = tableFiles.getFleaTable(name);
                    }
                }
            }
        }
        return table;
    }

    public FleaTableSplit getFleaTableSplit() {
        return fleaTableSplit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
