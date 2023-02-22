package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.base.role.AddFleaRole;
import com.huazie.fleaframework.auth.base.role.AddFleaRoleGroup;
import com.huazie.fleaframework.auth.base.role.AddFleaRoleGroupRel;
import com.huazie.fleaframework.auth.base.role.AddFleaRoleRel;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaRoleModuleSV;
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
public class FleaRoleModuleSVImplTest {

    @Resource(name = "fleaRoleModuleSV")
    private IFleaRoleModuleSV fleaRoleModuleSV;

    @Test
    public void addFleaRole() throws CommonException {
        AddFleaRole fleaRoleAdd = new AddFleaRole(fleaRoleModuleSV);
        fleaRoleAdd.addFleaRole();
    }

    @Test
    public void modifyFleaRole() throws CommonException {
        FleaRolePOJO fleaRolePOJO = new FleaRolePOJO();
        fleaRolePOJO.setRoleName("超级管理员");
        fleaRolePOJO.setRoleDesc("最高系统权限拥有者角色");
        fleaRolePOJO.setRemarks("超级管理员拥有系统最高权限");
        fleaRoleModuleSV.modifyFleaRole(1000L, fleaRolePOJO);
    }

    @Test
    public void addFleaRoleGroup() throws CommonException {
        AddFleaRoleGroup fleaRoleGroupAdd = new AddFleaRoleGroup(fleaRoleModuleSV);
        fleaRoleGroupAdd.addFleaRoleGroup();
    }

    @Test
    public void modifyFleaRoleGroup() throws CommonException {
        FleaRoleGroupPOJO fleaRoleGroupPOJO = new FleaRoleGroupPOJO();
        fleaRoleGroupPOJO.setRoleGroupName("管理员");
        fleaRoleGroupPOJO.setRoleGroupDesc("各种管理员角色归属的角色组");
        fleaRoleGroupPOJO.setRemarks("管理员角色组包含各种管理员角色");
        fleaRoleModuleSV.modifyFleaRoleGroup(1000L, fleaRoleGroupPOJO);
    }

    @Test
    public void roleRelRole() throws CommonException { // TODO
        Long roleId = 1000L;
        Long relRoleId = 1001L;
        // 角色关联角色
        fleaRoleModuleSV.roleRelRole(roleId, relRoleId, null);
    }

    @Test
    public void roleRelPrivilege() throws CommonException { // TODO
        Long roleId = 1000L;
        Long privilegeId = 1000L;
        // 角色关联权限
        fleaRoleModuleSV.roleRelPrivilege(roleId, privilegeId, null);
    }

    @Test
    public void roleRelPrivilegeGroup() throws CommonException {
        AddFleaRoleRel addFleaRoleRel = new AddFleaRoleRel(fleaRoleModuleSV);
        addFleaRoleRel.roleRelPrivilegeGroup();
    }

    @Test
    public void roleGroupRelRole() throws CommonException {
        AddFleaRoleGroupRel addFleaRoleGroupRel = new AddFleaRoleGroupRel(fleaRoleModuleSV);
        addFleaRoleGroupRel.roleGroupRelRole();
    }
}