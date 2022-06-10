package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.core.common.pojo.FleaMenuFavoritesPOJO;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea菜单收藏夹SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuFavoritesSV extends IAbstractFleaJPASV<FleaMenuFavorites> {

    /**
     * 根据操作账户编号和菜单编码（可为空），查询有效的菜单收藏夹。
     *
     * @param accountId 操作账户编号
     * @param menuCode  菜单编码
     * @return 菜单收藏夹列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenuFavorites> queryValidFleaMenuFavorites(Long accountId, String menuCode) throws CommonException;

    /**
     * 保存菜单收藏夹
     *
     * @param fleaMenuFavoritesPOJO Flea菜单收藏夹POJO类
     * @return 菜单收藏夹实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaMenuFavorites saveFleaMenuFavorites(FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO) throws CommonException;

}