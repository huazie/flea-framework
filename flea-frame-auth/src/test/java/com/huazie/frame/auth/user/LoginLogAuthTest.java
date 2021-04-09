package com.huazie.frame.auth.user;

import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoginLogAuthTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LoginLogAuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testFleaLoginLogInsert() {
        try {
            IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");
            FleaLoginLog fleaLoginLog = new FleaLoginLog();
            fleaLoginLog.setAccountId(1000000L);
            fleaLoginLog.setSystemAccountId(2000L);
            fleaLoginLog.setLoginIp4("127.0.0.1");
            fleaLoginLog.setLoginState(1);
            fleaLoginLog.setLoginTime(DateUtils.getCurrentTime());
            fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

            Long fleaLoginId = fleaLoginLogSV.getFleaNextValue(fleaLoginLog);
            fleaLoginLog.setLoginLogId(fleaLoginId);

            // 保存至分表
            fleaLoginLogSV.save(fleaLoginLog);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaLoginLogQuery() {

        try {
            IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");

            FleaLoginLog fleaLoginLog = new FleaLoginLog();
            fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

            fleaLoginLog = fleaLoginLogSV.queryNew(1L, fleaLoginLog);

            LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }

    }

    @Test
    public void testLastUserLoginLogQuery() {
        try {
            IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");

            FleaLoginLog fleaLoginLog = fleaLoginLogSV.queryLastUserLoginLog(1L);
            LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaLoginLogUpdate() {

        try {
            IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");

            FleaLoginLog fleaLoginLog = new FleaLoginLog();
            fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

            fleaLoginLog = fleaLoginLogSV.queryNew(1L, fleaLoginLog);

            LOGGER.debug("FleaLoginLog = {}", fleaLoginLog);

            // 更新记录（分表）
            fleaLoginLog.setLogoutTime(DateUtils.getCurrentTime());
            fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
            fleaLoginLog.setLoginState(2);
            fleaLoginLog.setRemarks("用户退出登陆");

            fleaLoginLogSV.update(fleaLoginLog);

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }

    }

    @Test
    public void testFleaLoginLogDelete() {

        try {
            IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");

            FleaLoginLog fleaLoginLog = new FleaLoginLog();
            fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

            fleaLoginLogSV.removeNew(1L, fleaLoginLog);

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }

    }
}
