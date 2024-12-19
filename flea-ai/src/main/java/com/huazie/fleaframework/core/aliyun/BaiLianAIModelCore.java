package com.huazie.fleaframework.core.aliyun;

import com.google.gson.Gson;
import com.huazie.fleaframework.common.BaiLianCom;
import com.huazie.fleaframework.config.aliyun.BaiLianAIConfig;
import com.huazie.fleaframework.core.AIModelCore;
import io.micrometer.common.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class BaiLianAIModelCore implements AIModelCore {

    private final BaiLianAIConfig baiLianAIConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public BaiLianAIModelCore(BaiLianAIConfig baiLianAIConfig, RestTemplate restTemplate) {
        this.baiLianAIConfig = baiLianAIConfig;
        this.restTemplate = restTemplate;
    }

    @Override
    public String generateText(String prompt) {
        String url = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
        String model = baiLianAIConfig.getModelId();
        String apiKey = baiLianAIConfig.getApiKey();

        String respon = null;

        try {
            // 创建请求体
            BaiLianCom.RequestBody requestBody = new BaiLianCom.RequestBody(
                    model,
                    new BaiLianCom.Message[]{
                            new BaiLianCom.Message("user", prompt)
                    },
                    false//流式输出
            );

            // 将请求体转换为 JSON
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 创建HTTP请求
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

            // 发送POST请求
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // 处理响应
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("API调用失败: Response StatusCode: " + response.getStatusCode());
            }
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            JSONObject firstChoice = choicesArray.getJSONObject(0);
            JSONObject messageObject = firstChoice.getJSONObject("message");
            respon = messageObject.getString("content");
            System.out.println(respon);
            return respon;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String generateImage(String prompt) {
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text2image/image-synthesis";
        String model = "wanx-v1";
        String apiKey = baiLianAIConfig.getApiKey();

        try {
            // 构建请求体

            BaiLianCom.RequestBody requestBody = new BaiLianCom.RequestBody(
                    model,
                    new BaiLianCom.input(prompt)
            );

            // 将请求体转换为 JSON
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(requestBody);
            System.out.println(jsonInputString);
            // 创建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-DashScope-Async", "enable");//X-DashScope-Async: enable 异步处理

            // 创建HTTP请求
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

            // 发送POST请求
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // 处理响应
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("getTaskId响应成功！响应内容output：" + response.getBody());

                JSONObject jsonObject = new JSONObject(response.getBody());
                JSONObject output = jsonObject.getJSONObject("output");
                String taskId = output.get("task_id").toString();
                String getUrl = "https://dashscope.aliyuncs.com/api/v1/tasks/" + taskId;

                // 轮询任务状态，直到任务完成
                int retries = 0;
                while (true) {
                    String responseBody = getTaskStatus(getUrl, apiKey);
                    if (responseBody != null) {
                        if (isTaskCompleted(responseBody)) {
                            System.out.println("Task is completed!");
                            JSONObject jsonObjectfinal = new JSONObject(responseBody);
                            JSONObject outputfinal = jsonObjectfinal.getJSONObject("output");
                            JSONArray results = outputfinal.getJSONArray("results");
                            JSONObject result = results.getJSONObject(0);
                            String imgUrl = result.get("url").toString();
                            System.out.println("图像url：" + imgUrl);
                            return imgUrl;
                        } else {
                            System.out.println("Task is still pending...");
                        }
                    } else {
                        System.out.println("Error fetching task status. Retrying...");
                    }

                    retries++;
                    if (retries >= 20) {  // 设置最大重试次数
                        System.out.println("Max retries reached. Stopping polling.");
                        break;
                    }

                    try {
                        Thread.sleep(10000);  // 每 10 秒检查一次任务状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //查询任务状态的 API
    public String getTaskStatus(String url, String apiKey) {
        try {
            HttpHeaders getHeaders = new HttpHeaders();
            getHeaders.set("Authorization", "Bearer " + apiKey);
            // 创建HTTP请求
            HttpEntity<String> requestEntityfinal = new HttpEntity<>(getHeaders);

            // 发送GET请求
            ResponseEntity<String> responsefinal = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntityfinal,
                    String.class
            );
            if (responsefinal.getStatusCode() == HttpStatus.OK) {
                return responsefinal.getBody();  // 返回 JSON 字符串
            } else {
                System.err.println("Failed to get task status, HTTP code: " + responsefinal.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isTaskCompleted(String responseBody) {
        if (StringUtils.isEmpty(responseBody)) return false;

        JSONObject jsonObject = new JSONObject(responseBody);  // 使用 org.json 解析 JSON
        String taskStatus = jsonObject.getJSONObject("output").getString("task_status");

        return taskStatus.equals("SUCCEEDED");

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
