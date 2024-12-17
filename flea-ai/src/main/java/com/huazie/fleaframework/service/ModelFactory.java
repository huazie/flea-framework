package com.huazie.fleaframework.service;

import com.huazie.fleaframework.model.Model;
import com.huazie.fleaframework.model.openai.OpenAIModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Service
public class ModelFactory {

    @Value("${flea.ai.model.type}") // 从配置文件读取模型类型
    private String modelType;

    /**
     * 根据配置文件中的模型类型返回相应的模型实现
     */
    public Model getModel() {
        switch (modelType.toLowerCase()) {
            case "openai":
                return new OpenAIModel();
//            case "gpt":
//                return new GPTModel();
            default:
                throw new IllegalArgumentException("Unknown model type: " + modelType);
        }
    }
}
