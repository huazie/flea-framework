package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaRealNameInfoSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaRealNameInfoPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
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
public class FleaRealNameInfoSVImplTest {

    @Resource(name = "fleaRealNameInfoSV")
    private IFleaRealNameInfoSV fleaRealNameInfoSV; // Flea实名服务

    @Test
    public void queryValidRealNameInfo() throws CommonException {
        this.fleaRealNameInfoSV.queryValidRealNameInfo(1L);
    }

    @Test
    public void saveRealNameInfo() throws CommonException {
        FleaRealNameInfoPOJO fleaRealNameInfoPOJO = new FleaRealNameInfoPOJO();
        fleaRealNameInfoPOJO.setCertType(1);
        fleaRealNameInfoPOJO.setCertCode("222222222222222222");
        fleaRealNameInfoPOJO.setCertName("张三");
        fleaRealNameInfoPOJO.setCertAddress("浙江杭州");
        this.fleaRealNameInfoSV.saveRealNameInfo(fleaRealNameInfoPOJO);
    }
}