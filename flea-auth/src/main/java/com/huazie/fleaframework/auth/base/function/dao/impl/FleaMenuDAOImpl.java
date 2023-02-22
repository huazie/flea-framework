package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaMenuDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea菜单DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaMenuDAO")
public class FleaMenuDAOImpl extends FleaAuthDAOImpl<FleaMenu> implements IFleaMenuDAO {

    @Override
    public FleaMenu queryValidMenu(Long menuId) throws CommonException {
        List<FleaMenu> fleaMenuList = this.getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_ID, menuId)
                .getResultList();

        return CollectionUtils.getFirstElement(fleaMenuList, FleaMenu.class);
    }

    @Override
    public List<FleaMenu> queryValidMenus(String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException {
        return this.getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_CODE, menuCode)
                .like(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_NAME, menuName) // 模糊匹配
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_LEVEL, menuLevel)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_PARENT_ID, parentId)
                .getResultList();
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery() throws CommonException {
        Date currentDate = DateUtils.getCurrentTime();
        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate);
    }
}