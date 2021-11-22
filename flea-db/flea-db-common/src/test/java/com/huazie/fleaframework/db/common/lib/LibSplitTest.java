package com.huazie.fleaframework.db.common.lib;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.lib.split.ILibSplit;
import com.huazie.fleaframework.db.common.lib.split.config.LibSplitConfig;
import com.huazie.fleaframework.db.common.lib.split.impl.DecAlphabetLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.DecNumberLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.DecUpperAlphabetLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.HexAlphabetLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.HexNumberLibSplitImpl;
import com.huazie.fleaframework.db.common.lib.split.impl.HexUpperAlphabetLibSplitImpl;
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
    public void testDecAlphabetListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit alphabetLibSplit = new DecAlphabetLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", alphabetLibSplit.convert("2", 2));
            LOGGER.debug("SEQ2 = {}", alphabetLibSplit.convert("3", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testDecUpperAlphabetListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit upperAlphabetLibSplit = new DecUpperAlphabetLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", upperAlphabetLibSplit.convert("8", 2));
            LOGGER.debug("SEQ2 = {}", upperAlphabetLibSplit.convert("9", 2));
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
    public void testHexAlphabetListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit alphabetLibSplit = new HexAlphabetLibSplitImpl();
            LOGGER.debug("SEQ1 = {}", alphabetLibSplit.convert("8", 4));
            LOGGER.debug("SEQ2 = {}", alphabetLibSplit.convert("9", 4));
            LOGGER.debug("SEQ3 = {}", alphabetLibSplit.convert("A", 4));
            LOGGER.debug("SEQ4 = {}", alphabetLibSplit.convert("b", 4));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testHexUpperAlphabetListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit upperAlphabetLibSplit = new HexUpperAlphabetLibSplitImpl();
            LOGGER.debug("SEQ = {}", upperAlphabetLibSplit.convert("6", 2));
            LOGGER.debug("SEQ = {}", upperAlphabetLibSplit.convert("7", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testLibSplitConfig() {
        LOGGER.debug("Libs = {}", LibSplitConfig.getConfig().getLibs());
    }
}
