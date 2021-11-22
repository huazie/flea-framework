package com.huazie.fleaframework.auth.concurrency;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;

import java.util.concurrent.ExecutorService;

/**
 * 测试 EntityManager 并发 merge
 * 
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class UpdateTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(UpdateTest.class);

    public static void testUpdate(ExecutorService executorService, IFleaLoginLogSV fleaLoginLogSV) {
        executorService.execute(new FleaUpdateRunable0(fleaLoginLogSV));
        executorService.execute(new FleaUpdateRunable1(fleaLoginLogSV));
        executorService.execute(new FleaUpdateRunable2(fleaLoginLogSV));
    }

    static class FleaUpdateRunable0 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaUpdateRunable0(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

                fleaLoginLog = fleaLoginLogSV.queryNew(2L, fleaLoginLog);

                fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
                fleaLoginLog.setLoginState(2);
                fleaLoginLog.setLoginIp4("0.0.0.0");
                fleaLoginLogSV.update(fleaLoginLog);

                LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaUpdateRunable1 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaUpdateRunable1(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-08-01 15:20:00"));

                fleaLoginLog = fleaLoginLogSV.queryNew(2L, fleaLoginLog);

                fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
                fleaLoginLog.setLoginState(3);
                fleaLoginLog.setLoginIp4("0.0.0.0");
                fleaLoginLogSV.update(fleaLoginLog);

                LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaUpdateRunable2 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaUpdateRunable2(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-07-01 15:20:00"));

                fleaLoginLog = fleaLoginLogSV.queryNew(2L, fleaLoginLog);

                fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
                fleaLoginLog.setLoginState(4);
                fleaLoginLog.setLoginIp4("0.0.0.0");
                fleaLoginLogSV.update(fleaLoginLog);

                LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
