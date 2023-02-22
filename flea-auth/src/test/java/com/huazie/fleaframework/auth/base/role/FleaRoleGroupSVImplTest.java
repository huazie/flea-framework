package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
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
public class FleaRoleGroupSVImplTest {

    @Resource(name = "fleaRoleGroupSV")
    private IFleaRoleGroupSV fleaRoleGroupSV;

    @Test
    public void queryRoleGroupInUse() throws CommonException {
        fleaRoleGroupSV.queryRoleGroupInUse(1000L);
    }

    @Test
    public void queryRoleGroupsInUse4Page() throws CommonException {
        fleaRoleGroupSV.queryRoleGroupsInUse4Page(null, 1, 2);
    }

    @Test
    public void queryRoleGroupsInUse() throws CommonException {
        fleaRoleGroupSV.queryRoleGroupsInUse("");
    }

    @Test
    public void queryRoleGroupsInUseCount() throws CommonException {
        fleaRoleGroupSV.queryRoleGroupsInUseCount(null);
    }

    @Test
    public void saveRoleGroup() throws CommonException {
        FleaRoleGroupPOJO fleaRoleGroupPOJO = new FleaRoleGroupPOJO();
        fleaRoleGroupPOJO.setRoleGroupName("管理员");
        fleaRoleGroupPOJO.setRoleGroupDesc("各种管理员角色归属的角色组");
        fleaRoleGroupPOJO.setRemarks("【管理员】角色组包含各种管理员角色");
        fleaRoleGroupSV.saveRoleGroup(fleaRoleGroupPOJO);
    }
}