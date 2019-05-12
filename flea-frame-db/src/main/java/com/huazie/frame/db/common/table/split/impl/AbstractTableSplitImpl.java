package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.table.split.ITableSplit;

import java.util.Date;

/**
 * <p> 抽象分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractTableSplitImpl implements ITableSplit {

    private static final long serialVersionUID = 142194326684219859L;

    /**
     * <p> 分表转换(指定日期格式化分表字段获取分表后缀) </p>
     *
     * @param tableSplitColumn 分表字段
     * @param dateFormatEnum   日期格式化类型枚举
     * @return 分表后缀名
     * @throws TableSplitException 分表转换异常类
     */
    protected String convert(Object tableSplitColumn, DateFormatEnum dateFormatEnum) throws TableSplitException {
        String tSplitPrefix = null;
        if (ObjectUtils.isEmpty(tableSplitColumn)) {
            tSplitPrefix = DateUtils.date2String(null, dateFormatEnum);
        }
        if (tableSplitColumn instanceof Date) {
            tSplitPrefix = DateUtils.date2String((Date) tableSplitColumn, dateFormatEnum);
        }
        if (StringUtils.isBlank(tSplitPrefix)) {
            // 获取【{0}】分表后缀异常
            throw new TableSplitException("ERROR-DB-TSP0000000001", dateFormatEnum.getFormat());
        }
        return tSplitPrefix;
    }

    /**
     * <p> 分表后缀转换(截取分表字段后len位得到) </p>
     *
     * @param tableSplitColumn 分表字段
     * @param len              分表后缀长度
     * @return 分表后缀名
     * @throws TableSplitException 分表转换异常类
     */
    protected String convert(Object tableSplitColumn, int len) throws TableSplitException {
        if (ObjectUtils.isEmpty(tableSplitColumn)) {
            // 分表列名字段不能为空
            throw new TableSplitException("ERROR-DB-TSP0000000003");
        }
        String tSplitCol = tableSplitColumn.toString();
        if (StringUtils.isBlank(tSplitCol)) {
            // 分表列名字段不能为空
            throw new TableSplitException("ERROR-DB-TSP0000000003");
        }
        String tSplitPrefix = StringUtils.subStrLast(tSplitCol, len);
        return tSplitPrefix.toLowerCase();
    }
}
