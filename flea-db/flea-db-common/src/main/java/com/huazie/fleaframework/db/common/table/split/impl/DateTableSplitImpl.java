package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 日期格式化分表实现类，通过日期格式化类型参数化，替代此前
 * YYYY / YYYYMM / YYYYMMDD 三个独立实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class DateTableSplitImpl extends AbstractTableSplitImpl {

    private final DateFormatEnum dateFormatEnum; // 分表后缀使用的日期格式化类型

    /**
     * 构造方法
     *
     * @param dateFormatEnum 分表后缀使用的日期格式化类型
     * @since 2.0.0
     */
    public DateTableSplitImpl(DateFormatEnum dateFormatEnum) {
        this.dateFormatEnum = dateFormatEnum;
    }

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, dateFormatEnum);
    }
}
