package com.huazie.fleaframework.config.openai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "flea.ai.openai")
public class OpenAIConfig {

    private String apiKey;

    // 定义大模型API的基础URL
    @Value("${flea.ai.openai.base-url:https://api.openai.com}")
    private String baseUrl;

    @Value("${flea.ai.openai.model-id:gpt-3.5-turbo}")
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
