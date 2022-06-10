package com.huazie.fleaframework.db.common.lib.split.config;

import com.huazie.fleaframework.common.util.xml.ImportList;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea分库定义，对应【flea-lib-split.xml】中
 * 【{@code <flea-lib-split> <flea-lib-split>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaLibSplit extends ImportList {

    private Libs libs; // 分库配置集

    private LibFiles libFiles; // 分库配置文件集

    public Libs getLibs() {
        return libs;
    }

    public void setLibs(Libs libs) {
        this.libs = libs;
    }

    public LibFiles getLibFiles() {
        return libFiles;
    }

    public void setLibFiles(LibFiles libFiles) {
        this.libFiles = libFiles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
