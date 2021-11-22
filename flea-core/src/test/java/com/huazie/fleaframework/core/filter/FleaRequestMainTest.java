package com.huazie.fleaframework.core.filter;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.request.FleaRequestContext;
import com.huazie.fleaframework.core.request.FleaRequestUtil;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestMainTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaRequestMainTest.class);

    public static void main(String[] args) {
        Thread thread = new Thread(new DoFilterTaskRunnable());
        Thread thread1 = new Thread(new DoFilterTaskRunnable());
        Thread thread2 = new Thread(new DoFilterTaskRunnable());

        thread.start();
        thread1.start();
        thread2.start();
    }

    private static class DoFilterTaskRunnable implements Runnable {
        @Override
        public void run() {
            try {
                FleaRequestUtil.doFilterTask(new FleaRequestContext());
            } catch (CommonException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Exception : \n", e);
                }
            }
        }
    }
}
