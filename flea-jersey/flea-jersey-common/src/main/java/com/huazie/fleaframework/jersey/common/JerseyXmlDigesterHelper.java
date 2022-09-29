package com.huazie.fleaframework.jersey.common;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.xml.Import;
import com.huazie.fleaframework.common.util.xml.XmlDigesterHelper;
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

    private Jersey jersey;

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
                    jersey = newJerseyFilter();
                }
            }
        }
        return jersey;
    }

    private Jersey newJerseyFilter() {

        String fileName = FleaJerseyConstants.JerseyFilterConstants.JSERSY_FILTER_FILE_PATH;
        if (StringUtils.isNotBlank(System.getProperty(FleaJerseyConstants.JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(FleaJerseyConstants.JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Use the specified flea-jersey-filter.xml : " + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Use the current flea-jersey-filter.xml : " + fileName);
            LOGGER.debug("Start to parse the flea-jersey-filter.xml");
        }

        Digester digester = newFleaJerseyFilterFileDigester();
        Jersey jersey = XmlDigesterHelper.parse(fileName, digester, Jersey.class);

        if (ObjectUtils.isNotEmpty(jersey) && CollectionUtils.isNotEmpty(jersey.getImportList())) {
            Import mImport = jersey.getImportList().get(0); // 导入的flea-Jersey接口过滤器配置文件只有一个
            if (ObjectUtils.isNotEmpty(mImport)) {
                String resource = mImport.getResource();
                Jersey other = XmlDigesterHelper.parse(resource, digester, Jersey.class);
                if (ObjectUtils.isNotEmpty(other)) {
                    FilterChain filterChain = jersey.getFilterChain();
                    FilterChain otherFilterChain = other.getFilterChain();
                    // 合并前置过滤器
                    filterChain.getBefore().addFilters(otherFilterChain.getBefore().getFilters());
                    // 合并业务服务过滤器
                    filterChain.getService().addFilters(otherFilterChain.getService().getFilters());
                    // 合并后置过滤器
                    filterChain.getAfter().addFilters(otherFilterChain.getAfter().getFilters());
                    // 合并错误过滤器
                    filterChain.getError().addFilters(otherFilterChain.getError().getFilters());

                    FilterI18nError filterI18nError = jersey.getFilterI18nError();
                    FilterI18nError otherFilterI18NError = other.getFilterI18nError();
                    // 合并过滤器国际码错误码映射配置
                    filterI18nError.addI18nErrorMappings(otherFilterI18NError.getI18nErrorMappingList());
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End to parse the flea-jersey-filter.xml");
        }

        return jersey;
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

        // flea jersey 接口过滤器配置文件资源导入
        digester.addObjectCreate("jersey/import", Import.class.getName());
        digester.addSetProperties("jersey/import");
        digester.addSetNext("jersey/import", "addImport", Import.class.getName());

        return digester;
    }

}
