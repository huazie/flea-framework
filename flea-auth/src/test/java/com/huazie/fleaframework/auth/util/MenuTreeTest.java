package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.common.FleaTree;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 已验证
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MenuTreeTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(MenuTreeTest.class);

    @Resource(name = "fleaAuthSV")
    private IFleaAuthSV fleaAuthSV;

    @Resource(name = "fleaFunctionModuleSV")
    private IFleaFunctionModuleSV fleaFunctionModuleSV;

    @Test
    public void testFleaTree() {

        FleaTree<String> fleaTree = new FleaTree<>();
        fleaTree.addRootTreeNote("a");
        fleaTree.addTreeNote("a112", 112L, 4, "a11", 11L, 3);
        fleaTree.addTreeNote("a111", 111L, 4, "a11", 11L, 3);
        fleaTree.addTreeNote("a11", 11L, 3, "a1", 1L, 2);
        fleaTree.addTreeNote("a42", 42L, 3, "a4", 4L, 2);
        fleaTree.addTreeNote("a12", 12L, 3, "a1", 1L, 2);
        fleaTree.addTreeNote("a311", 311L, 4, "a31", 31L, 3);
        fleaTree.addTreeNote("a1", 1L, 2, null, -1L, 1);
        fleaTree.addTreeNote("a121", 121L, 4, "a12", 12L, 3);
        fleaTree.addTreeNote("a23", 23L, 3, "a2", 2L, 2);
        fleaTree.addTreeNote("a22", 22L, 3, "a2", 2L, 2);
        fleaTree.addTreeNote("a21", 21L, 3, "a2", 2L, 2);
        fleaTree.addTreeNote("a3", 3L, 2, null, -1L, 1);
        fleaTree.addTreeNote("a31", 31L, 3, "a3", 3L, 2);
        fleaTree.addTreeNote("a41", 41L, 3, "a4", 4L, 2);
        fleaTree.addTreeNote("a421", 421L, 4, "a42", 42L, 3);
        fleaTree.addTreeNote("a422", 422L, 4, "a42", 42L, 3);
        fleaTree.addTreeNote("a4221", 4221L, 5, "a422", 422L, 4);
        fleaTree.addTreeNote("a4211", 4211L, 5, "a421", 421L, 4);
        fleaTree.addTreeNote("a2", 2L, 2, null, -1L, 1);
        fleaTree.addTreeNote("a4", 4L, 2, null, -1L, 1);
        LOGGER.debug("FleaTree = \n{}", fleaTree);
        LOGGER.debug("Size = {}", fleaTree.size());
        LOGGER.debug("TreeLeaf List= {}", fleaTree.getAllTreeLeafElement());
        LOGGER.debug("TreeLeaf = {}", fleaTree.getTreeLeafElement(22L));
        LOGGER.debug("Tree = \n{}", fleaTree.toMapList(false));
        LOGGER.debug("Tree【root】 = \n{}", fleaTree.toMapList(true));
    }

    @Test
    public void testSymbolLength() {
        LOGGER.debug("├─ length = {}", "├─".length());
        LOGGER.debug("└─ length = {}", "└─".length());
        LOGGER.debug("│  ├─ length = {}", "│  ├─".length());
        LOGGER.debug("│  │  ├─ length = {}", "│  │  ├─".length());
        LOGGER.debug("│  │  │  ├─ length = {}", "│  │  │  ├─".length());
    }

    @Test
    public void testFleaMenuTree() throws CommonException {
        Long accountId = 10000L;
        Long systemAccountId = 1001L;

        List<FleaMenu> fleaMenuList = fleaAuthSV.queryAllAccessibleMenus(accountId, systemAccountId);

        FleaMenuTree fleaMenuTree = new FleaMenuTree("Flea管家");
        fleaMenuTree.addAll(fleaMenuList);

        LOGGER.debug("MENU_TREE = \n{}", fleaMenuTree);

        LOGGER.debug("MENU = \n{}", fleaMenuTree.toMapList(false));

        LOGGER.debug("MENU1 = \n{}", fleaMenuTree.toMapList(true));
    }

    @Test
    public void testFueluxMenuTree() throws CommonException {
        Long accountId = 10000L;
        Long systemAccountId = 1001L;

        List<FleaMenu> fleaMenuList = fleaAuthSV.queryAllAccessibleMenus(accountId, systemAccountId);

        FueluxMenuTree fueluxMenuTree = new FueluxMenuTree("Flea管家", null);
        fueluxMenuTree.addAll(fleaMenuList);

        LOGGER.debug("MENU_TREE = \n{}", fueluxMenuTree);

        LOGGER.debug("MENU = \n{}", fueluxMenuTree.toMapList(true));
    }

    @Test
    public void testFueluxMenuTree1() throws CommonException {
        List<FleaMenu> fleaMenuList = fleaFunctionModuleSV.queryValidMenus(null);

        FueluxMenuTree fueluxMenuTree = new FueluxMenuTree("FleaFrameAuth", null);
        fueluxMenuTree.addAll(fleaMenuList);

        LOGGER.debug("MENU_TREE = \n{}", fueluxMenuTree);

        LOGGER.debug("MENU = \n{}", fueluxMenuTree.toMapList(true));
    }
}
