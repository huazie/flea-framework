package com.huazie.fleaframework.core.openai;

import com.huazie.fleaframework.common.OpenAiApi.ChatRequest;
import com.huazie.fleaframework.config.openai.OpenAIConfig;
import com.huazie.fleaframework.core.AIModelCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OpenAIModelCore implements AIModelCore {

    private final OpenAIConfig openAIConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public OpenAIModelCore(OpenAIConfig openAIConfig, RestTemplate restTemplate) {
        this.openAIConfig = openAIConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public Flux<String> genetateText4Stream(ChatRequest chatRequest) {
        return null;
    }

    @Override
    public String generateText(ChatRequest chatRequest) {
        String url = openAIConfig.getBaseUrl() + "/v1/completions";

        // 使用安全的JSON序列化方法
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + openAIConfig.getApiKey()); // 假设API密钥是通过这种方式传递的

            // 创建请求体
            Map<String, String> payload = new HashMap<>();
            payload.put("model", openAIConfig.getModelId());
            //payload.put("prompt", prompt);

            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);

            // 发送请求并获取响应
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // 检查响应状态码并处理响应体（这里假设响应体是一个字符串，但通常可能需要解析为更复杂的对象）
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                // 处理错误响应
                throw new RuntimeException("API调用失败: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            // 处理异常（如网络错误、JSON序列化错误等）
            throw new RuntimeException("生成文本时发生错误", e);
        }
    }

    @Override
    public String generateImage(String prompt) {
        return null;
    }

    @Override
    public String transcribeAudio(byte[] audioData) {
        return null;
    }

    @Override
    public List<Float> generateEmbedding(String input) {
        return null;
    }
}
