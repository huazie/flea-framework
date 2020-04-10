package com.huazie.frame.core.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> flea-request.xml 配置 {@code <flea-request> </flea-request>} 节点 </p>
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
