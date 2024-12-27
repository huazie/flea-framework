package com.huazie.fleaframework.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FunctionUtil {
    public static String getCurrentTime() {
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 定义格式化的模式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化当前日期和时间
        String formattedTime = currentDateTime.format(formatter);
        // 返回格式化后的当前时间
        return "当前时间：" + formattedTime + "。";
    }


    public static String getCurrentWeather(String location) {
        return location + "今天是晴天。====================================================================================================================";
    }
}
