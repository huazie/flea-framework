package com.huazie.fleaframework.common;

import com.huazie.fleaframework.common.util.FleaMap;

import java.util.Map;

/**
 * Flea 通用配置类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCommonConfig extends FleaMap {

    private static final long serialVersionUID = -3199209255176650293L;

    /**
     * 获取配置数据Map
     *
     * @return 配置数据Map
     * @since 1.0.0
     */
    public Map<String, Object> getConfig() {
        return getFleaMap();
    }

}
