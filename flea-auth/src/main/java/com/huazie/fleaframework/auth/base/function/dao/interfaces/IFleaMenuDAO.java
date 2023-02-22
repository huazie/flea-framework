package com.huazie.fleaframework.auth.base.function.dao.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea菜单DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuDAO extends IAbstractFleaJPADAO<FleaMenu> {

    /**
     * 根据菜单编号，查询有效的Flea菜单数据
     *
     * @param menuId    菜单编号
     * @return Flea菜单实体
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaMenu queryValidMenu(Long menuId) throws CommonException;

    /**
     * 根据菜单编码、菜单名称、菜单等级和父菜单编号，查询有效的Flea菜单数据集
     *
     * @param menuCode  菜单编码
     * @param menuName  菜单名称
     * @param menuLevel 菜单等级
     * @param parentId  父菜单编号
     * @return Flea菜单数据集
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryValidMenus(String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException;
}