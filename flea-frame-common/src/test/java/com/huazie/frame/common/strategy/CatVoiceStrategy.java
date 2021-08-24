package com.huazie.frame.common.strategy;

import com.huazie.frame.common.exception.FleaStrategyException;

/**
 * 猫喊叫声策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class CatVoiceStrategy implements IFleaStrategy<String, String> {

    @Override
    public String execute(String name) throws FleaStrategyException {
        return "阿猫【" + name + "】正在叫喊着；喵喵喵";
    }

}
