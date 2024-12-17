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
}
