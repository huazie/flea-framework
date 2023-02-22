package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountSV;
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
public class FleaAccountCacheSVImplTest {

    @Resource(name = "fleaAccountCacheSV")
    private IFleaAccountSV fleaAccountCacheSV; // Flea账户缓存服务

    @Test
    public void queryValidAccountByAccountId() throws CommonException {
        fleaAccountCacheSV.queryValidAccount(10002L);
    }

    @Test
    public void queryValidAccountByAccountCode() throws CommonException {
        fleaAccountCacheSV.queryValidAccount("13218010892");
    }

}