package com.huazie.fleaframework.core;

import java.util.List;

public interface AIModelCore {
    String generateText(String prompt);

    String generateImage(String prompt);

    String transcribeAudio(byte[] audioData);

    List<Float> generateEmbedding(String input);


}
