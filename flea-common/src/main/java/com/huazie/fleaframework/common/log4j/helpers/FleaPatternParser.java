package com.huazie.fleaframework.common.log4j.helpers;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LocationInfo;
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
                pc = new UserSessionPatternConverter(formattingInfo, "ACCOUNT_ID");
                break;
            // 账号
            case 'A':
                pc = new UserSessionPatternConverter(formattingInfo, "ACCOUNT_CODE");
                break;
            // 方法名
            case 'M':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_MN, c);
                break;
            // 方法参数名
            case 'P':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_MPN, c);
                break;
            // 日志类名
            case 'c':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_CFN, c, extractPrecisionOption());
                break;
            // 类全名
            case 'C':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_CFN, c, extractPrecisionOption());
                break;
            // 代码行数
            case 'L':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_LN, c);
                break;
            // 源文件名
            case 'F':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_FN, c);
                break;
            // 输出日志事件的发生位置
            case 'l':
                pc = new FleaMDCPatternConverter(formattingInfo, FleaLogger.MDC_KEY_LOC, c);
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

        private String key; // 日志上下文关键字

        private char symbol; // 符号

        private int precision; // 精度

        FleaMDCPatternConverter(FormattingInfo formattingInfo, String key, char symbol) {
            super(formattingInfo);
            this.key = key;
            this.symbol = symbol;
        }

        FleaMDCPatternConverter(FormattingInfo formattingInfo, String key, char symbol, int precision) {
            super(formattingInfo);
            this.key = key;
            this.symbol = symbol;
            this.precision = precision;
        }

        @Override
        public String convert(LoggingEvent event) {

            Object val = event.getMDC(key);

            String result;
            if (ObjectUtils.isEmpty(val)) {
                result = convert1(event);
            } else {
                result = val.toString();
            }

            if (precision <= 0)
                return result;
            else {
                assert result != null;
                int len = result.length();

                // We substract 1 from 'len' when assigning to 'end' to avoid out of
                // bounds exception in return r.substring(end+1, len). This can happen if
                // precision is 1 and the category name ends with a dot.
                int end = len - 1;
                for (int i = precision; i > 0; i--) {
                    end = result.lastIndexOf('.', end - 1);
                    if (end == -1)
                        return result;
                }
                return result.substring(end + 1, len);
            }
        }

        /**
         * <p> 兼容log4j </p>
         *
         * @param event 日志事件
         * @return 不同的日志信息
         * @since 1.0.0
         */
        private String convert1(LoggingEvent event) {
            LocationInfo locationInfo = event.getLocationInformation();
            switch (symbol) {
                // 方法名
                case 'M':
                    return locationInfo.getMethodName();
                // 日志类名
                case 'c':
                    return event.getLoggerName();
                // 类全名
                case 'C':
                    return locationInfo.getClassName();
                // 代码行数
                case 'L':
                    return locationInfo.getLineNumber();
                // 源文件名
                case 'F':
                    return locationInfo.getFileName();
                // 输出日志事件的发生位置
                case 'l':
                    return locationInfo.fullInfo;
                default:
                    return null;
            }
        }
    }

}
