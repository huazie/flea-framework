package com.huazie.fleaframework.model;

public interface Model {
    /**
     * 模型推理接口
     * @param input 输入数据
     * @return 模型推理结果
     */
    String predict(String input);
}
