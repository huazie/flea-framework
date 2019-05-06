package com.huazie.frame.tools;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.tools.i18n.FleaI18NMain;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaI18NTest {

    public static void main(String[] args) throws Exception{
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaI18NMain main = new FleaI18NMain();
        main.setVisible(true);
    }

}
