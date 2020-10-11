package com.huazie.frame.auth.base.function.dao.interfaces;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> Flea菜单DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuDAO extends IAbstractFleaJPADAO<FleaMenu> {

    /**
     * <p> 获取有效的菜单信息 </p>
     *
     * @param menuId    菜单编号
     * @param menuCode  菜单编码
     * @param menuName  菜单名称
     * @param menuLevel 菜单等级
     * @param parentId  父菜单编号
     * @return 菜单信息列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> getValidMenu(Long menuId, String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException;
}