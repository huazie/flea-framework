package com.huazie.fleaframework.auth.privilege;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PrivilegeAuthTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PrivilegeAuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertPrivilegeGroup() {

        FleaPrivilegeGroup fleaPrivilegeGroup = new FleaPrivilegeGroup();
        fleaPrivilegeGroup.setPrivilegeGroupName("菜单访问");
        fleaPrivilegeGroup.setPrivilegeGroupDesc("与访问菜单相关的权限归属的权限组");
        fleaPrivilegeGroup.setPrivilegeGroupState(EntityStateEnum.IN_USE.getState());
        fleaPrivilegeGroup.setCreateDate(DateUtils.getCurrentTime());
        fleaPrivilegeGroup.setRemarks("该权限组包含了访问菜单相关的权限");

        try {
            IFleaPrivilegeGroupSV fleaPrivilegeGroupSV = (IFleaPrivilegeGroupSV) applicationContext.getBean("fleaPrivilegeGroupSV");
            fleaPrivilegeGroupSV.save(fleaPrivilegeGroup);
        } catch (CommonException e) {
            LOGGER.error("Exception :", e);
        }
    }

    @Test
    public void testInsertPrivilege() {

        FleaPrivilege fleaPrivilege = new FleaPrivilege();
        fleaPrivilege.setPrivilegeName("访问《控制台》菜单");
        fleaPrivilege.setPrivilegeDesc("拥有可以访问《控制台》菜单的权限");
        fleaPrivilege.setGroupId(1000L);
        fleaPrivilege.setPrivilegeState(EntityStateEnum.IN_USE.getState());
        fleaPrivilege.setCreateDate(DateUtils.getCurrentTime());
        fleaPrivilege.setRemarks("【访问《控制台》菜单】权限对应【控制台】菜单，新增菜单时自动生成");

        try {
            IFleaPrivilegeSV fleaPrivilegeSV = (IFleaPrivilegeSV) applicationContext.getBean("fleaPrivilegeSV");
            fleaPrivilegeSV.save(fleaPrivilege);
        } catch (CommonException e) {
            LOGGER.error("Exception :", e);
        }
    }

    @Test
    public void testInsertPrivilegeRel() {

        FleaPrivilegeRel fleaPrivilegeRel = new FleaPrivilegeRel();
        fleaPrivilegeRel.setPrivilegeId(1000L);
        fleaPrivilegeRel.setRelId(1000L);
        fleaPrivilegeRel.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
        fleaPrivilegeRel.setRelState(EntityStateEnum.IN_USE.getState());
        fleaPrivilegeRel.setCreateDate(DateUtils.getCurrentTime());
        fleaPrivilegeRel.setRemarks("【控制台】菜单绑定【访问《控制台》菜单】权限, 新增菜单时自动生成");

        try {
            IFleaPrivilegeRelSV fleaPrivilegeRelSV = (IFleaPrivilegeRelSV) applicationContext.getBean("fleaPrivilegeRelSV");
            fleaPrivilegeRelSV.save(fleaPrivilegeRel);
        } catch (CommonException e) {
            LOGGER.error("Exception :", e);
        }
    }

    @Test
    public void testInsertPrivilegeGroupRel() {

        FleaPrivilegeGroupRel fleaPrivilegeGroupRel = new FleaPrivilegeGroupRel();
        fleaPrivilegeGroupRel.setPrivilegeGroupId(1000L);
        fleaPrivilegeGroupRel.setRelId(1000L);
        fleaPrivilegeGroupRel.setRelType(AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType());
        fleaPrivilegeGroupRel.setRelState(EntityStateEnum.IN_USE.getState());
        fleaPrivilegeGroupRel.setCreateDate(DateUtils.getCurrentTime());
        fleaPrivilegeGroupRel.setRemarks("【菜单访问】权限组关联【访问《控制台》菜单】权限");

        try {
            IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV = (IFleaPrivilegeGroupRelSV) applicationContext.getBean("fleaPrivilegeGroupRelSV");
            fleaPrivilegeGroupRelSV.save(fleaPrivilegeGroupRel);
        } catch (CommonException e) {
            LOGGER.error("Exception :", e);
        }

    }

    @Test
    public void testQueryPrivilegeRel() {
        try {
            IFleaPrivilegeRelSV fleaPrivilegeRelSV = (IFleaPrivilegeRelSV) applicationContext.getBean("fleaPrivilegeRelSV");
            fleaPrivilegeRelSV.getPrivilegeRelList(1000L, AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testQueryPrivilegeRelMenu() {
        try {
            IFleaPrivilegeRelSV fleaPrivilegeRelSV = (IFleaPrivilegeRelSV) applicationContext.getBean("fleaPrivilegeRelSV");
            fleaPrivilegeRelSV.getPrivilegeRelMenu(1000L);
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

    @Test
    public void testQueryPrivilegeGroupRel() {
        try {
            IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV = (IFleaPrivilegeGroupRelSV) applicationContext.getBean("fleaPrivilegeGroupRelSV");
            fleaPrivilegeGroupRelSV.getPrivilegeGroupRelList(1000L, AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType());
        } catch (CommonException e) {
            LOGGER.error("Exception = ", e);
        }
    }

}
