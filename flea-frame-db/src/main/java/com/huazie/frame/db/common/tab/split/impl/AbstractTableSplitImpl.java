package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.tab.split.ITableSplit;

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
     * @param tableName        分表对应主表名
     * @param tableSplitColumn 分表字段
     * @param dateFormatEnum   日期格式化类型枚举
     * @return 分表名
     * @throws TableSplitException 分表转换异常类
     */
    protected String convert(String tableName, Object tableSplitColumn, DateFormatEnum dateFormatEnum) throws TableSplitException {
        String tSplitPrefix = null;
        if (ObjectUtils.isEmpty(tableSplitColumn)) {
            tSplitPrefix = DateUtils.date2String(null, dateFormatEnum);
        }
        if (tableSplitColumn instanceof Date) {
            tSplitPrefix = DateUtils.date2String((Date) tableSplitColumn, dateFormatEnum);
        }
        if (StringUtils.isBlank(tSplitPrefix)) {
            throw new TableSplitException("获取" + dateFormatEnum.getFormat() + "分表后缀异常");
        }
        return StringUtils.strCat(tableName, DBConstants.SQLConstants.SQL_UNDERLINE, tSplitPrefix);
    }

    /**
     * <p> 分表转换(截取分表字段后len位得到) </p>
     *
     * @param tableName        分表对应主表名
     * @param tableSplitColumn 分表字段
     * @param len              分表后缀长度
     * @return 分表名
     * @throws TableSplitException 分表转换异常类
     */
    protected String convert(String tableName, Object tableSplitColumn, int len) throws TableSplitException {
        if (StringUtils.isBlank(tableName)) {
            throw new TableSplitException("分表对应主表名不能为空");
        }
        if (ObjectUtils.isEmpty(tableSplitColumn)) {
            throw new TableSplitException("分表字段不能为空");
        }
        String tSplitCol = tableSplitColumn.toString();
        if (StringUtils.isBlank(tSplitCol)) {
            throw new TableSplitException("分表字段不能为空");
        }
        String tSplitPrefix = StringUtils.subStrLast(tSplitCol, len);
        return StringUtils.strCat(tableName, DBConstants.SQLConstants.SQL_UNDERLINE, tSplitPrefix.toLowerCase());
    }
}
