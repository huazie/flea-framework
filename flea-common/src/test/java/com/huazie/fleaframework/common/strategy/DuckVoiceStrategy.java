package com.huazie.fleaframework.common.strategy;

import com.huazie.fleaframework.common.exceptions.FleaStrategyException;

/**
 * 鸭喊叫声策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class DuckVoiceStrategy implements IFleaStrategy<String, String> {

    @Override
    public String execute(String name) throws FleaStrategyException {
        return "阿鸭【" + name + "】正在叫喊着；嘎嘎嘎";
    }

}
