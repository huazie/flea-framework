package com.huazie.fleaframework.db.common.lib.split.config;

import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他分库配置文件集，对应【flea-lib-split.xml】中
 * 【{@code <lib-files> </lib-files>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class LibFiles {

    private List<LibFile> libFiles = new ArrayList<>();

    public List<LibFile> getLibFiles() {
        return libFiles;
    }

    /**
     * 添加一个分库配置文件
     *
     * @param libFile 分库配置文件
     * @since 2.0.0
     */
    public void addLibFile(LibFile libFile) {
        libFiles.add(libFile);
    }

    /**
     * 根据模板表名获取指定的分库配置定义
     *
     * @param name 模板表名
     * @return 分库配置定义
     * @since 2.0.0
     */
    public Lib getFleaLib(String name) {
        Lib lib = null;
        if (CollectionUtils.isNotEmpty(libFiles)) {
            for (LibFile libFile : libFiles) {
                if (ObjectUtils.isNotEmpty(libFile)) {
                    lib = libFile.getFleaLib(name);
                    if (ObjectUtils.isNotEmpty(lib)) {
                        // 取到指定非空的分库配置，则跳出循环
                        break;
                    }
                }
            }
        }
        return lib;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
