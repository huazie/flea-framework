package com.huazie.frame.auth.user;

import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LoginLogAuthMainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogAuthMainTest.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);

        IFleaLoginLogSV fleaLoginLogSV = (IFleaLoginLogSV) applicationContext.getBean("fleaLoginLogSV");

        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
        Executors.newCachedThreadPool().execute(new FleaRunable(fleaLoginLogSV));
    }

    static class FleaRunable implements Runnable {

        IFleaLoginLogSV fleaLoginLogSV;

        public FleaRunable(IFleaLoginLogSV fleaLoginLogSV) {
            this.fleaLoginLogSV = fleaLoginLogSV;
        }

        @Override
        public void run() {
            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog();
                fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());
                LOGGER.debug("flea_log_id = {}" , fleaLoginLogSV.getFleaNextValue(fleaLoginLog));
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
