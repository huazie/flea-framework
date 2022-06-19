package com.huazie.fleaframework.auth.cache.bean;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.common.exception.CommonException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea 授权缓存，对外提供可缓存的授权数据查询API。
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Component
public class FleaAuthCache {

    private IFleaAuthSV fleaAuthSV; // Flea 授权服务

    @Resource(name = "fleaAuthSV")
    public void setFleaAuthSV(IFleaAuthSV fleaAuthSV) {
        this.fleaAuthSV = fleaAuthSV;
    }

    /**
     * 根据账户编号获取用户模块数据，包含用户，账户，
     * 用户扩展属性，账户扩展属性，用户实名信息。
     *
     * @param accountId 账户编号
     * @return 用户模块数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    @Cacheable(value = "fleaauthuser", key = "'accountId_' + #accountId")
    public FleaUserModuleData getFleaUserModuleData(Long accountId) throws CommonException {
        return fleaAuthSV.getFleaUserModuleData(accountId);
    }

    /**
     * 获取指定系统账户下所有指定操作账户可以访问的菜单。
     *
     * @param accountId       操作账户编号
     * @param systemAccountId 系统帐户编号
     * @return 所有可以访问的菜单
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    @Cacheable(value = "fleaauthmenu", key = "#accountId + '_' + #systemAccountId")
    public List<FleaMenu> queryAllAccessibleMenus(Long accountId, Long systemAccountId) throws CommonException {
        return fleaAuthSV.queryAllAccessibleMenus(accountId, systemAccountId);
    }

    /**
     * 校验资源授权，如果允许指定账号，调用指定系统账户下的资源，
     * 则校验通过，返回true；否则校验失败，返回false。
     *
     * @param accountId       账户编号【操作账户或系统账户编号】
     * @param systemAccountId 系统账户编号
     * @param resourceCode    资源编码
     * @return true：允许调用该资源  false：不允许调用该资源
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    @Cacheable(value = "fleaauthresource", key = "#accountId + '_' + #systemAccountId + '_' + #resourceCode")
    public boolean checkResourceAuth(Long accountId, Long systemAccountId, String resourceCode) throws CommonException {
        return this.fleaAuthSV.checkResourceAuth(accountId, systemAccountId, resourceCode);
    }

}
