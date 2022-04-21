package com.huazie.fleaframework.db.common.lib;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.lib.split.ILibSplit;
import com.huazie.fleaframework.db.common.lib.split.config.LibSplitConfig;
import com.huazie.fleaframework.db.common.lib.split.impl.DecLowercaseLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.DecNumberLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.DecUppercaseLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.HexLowercaseLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.HexNumberLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.HexUppercaseLibSplitImpl;
import org.junit.Test;

/**
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class LibSplitTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LibSplitTest.class);

    @Test
    public void testDecNumberListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit numberLibSplit = new DecNumberLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", numberLibSplit.convert("0", 2));
            LOGGER.debug("SEQ2 = {}", numberLibSplit.convert("1", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDecLowercaseListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit decLowercaseLibSplit = new DecLowercaseLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", decLowercaseLibSplit.convert("2", 2));
            LOGGER.debug("SEQ2 = {}", decLowercaseLibSplit.convert("3", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDecUppercaseListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit decUppercaseLibSplit = new DecUppercaseLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", decUppercaseLibSplit.convert("8", 2));
            LOGGER.debug("SEQ2 = {}", decUppercaseLibSplit.convert("9", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testHexNumberListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit numberLibSplit = new HexNumberLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", numberLibSplit.convert("c", 4));
            LOGGER.debug("SEQ2 = {}", numberLibSplit.convert("D", 4));
            LOGGER.debug("SEQ3 = {}", numberLibSplit.convert("E", 4));
            LOGGER.debug("SEQ4 = {}", numberLibSplit.convert("f", 4));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testHexLowercaseListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit hexLowercaseLibSplit = new HexLowercaseLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", hexLowercaseLibSplit.convert("8", 4));
            LOGGER.debug("SEQ2 = {}", hexLowercaseLibSplit.convert("9", 4));
            LOGGER.debug("SEQ3 = {}", hexLowercaseLibSplit.convert("A", 4));
            LOGGER.debug("SEQ4 = {}", hexLowercaseLibSplit.convert("b", 4));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testHexUppercaseListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit hexUppercaseLibSplit = new HexUppercaseLibSplitImpl();
            LOGGER.debug("SEQ = {}", hexUppercaseLibSplit.convert("6", 2));
            LOGGER.debug("SEQ = {}", hexUppercaseLibSplit.convert("7", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testLibSplitConfig() {
        LOGGER.debug("Libs = {}", LibSplitConfig.getConfig().getFleaLibSplit());
        LOGGER.debug("Lib = {}", LibSplitConfig.getConfig().getLib("fleafs"));
    }
}
