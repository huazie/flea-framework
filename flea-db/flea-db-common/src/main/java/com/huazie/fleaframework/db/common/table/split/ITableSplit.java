package com.huazie.fleaframework.db.common.table.split;

import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 分表转换接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ITableSplit {

    /**
     * 分表转换
     *
     * @param tableSplitColumn 分表字段
     * @return 分表转换值
     * @throws CommonException 通用异常
     */
    String convert(Object tableSplitColumn) throws CommonException;
}
