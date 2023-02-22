package com.huazie.fleaframework.core.base.cfgdata.dao.interfaces;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea菜单收藏夹DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuFavoritesDAO extends IAbstractFleaJPADAO<FleaMenuFavorites> {

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
}