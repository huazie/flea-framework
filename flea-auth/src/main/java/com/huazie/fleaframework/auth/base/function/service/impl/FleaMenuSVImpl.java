package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaMenuDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea菜单SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaMenuSV")
public class FleaMenuSVImpl extends AbstractFleaJPASVImpl<FleaMenu> implements IFleaMenuSV {

    private IFleaMenuDAO fleaMenuDao;

    @Autowired
    @Qualifier("fleaMenuDAO")
    public void setFleaMenuDao(IFleaMenuDAO fleaMenuDao) {
        this.fleaMenuDao = fleaMenuDao;
    }

    @Override
    public List<FleaMenu> queryAllAccessibleMenus(List<Long> systemRelMenuIdList, List<Long> menuIdList) throws CommonException {

        // 取交集
        systemRelMenuIdList.retainAll(menuIdList);

        // 获取菜单列表
        List<FleaMenu> allAccessibleMenus = null;
        if (CollectionUtils.isNotEmpty(systemRelMenuIdList)) {
            allAccessibleMenus = new ArrayList<>();
            for (Long menuId : systemRelMenuIdList) {
                if (NumberUtils.isPositiveNumber(menuId)) {
                    // 获取系统关联菜单和用户授权菜单交集中有效的菜单数据
                    FleaMenu fleaMenu = this.queryValidMenu(menuId);
                    if (ObjectUtils.isNotEmpty(fleaMenu)) {
                        allAccessibleMenus.add(fleaMenu);
                    }
                }
            }
        }

        return allAccessibleMenus;
    }

    @Override
    public FleaMenu queryValidMenu(Long menuId) throws CommonException {
        return this.fleaMenuDao.queryValidMenu(menuId);
    }

    @Override
    public List<FleaMenu> queryValidMenus(String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException {
        return this.fleaMenuDao.queryValidMenus(menuCode, menuName, menuLevel, parentId);
    }

    @Override
    public List<FleaMenu> queryAllValidMenus() throws CommonException {
        return this.fleaMenuDao.queryValidMenus(null, null, null, null);
    }

    @Override
    public FleaMenu saveFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException {
        FleaMenu fleaMenu = newFleaMenu(fleaMenuPOJO);
        // 保存Flea菜单数据
        this.save(fleaMenu);
        return fleaMenu;
    }

    /**
     * 新建Flea菜单数据
     *
     * @param fleaMenuPOJO flea菜单POJO对象
     * @return Flea菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaMenu newFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException {
        // 校验Flea菜单POJO对象
        FleaAuthCheck.checkFleaMenuPOJO(fleaMenuPOJO);

        return new FleaMenu(fleaMenuPOJO.getMenuCode(), fleaMenuPOJO.getMenuName(),
                fleaMenuPOJO.getMenuIcon(), fleaMenuPOJO.getMenuSort(),
                fleaMenuPOJO.getMenuView(), fleaMenuPOJO.getMenuLevel(),
                fleaMenuPOJO.getParentId(), fleaMenuPOJO.getEffectiveDate(),
                fleaMenuPOJO.getExpiryDate(), fleaMenuPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaMenu> getDAO() {
        return this.fleaMenuDao;
    }
}