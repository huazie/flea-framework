package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaAccountSVImplTest {

    @Resource(name = "fleaAccountSV")
    private IFleaAccountSV fleaAccountSV; // Flea账户服务

    @Resource(name = "fleaAccountCacheSV")
    private IFleaAccountSV fleaAccountCacheSV; // Flea账户服务【用于缓存】

    @Test
    public void queryValidAccountByAccountId() throws CommonException {
        fleaAccountSV.queryValidAccount(10000L);
    }

    @Test
    public void queryValidAccountByAccountId4Cache() throws CommonException {
        fleaAccountCacheSV.queryValidAccount(10002L);
    }

    @Test
    public void queryAccount() throws CommonException {
        fleaAccountSV.queryAccount("13218010892", "2022#lgh");
    }

    @Test
    public void queryValidAccountByAccountCode() throws CommonException {
        fleaAccountSV.queryValidAccount("13218010892");
    }

    @Test
    public void saveFleaAccount() throws CommonException {
        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setUserName("Huazie");
        fleaUserRegisterPOJO.setAccountCode("13218010892");
        fleaUserRegisterPOJO.setAccountPwd("2022#lgh");
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getState());
        fleaUserRegisterPOJO.setRemarks("用户自己注册时新增数据");

        Long userId = 10000L;
        fleaAccountSV.saveFleaAccount(fleaUserRegisterPOJO.newFleaAccountPOJO(userId));
    }
}