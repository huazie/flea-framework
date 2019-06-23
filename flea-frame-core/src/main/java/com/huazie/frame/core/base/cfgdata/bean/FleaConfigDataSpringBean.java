package com.huazie.frame.core.base.cfgdata.bean;

import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p> Flea Config 配置数据Bean </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FleaConfigDataSpringBean {

    @Resource(name = "fleaJerseyI18nErrorMappingSVImpl")
    private IFleaJerseyI18nErrorMappingSV mappingSV;

    /**
     * <p> 获取国际码和错误码映射数据 </p>
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @param i18nCode     国际码
     * @return 国际码和错误码映射数据
     * @throws Exception
     * @since 1.0.0
     */
    public FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws Exception {
        return mappingSV.getMapping(resourceCode, serviceCode, i18nCode);
    }

}
