package com.huazie.frame;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.tools.i18n.FleaI18NMain;
import org.junit.Test;

import java.util.Locale;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaI18NTest {

    @Test
    public void testFleaI18N() {
        FleaI18nHelper.i18nForCommon("COMMON_I18N_00008");
    }

    public static void main(String[] args) throws Exception{
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaI18NMain main = new FleaI18NMain();
        main.setVisible(true);
    }
}
