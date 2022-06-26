package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
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
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaUserSVImplTest {

    @Resource(name = "fleaUserSV")
    private IFleaUserSV fleaUserSV;

    @Test
    public void queryValidUser() throws CommonException {
        fleaUserSV.queryValidUser(10000L);
    }

    @Test
    public void saveFleaUser() throws CommonException {
        FleaUserRegisterPOJO fleaUserRegisterPOJO = new FleaUserRegisterPOJO();

        fleaUserRegisterPOJO.setUserName("Huazie");
        fleaUserRegisterPOJO.setAccountCode("13218010892");
        fleaUserRegisterPOJO.setAccountPwd("2022#lgh");
        fleaUserRegisterPOJO.setState(UserStateEnum.IN_USE.getState());
        fleaUserRegisterPOJO.setRemarks("用户自己注册时新增数据");

        fleaUserSV.saveFleaUser(fleaUserRegisterPOJO.newFleaUserPOJO());
    }
}