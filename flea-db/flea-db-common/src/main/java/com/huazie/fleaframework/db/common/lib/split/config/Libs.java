package com.huazie.fleaframework.db.common.lib.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分库配置集合定义，参考 flea-lib-split.xml 中 {@code <libs></libs>}
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class Libs {

    private List<Lib> libList = new ArrayList<>();

    public List<Lib> getLibList() {
        return libList;
    }

    public Lib[] getLibArray() {
        return libList.toArray(new Lib[0]);
    }

    /**
     * <p> 获取各分表配置定义 </p>
     *
     * @return 各分表配置定义集合
     * @since 1.0.0
     */
    Map<String, Lib> toLibMap() {
        Map<String, Lib> libMap = new HashMap<>();
        for (Lib lib : libList) {
            libMap.put(lib.getName(), lib);
        }
        return libMap;
    }

    public void addLib(Lib lib) {
        libList.add(lib);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
