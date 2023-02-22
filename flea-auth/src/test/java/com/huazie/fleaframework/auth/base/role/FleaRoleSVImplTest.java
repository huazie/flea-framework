package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleSV;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
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
public class FleaRoleSVImplTest {

    @Resource(name = "fleaRoleSV")
    private IFleaRoleSV fleaRoleSV;

    @Test
    public void queryRoleInUse() throws CommonException {
        fleaRoleSV.queryRoleInUse(1000L);
    }

    @Test
    public void queryRolesInUse4Page() throws CommonException {
        fleaRoleSV.queryRolesInUse4Page(null, null, 1, 2);
    }

    @Test
    public void queryRolesInUse() throws CommonException {
        fleaRoleSV.queryRolesInUse("管理", null);
    }

    @Test
    public void queryRolesInUseCount() throws CommonException {
        fleaRoleSV.queryRolesInUseCount(null, null);
    }

    @Test
    public void saveRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("超级管理员");
        fleaRolePOJO.setRoleDesc("最高系统权限拥有者角色");
        fleaRolePOJO.setRemarks("超级管理员拥有系统最高权限");
        fleaRoleSV.saveRole(fleaRolePOJO);
    }
}