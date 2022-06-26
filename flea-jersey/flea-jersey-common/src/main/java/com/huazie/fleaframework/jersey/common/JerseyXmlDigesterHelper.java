package com.huazie.fleaframework.jersey.common;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.exception.FleaException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.xml.XmlDigesterHelper;
import com.huazie.fleaframework.jersey.common.exception.FleaJerseyFilterException;
import com.huazie.fleaframework.jersey.common.filter.config.After;
import com.huazie.fleaframework.jersey.common.filter.config.Before;
import com.huazie.fleaframework.jersey.common.filter.config.Error;
import com.huazie.fleaframework.jersey.common.filter.config.Filter;
import com.huazie.fleaframework.jersey.common.filter.config.FilterChain;
import com.huazie.fleaframework.jersey.common.filter.config.FilterI18nError;
import com.huazie.fleaframework.jersey.common.filter.config.I18nErrorMapping;
import com.huazie.fleaframework.jersey.common.filter.config.Jersey;
import com.huazie.fleaframework.jersey.common.filter.config.Service;
import org.apache.commons.digester.Digester;

import java.io.InputStream;

/**
 * XML解析类（涉及flea-jersey-filter.xml jersey过滤器链）
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyXmlDigesterHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JerseyXmlDigesterHelper.class);

    private static volatile JerseyXmlDigesterHelper xmlDigester;

    private final Object jerseyFilterInitLock = new Object();

    private static Jersey jersey;

    /**
     * 只允许通过getInstance()获取 XML解析类
     */
    private JerseyXmlDigesterHelper() {
    }

    /**
     * 获取XML工具类
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static JerseyXmlDigesterHelper getInstance() {
        if (ObjectUtils.isEmpty(xmlDigester)) {
            synchronized (JerseyXmlDigesterHelper.class) {
                if (ObjectUtils.isEmpty(xmlDigester)) {
                    xmlDigester = new JerseyXmlDigesterHelper();
                }
            }
        }
        return xmlDigester;
    }

    /**
     * 获取Jersey配置
     *
     * @return Jersey配置对象
     * @since 1.0.0
     */
    public Jersey getJersey() {
        if (ObjectUtils.isEmpty(jersey)) {
            synchronized (jerseyFilterInitLock) {
                if (ObjectUtils.isEmpty(jersey)) {
                    try {
                        jersey = newJerseyFilter();
                    } catch (CommonException e) {
                        ExceptionUtils.throwFleaException(FleaException.class, e);
                    }
                }
            }
        }
        return jersey;
    }

    private Jersey newJerseyFilter() throws CommonException {
        Jersey obj = null;

        String fileName = FleaJerseyConstants.JerseyFilterConstants.JSERSY_FILTER_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(FleaJerseyConstants.JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(FleaJerseyConstants.JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("JerseyXmlDigesterHelper##newJerseyFilter Use the specified flea-jersey-filter.xml : " + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Use the current flea-jersey-filter.xml : " + fileName);
            LOGGER.debug("Start to parse the flea-jersey-filter.xml");
        }

        try (InputStream input = IOUtils.getInputStreamFromClassPath(fileName)) {

            // 该路径下【0】找不到指定配置文件
            ObjectUtils.checkEmpty(input, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000001", fileName);

            obj = XmlDigesterHelper.parse(input, newFleaJerseyFilterFileDigester(), Jersey.class);

        } catch (Exception e) {
            // XML转化异常：
            ExceptionUtils.throwCommonException(FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000002", e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End to parse the flea-jersey-filter.xml");
        }

        return obj;
    }

    /**
     * 解析flea-jersey-filter.xml的Digester对象
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
        digester.addObjectCreate("jersey/filter-chain", FilterChain.class.getName());
        digester.addSetProperties("jersey/filter-chain");

        // 前置过滤器链
        digester.addObjectCreate("jersey/filter-chain/before", Before.class.getName());
        digester.addSetProperties("jersey/filter-chain/before");

        digester.addObjectCreate("jersey/filter-chain/before/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filter-chain/before/filter");

        // 业务服务过滤器链
        digester.addObjectCreate("jersey/filter-chain/service", Service.class.getName());
        digester.addSetProperties("jersey/filter-chain/service");

        digester.addObjectCreate("jersey/filter-chain/service/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filter-chain/service/filter");

        // 后置过滤器链
        digester.addObjectCreate("jersey/filter-chain/after", After.class.getName());
        digester.addSetProperties("jersey/filter-chain/after");

        digester.addObjectCreate("jersey/filter-chain/after/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filter-chain/after/filter");

        // 异常过滤器链
        digester.addObjectCreate("jersey/filter-chain/error", Error.class.getName());
        digester.addSetProperties("jersey/filter-chain/error");

        digester.addObjectCreate("jersey/filter-chain/error/filter", Filter.class.getName());
        digester.addSetProperties("jersey/filter-chain/error/filter");
        
        // 过滤器国际码和错误码映射
        digester.addObjectCreate("jersey/filter-i18n-error", FilterI18nError.class.getName());
        digester.addSetProperties("jersey/filter-i18n-error");

        digester.addObjectCreate("jersey/filter-i18n-error/i18n-error-mapping", I18nErrorMapping.class.getName());
        digester.addSetProperties("jersey/filter-i18n-error/i18n-error-mapping");
        digester.addBeanPropertySetter("jersey/filter-i18n-error/i18n-error-mapping", "returnMess");

        digester.addSetNext("jersey/filter-chain", "setFilterChain", FilterChain.class.getName());

        digester.addSetNext("jersey/filter-chain/before", "setBefore", Before.class.getName());
        digester.addSetNext("jersey/filter-chain/before/filter", "addBeforeFilter", Filter.class.getName());

        digester.addSetNext("jersey/filter-chain/service", "setService", Service.class.getName());
        digester.addSetNext("jersey/filter-chain/service/filter", "addServiceFilter", Filter.class.getName());

        digester.addSetNext("jersey/filter-chain/after", "setAfter", After.class.getName());
        digester.addSetNext("jersey/filter-chain/after/filter", "addAfterFilter", Filter.class.getName());

        digester.addSetNext("jersey/filter-chain/error", "setError", Error.class.getName());
        digester.addSetNext("jersey/filter-chain/error/filter", "addErrorFilter", Filter.class.getName());
        
        digester.addSetNext("jersey/filter-i18n-error", "setFilterI18nError", FilterI18nError.class.getName());
        digester.addSetNext("jersey/filter-i18n-error/i18n-error-mapping", "addI18nErrorMapping", I18nErrorMapping.class.getName());

        return digester;
    }

}
