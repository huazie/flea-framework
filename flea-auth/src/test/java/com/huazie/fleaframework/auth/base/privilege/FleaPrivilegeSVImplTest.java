package com.huazie.fleaframework.auth.base.privilege;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
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
public class FleaPrivilegeSVImplTest {

    @Resource(name = "fleaPrivilegeSV")
    private IFleaPrivilegeSV fleaPrivilegeSV;

    @Resource(name = "fleaPrivilegeGroupSV")
    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV;

    @Test
    public void queryPrivilegeInUse() throws CommonException {
        this.fleaPrivilegeSV.queryPrivilegeInUse(1000L);
    }

    @Test
    public void queryPrivilegesInUse4Page() throws CommonException {
        this.fleaPrivilegeSV.queryPrivilegesInUse4Page(null, null, 1, 10);
    }

    @Test
    public void queryPrivilegesInUse() throws CommonException {
        this.fleaPrivilegeSV.queryPrivilegesInUse("控制台", null);
    }

    @Test
    public void queryPrivilegesInUseCount() throws CommonException {
        this.fleaPrivilegeSV.queryPrivilegesInUseCount(null, null);
    }

    @Test
    public void savePrivilege() throws CommonException {
        // 获取菜单访问相关的主权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.MENU.getType());

        // 添加权限【访问《XXX》菜单】
        String menuName = "控制台";
        FleaPrivilegePOJO fleaPrivilegePOJO = FleaAuthPOJOUtils.newFleaPrivilegePOJOForMenu(menuName, fleaPrivilegeGroup);
        fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO);
    }
}