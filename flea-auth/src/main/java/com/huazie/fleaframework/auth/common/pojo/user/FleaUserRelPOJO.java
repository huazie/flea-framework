package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea用户关联POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaUserRelPOJO extends FleaAuthRelPOJO {

    private static final long serialVersionUID = 3014742508622796686L;

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
