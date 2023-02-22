package com.huazie.fleaframework.auth.base.privilege;

import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
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
public class FleaPrivilegeGroupRelSVImplTest {

    @Resource(name = "fleaPrivilegeGroupRelSV")
    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV;

    @Test
    public void getPrivilegeGroupRelList() throws CommonException {
        fleaPrivilegeGroupRelSV.getPrivilegeGroupRelList(1000L, AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType());
    }

    @Test
    public void saveFleaPrivilegeGroupRel() throws CommonException {
        Long privilegeGroupId = 1000L;
        String privilegeGroupName = "菜单访问";
        Long privilegeId = 1000L;
        String privilegeName = "访问《控制台》菜单";
        // 新建权限组关联权限POJO对象
        FleaPrivilegeGroupRelPOJO privilegeGroupRelPOJO = FleaAuthPOJOUtils.newPrivilegeGroupRelPrivilegePOJO(privilegeGroupId, privilegeGroupName, privilegeId, privilegeName);

        // 保存Flea权限组关联
        this.fleaPrivilegeGroupRelSV.saveFleaPrivilegeGroupRel(privilegeGroupRelPOJO);
    }
}