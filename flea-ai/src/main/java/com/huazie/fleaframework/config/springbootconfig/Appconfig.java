package com.huazie.fleaframework.config.springbootconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

@Configuration
public class Appconfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        // 自定义 Netty 线程池配置
        HttpClient httpClient = HttpClient.create()
                .runOn(LoopResources.create("webclient-pool", 10, 20, true));  // 设置线程池大小，最小4，最大10

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
