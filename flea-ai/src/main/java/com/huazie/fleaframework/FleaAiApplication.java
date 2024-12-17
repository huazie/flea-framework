package com.huazie.fleaframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.huazie")
public class FleaAiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FleaAiApplication.class);
    }

}
