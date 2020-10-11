package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaMenuDAO;
import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p> Flea菜单DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaMenuDAO")
@SuppressWarnings(value = "unchecked")
public class FleaMenuDAOImpl extends FleaAuthDAOImpl<FleaMenu> implements IFleaMenuDAO {

    @Override
    public List<FleaMenu> getValidMenu(Long menuId, String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        return getQuery(null)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_ID, menuId)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_CODE, menuCode)
                .like(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_NAME, menuName)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_LEVEL, menuLevel)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_PARENT_ID, parentId)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();
    }
}