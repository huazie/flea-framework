package com.huazie.fleaframework.auth.base.user.concurrency;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;

import java.util.concurrent.ExecutorService;

/**
 * 测试多线程获取主键
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class GetFleaNextValueTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(GetFleaNextValueTest.class);

    public static void testGetFleaNextValue(ExecutorService executorService, IFleaLoginLogSV fleaLoginLogSV) {
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue(fleaLoginLogSV));
        executorService.execute(new FleaRunableNextValue1(fleaLoginLogSV));
    }

    static class FleaRunableNextValue implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRunableNextValue(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());
                LOGGER.debug("flea_log_id = {}", fleaLoginLogSV.getFleaNextValue(fleaLoginLog));
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaRunableNextValue1 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaRunableNextValue1(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-08-01 15:20:00"));
                LOGGER.debug("flea_log_id = {}", fleaLoginLogSV.getFleaNextValue(fleaLoginLog));
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
