package com.huazie.fleaframework.core;

import reactor.core.publisher.Flux;

import java.util.List;

public interface AIModelCore {
    Flux<String> genetateText4Stream(String prompt);
    String generateText(String prompt);

    String generateImage(String prompt);

    String transcribeAudio(byte[] audioData);

    List<Float> generateEmbedding(String input);


}
