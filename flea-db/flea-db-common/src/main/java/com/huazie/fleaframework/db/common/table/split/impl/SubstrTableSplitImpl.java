package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 截取分表实现类，通过截取长度、截取方向与大小写参数化，替代此前
 * 位长（全列/1位/2位）× 方向（前/后）× 大小写组合出的各个独立实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class SubstrTableSplitImpl extends AbstractTableSplitImpl {

    private final int len; // 分表后缀长度，小于等于 0 表示取全列

    private final boolean before; // 是否从分表字段前面截取

    private final boolean upperCase; // 是否以大写返回

    /**
     * 构造方法
     *
     * @param len       分表后缀长度，小于等于 0 表示取全列
     * @param before    是否从分表字段前面截取
     * @param upperCase 是否以大写返回
     * @since 2.0.0
     */
    public SubstrTableSplitImpl(int len, boolean before, boolean upperCase) {
        this.len = len;
        this.before = before;
        this.upperCase = upperCase;
    }

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, len, before, upperCase);
    }
}
