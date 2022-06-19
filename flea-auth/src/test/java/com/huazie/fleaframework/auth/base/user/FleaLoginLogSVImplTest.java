package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.auth.common.LoginStateEnum;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaLoginLogPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 已验证
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaLoginLogSVImplTest {

    @Resource(name = "fleaLoginLogSV")
    private IFleaLoginLogSV fleaLoginLogSV;

    @Test
    public void queryLastUserLoginLog() throws CommonException {
        fleaLoginLogSV.queryLastUserLoginLog(10000L);
    }

    @Test
    public void saveLoginLog() throws CommonException {
        Long accountId = 10000L;
        String ip4 = "127.0.0.1";
        String ip6 = "";
        String address = "内网";
        FleaLoginLogPOJO fleaLoginLogPOJO = new FleaLoginLogPOJO(accountId, ip4, ip6, address);
        fleaLoginLogSV.saveLoginLog(fleaLoginLogPOJO);
    }

    @Test
    public void saveQuitLog() throws CommonException {
        fleaLoginLogSV.saveQuitLog(10000L);
    }

    @Test
    public void fleaLoginLogQuery() throws CommonException {
        FleaLoginLog fleaLoginLog = new FleaLoginLog();
        fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

        fleaLoginLogSV.query(1L, fleaLoginLog);
    }

    @Test
    public void testFleaLoginLogUpdate() throws CommonException {
        FleaLoginLog fleaLoginLog = new FleaLoginLog();
        fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

        fleaLoginLog = fleaLoginLogSV.query(1L, fleaLoginLog);

        // 更新记录（分表）
        fleaLoginLog.setLogoutTime(DateUtils.getCurrentTime());
        fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
        fleaLoginLog.setLoginState(LoginStateEnum.QUITTED.getState());
        // 用户已退出
        fleaLoginLog.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-COMMON0000000001"));

        fleaLoginLogSV.update(fleaLoginLog);
    }

    @Test
    public void testFleaLoginLogDelete() throws CommonException {
        FleaLoginLog fleaLoginLog = new FleaLoginLog();
        fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());

        fleaLoginLogSV.remove(4L, fleaLoginLog);
    }
}