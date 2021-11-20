package com.huazie.frame.auth.concurrency;

import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.DateUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * 测试 FleaJPAQuery 并发
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaJPAQueryTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJPAQueryTest.class);

    public static void testFleaJPAQuery(ExecutorService executorService, IFleaLoginLogSV fleaLoginLogSV) {
        executorService.execute(new FleaJPAQueryRunnable(fleaLoginLogSV));
        executorService.execute(new FleaJPAQueryRunnable1(fleaLoginLogSV));
        executorService.execute(new FleaJPAQueryRunnable2(fleaLoginLogSV));
    }

    static class FleaJPAQueryRunnable implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaJPAQueryRunnable(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setLoginLogId(2L);
                fleaLoginLog.setAccountId(1000000L);
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

                Set<String> attrNames = new HashSet<>();
                attrNames.add("loginLogId");
                attrNames.add("accountId");

                List<FleaLoginLog> fleaLoginLogList = fleaLoginLogSV.query(attrNames, fleaLoginLog);

                LOGGER.debug("FleaLoginLogList = {}", fleaLoginLogList);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaJPAQueryRunnable1 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaJPAQueryRunnable1(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setLoginLogId(2L);
                fleaLoginLog.setAccountId(1000000L);
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-08-01 14:20:00"));

                Set<String> attrNames = new HashSet<>();
                attrNames.add("loginLogId");
                attrNames.add("accountId");

                List<FleaLoginLog> fleaLoginLogList = fleaLoginLogSV.query(attrNames, fleaLoginLog);

                LOGGER.debug("FleaLoginLogList = {}", fleaLoginLogList);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaJPAQueryRunnable2 implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        FleaJPAQueryRunnable2(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setLoginLogId(2L);
                fleaLoginLog.setAccountId(1000000L);
                fleaLoginLog.setCreateDate(DateUtils.string2Date("2021-07-01 15:20:00"));

                Set<String> attrNames = new HashSet<>();
                attrNames.add("loginLogId");
                attrNames.add("accountId");

                List<FleaLoginLog> fleaLoginLogList = fleaLoginLogSV.query(attrNames, fleaLoginLog);

                LOGGER.debug("FleaLoginLogList = {}", fleaLoginLogList);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
