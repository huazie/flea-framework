package com.huazie.fleaframework.core.aliyun;

import com.google.gson.Gson;
import com.huazie.fleaframework.common.BaiLianCom;
import com.huazie.fleaframework.config.aliyun.BaiLianAIConfig;
import com.huazie.fleaframework.core.AIModelCore;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
        String url = baiLianAIConfig.getBaseUrl();
        String model = baiLianAIConfig.getModelId();

        String respon = null;

        try {

           /* BaiLianCom.Message[] messages = {
                    new BaiLianCom.Message("user", prompt)};

            // 将请求体转换为 JSON
            Gson gson = new Gson();
            String messagestr = gson.toJson(messages);

            //请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + baiLianAIConfig.getApiKey());

            // 创建请求体
            Map<String, String> payload = new HashMap<>();
            payload.put("model", model);
            payload.put("message", messagestr);
            payload.put("stream", "true");

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
        }*/


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

            // 创建 URL 对象
            URL url2 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();

            // 设置请求方法为 POST
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            // 若没有配置环境变量，请用百炼API Key将下行替换为：String apiKey = "sk-xxx";
            String apiKey = baiLianAIConfig.getApiKey();
            String auth = "Bearer " + apiKey;
            httpURLConnection.setRequestProperty("Authorization", auth);

            // 启用输入输出流
            httpURLConnection.setDoOutput(true);

            // 写入请求体
            try (OutputStream os = httpURLConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 获取响应码
            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode != 200){
                // 处理错误响应
                throw new RuntimeException("API调用失败: Response Code: " + responseCode);
            }

            // 读取响应体
            try (BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response Body: " + response);
                respon = response.toString();
            }
            JSONObject jsonObject = new JSONObject(respon);
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            JSONObject firstChoice = choicesArray.getJSONObject(0);
            JSONObject messageObject = firstChoice.getJSONObject("message");
            respon = messageObject.getString("content");
            System.out.println(respon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respon;
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
