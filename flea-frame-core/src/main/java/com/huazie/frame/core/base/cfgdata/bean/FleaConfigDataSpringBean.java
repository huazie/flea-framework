package com.huazie.frame.core.base.cfgdata.bean;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.frame.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResClientSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResourceSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaMenuFavoritesSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import com.huazie.frame.core.common.pojo.FleaMenuFavoritesPOJO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> Flea Config 配置数据Bean </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FleaConfigDataSpringBean {

    private IFleaJerseyI18nErrorMappingSV mappingSV;

    private IFleaParaDetailSV paraDetailSV;

    private IFleaJerseyResServiceSV resServiceSV;

    private IFleaJerseyResClientSV resClientSV;

    private IFleaJerseyResourceSV resourceSV;

    private IFleaMenuFavoritesSV fleaMenuFavoritesSV;

    @Resource(name = "i18nErrorMappingSV")
    public void setMappingSV(IFleaJerseyI18nErrorMappingSV mappingSV) {
        this.mappingSV = mappingSV;
    }

    @Resource(name = "fleaParaDetailSV")
    public void setParaDetailSV(IFleaParaDetailSV paraDetailSV) {
        this.paraDetailSV = paraDetailSV;
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
     * <p> 获取国际码和错误码映射数据 </p>
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @param i18nCode     国际码
     * @return 国际码和错误码映射数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws CommonException {
        return mappingSV.getMapping(resourceCode, serviceCode, i18nCode);
    }

    /**
     * <p> 获取参数配置数据集合 </p>
     *
     * @param paraType 参数配置数据类型
     * @param paraCode 参数配置数据编码
     * @return 参数配置数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public List<FleaParaDetail> getParaDetails(String paraType, String paraCode) throws CommonException {
        return paraDetailSV.getParaDetails(paraType, paraCode);
    }

    /**
     * <p> 获取单个参数配置数据 </p>
     *
     * @param paraType 参数配置数据类型
     * @param paraCode 参数配置数据编码
     * @return 单个参数配置数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaParaDetail getParaDetail(String paraType, String paraCode) throws CommonException {
        return paraDetailSV.getParaDetail(paraType, paraCode);
    }

    /**
     * <p> 获取资源服务 </p>
     *
     * @param serviceCode  服务编码
     * @param resourceCode 资源编码
     * @return 资源服务
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaJerseyResService getResService(String serviceCode, String resourceCode) throws CommonException {
        return resServiceSV.getResService(serviceCode, resourceCode);
    }

    /**
     * <p> 获取资源客户端配置数据 </p>
     *
     * @param clientCode 客户端编码
     * @return 资源客户端配置数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaJerseyResClient getResClient(String clientCode) throws CommonException {
        return resClientSV.getResClient(clientCode);
    }

    /**
     * <p> 获取指定资源定义 </p>
     *
     * @param resourceCode 资源编码
     * @return 资源
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaJerseyResource getResource(String resourceCode) throws CommonException {
        return resourceSV.getResource(resourceCode);
    }

    /**
     * <p> 获取全部资源包名 </p>
     *
     * @return 所有资源包名列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public List<String> getResourcePackages() throws CommonException {
        return resourceSV.getResourcePackages();
    }

    /**
     * <p> 保存菜单收藏夹 </p>
     *
     * @param fleaMenuFavoritesPOJO 菜单收藏夹POJO类对象
     * @return Flea菜单收藏夹
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaMenuFavorites saveFleaMenuFavorites(FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO) throws CommonException {
        return fleaMenuFavoritesSV.saveFleaMenuFavorites(fleaMenuFavoritesPOJO);
    }
}
