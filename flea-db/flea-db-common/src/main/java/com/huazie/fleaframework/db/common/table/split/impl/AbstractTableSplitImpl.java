package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.exceptions.TableSplitException;
import com.huazie.fleaframework.db.common.table.split.ITableSplit;

import java.util.Date;

/**
 * 抽象分表转换实现，实现分表转换接口，封装公共的分表转换处理实现逻辑
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractTableSplitImpl implements ITableSplit {

    /**
     * 分表转换 (指定日期格式化分表字段获取分表后缀)
     *
     * @param tableSplitColumn 分表字段
     * @param dateFormatEnum   日期格式化类型枚举
     * @return 分表转换值
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
            ObjectUtils.checkNotEmpty(tableSplitColumn, TableSplitException.class, "ERROR-DB-TSP0000000002");
        }
        // 获取【{0}】分表后缀异常
        StringUtils.checkBlank(tSplitPrefix, TableSplitException.class, "ERROR-DB-TSP0000000001", dateFormatEnum.getFormat());
        return tSplitPrefix;
    }

    /**
     * 分表转换 (截取分表字段前面或后面len位得到)
     *
     * @param tableSplitColumn 分表字段
     * @param len              分表后缀长度
     * @return 分表转换值
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    protected String convert(Object tableSplitColumn, int len, boolean isBefore, boolean isUpperCase) throws CommonException {

        // 分表属性列值不能为空
        ObjectUtils.checkEmpty(tableSplitColumn, TableSplitException.class, "ERROR-DB-TSP0000000003");

        String tSplitCol = tableSplitColumn.toString();
        // 分表属性列值不能为空
        StringUtils.checkBlank(tSplitCol, TableSplitException.class, "ERROR-DB-TSP0000000003");

        if (len <= 0) {
            len = tSplitCol.length();
        }

      String tSplitPrefix;
        if (isBefore) {
            tSplitPrefix = StringUtils.subStrBefore(tSplitCol, len);
        } else {
            tSplitPrefix = StringUtils.subStrLast(tSplitCol, len);
        }
        if (isUpperCase) {
            return tSplitPrefix.toUpperCase();
        } else {
            return tSplitPrefix.toLowerCase();
        }
    }

    /**
     * 奇偶分表转换的通用实现逻辑
     *
     * @param tableSplitColumn 分表字段
     * @param radix            基数
     * @return 分库转换值
     * @throws CommonException 通用异常
     * @since 1.2.0
     */
    protected int convertCommon(Object tableSplitColumn, int radix) throws CommonException {
        // 分表属性列值不能为空
        ObjectUtils.checkEmpty(tableSplitColumn, TableSplitException.class, "ERROR-DB-TSP0000000003");

        String tSplitCol = tableSplitColumn.toString();
        // 分表属性列值不能为空
        StringUtils.checkBlank(tSplitCol, TableSplitException.class, "ERROR-DB-TSP0000000003");

        String lastChar = tSplitCol.substring(tSplitCol.length() - CommonConstants.NumeralConstants.INT_ONE);
        return Integer.parseInt(lastChar, radix) % CommonConstants.NumeralConstants.INT_TWO;
    }
}
