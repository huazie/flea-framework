package com.huazie.frame.common.log4j.helpers;

import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.slf4j.LoggerUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Arrays;
import java.util.Map;

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
            // 账号
            case 'A':
                pc = new UserSessionPatternConverter(formattingInfo, "ACCOUNT_CODE");
                break;
            // 方法名
            case 'M':
                pc = new FleaMDCPatternConverter(formattingInfo, LoggerUtils.MDC_KEY_MN);
                break;
            // 方法参数名
            case 'P':
                pc = new FleaMDCPatternConverter(formattingInfo, LoggerUtils.MDC_KEY_MPN);
                break;
            // 类全名【含包目录】
            case 'c':
                pc = new FleaMDCPatternConverter(formattingInfo, LoggerUtils.MDC_KEY_CFN);
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

    /**
     * <p> 自定义MDC日志上下文模式转换类 </p>
     *
     * @author huazie
     * @version 1.0.0
     * @since 1.0.0
     */
    private static class FleaMDCPatternConverter extends PatternConverter {

        private String key;

        FleaMDCPatternConverter(FormattingInfo formattingInfo, String key) {
            super(formattingInfo);
            this.key = key;
        }

        @Override
        public String convert(LoggingEvent event) {
            if (key == null) {
                StringBuffer buf = new StringBuffer("{");
                Map properties = event.getProperties();
                if (properties.size() > 0) {
                    Object[] keys = properties.keySet().toArray();
                    Arrays.sort(keys);
                    for (int i = 0; i < keys.length; i++) {
                        buf.append('{');
                        buf.append(keys[i]);
                        buf.append(',');
                        buf.append(properties.get(keys[i]));
                        buf.append('}');
                    }
                }
                buf.append('}');
                return buf.toString();
            } else {
                Object val = event.getMDC(key);
                if (val == null) {
                    return null;
                } else {
                    return val.toString();
                }
            }
        }

    }

}
