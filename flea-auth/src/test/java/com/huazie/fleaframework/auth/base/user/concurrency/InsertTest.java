package com.huazie.fleaframework.auth.base.user.concurrency;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;

import java.util.concurrent.ExecutorService;

/**
 * 测试 EntityManager 并发 persist
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class InsertTest {
    
    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(InsertTest.class);

    public static void testInsert(ExecutorService executorService, IFleaLoginLogSV fleaLoginLogSV) {
        executorService.execute(new FleaInsertRunable0(fleaLoginLogSV));
        executorService.execute(new FleaInsertRunable1(fleaLoginLogSV));
        executorService.execute(new FleaInsertRunable2(fleaLoginLogSV));
    }

    static class FleaInsertRunable0 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaInsertRunable0(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setAccountId(1000000L);
                fleaLoginLog.setSystemAccountId(2000L);
                fleaLoginLog.setLoginIp4("127.0.0.1");
                fleaLoginLog.setLoginState(1);
                fleaLoginLog.setLoginTime(DateUtils.getCurrentTime());
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

                Long fleaLoginId = (Long) fleaLoginLogSV.getFleaNextValue(fleaLoginLog);
                fleaLoginLog.setLoginLogId(fleaLoginId);

                // 保存至分表
                fleaLoginLogSV.save(fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaInsertRunable1 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaInsertRunable1(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setAccountId(1000000L);
                fleaLoginLog.setSystemAccountId(2000L);
                fleaLoginLog.setLoginIp4("127.0.0.1");
                fleaLoginLog.setLoginState(1);
                fleaLoginLog.setLoginTime(DateUtils.string2Date("2021-08-01 15:20:00"));
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-08-01 15:20:00"));

                Long fleaLoginId = (Long) fleaLoginLogSV.getFleaNextValue(fleaLoginLog);
                fleaLoginLog.setLoginLogId(fleaLoginId);

                // 保存至分表
                fleaLoginLogSV.save(fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaInsertRunable2 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaInsertRunable2(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setAccountId(1000000L);
                fleaLoginLog.setSystemAccountId(2000L);
                fleaLoginLog.setLoginIp4("127.0.0.1");
                fleaLoginLog.setLoginState(1);
                fleaLoginLog.setLoginTime(DateUtils.string2Date("2021-07-01 15:20:00"));
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-07-01 15:20:00"));

                Long fleaLoginId = (Long) fleaLoginLogSV.getFleaNextValue(fleaLoginLog);
                fleaLoginLog.setLoginLogId(fleaLoginId);

                // 保存至分表
                fleaLoginLogSV.save(fleaLoginLog);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
