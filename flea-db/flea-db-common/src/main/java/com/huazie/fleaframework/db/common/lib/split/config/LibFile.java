package com.huazie.fleaframework.db.common.lib.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他分库配置文件，对应【flea-lib-split.xml】中
 * 【{@code <lib-file> </lib-file>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class LibFile extends LibMap {

    private String location; // 其他分库配置文件路径

    private List<Lib> libList = new ArrayList<>(); // 其他分库配置文件中定义的分库配置定义集合

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLibList(List<Lib> libList) {
        this.libList = libList;
    }

    @Override
    public List<Lib> getLibList() {
        return libList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
