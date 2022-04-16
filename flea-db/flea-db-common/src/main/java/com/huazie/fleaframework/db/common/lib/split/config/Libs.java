package com.huazie.fleaframework.db.common.lib.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 分库配置集合定义，参考【flea-lib-split.xml】中
 * {@code <libs> </libs>}。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.1.0
 */
public class Libs extends LibMap {

    private List<Lib> libList = new ArrayList<>();

    @Override
    public List<Lib> getLibList() {
        return libList;
    }

    public void addLib(Lib lib) {
        libList.add(lib);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
