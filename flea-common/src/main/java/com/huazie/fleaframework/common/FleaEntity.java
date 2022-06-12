package com.huazie.fleaframework.common;

import com.huazie.fleaframework.common.util.FleaMap;

import java.util.Map;

/**
 * 通用实体父类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaEntity extends FleaMap {

    private static final long serialVersionUID = 1828296410313065859L;

    /**
     * 获取实体数据Map
     *
     * @return 实体数据Map
     * @since 1.1.0
     */
    public Map<String, Object> getEntityMap() {
        return getFleaMap();
    }
}
