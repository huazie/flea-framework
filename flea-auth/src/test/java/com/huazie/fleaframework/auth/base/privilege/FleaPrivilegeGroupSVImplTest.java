package com.huazie.fleaframework.auth.base.privilege;

import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
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
public class FleaPrivilegeGroupSVImplTest {

    @Resource(name = "fleaPrivilegeGroupSV")
    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV;

    @Test
    public void queryPrivilegeGroupInUse() throws CommonException {
        fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(1000L);
    }

    @Test
    public void queryPrivilegeGroupsInUse4Page() throws CommonException {
        fleaPrivilegeGroupSV.queryPrivilegeGroupsInUse4Page("", null, null, 1, 2);
    }

    @Test
    public void queryPrivilegeGroupsInUse() throws CommonException {
        fleaPrivilegeGroupSV.queryPrivilegeGroupsInUse("访问", 1, null);
    }

    @Test
    public void queryPrivilegeGroupsInUseCount() throws CommonException {
        fleaPrivilegeGroupSV.queryPrivilegeGroupsInUseCount("", null, null);
    }

    @Test
    public void queryMainPrivilegeGroupInUse() throws CommonException {
        fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.MENU.getType());
    }

    @Test
    public void savePrivilegeGroupForMenu() throws CommonException {
        int isMain = FleaAuthConstants.PrivilegeModuleConstants.MAIN;
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForMenu("菜单访问", "", isMain, "");
        fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    @Test
    public void savePrivilegeGroupForOperation() throws CommonException {
        int isMain = FleaAuthConstants.PrivilegeModuleConstants.MAIN;
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForOperation("操作执行", "", isMain, "");
        fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    @Test
    public void savePrivilegeGroupForElement() throws CommonException {
        int isMain = FleaAuthConstants.PrivilegeModuleConstants.MAIN;
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForElement("元素展示", "", isMain, "");
        fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    @Test
    public void savePrivilegeGroupForResource() throws CommonException {
        int isMain = FleaAuthConstants.PrivilegeModuleConstants.MAIN;
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForResource("资源调用", "", isMain, "");
        fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    @Test
    public void savePrivilegeGroupForFleaFSResource() throws CommonException {
        int isMain = FleaAuthConstants.PrivilegeModuleConstants.NOT_MAIN;
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForResource("FleaFS资源调用", "", isMain, "该权限组包含了【FleaFS资源调用】相关的权限，FleaFS后续新增资源调用权限也需要关联该权限组");
        fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

}