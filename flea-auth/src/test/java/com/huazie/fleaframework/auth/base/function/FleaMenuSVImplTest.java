package com.huazie.fleaframework.auth.base.function;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.fleaframework.auth.common.MenuLevelEnum;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
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
public class FleaMenuSVImplTest {

    @Resource(name = "fleaMenuSV")
    private IFleaMenuSV fleaMenuSV;

    @Test
    public void queryValidMenu() throws CommonException {
        fleaMenuSV.queryValidMenu(1000L);
    }

    @Test
    public void queryValidMenus() throws CommonException {
        fleaMenuSV.queryValidMenus("console", "控制", 1, null);
    }

    @Test
    public void queryAllValidMenus() throws CommonException {
        fleaMenuSV.queryAllValidMenus();
    }

    @Test
    public void saveMenu() throws CommonException {

        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();

        fleaMenuPOJO.setMenuCode("console"); // 菜单编码
        fleaMenuPOJO.setMenuName("控制台"); // 菜单名称
        fleaMenuPOJO.setMenuIcon("dashboard"); // 菜单FontAwesome小图标
        fleaMenuPOJO.setMenuSort(1);  // 菜单展示顺序(同一个父菜单下)
        fleaMenuPOJO.setMenuView("mgmt/console.html"); // 菜单对应页面（非叶子菜单的可以为空）
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel()); // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）
        fleaMenuPOJO.setRemarks("控制台，展示收藏夹，快捷菜单入口");

        fleaMenuSV.saveFleaMenu(fleaMenuPOJO);
    }
}