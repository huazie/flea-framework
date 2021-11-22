package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaMenuFavoritesDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p> Flea菜单收藏夹DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaMenuFavoritesDAO")
@SuppressWarnings(value = "unchecked")
public class FleaMenuFavoritesDAOImpl extends FleaConfigDAOImpl<FleaMenuFavorites> implements IFleaMenuFavoritesDAO {

    @Override
    public List<FleaMenuFavorites> queryValidFleaMenuFavorites(Long accountId, String menuCode) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        return getQuery(null)
                .equal(FleaConfigEntityConstants.E_ACCOUNT_ID, accountId)
                .equal(FleaConfigEntityConstants.E_MENU_CODE, menuCode)
                .equal(FleaConfigEntityConstants.E_FAVORITE_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaConfigEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaConfigEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();
    }
}