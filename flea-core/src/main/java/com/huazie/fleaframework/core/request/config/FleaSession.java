package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea 会话，在配置文件 <b>flea-request.xml</b>
 * 中查看 {@code <flea-session>} 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaSession {

    public static final String SESSION_ACTIVE_TIME = "SESSION_ACTIVE_TIME"; // Session激活时间（业务请求重置改时间）

    private String userSessionKey; // 用户SESSION信息键

    private String idleTime; // 用户SESSION空闲保持时间（单位：s）

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
