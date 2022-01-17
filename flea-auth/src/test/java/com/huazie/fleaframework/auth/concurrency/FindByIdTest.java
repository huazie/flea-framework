package com.huazie.fleaframework.auth.concurrency;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;

import java.util.concurrent.ExecutorService;

/**
 * 测试 EntityManager 并发 find
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FindByIdTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FindByIdTest.class);

    public static void testFindById(ExecutorService executorService, IFleaLoginLogSV fleaLoginLogSV) {
        executorService.execute(new FleaRunable0(fleaLoginLogSV));
        executorService.execute(new FleaRunable1(fleaLoginLogSV));
        executorService.execute(new FleaRunable2(fleaLoginLogSV));
    }

    static class FleaRunable0 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRunable0(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

                fleaLoginLog = fleaLoginLogSV.query(2L, fleaLoginLog);

                LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRunable1 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRunable1(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-08-01 15:20:00"));

                fleaLoginLog = fleaLoginLogSV.query(2L, fleaLoginLog);

                LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRunable2 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRunable2(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-07-01 15:20:00"));

                fleaLoginLog = fleaLoginLogSV.query(2L, fleaLoginLog);

                LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
