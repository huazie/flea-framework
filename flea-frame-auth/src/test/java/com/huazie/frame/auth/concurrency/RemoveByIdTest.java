package com.huazie.frame.auth.concurrency;

import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.DateUtils;

import java.util.concurrent.ExecutorService;

/**
 * 测试 EntityManager 并发 remove
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class RemoveByIdTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RemoveByIdTest.class);

    public static void testRemoveById(ExecutorService executorService, IFleaLoginLogSV fleaLoginLogSV) {
        executorService.execute(new FleaRemoveRunable0(fleaLoginLogSV));
        executorService.execute(new FleaRemoveRunable1(fleaLoginLogSV));
        executorService.execute(new FleaRemoveRunable2(fleaLoginLogSV));
    }

    static class FleaRemoveRunable0 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRemoveRunable0(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

                boolean isRemoved = fleaLoginLogSV.removeNew(1L, fleaLoginLog);

                LOGGER.debug("REMOVE0 = {}", isRemoved);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRemoveRunable1 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRemoveRunable1(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-08-01 15:20:00"));

                boolean isRemoved = fleaLoginLogSV.removeNew(1L, fleaLoginLog);

                LOGGER.debug("REMOVE0 = {}", isRemoved);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRemoveRunable2 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRemoveRunable2(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-07-01 15:20:00"));

                boolean isRemoved = fleaLoginLogSV.removeNew(1L, fleaLoginLog);

                LOGGER.debug("REMOVE0 = {}", isRemoved);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
    
}
