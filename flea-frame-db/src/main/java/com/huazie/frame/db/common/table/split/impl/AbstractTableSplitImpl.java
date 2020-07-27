package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.ExceptionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
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
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected String convert(Object tableSplitColumn, DateFormatEnum dateFormatEnum) throws CommonException {
        String tSplitPrefix = "";
        if (ObjectUtils.isEmpty(tableSplitColumn)) {
            tSplitPrefix = DateUtils.date2String(null, dateFormatEnum);
        }
        if (StringUtils.isBlank(tSplitPrefix) && tableSplitColumn instanceof Date) {
            tSplitPrefix = DateUtils.date2String((Date) tableSplitColumn, dateFormatEnum);
        } else {
            // 分表属性列必须是日期类型
            ExceptionUtils.throwCommonException(TableSplitException.class, "ERROR-DB-TSP0000000002");
        }
        // 获取【{0}】分表后缀异常
        StringUtils.checkBlank(tSplitPrefix, TableSplitException.class, "ERROR-DB-TSP0000000001", dateFormatEnum.getFormat());
        return tSplitPrefix;
    }

    /**
     * <p> 分表后缀转换(截取分表字段后len位得到) </p>
     *
     * @param tableSplitColumn 分表字段
     * @param len              分表后缀长度
     * @return 分表后缀名
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected String convert(Object tableSplitColumn, int len) throws CommonException {

        // 分表属性列值不能为空
        ObjectUtils.checkEmpty(tableSplitColumn, TableSplitException.class, "ERROR-DB-TSP0000000003");

        String tSplitCol = tableSplitColumn.toString();
        // 分表属性列值不能为空
        StringUtils.checkBlank(tSplitCol, TableSplitException.class, "ERROR-DB-TSP0000000003");

        String tSplitPrefix = StringUtils.subStrLast(tSplitCol, len);
        return tSplitPrefix.toLowerCase();
    }
}
