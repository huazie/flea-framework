package com.huazie.frame.auth;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.frame.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.frame.auth.util.FleaMenuTree;
import com.huazie.frame.auth.util.FueluxMenuTree;
import com.huazie.frame.common.FleaTree;
import com.huazie.frame.common.exception.CommonException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MenuTreeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuTreeTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();

        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        list.add("eeee");

        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String temp = it.next();
            if ("bbbb".equals(temp) || "dddd".equals(temp)) {
                it.remove();
            }
        }

        LOGGER.debug("list = {}", list);
    }

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
        LOGGER.debug("Tree = \n{}", fleaTree.toMapList());
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
    public void testFleaMenuTree() {

        IFleaAuthSV fleaAuthSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
        Long accountId = 10000L;
        Long systemAcctId = 1000L;
        try {
            List<FleaMenu> fleaMenuList = fleaAuthSV.queryAllAccessibleMenus(accountId, systemAcctId);

            FleaMenuTree fleaMenuTree = new FleaMenuTree("跳蚤管家");
            fleaMenuTree.addAll(fleaMenuList);

            LOGGER.debug("MENU_TREE = \n{}", fleaMenuTree);

            LOGGER.debug("MENU = \n{}", fleaMenuTree.toMapList());

        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }
    }

    @Test
    public void testFueluxMenuTree() {

        IFleaAuthSV fleaAuthSV = (IFleaAuthSV) applicationContext.getBean("fleaAuthSV");
        Long accountId = 10000L;
        Long systemAcctId = 1000L;
        try {
            List<FleaMenu> fleaMenuList = fleaAuthSV.queryAllAccessibleMenus(accountId, systemAcctId);

            FueluxMenuTree fueluxMenuTree = new FueluxMenuTree("跳蚤管家", null);
            fueluxMenuTree.addAll(fleaMenuList);

            LOGGER.debug("MENU_TREE = \n{}", fueluxMenuTree);

            LOGGER.debug("MENU = \n{}", fueluxMenuTree.toMapList());

        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }
    }

    @Test
    public void testFueluxMenuTree1() {

        IFleaFunctionModuleSV fleaFunctionModuleSV = (IFleaFunctionModuleSV) applicationContext.getBean("fleaFunctionModuleSV");
        try {
            List<FleaMenu> fleaMenuList = fleaFunctionModuleSV.queryValidMenus(null);

            FueluxMenuTree fueluxMenuTree = new FueluxMenuTree("FleaFrameAuth", null);
            fueluxMenuTree.addAll(fleaMenuList);

            LOGGER.debug("MENU_TREE = \n{}", fueluxMenuTree);

            LOGGER.debug("MENU = \n{}", fueluxMenuTree.toMapList());

        } catch (CommonException e) {
            LOGGER.error("Exception: ", e);
        }
    }
}
