package com.huazie.fleaframework.auth.common.pojo.user.attr;

import com.huazie.fleaframework.auth.common.pojo.FleaAttrPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 用户属性POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserAttrPOJO extends FleaAttrPOJO {

    private static final long serialVersionUID = -6461714250374550778L;

    private Long userId; // 用户编号

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
