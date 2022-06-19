package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.base.privilege.AddFleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaPrivilegeModuleSV;
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
public class FleaPrivilegeModuleSVImplTest {

    @Resource(name = "fleaPrivilegeModuleSV")
    private IFleaPrivilegeModuleSV fleaPrivilegeModuleSV;

    @Resource(name = "fleaPrivilegeGroupSV")
    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV;

    @Test
    public void addFleaPrivilege() throws CommonException {
        // 获取菜单访问相关的主权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.MENU.getType());

        // 添加权限【访问《XXX》菜单】
        String menuName = "控制台";
        FleaPrivilegePOJO fleaPrivilegePOJO = FleaAuthPOJOUtils.newFleaPrivilegePOJOForMenu(menuName, fleaPrivilegeGroup);
        fleaPrivilegeModuleSV.addFleaPrivilege(fleaPrivilegePOJO);
    }

    @Test
    public void modifyFleaPrivilege() throws CommonException {
        FleaPrivilegePOJO fleaPrivilegePOJO = new FleaPrivilegePOJO();
        fleaPrivilegePOJO.setPrivilegeName("访问《控制台》菜单");
        fleaPrivilegePOJO.setPrivilegeDesc("拥有可以访问《控制台》菜单的权限");
        fleaPrivilegePOJO.setGroupId(-1L);
        fleaPrivilegePOJO.setRemarks("【访问《控制台》菜单】权限对应【控制台】菜单，新增菜单时自动生成");
        fleaPrivilegeModuleSV.modifyFleaPrivilege(1000L, fleaPrivilegePOJO);
    }

    @Test
    public void addFleaPrivilegeGroup() throws CommonException {
        AddFleaPrivilegeGroup fleaPrivilegeGroupAdd = new AddFleaPrivilegeGroup(fleaPrivilegeModuleSV);
        fleaPrivilegeGroupAdd.addFleaPrivilegeGroup();
    }

    @Test
    public void modifyFleaPrivilegeGroup() throws CommonException {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = new FleaPrivilegeGroupPOJO();
        fleaPrivilegeGroupPOJO.setPrivilegeGroupName("菜单访问");
        fleaPrivilegeGroupPOJO.setPrivilegeGroupDesc("与【菜单访问】相关的权限归属的权限组");
        fleaPrivilegeGroupPOJO.setRemarks("该权限组包含了【菜单访问】相关的权限");
        fleaPrivilegeModuleSV.modifyFleaPrivilegeGroup(1000L, fleaPrivilegeGroupPOJO);
    }

    @Test
    public void privilegeGroupRelPrivilege() throws CommonException {
        Long privilegeGroupId = 1000L;
        Long privilegeId = 1000L;
        // 【菜单访问】权限组关联【访问《控制台》菜单】权限
        fleaPrivilegeModuleSV.privilegeGroupRelPrivilege(privilegeGroupId, privilegeId, null);
    }
}