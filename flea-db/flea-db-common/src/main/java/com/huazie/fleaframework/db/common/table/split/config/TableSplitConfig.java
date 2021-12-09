package com.huazie.fleaframework.db.common.table.split.config;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBXmlDigesterHelper;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分表配置类，参考分表配置文件 flea-table-split.xml
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TableSplitConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(TableSplitConfig.class);

    private static volatile TableSplitConfig config;

    private Tables tables;    //分表集合类

    private TableSplitConfig() throws CommonException {
        this.tables = DBXmlDigesterHelper.getInstance().getTables();
    }

    /**
     * <p> 获取分表配置信息实例对象 </p>
     *
     * @return 分表配置信息实例对象
     * @since 1.0.0
     */
    public static TableSplitConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (TableSplitConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    try {
                        config = new TableSplitConfig();
                    } catch (Exception e) {
                        if (LOGGER.isErrorEnabled()) {
                            LOGGER.error("Fail to init flea-table-split.xml", e);
                        }
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
        return tables.toTableMap().get(name);
    }

    public Tables getTables() {
        return tables;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}