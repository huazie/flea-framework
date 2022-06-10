package com.huazie.fleaframework.common.config.concurrency;

import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.config.ConfigItems;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

/**
 * 测试 EntityManager 并发 find
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class GetConfigItemTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(GetConfigItemTest.class);

    public static void testGetConfigItem(ConfigItems configItems) {
        Thread thread1 = new Thread(new FleaRunable0(configItems));
        Thread thread2 = new Thread(new FleaRunable1(configItems));
        Thread thread3 = new Thread(new FleaRunable2(configItems));
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class FleaRunable0 implements Runnable {

        private ConfigItems configItems;

        public FleaRunable0(ConfigItems configItems) {
            this.configItems = configItems;
        }

        @Override
        public void run() {
            try {
                System.out.println("Before ConfigItems0 = " + configItems);
                ConfigItem configItem = configItems.getConfigItem("system_account_id");
                System.out.println("ConfigItem0 = " + configItem);
                System.out.println("After ConfigItems0 = " + configItems);
            } catch (Exception e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRunable1 implements Runnable {

        private ConfigItems configItems;

        public FleaRunable1(ConfigItems configItems) {
            this.configItems = configItems;
        }

        @Override
        public void run() {
            try {
                System.out.println("Before ConfigItems1 = " + configItems);
                ConfigItem configItem = configItems.getConfigItem("system_account_id");
                System.out.println("ConfigItem1 = " + configItem);
                System.out.println("After ConfigItems1 = " + configItems);
            } catch (Exception e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRunable2 implements Runnable {

        private ConfigItems configItems;

        public FleaRunable2(ConfigItems configItems) {
            this.configItems = configItems;
        }

        @Override
        public void run() {
            try {
                System.out.println("Before ConfigItems2 = " + configItems);
                ConfigItem configItem = configItems.getConfigItem("system_account_id");
                System.out.println("ConfigItem2 = " + configItem);
                System.out.println("After ConfigItems2 = " + configItems);
            } catch (Exception e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
