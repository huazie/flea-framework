package com.huazie.fleaframework.db.common.lib;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.db.common.lib.split.ILibSplit;
import com.huazie.fleaframework.db.common.lib.split.LibSplitEnum;
import com.huazie.fleaframework.db.common.lib.split.config.LibSplitConfig;
import org.junit.Test;

/**
 * 分库转换测试类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.1.0
 */
public class LibSplitTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LibSplitTest.class);

    @Test
    public void testDecNumberListSplitImpl() {
        try {
//            FleaFrameManager.getManager().setLocale(Locale.US);
            ILibSplit numberLibSplit = LibSplitEnum.DEC_NUM.newInstance();
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
            ILibSplit decLowercaseLibSplit = LibSplitEnum.DEC_ABC.newInstance();
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
            ILibSplit decUppercaseLibSplit = LibSplitEnum.DEC_UABC.newInstance();
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
            ILibSplit numberLibSplit = LibSplitEnum.HEX_NUM.newInstance();
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
            ILibSplit hexLowercaseLibSplit = LibSplitEnum.HEX_ABC.newInstance();
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
            ILibSplit hexUppercaseLibSplit = LibSplitEnum.HEX_UABC.newInstance();
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
