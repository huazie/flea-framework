package com.huazie.fleaframework.auth.base.user;

import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
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
public class FleaUserRelSVImplTest {

    @Resource(name = "fleaUserRelSV")
    private IFleaUserRelSV fleaUserRelSV;

    @Test
    public void getUserRelList() throws CommonException {
        fleaUserRelSV.getUserRelList(10000L, AuthRelTypeEnum.USER_REL_ROLE.getRelType());
    }

    @Test
    public void saveUserRel() throws CommonException {
        Long userId = 10000L;
        String userName = "Huazie";
        Long roleId = 1000L;
        String roleName = "超级管理员";
        // 新建用户关联角色POJO对象
        FleaUserRelPOJO userRelRolePOJO = FleaAuthPOJOUtils.newUserRelRolePOJO(userId, userName, roleId, roleName);

        // 保存用户关联角色
        this.fleaUserRelSV.saveUserRel(userRelRolePOJO);
    }
}