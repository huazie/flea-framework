package com.huazie.fleaframework.auth.base.privilege;

import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
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
public class FleaPrivilegeRelSVImplTest {

    @Resource(name = "fleaPrivilegeRelSV")
    private IFleaPrivilegeRelSV fleaPrivilegeRelSV;

    @Test
    public void getPrivilegeRelList() throws CommonException {
        fleaPrivilegeRelSV.getPrivilegeRelList(1000L, AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
    }

    @Test
    public void getPrivilegeRelMenu() throws CommonException {
        fleaPrivilegeRelSV.getPrivilegeRelMenu(1000L);
    }

    @Test
    public void getPrivilegeRelOperation() throws CommonException {
        fleaPrivilegeRelSV.getPrivilegeRelOperation(1000L);
    }

    @Test
    public void getPrivilegeRelElement() throws CommonException {
        fleaPrivilegeRelSV.getPrivilegeRelElement(1000L);
    }

    @Test
    public void getPrivilegeRelResource() throws CommonException {
        fleaPrivilegeRelSV.getPrivilegeRelResource(1000L);
    }

    @Test
    public void savePrivilegeRel() throws CommonException {
        // 添加权限关联【访问《XXX》菜单】
        Long privilegeId = 1000L;
        Long menuId = 1000L;
        String menuName = "控制台";
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelMenuPOJO(privilegeId, menuId, menuName);
        fleaPrivilegeRelSV.savePrivilegeRel(fleaPrivilegeRelPOJO);
    }

}