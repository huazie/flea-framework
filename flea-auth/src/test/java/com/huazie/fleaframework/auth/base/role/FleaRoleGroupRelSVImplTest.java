package com.huazie.fleaframework.auth.base.role;

import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
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
public class FleaRoleGroupRelSVImplTest {

    @Resource(name = "fleaRoleGroupRelSV")
    private IFleaRoleGroupRelSV fleaRoleGroupRelSV;

    @Test
    public void getRoleGroupRelList() throws CommonException {
        fleaRoleGroupRelSV.getRoleGroupRelList(1000L, AuthRelTypeEnum.ROLE_GROUP_REL_ROLE.getRelType());
    }

    @Test
    public void saveRoleGroupRel() throws CommonException {
        Long roleGroupId = 1000L;
        String roleGroupName = "管理员";
        Long roleId = 1000L;
        String roleName = "超级管理员";
        FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO = FleaAuthPOJOUtils.newRoleGroupRelRolePOJO(roleGroupId, roleGroupName, roleId, roleName);
        fleaRoleGroupRelSV.saveRoleGroupRel(fleaRoleGroupRelPOJO);
    }
}