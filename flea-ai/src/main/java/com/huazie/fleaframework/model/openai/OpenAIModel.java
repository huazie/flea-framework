package com.huazie.fleaframework.model.openai;

import com.huazie.fleaframework.model.Model;

public class OpenAIModel implements Model {
    @Override
    public String predict(String input) {
        // OpenAI API 调用逻辑
        return callOpenAIAPI(input);
    }

    private String callOpenAIAPI(String input) {
        // 这里调用 OpenAI 的实际 API，并返回结果
        return "OpenAI response for input: " + input;
    }
}
