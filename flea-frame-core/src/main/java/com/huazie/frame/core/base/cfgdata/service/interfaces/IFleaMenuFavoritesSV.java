package com.huazie.frame.core.base.cfgdata.service.interfaces;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.frame.core.common.pojo.FleaMenuFavoritesPOJO;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea菜单收藏夹SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuFavoritesSV extends IAbstractFleaJPASV<FleaMenuFavorites> {

    /**
     * <p> 查询有效的菜单收藏夹 </p>
     *
     * @param accountId 操作账户编号
     * @param menuCode  菜单编码
     * @return 菜单收藏夹列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenuFavorites> queryValidFleaMenuFavorites(Long accountId, String menuCode) throws CommonException;

    /**
     * <p> 保存菜单收藏夹 </p>
     *
     * @param fleaMenuFavoritesPOJO Flea菜单收藏夹POJO类
     * @return 菜单收藏夹实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaMenuFavorites saveFleaMenuFavorites(FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO) throws CommonException;

}