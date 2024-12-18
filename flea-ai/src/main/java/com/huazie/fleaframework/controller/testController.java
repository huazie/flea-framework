package com.huazie.fleaframework.controller;

import com.huazie.fleaframework.core.aliyun.BaiLianAIModelCore;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @Resource
    private  BaiLianAIModelCore baiLianAIModelCore;

    @GetMapping("/test")
    public String test(@RequestParam(value = "message", defaultValue = "Hi") String prompt){
        String s = baiLianAIModelCore.generateText(prompt);
        return s;
    }

    @GetMapping("/img")
    public String testimg(@RequestParam(value = "message", defaultValue = "一只坐着的橘黄色的猫，表情愉悦，活泼可爱，逼真准确") String prompt){
        String s = baiLianAIModelCore.generateImage(prompt);
        return s;
    }
}
