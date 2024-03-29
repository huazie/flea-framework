package com.huazie.fleaframework.common.strategy;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
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

        context.setContext("Tom");
        LOGGER.debug(FleaStrategyFacade.invoke("cat", context));

        AnimalVoiceContext context1 = new AnimalVoiceContext();
        context1.setContext("Donald");
        LOGGER.debug(FleaStrategyFacade.invoke("duck", context1));

    }
}
