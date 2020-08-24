package com.huazie.frame.auth;

import com.huazie.frame.common.FleaTree;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
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
        fleaTree.addTreeNote("a2", 2L, 2, null, -1L, 1);
        fleaTree.addTreeNote("a112", 112L, 4, "a11", 11L, 3);
        fleaTree.addTreeNote("a111", 111L, 4, "a11", 11L, 3);
        fleaTree.addTreeNote("a11", 11L, 3, "a1", 1L, 2);
        fleaTree.addTreeNote("a422", 422L, 4, "a42", 42L, 3);
        fleaTree.addTreeNote("a4221", 4221L, 5, "a422", 422L, 4);
        fleaTree.addTreeNote("a4211", 4211L, 5, "a421", 421L, 4);
        fleaTree.addTreeNote("a4", 4L, 2, null, -1L, 1);
        LOGGER.debug("FleaTree = \n{}", fleaTree);
    }
}
