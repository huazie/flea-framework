package com.huazie.frame.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> 分表后缀配置列表 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Splits {

    private List<Split> splitList = new ArrayList<Split>();

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
