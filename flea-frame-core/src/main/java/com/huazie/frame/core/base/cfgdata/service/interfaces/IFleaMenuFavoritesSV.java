package com.huazie.frame.core.base.cfgdata.service.interfaces;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.frame.core.common.pojo.FleaMenuFavoritesPOJO;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea菜单收藏夹SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuFavoritesSV extends IAbstractFleaJPASV<FleaMenuFavorites> {

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