package com.huazie.frame.common.log4j.helpers;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

/**
 * <p> Flea Pattern Parser </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPatternParser extends PatternParser {

    public FleaPatternParser(String pattern) {
        super(pattern);
    }

    @Override
    protected void finalizeConverter(char c) {
        PatternConverter pc = null;
        boolean isAddConverter = false;
        switch (c) {
            // 账户编号
            case 'a':
                pc = new UserSessionPatternConverter(formattingInfo, "ACCT_ID");
                break;
            default:
                super.finalizeConverter(c);
                isAddConverter = true;
        }
        if (!isAddConverter) {
            addConverter(pc);
        }
    }

    /**
     * <p> 自定义用户Session模式转换类 </p>
     *
     * @author huazie
     * @version 1.0.0
     * @since 1.0.0
     */
    private static class UserSessionPatternConverter extends PatternConverter {

        private String key; // 用户Session键

        UserSessionPatternConverter(FormattingInfo formattingInfo, String key) {
            super(formattingInfo);
            this.key = key;
        }

        @Override
        protected String convert(LoggingEvent event) {
            IFleaUser fleaUser = FleaSessionManager.getUserInfo();
            if (ObjectUtils.isNotEmpty(fleaUser)) {
                return StringUtils.valueOf(fleaUser.get(key));
            }
            return "";
        }
    }

}
