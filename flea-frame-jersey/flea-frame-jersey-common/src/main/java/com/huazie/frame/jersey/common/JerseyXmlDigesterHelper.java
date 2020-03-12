package com.huazie.frame.jersey.common;

import com.huazie.frame.common.XmlDigesterHelper;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.jersey.common.exception.FleaJerseyFilterException;
import com.huazie.frame.jersey.common.filter.config.After;
import com.huazie.frame.jersey.common.filter.config.Before;
import com.huazie.frame.jersey.common.filter.config.Error;
import com.huazie.frame.jersey.common.filter.config.Filter;
import com.huazie.frame.jersey.common.filter.config.FilterChain;
import com.huazie.frame.jersey.common.filter.config.Jersey;
import com.huazie.frame.jersey.common.filter.config.Service;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * XML解析类（涉及flea-jersey-filter.xml jersey过滤器链）
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyXmlDigesterHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyXmlDigesterHelper.class);

    private static volatile JerseyXmlDigesterHelper xmlDigester;

    private static Boolean isInit = Boolean.FALSE;
    private static Boolean isJerseyFilterInit = Boolean.FALSE;

    private static Jersey jersey;

    /**
     * <p> 只允许通过getInstance()获取 XML解析类 </p>
     */
    private JerseyXmlDigesterHelper() {
    }

    /**
     * <p> 获取XML工具类 </p>
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static JerseyXmlDigesterHelper getInstance() {
        if (isInit.equals(Boolean.FALSE)) {
            synchronized (isInit) {
                if (isInit.equals(Boolean.FALSE)) {
                    isInit = Boolean.TRUE;
                    xmlDigester = new JerseyXmlDigesterHelper();
                }
            }
        }
        return xmlDigester;
    }

    /**
     * <p> 获取Jersey配置 </p>
     *
     * @return Jersey配置对象
     * @since 1.0.0
     */
    public Jersey getJersey() {
        if (ObjectUtils.isEmpty(jersey)) {
            synchronized (isJerseyFilterInit) {
                if (isJerseyFilterInit.equals(Boolean.FALSE)) {
                    try {
                        jersey = newJerseyFilter();
                        isJerseyFilterInit = Boolean.TRUE;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return jersey;
    }

    private Jersey newJerseyFilter() throws Exception {
        Jersey obj;

        String fileName = FleaJerseyConstants.JerseyFilterConstants.JSERSY_FILTER_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(FleaJerseyConstants.JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(FleaJerseyConstants.JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("JerseyXmlDigesterHelper##newJerseyFilter Use the specified flea-jersey-filter.xml : " + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JerseyXmlDigesterHelper##newJerseyFilter() Use the current flea-jersey-filter.xml : " + fileName);
            LOGGER.debug("JerseyXmlDigesterHelper##newJerseyFilter() Start to parse the flea-jersey-filter.xml");
        }

        try (InputStream input = IOUtils.getInputStreamFromClassPath(fileName)) {

            if (ObjectUtils.isEmpty(input)) {
                // 该路径下【0】找不到指定配置文件
                throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000001", fileName);
            }

            obj = XmlDigesterHelper.parse(input, newFleaJerseyFilterFileDigester(), Jersey.class);

        } catch (Exception e) {
            // XML转化异常：
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000002", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JerseyXmlDigesterHelper##newJerseyFilter() End to parse the flea-jersey-filter.xml");
        }

        return obj;
    }

    /**
     * <p> 解析flea-jersey-filter.xml的Digester对象 </p>
     *
     * @return Digester对象
     * @since 1.0.0
     */
    private Digester newFleaJerseyFilterFileDigester() {
        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("jersey", Jersey.class.getName());
        digester.addSetProperties("jersey");

        // 过滤器链
        digester.addObjectCreate("jersey/filterchain", FilterChain.class.getName());
        digester.addSetProperties("jersey/filterchain");

        // 前置过滤器链
        digester.addObjectCreate("jersey/filterchain/before", Before.class.getName());
        digester.addSetProperties("jersey/filterchain/before");

        digester.addObjectCreate("jersey/filterchain/before/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filterchain/before/filter");
        // 业务服务过滤器链
        digester.addObjectCreate("jersey/filterchain/service", Service.class.getName());
        digester.addSetProperties("jersey/filterchain/service");

        digester.addObjectCreate("jersey/filterchain/service/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filterchain/service/filter");
        // 后置过滤器链
        digester.addObjectCreate("jersey/filterchain/after", After.class.getName());
        digester.addSetProperties("jersey/filterchain/after");

        digester.addObjectCreate("jersey/filterchain/after/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filterchain/after/filter");
        // 异常过滤器链
        digester.addObjectCreate("jersey/filterchain/error", Error.class.getName());
        digester.addSetProperties("jersey/filterchain/error");

        digester.addObjectCreate("jersey/filterchain/error/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filterchain/error/filter");

        digester.addSetNext("jersey/filterchain", "setFilterChain", FilterChain.class.getName());

        digester.addSetNext("jersey/filterchain/before", "setBefore", Before.class.getName());
        digester.addSetNext("jersey/filterchain/before/filter", "addBeforeFilter", Filter.class.getName());

        digester.addSetNext("jersey/filterchain/service", "setService", Service.class.getName());
        digester.addSetNext("jersey/filterchain/service/filter", "addServiceFilter", Filter.class.getName());

        digester.addSetNext("jersey/filterchain/after", "setAfter", After.class.getName());
        digester.addSetNext("jersey/filterchain/after/filter", "addAfterFilter", Filter.class.getName());

        digester.addSetNext("jersey/filterchain/error", "setError", Error.class.getName());
        digester.addSetNext("jersey/filterchain/error/filter", "addErrorFilter", Filter.class.getName());
        return digester;
    }

}
