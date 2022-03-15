package com.huazie.fleaframework.core.base.cfgdata.bean;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaConfigDataSV;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResClientSV;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResourceSV;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaMenuFavoritesSV;
import com.huazie.fleaframework.core.common.pojo.FleaMenuFavoritesPOJO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea 配置数据 Spring Bean，对外提供了配置数据的查询服务。
 *
 * <p> 配置数据在这里可以理解为程序运行过程中始终不变的数据，
 * 这部分数据因为始终不变，所以每当应用程序通过代码访问它们，
 * 不能每次都从数据库中获取，除第一次访问，后续都应从缓存获取。
 *
 * <p> 这些配置数据的查询服务上采用注解 {@code Cacheable} 标记，
 * 在 Spring 的配置中开启缓存后，后续这些查询服务方法被调用时，
 * 首先从缓存中获取，如果没有则从数据库中获取，然后再添加到缓存中。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FleaConfigDataSpringBean {

    private IFleaJerseyI18nErrorMappingSV mappingSV;

    private IFleaConfigDataSV configDataSV;

    private IFleaJerseyResServiceSV resServiceSV;

    private IFleaJerseyResClientSV resClientSV;

    private IFleaJerseyResourceSV resourceSV;

    private IFleaMenuFavoritesSV fleaMenuFavoritesSV;

    @Resource(name = "i18nErrorMappingSV")
    public void setMappingSV(IFleaJerseyI18nErrorMappingSV mappingSV) {
        this.mappingSV = mappingSV;
    }

    @Resource(name = "fleaConfigDataSV")
    public void setConfigDataSV(IFleaConfigDataSV configDataSV) {
        this.configDataSV = configDataSV;
    }

    @Resource(name = "resServiceSV")
    public void setResServiceSV(IFleaJerseyResServiceSV resServiceSV) {
        this.resServiceSV = resServiceSV;
    }

    @Resource(name = "resClientSV")
    public void setResClientSV(IFleaJerseyResClientSV resClientSV) {
        this.resClientSV = resClientSV;
    }

    @Resource(name = "resourceSV")
    public void setResourceSV(IFleaJerseyResourceSV resourceSV) {
        this.resourceSV = resourceSV;
    }

    @Resource(name = "fleaMenuFavoritesSV")
    public void setFleaMenuFavoritesSV(IFleaMenuFavoritesSV fleaMenuFavoritesSV) {
        this.fleaMenuFavoritesSV = fleaMenuFavoritesSV;
    }

    /**
     * 获取国际码和错误码映射数据集合
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @return 国际码和错误码映射数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleajerseyi18nerrormapping", key = "#resourceCode + '_' + #serviceCode")
    public List<FleaJerseyI18nErrorMapping> getMappings(String resourceCode, String serviceCode) throws CommonException {
        return mappingSV.getMappings(resourceCode, serviceCode);
    }

    /**
     * 获取国际码和错误码映射数据
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @param i18nCode     国际码
     * @return 国际码和错误码映射数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleajerseyi18nerrormapping", key = "#resourceCode + '_' + #serviceCode + '_' + #i18nCode")
    public FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws CommonException {
        return mappingSV.getMapping(resourceCode, serviceCode, i18nCode);
    }

    /**
     * 获取配置数据集合
     *
     * @param configType 配置数据类型
     * @param configCode 配置数据编码
     * @return 配置数据集合
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    @Cacheable(value = "fleaconfigdata", key = "#configType")
    public List<FleaConfigData> getConfigDatas(String configType, String configCode) throws CommonException {
        return configDataSV.getConfigDatas(configType, configCode);
    }

    /**
     * 获取单个配置数据集合
     *
     * @param configType 配置数据类型
     * @param configCode 配置数据编码
     * @return 配置数据
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    @Cacheable(value = "fleaconfigdata", key = "#configType + '_' + #configCode")
    public FleaConfigData getConfigData(String configType, String configCode) throws CommonException {
        return configDataSV.getConfigData(configType, configCode);
    }

    /**
     * 获取资源服务
     *
     * @param serviceCode  服务编码
     * @param resourceCode 资源编码
     * @return 资源服务
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleajerseyresservice", key = "#serviceCode + '_' + #resourceCode")
    public FleaJerseyResService getResService(String serviceCode, String resourceCode) throws CommonException {
        return resServiceSV.getResService(serviceCode, resourceCode);
    }

    /**
     * 获取资源客户端配置数据
     *
     * @param clientCode 客户端编码
     * @return 资源客户端配置数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleajerseyresclient", key = "#clientCode")
    public FleaJerseyResClient getResClient(String clientCode) throws CommonException {
        return resClientSV.getResClient(clientCode);
    }

    /**
     * 获取指定资源定义
     *
     * @param resourceCode 资源编码
     * @return 资源
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleajerseyresource", key = "#resourceCode")
    public FleaJerseyResource getResource(String resourceCode) throws CommonException {
        return resourceSV.getResource(resourceCode);
    }

    /**
     * 获取全部资源包名
     *
     * @return 所有资源包名列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleajerseyresource", key = "'packages'")
    public List<String> getResourcePackages() throws CommonException {
        return resourceSV.getResourcePackages();
    }

    /**
     * 保存菜单收藏夹
     *
     * @param fleaMenuFavoritesPOJO 菜单收藏夹POJO类对象
     * @return Flea菜单收藏夹
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaMenuFavorites saveFleaMenuFavorites(FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO) throws CommonException {
        return fleaMenuFavoritesSV.saveFleaMenuFavorites(fleaMenuFavoritesPOJO);
    }

    /**
     * 查询有效的菜单收藏夹
     *
     * @param accountId 操作账户编号
     * @return 菜单收藏夹列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleamenufavorites", key = "#accountId")
    public List<FleaMenuFavorites> queryValidFleaMenuFavorites(Long accountId) throws CommonException {
        return fleaMenuFavoritesSV.queryValidFleaMenuFavorites(accountId, null);
    }

    /**
     * 查询有效的菜单收藏夹
     *
     * @param accountId 操作账户编号
     * @param menuCode  菜单编码
     * @return 菜单收藏夹列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    @Cacheable(value = "fleamenufavorites", key = "#accountId + '_' + #menuCode")
    public FleaMenuFavorites queryValidFleaMenuFavorites(Long accountId, String menuCode) throws CommonException {
        List<FleaMenuFavorites> fleaMenuFavoritesList = fleaMenuFavoritesSV.queryValidFleaMenuFavorites(accountId, menuCode);
        FleaMenuFavorites fleaMenuFavorites = null;
        if (CollectionUtils.isNotEmpty(fleaMenuFavoritesList)) {
            fleaMenuFavorites = fleaMenuFavoritesList.get(0);
        }
        return fleaMenuFavorites;
    }
}
