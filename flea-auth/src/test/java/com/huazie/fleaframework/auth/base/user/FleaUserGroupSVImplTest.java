package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
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
public class FleaUserGroupSVImplTest {

    @Resource(name = "fleaUserGroupSV")
    private IFleaUserGroupSV fleaUserGroupSV;

    @Test
    public void queryUserGroupInUse() throws CommonException {
        fleaUserGroupSV.queryUserGroupInUse(1000L);
    }

    @Test
    public void saveUserGroup() throws CommonException {
        FleaUserGroupPOJO fleaUserGroupPOJO = new FleaUserGroupPOJO();
        fleaUserGroupPOJO.setUserGroupName("系统用户");
        fleaUserGroupPOJO.setUserGroupDesc("实际的系统用户归属的用户组");
        fleaUserGroupPOJO.setRemarks("该用户组与实际的系统用户相关");
        fleaUserGroupSV.saveUserGroup(fleaUserGroupPOJO);
    }
}