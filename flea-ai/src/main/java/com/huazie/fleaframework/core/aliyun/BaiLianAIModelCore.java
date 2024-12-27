package com.huazie.fleaframework.core.aliyun;

import com.google.gson.Gson;
import com.huazie.fleaframework.common.BaiLianRequest;
import com.huazie.fleaframework.common.OpenAiApi;
import com.huazie.fleaframework.common.OpenAiApi.ChatRequest;
import com.huazie.fleaframework.common.util.json.FastJsonUtils;
import com.huazie.fleaframework.config.aliyun.BaiLianAIConfig;
import com.huazie.fleaframework.core.AIModelCore;
import com.huazie.fleaframework.util.FunctionUtil;
import io.micrometer.common.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Component
public class BaiLianAIModelCore implements AIModelCore {

    private final BaiLianAIConfig baiLianAIConfig;
    private final RestTemplate restTemplate;

    static List<ChatRequest.Message> historyMessage = new ArrayList<>();

    @Autowired
    public BaiLianAIModelCore(BaiLianAIConfig baiLianAIConfig, RestTemplate restTemplate) {
        this.baiLianAIConfig = baiLianAIConfig;
        this.restTemplate = restTemplate;
    }


    //非流式输出
    @Override
    public String generateText(ChatRequest chatRequest) {
        String url = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
        String model = baiLianAIConfig.getModelId();
        String apiKey = baiLianAIConfig.getApiKey();


        try {
            String respon;
            ChatRequest chatRequestNew = chatRequest;
            if (StringUtils.isEmpty(chatRequest.getModel())) {
                chatRequestNew = chatRequestNew.convertBuilder().model(model).build();
            }

            // 将请求体转换为 JSON
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(chatRequestNew);

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

            if (StringUtils.isEmpty(response.getBody())) {
                throw new Exception("body is null");
            }
            System.out.println(response.getBody());
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            JSONObject firstChoice = choicesArray.getJSONObject(0);
            JSONObject messageObject = firstChoice.getJSONObject("message");
            //工具调用
            JSONArray toolCalls = messageObject.optJSONArray("tool_calls");
            if (toolCalls != null && !toolCalls.isEmpty()) {
                JSONObject functionJsonObj = toolCalls.getJSONObject(0).optJSONObject("function");
                if (functionJsonObj != null && !functionJsonObj.isEmpty()) {
                    OpenAiApi.ChatResponse.Function function = FastJsonUtils.toEntity(functionJsonObj.toString(), OpenAiApi.ChatResponse.Function.class);
                    return executeTool(function);
                }
            }
            respon = messageObject.getString("content");
            System.out.println(respon);

            ChatRequest.Message.Builder respmessageBuilder = FastJsonUtils.toEntity(messageObject.toString(), ChatRequest.Message.Builder.class);
            ChatRequest.Message respmessage = respmessageBuilder.build();
            historyMessage.add(respmessage);
            return respon;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //流式输出
    @Override
    public Flux<String> genetateText4Stream(ChatRequest chatRequest) {
        String model = baiLianAIConfig.getModelId();
        String apiKey = baiLianAIConfig.getApiKey();

        final Predicate<String> SSE_DONE_PREDICATE = "[DONE]"::equals;//(content -> "[DONE]".equals(content);)
        AtomicBoolean functionCall = new AtomicBoolean(false);

        try {

            ChatRequest chatRequestNew = chatRequest;
            if (StringUtils.isEmpty(chatRequest.getModel())) {
                chatRequestNew = chatRequestNew.convertBuilder().model(model).build();
            }
            if (chatRequest.getStream() != null && !chatRequest.getStream()) {
                chatRequestNew = chatRequestNew.convertBuilder().stream(true).build();
            }
            System.out.println(FastJsonUtils.toJsonString(chatRequestNew));
            WebClient webClient = WebClient.create("https://dashscope.aliyuncs.com");
            Flux<String> fluxResponse = webClient.post()
                    .uri("/compatible-mode/v1/chat/completions") // 请求的 URI 路径
                    .header("Content-Type", "application/json;charset=utf-8")  // 设置请求头
                    .header("Authorization", apiKey) // 替换为实际的 API key
                    .bodyValue(chatRequestNew)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .takeUntil(SSE_DONE_PREDICATE)//从流中读取数据直到 SSE_DONE_PREDICATE 条件满足
                    .filter(SSE_DONE_PREDICATE.negate());//过滤;


            List<String> respList = fluxResponse.collect(Collectors.toList()).block();

            //判读function-call
            StringBuilder nameNew = new StringBuilder();
            StringBuilder argumentsNew = new StringBuilder();
            for (int i = 0; i < respList.size(); i++) {
                System.out.println("=======================\n" + respList.get(i) + "\n=======================");
                String resp = respList.get(i);
                OpenAiApi.ChatResponse chatResponse = FastJsonUtils.toEntity(resp, OpenAiApi.ChatResponse.class);
                OpenAiApi.ChatResponse.Choice choice = chatResponse.getChoices().get(0);
                OpenAiApi.ChatResponse.Delta delta = choice.getDelta();
                String detalStr = FastJsonUtils.toJsonString(delta);
                JSONObject deltaJsonObj = new JSONObject(detalStr);
                if (deltaJsonObj.has("toolCalls")) {
                    functionCall.set(true);
                    for (OpenAiApi.ChatResponse.ToolCall toolCall : delta.getToolCalls()) {
                        JSONObject toolcallJsonObj = new JSONObject(FastJsonUtils.toJsonString(toolCall));
                        if (toolcallJsonObj.has("function")) {
                            OpenAiApi.ChatResponse.Function function = toolCall.getFunction();
                            if (function != null) {
                                String name = function.getName();
                                String arguments = function.getArguments();
                                if (StringUtils.isNotEmpty(name)) {
                                    nameNew.append(name);
                                }
                                if (StringUtils.isNotEmpty(arguments)) {
                                    argumentsNew.append(arguments);
                                }
                            }
                        }
                    }

                }
            }


            if (functionCall.get()) {
                OpenAiApi.ChatResponse.Function function1 = new OpenAiApi.ChatResponse.Function();
                function1.setName(nameNew.toString());
                function1.setArguments(argumentsNew.toString());
                String str = executeTool(function1);
                return Flux.<String>create(sink -> {
                            for (int i = 0; i < str.length(); ) {
                                int codePoint = str.codePointAt(i);
                                String character = new String(Character.toChars(codePoint));
                                sink.next(character);
                                i += Character.charCount(codePoint);
                            }
                            sink.complete();
                        })
                        // 使用 delayElements 模拟延迟
                        .delayElements(Duration.ofMillis(200));
            }


            return fluxResponse  // 获取原始的事件流字符串
                    .map(content -> {
                        System.out.println("Received content: " + content); // 打印 content 以进行调试
                        OpenAiApi.ChatResponse chatResponse = FastJsonUtils.toEntity(content, OpenAiApi.ChatResponse.class);

                        return chatResponse.getChoices()
                                .stream()
                                .map(choice -> choice.getDelta().getContent())
                                .filter(deltaContent -> deltaContent != null)  // 过滤掉 null 值
                                .collect(Collectors.toList());
                    })
                    .flatMap(Flux::fromIterable);
            //.subscribe(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //上下文对话(非流式)
    public String generateTextContext(ChatRequest chatRequest, String prompt) {

        ChatRequest.Message userMessage = new ChatRequest.Message.Builder("user", prompt).build();
        historyMessage.add(userMessage);
        ChatRequest chatRequestNew = chatRequest.convertBuilder().messages(historyMessage).build();
        return generateText(chatRequestNew);
    }

    //上下文对话(流式)
    public Flux<String> generateText4StreamContext(ChatRequest chatRequest, String prompt) {

        if (chatRequest.getStream() != null && !chatRequest.getStream()) {
            chatRequest = chatRequest.convertBuilder().stream(true).build();
        }
        ChatRequest.Message userMessage = new ChatRequest.Message.Builder("user", prompt).build();
        historyMessage.add(userMessage);
        ChatRequest chatRequestNew = chatRequest.convertBuilder().messages(historyMessage).build();
        Flux<String> flux = genetateText4Stream(chatRequestNew);
        flux.reduce("", (a, b) -> a + b)
                .subscribe(content -> {         //subscribe() 方法会异步地订阅 Mono<String> 并在数据可用时通过回调函数处理。
                    ChatRequest.Message assistantMessage = new ChatRequest.Message.Builder("assistant", content).build();
                    historyMessage.add(assistantMessage);
//                    for (ChatRequest.Message message : historyMessage) {
//                        System.out.println(message.getRole() + ": " + message.getContent());
//                    }
                });
        //String content = flux.reduce("", (a, b) -> a + b).toFuture().join();
        //String content = flux.reduce("", (a, b) -> a + b).block();

        return flux;
    }

    /**
     * @Description: 调用functiontools
     * @Param: [functionJsonObj]
     * @return: java.lang.String
     * @Author: LC
     * @Date: 2024/12/26
     */
    public String executeTool(OpenAiApi.ChatResponse.Function function) {
        String functionName = function.getName();
        String argumentsStr = function.getArguments();

        if (functionName.equals("get_current_time")) {
            return FunctionUtil.getCurrentTime();
        } else if (StringUtils.isNotEmpty(argumentsStr) && functionName.equals("get_current_weather")) {
            JSONObject arguments = new JSONObject(argumentsStr);
            String location = arguments.getString("location");
            return FunctionUtil.getCurrentWeather(location);
        } else {
            return "未知工具调用，无法回答！";
        }
    }


    @Override
    public String generateImage(String prompt) {
        String url = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text2image/image-synthesis";
        String model = "wanx-v1";
        String apiKey = baiLianAIConfig.getApiKey();

        try {
            // 构建请求体

            BaiLianRequest.RequestBody requestBody = new BaiLianRequest.RequestBody(
                    model,
                    new BaiLianRequest.input(prompt)
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
