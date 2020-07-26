package com.huazie.frame.auth.function;

import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.auth.common.FunctionTypeEnum;
import com.huazie.frame.auth.common.MenuLevelEnum;
import com.huazie.frame.auth.user.UserAuthTest;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FunctionAuthTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testSaveMenu() {

        FleaMenu fleaMenu = new FleaMenu();
        fleaMenu.setMenuCode("console"); // 菜单编码
        fleaMenu.setMenuName("控制台"); // 菜单名称
        fleaMenu.setMenuIcon("dashboard"); // 菜单FontAwesome小图标
        fleaMenu.setMenuSort(1);  // 菜单展示顺序(同一个父菜单下)
        fleaMenu.setMenuView("mgmt/console.html"); // 菜单对应页面（非叶子菜单的可以为空）
        fleaMenu.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel()); // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）
        fleaMenu.setMenuState(EntityStateEnum.IN_USE.getState()); // 菜单状态（0:下线，1: 在用 ）
        fleaMenu.setParentId(CommonConstants.NumeralConstants.MINUS_ONE); // 父菜单编号
        fleaMenu.setCreateDate(DateUtils.getCurrentTime());
        fleaMenu.setEffectiveDate(fleaMenu.getCreateDate());
        fleaMenu.setExpiryDate(DateUtils.getExpiryTimeForever());
        fleaMenu.setRemarks("控制台，展示收藏夹，快捷菜单入口");

        try {
            IFleaMenuSV fleaMenuSV = (IFleaMenuSV) applicationContext.getBean("fleaMenuSV");
            fleaMenuSV.save(fleaMenu);
        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }

    }

    @Test
    public void testSaveMenuAttr() {

        FleaFunctionAttr fleaFunctionAttr = new FleaFunctionAttr();

        // 功能编号
        fleaFunctionAttr.setFunctionId(1L);
        // 功能类型(菜单、操作、元素)
        fleaFunctionAttr.setFunctionType(FunctionTypeEnum.MENU.getType());
        // 属性码
        fleaFunctionAttr.setAttrCode(FleaAuthConstants.FunctionConstants.MENU_ATTR_CODE_SYSTEM_IN_USE);
        // 属性值
        fleaFunctionAttr.setAttrValue("1000");
        // 属性状态(0: 删除 1: 正常）
        fleaFunctionAttr.setState(EntityStateEnum.IN_USE.getState());
        fleaFunctionAttr.setCreateDate(DateUtils.getCurrentTime());
        fleaFunctionAttr.setEffectiveDate(fleaFunctionAttr.getCreateDate());
        fleaFunctionAttr.setExpiryDate(DateUtils.getExpiryTimeForever());
        fleaFunctionAttr.setRemarks("配置正在使用中的系统");

        try {
            IFleaFunctionAttrSV fleaFunctionAttrSV = (IFleaFunctionAttrSV) applicationContext.getBean("fleaFunctionAttrSV");
            fleaFunctionAttrSV.save(fleaFunctionAttr);
        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }

    }
}
