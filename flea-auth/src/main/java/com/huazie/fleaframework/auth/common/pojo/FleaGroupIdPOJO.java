package com.huazie.fleaframework.auth.common.pojo;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;

/**
 * Flea组编号POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaGroupIdPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = 5628174424793369617L;

    private Long groupId; // 组编号

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
