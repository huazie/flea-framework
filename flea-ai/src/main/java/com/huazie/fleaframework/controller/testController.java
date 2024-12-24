package com.huazie.fleaframework.controller;

import com.huazie.fleaframework.common.OpenAiApi.ChatRequest;
import com.huazie.fleaframework.core.aliyun.BaiLianAIModelCore;
import jakarta.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
public class testController {
    @Resource
    private  BaiLianAIModelCore baiLianAIModelCore;

    //生成文本
    @GetMapping("/test")
    public String test(@RequestParam(value = "message", defaultValue = "Hi") String prompt){
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).build();
        String s = baiLianAIModelCore.generateText(chatRequest);
        return s;
    }

    //流式文本
    @GetMapping(value = "/test1",produces = "text/sse;charset=utf-8")
    public Flux<String> test1(@RequestParam(value = "message", defaultValue = "Hi") String prompt){
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).build();
       return baiLianAIModelCore.genetateText4Stream(chatRequest);
    }


    static List<ChatRequest.Message> historyMessage = new ArrayList<>();
    @GetMapping(value = "/test2",produces = "text/sse;charset=utf-8")
    public Flux<String> test2(@RequestParam(value = "message", defaultValue = "Hi") String prompt){
        ChatRequest.Message message = new ChatRequest.Message.Builder("user", prompt).build();
        ChatRequest chatRequest = new ChatRequest.Builder().message(message).build();
        List<ChatRequest.Message> messages = chatRequest.getMessages();
        historyMessage = messages;
        return baiLianAIModelCore.genetateText4Stream(chatRequest);
    }

    //文本转图像（简易）
    @GetMapping("/img")
    public String testimg(@RequestParam(value = "message", defaultValue = "一只坐着的橘黄色的猫，表情愉悦，活泼可爱，逼真准确") String prompt){
        String s = baiLianAIModelCore.generateImage(prompt);
        return s;
    }



}
