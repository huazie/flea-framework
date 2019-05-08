package com.huazie.frame.db.common.table.split.config;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.db.common.XmlDigesterHelper;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p> 分表配置信息 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(TableSplitConfig.class);

    private static volatile TableSplitConfig config;

    private Tables tables;    //分表集合类

    private TableSplitConfig(Tables tables) {
        this.tables = tables;
    }

    /**
     * <p> 获取分表配置信息实例对象 </p>
     * @return 分表配置信息实例对象
     * @since 1.0.0
     */
    public static TableSplitConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (TableSplitConfig.class){
                if(ObjectUtils.isEmpty(config)){
                    try {
                        config = new TableSplitConfig(XmlDigesterHelper.getInstance().getTables());
                    } catch (Exception e) {
                        LOGGER.debug("Fail to init flea-table-split.xml");
                    }
                }
            }

        }
        return config;
    }

    /**
     * <p> 根据name获取指定的分表配置信息 </p>
     *
     * @param name 主表名
     * @return 分表配置信息
     * @since 1.0.0
     */
    public Table getTable(String name) {
        Map<String, Table> tableMap = tables.toTableMap();
        Table table = tableMap.get(name);
        return table;
    }

    public Tables getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
