package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
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
public class FleaRoleRelSVImplTest {

    @Resource(name = "fleaRoleRelSV")
    private IFleaRoleRelSV fleaRoleRelSV;

    @Test
    public void getRoleRelList() throws CommonException {
        fleaRoleRelSV.getRoleRelList(1000L, AuthRelTypeEnum.ROLE_REL_PRIVILEGE_GROUP.getRelType());
    }

    @Test
    public void saveRoleRel() throws CommonException {
        Long roleId = 1000L;
        String roleName = "超级管理员";
        Long privilegeGroupId = 1000L;
        String privilegeGroupName = "菜单访问";
        // 新建角色关联权限组POJO对象
        FleaRoleRelPOJO roleRelPrivilegeGroupPOJO = FleaAuthPOJOUtils.newRoleRelPrivilegeGroupPOJO(roleId, roleName, privilegeGroupId, privilegeGroupName);

        // 保存角色关联权限组
        this.fleaRoleRelSV.saveRoleRel(roleRelPrivilegeGroupPOJO);
    }
}