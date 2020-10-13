package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaMenuFavoritesDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaMenuFavorites;
import org.springframework.stereotype.Repository;

/**
 * <p> Flea菜单收藏夹DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaMenuFavoritesDAO")
public class FleaMenuFavoritesDAOImpl extends FleaConfigDAOImpl<FleaMenuFavorites> implements IFleaMenuFavoritesDAO {
}