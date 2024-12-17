package com.huazie.fleaframework.config.aliyun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "flea.ai.aliyun")
public class BaiLianAIConfig {

    @Value("${flea.ai.aliyun.api-key}")
    private String apiKey;

    // 定义大模型API的基础URL
    @Value("${flea.ai.aliyun.base-url:https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions}")
    private String baseUrl;

    @Value("${flea.ai.aliyun.model-id:qwen1.5-110b-chat}")
    private String modelId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}

