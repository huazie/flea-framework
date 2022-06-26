package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
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
public class FleaUserGroupRelSVImplTest {

    @Resource(name = "fleaUserGroupRelSV")
    private IFleaUserGroupRelSV fleaUserGroupRelSV;

    @Test
    public void getUserGroupRelRoleList() throws CommonException {
        fleaUserGroupRelSV.getUserGroupRelList(1000L, null, AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType());
    }

    @Test
    public void getUserGroupRelRoleGroupList() throws CommonException {
        fleaUserGroupRelSV.getUserGroupRelList(1000L, null, AuthRelTypeEnum.USER_GROUP_REL_ROLE_GROUP.getRelType());
    }

    @Test
    public void getUserGroupRelUserList() throws CommonException {
        fleaUserGroupRelSV.getUserGroupRelList(1000L, 1000L, AuthRelTypeEnum.USER_GROUP_REL_USER.getRelType());
    }

    @Test
    public void saveUserGroupRel() throws CommonException {
        Long userGroupId = 1000L;
        String userGroupName = "";
        Long roleGroupId = 1000L;
        String roleGroupName = "";
        // 新建用户组关联角色POJO对象
        FleaUserGroupRelPOJO userGroupRelRoleGroupPOJO = FleaAuthPOJOUtils.newUserGroupRelRoleGroupPOJO(userGroupId, userGroupName, roleGroupId, roleGroupName);

        // 保存用户组关联角色
        this.fleaUserGroupRelSV.saveUserGroupRel(userGroupRelRoleGroupPOJO);
    }
}