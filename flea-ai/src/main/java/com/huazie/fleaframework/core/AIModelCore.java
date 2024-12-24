package com.huazie.fleaframework.core;

import com.huazie.fleaframework.common.OpenAiApi.ChatRequest;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AIModelCore {
    Flux<String> genetateText4Stream(ChatRequest chatRequest);
    String generateText(ChatRequest chatRequest);

    String generateImage(String prompt);

    String transcribeAudio(byte[] audioData);

    List<Float> generateEmbedding(String input);


}
