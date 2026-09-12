package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea数据权限（数据范围）解析测试
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaAuthDataScopeSVImplTest {

    @Resource(name = "fleaAuthSV")
    private IFleaAuthSV fleaAuthSV;

    @Test
    public void getDataScopeOrgIds() throws CommonException {
        // 获取用户 1000 的数据范围允许的组织编号集
        List<Long> allowedOrgIdList = fleaAuthSV.getDataScopeOrgIds(1000L);
        System.out.println("允许的组织编号集：null 表示无约束，否则为允许的组织编号列表");
        System.out.println(allowedOrgIdList);
    }

    @Test
    public void checkDataScope() throws CommonException {
        // 校验用户 1000 是否可以访问归属组织 1000 的数据
        boolean checkResult = fleaAuthSV.checkDataScope(1000L, 1000L);
        System.out.println(checkResult);
    }
}
