package com.huazie.fleaframework.controller;

import com.huazie.fleaframework.common.OpenAiApi.ChatRequest;
import com.huazie.fleaframework.common.util.json.GsonUtils;
import com.huazie.fleaframework.core.aliyun.BaiLianAIModelCore;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class testController {
    @Resource
    private BaiLianAIModelCore baiLianAIModelCore;

    //生成文本
    @GetMapping("/test")
    public String test(@RequestParam(value = "message", defaultValue = "Hi") String prompt) {
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).build();
        String s = baiLianAIModelCore.generateText(chatRequest);
        return s;
    }

    //流式文本
    @GetMapping(value = "/test1", produces = "text/sse;charset=utf-8")
    public Flux<String> test1(@RequestParam(value = "message", defaultValue = "Hi") String prompt) {
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).build();
        return baiLianAIModelCore.genetateText4Stream(chatRequest);
    }


    //上下文对话（非流）
    @GetMapping("/test2")
    public String test2(@RequestParam(value = "message", defaultValue = "Hi") String prompt) {
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).build();
        String s = baiLianAIModelCore.generateTextContext(chatRequest, prompt);
        return s;
    }

    //上下文对话（流式）
    @GetMapping(value = "/test3", produces = "text/sse;charset=utf-8")
    public Flux<String> test3(@RequestParam(value = "message", defaultValue = "Hi") String prompt) {
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).stream(true).build();
        return baiLianAIModelCore.generateText4StreamContext(chatRequest, prompt);
    }

    //简单的function-call。（非流+流）
    @GetMapping(value = "/test4", produces = "text/sse;charset=utf-8")
    public Flux<String> test4(@RequestParam(value = "message", defaultValue = "Hi") String prompt) {
        String jsonStr = "{"
                + "  \"type\": \"object\","
                + "  \"properties\": {"
                + "    \"location\": {"
                + "      \"type\": \"string\","
                + "      \"description\": \"杭州\""
                + "    }"
                + "  }"
                + "}";


        Map<String, Object> parameters = GsonUtils.toMap(jsonStr);
        ChatRequest.Function function = new ChatRequest.Function.Builder("查询指定城市的天气。", "get_current_weather", parameters).build();

        System.out.println(parameters);
        ChatRequest.Function function1 = new ChatRequest.Function.Builder("当你想知道现在的时间时非常有用。", "get_current_time", null).build();
        ChatRequest.FunctionTool functionTool = new ChatRequest.FunctionTool.Builder(function).build();
        ChatRequest.FunctionTool functionTool1 = new ChatRequest.FunctionTool.Builder(function1).build();
        List<ChatRequest.FunctionTool> functionToolList = new ArrayList<>();
        functionToolList.add(functionTool);
        functionToolList.add(functionTool1);

        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).tools(functionToolList).build();
        System.out.println(chatRequest);
        return baiLianAIModelCore.genetateText4Stream(chatRequest);
    }


    //文本转图像（简易）
    @GetMapping("/img")
    public String testimg(@RequestParam(value = "message", defaultValue = "一只坐着的橘黄色的猫，表情愉悦，活泼可爱，逼真准确") String prompt) {
        String s = baiLianAIModelCore.generateImage(prompt);
        return s;
    }


}
