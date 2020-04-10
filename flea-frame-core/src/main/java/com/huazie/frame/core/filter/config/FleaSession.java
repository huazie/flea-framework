package com.huazie.frame.core.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> flea-request.xml 配置 {@code <flea-session> </flea-session>} 节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaSession {

    private String userSessionKey; // 用户SESSION信息键

    private String idleTime; // 用户SESSION空闲保持时间（单位：秒）

    public String getUserSessionKey() {
        return userSessionKey;
    }

    public void setUserSessionKey(String userSessionKey) {
        this.userSessionKey = userSessionKey;
    }

    public String getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(String idleTime) {
        this.idleTime = idleTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
