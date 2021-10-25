package com.huazie.frame.common;

import com.huazie.frame.common.util.FleaMap;

import java.util.Map;

/**
 * <p> Flea 通用配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCommonConfig extends FleaMap {

    private static final long serialVersionUID = -3199209255176650293L;

    /**
     * <p> 获取配置数据Map </p>
     *
     * @return 配置数据Map
     * @since 1.0.0
     */
    public Map<String, Object> getConfig() {
        return getFleaMap();
    }

}
