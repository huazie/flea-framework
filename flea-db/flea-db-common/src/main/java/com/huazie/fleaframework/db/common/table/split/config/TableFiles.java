package com.huazie.fleaframework.db.common.table.split.config;

import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他分表配置文件集，对应【flea-table-split.xml】中
 * 【{@code <table-files> </table-files>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class TableFiles {

    private List<TableFile> tableFiles = new ArrayList<>();

    public List<TableFile> getTableFiles() {
        return tableFiles;
    }

    /**
     * 添加一个分表配置文件
     *
     * @param tableFile 分表配置文件
     * @since 2.0.0
     */
    public void addTableFile(TableFile tableFile) {
        tableFiles.add(tableFile);
    }

    /**
     * 根据主表名获取指定的分表配置定义
     *
     * @param name 主表名
     * @return 分表配置定义
     * @since 2.0.0
     */
    public Table getFleaTable(String name) {
        Table table = null;
        if (CollectionUtils.isNotEmpty(tableFiles)) {
            for (TableFile tableFile : tableFiles) {
                if (ObjectUtils.isNotEmpty(tableFile)) {
                    table = tableFile.getFleaTable(name);
                    if (ObjectUtils.isNotEmpty(table)) {
                        // 取到指定非空的缓存定义配置，则跳出循环
                        break;
                    }
                }
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
