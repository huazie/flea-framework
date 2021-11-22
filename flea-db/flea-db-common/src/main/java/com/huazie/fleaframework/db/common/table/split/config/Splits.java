package com.huazie.fleaframework.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 分表转换或分库转换配置集定义，参考 flea-table-split.xml 或
 * flea-lib-split.xml 中 {@code <splits></splits>}
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Splits {

    private List<Split> splitList = new ArrayList<>();

    public List<Split> getSplitList() {
        return splitList;
    }

    public Split[] getSplitArray() {
        return splitList.toArray(new Split[0]);
    }

    public void addSplit(Split split) {
        splitList.add(split);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
