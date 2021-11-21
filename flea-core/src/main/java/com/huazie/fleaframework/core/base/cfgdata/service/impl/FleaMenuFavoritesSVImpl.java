package com.huazie.fleaframework.core.base.cfgdata.service.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaMenuFavoritesSV;
import com.huazie.fleaframework.core.common.FleaCoreCommonException;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaMenuFavoritesDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaMenuFavorites;
import com.huazie.fleaframework.core.common.pojo.FleaMenuFavoritesPOJO;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Flea菜单收藏夹SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaMenuFavoritesSV")
public class FleaMenuFavoritesSVImpl extends AbstractFleaJPASVImpl<FleaMenuFavorites> implements IFleaMenuFavoritesSV {

    private IFleaMenuFavoritesDAO fleaMenuFavoritesDao;

    @Autowired
    @Qualifier("fleaMenuFavoritesDAO")
    public void setFleaMenuFavoritesDao(IFleaMenuFavoritesDAO fleaMenuFavoritesDao) {
        this.fleaMenuFavoritesDao = fleaMenuFavoritesDao;
    }

    @Override
    public List<FleaMenuFavorites> queryValidFleaMenuFavorites(Long accountId, String menuCode) throws CommonException {
        return fleaMenuFavoritesDao.queryValidFleaMenuFavorites(accountId, menuCode);
    }

    @Override
    public FleaMenuFavorites saveFleaMenuFavorites(FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO) throws CommonException {
        FleaMenuFavorites fleaMenuFavorites = newFleaMenuFavorites(fleaMenuFavoritesPOJO);
        // 保存Flea菜单收藏夹
        this.save(fleaMenuFavorites);
        return fleaMenuFavorites;
    }

    /**
     * <p> 新建一个Flea菜单收藏夹实体对象 </p>
     *
     * @param fleaMenuFavoritesPOJO Flea菜单收藏夹POJO类
     * @return 菜单收藏夹实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaMenuFavorites newFleaMenuFavorites(FleaMenuFavoritesPOJO fleaMenuFavoritesPOJO) throws CommonException {

        // 校验Flea菜单收藏夹POJO类对象是否为空
        // ERROR-CORE-COMMON0000000001 【{0}】不能为空，请检查
        ObjectUtils.checkEmpty(fleaMenuFavoritesPOJO, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", FleaMenuFavoritesPOJO.class.getSimpleName());

        // 校验操作账户编号不能为空
        Long accountId = fleaMenuFavoritesPOJO.getAccountId();
        // 【{0}】必须是正数！
        NumberUtils.checkNonPositiveNumber(accountId, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000002", "accountId");

        // 校验菜单编码不能为空
        String menuCode = fleaMenuFavoritesPOJO.getMenuCode();
        StringUtils.checkBlank(menuCode, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "menuCode");

        // 校验菜单名称不能为空
        String menuName = fleaMenuFavoritesPOJO.getMenuName();
        StringUtils.checkBlank(menuName, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "menuName");

        // 校验菜单FontAwesome小图标不能为空
        String menuIcon = fleaMenuFavoritesPOJO.getMenuIcon();
        StringUtils.checkBlank(menuIcon, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "menuIcon");

        return new FleaMenuFavorites(accountId, menuCode, menuName, menuIcon,
                fleaMenuFavoritesPOJO.getEffectiveDate(),
                fleaMenuFavoritesPOJO.getExpiryDate(),
                fleaMenuFavoritesPOJO.getRemarks(),
                fleaMenuFavoritesPOJO.getExt1(),
                fleaMenuFavoritesPOJO.getExt2(),
                fleaMenuFavoritesPOJO.getExt3());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaMenuFavorites> getDAO() {
        return fleaMenuFavoritesDao;
    }
}