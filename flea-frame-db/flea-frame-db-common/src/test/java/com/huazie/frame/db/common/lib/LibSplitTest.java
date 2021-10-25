package com.huazie.frame.db.common.lib;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.db.common.lib.split.ILibSplit;
import com.huazie.frame.db.common.lib.split.config.LibSplitConfig;
import com.huazie.frame.db.common.lib.split.impl.AlphabetLibSplitImpl;
import com.huazie.frame.db.common.lib.split.impl.NumberLibSplitImpl;
import com.huazie.frame.db.common.lib.split.impl.UpperAlphabetLibSplitImpl;
import org.junit.Test;

/**
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class LibSplitTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LibSplitTest.class);

    @Test
    public void testNumberListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit numberLibSplit = new NumberLibSplitImpl();
            LOGGER.debug("SEQ = {}", numberLibSplit.convert("f", 4));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testAlphabetListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit alphabetLibSplit = new AlphabetLibSplitImpl();
            LOGGER.debug("SEQ = {}", alphabetLibSplit.convert("0", 4));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testUpperAlphabetListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit upperAlphabetLibSplit = new UpperAlphabetLibSplitImpl();
            LOGGER.debug("SEQ = {}", upperAlphabetLibSplit.convert("9", 2));
        } catch (CommonException e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testLibSplitConfig() {
        LOGGER.debug("Libs = {}", LibSplitConfig.getConfig().getLibs());
    }
}
