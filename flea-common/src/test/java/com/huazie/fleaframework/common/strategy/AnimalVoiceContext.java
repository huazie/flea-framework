package com.huazie.fleaframework.common.strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 动物叫喊声策略上下文
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class AnimalVoiceContext extends FleaStrategyContext<String, String> {

    public AnimalVoiceContext() {
    }

    public AnimalVoiceContext(String contextParam) {
        super(contextParam);
    }

    @Override
    protected Map<String, IFleaStrategy<String, String>> init() {
        Map<String, IFleaStrategy<String, String>> fleaStrategyMap = new HashMap<>();
        fleaStrategyMap.put("dog", new DogVoiceStrategy());
        fleaStrategyMap.put("cat", new CatVoiceStrategy());
        fleaStrategyMap.put("duck", new DuckVoiceStrategy());
        return Collections.unmodifiableMap(fleaStrategyMap);
    }

}
