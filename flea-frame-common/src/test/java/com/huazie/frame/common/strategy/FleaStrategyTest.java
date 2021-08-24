package com.huazie.frame.common.strategy;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

/**
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaStrategyTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaStrategyTest.class);

    @Test
    public void testStrategy() {

        AnimalVoiceContext context = new AnimalVoiceContext("旺财");
        LOGGER.debug(FleaStrategyFacade.invoke("dog", context));

        context = new AnimalVoiceContext("Tom");
        LOGGER.debug(FleaStrategyFacade.invoke("cat", context));

        context = new AnimalVoiceContext("Donald");
        LOGGER.debug(FleaStrategyFacade.invoke("duck", context));

    }
}
