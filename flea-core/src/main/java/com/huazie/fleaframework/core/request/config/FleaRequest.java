package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea 请求，在配置文件 <b>flea-request.xml</b>
 * 中查看 {@code <flea-request> } 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequest {

    private FleaSession fleaSession; // Flea SESSION信息

    private FleaUrl fleaUrl; // Flea URL相关配置

    public FleaSession getFleaSession() {
        return fleaSession;
    }

    public void setFleaSession(FleaSession fleaSession) {
        this.fleaSession = fleaSession;
    }

    public FleaUrl getFleaUrl() {
        return fleaUrl;
    }

    public void setFleaUrl(FleaUrl fleaUrl) {
        this.fleaUrl = fleaUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
