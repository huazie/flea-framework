package com.huazie.fleaframework.jersey.common.filter.config;

import com.huazie.fleaframework.common.config.ConfigMap;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器国际码和错误码配置对象，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <filter-i18n-error> </filter-i18n-error>} 节点
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FilterI18nError extends ConfigMap<I18nErrorMapping> {

    private List<I18nErrorMapping> i18nErrorMappingList = new ArrayList<>();

    public List<I18nErrorMapping> getI18nErrorMappingList() {
        return i18nErrorMappingList;
    }

    /**
     * 添加一个国际码和错误码映射配置项
     *
     * @param i18nErrorMapping 国际码和错误码映射配置项
     * @since 2.0.0
     */
    public void addI18nErrorMapping(I18nErrorMapping i18nErrorMapping) {
        i18nErrorMappingList.add(i18nErrorMapping);
        addConfig(i18nErrorMapping);
    }

    @Override
    protected String getConfigKey(I18nErrorMapping i18nErrorMapping) {
        return i18nErrorMapping.getI18nCode();
    }

    /**
     * 根据国际码，获取国际码和错误码映射配置项
     *
     * @param i18nCode 国际码
     * @return 国际码和错误码配置映射项
     * @since 2.0.0
     */
    public I18nErrorMapping getI18nErrorMapping(String i18nCode) {
        return getConfig(i18nCode);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
