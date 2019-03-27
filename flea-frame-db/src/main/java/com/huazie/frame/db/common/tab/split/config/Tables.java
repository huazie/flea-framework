package com.huazie.frame.db.common.tab.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> 分表集合类 </p>
 *
 * @author huazie
 * @version v1.0.0
 * @since 1.0.0
 */
public class Tables implements Serializable {

    private static final long serialVersionUID = 3209329482179720497L;

    private List<Table> tableList = new ArrayList<Table>();

    public List<Table> getTableList() {
        return tableList;
    }

    public Table[] getTableArray() {
        return tableList.toArray(new Table[0]);
    }

    /**
     * <p> 获取分表定义相关属性集合 </p>
     *
     * @return 分表定义相关属性集合
     * @since 1.0.0
     */
    public Map<String, Table> toTableMap() {
        Map<String, Table> tableMap = new HashMap<String, Table>();
        Iterator<Table> tableIt = tableList.iterator();
        while (tableIt.hasNext()) {
            Table table = tableIt.next();
            tableMap.put(table.getName(), table);
        }
        return tableMap;
    }

    public void addTable(Table table) {
        tableList.add(table);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
