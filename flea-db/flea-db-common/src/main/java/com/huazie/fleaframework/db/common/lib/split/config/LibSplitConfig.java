package com.huazie.fleaframework.db.common.lib.split.config;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBXmlDigesterHelper;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分库配置类，参考 分库配置文件 flea-lib-split.xml
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class LibSplitConfig {

    private static volatile LibSplitConfig config;

    private FleaLibSplit fleaLibSplit; // Flea分库定义

    private LibSplitConfig() {
        this.fleaLibSplit = DBXmlDigesterHelper.getInstance().getFleaLibSplit();
    }

    /**
     * 获取分表配置类实例对象
     *
     * @return 分表配置类实例对象
     * @since 1.0.0
     */
    public static LibSplitConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (LibSplitConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new LibSplitConfig();
                }
            }
        }
        return config;
    }

    /**
     * 根据模板库名获取对应的分库配置定义
     *
     * @param name 模板库名
     * @return 分库配置定义
     * @since 1.1.0
     */
    public Lib getLib(String name) {
        Lib lib = null;
        if (ObjectUtils.isNotEmpty(fleaLibSplit)) {
            Libs libs = fleaLibSplit.getLibs();
            // 从主分库配置文件中获取指定模板库名的分库配置
            if (ObjectUtils.isNotEmpty(libs)) {
                lib = libs.getFleaLib(name);
                if (ObjectUtils.isEmpty(lib)) {
                    LibFiles libFiles = fleaLibSplit.getLibFiles();
                    if (ObjectUtils.isNotEmpty(libFiles)) {
                        // 从其他分库配置文件中获取指定模板库名的分库配置
                        lib = libFiles.getFleaLib(name);
                    }
                }
            }
        }
        return lib;
    }

    public FleaLibSplit getFleaLibSplit() {
        return fleaLibSplit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
