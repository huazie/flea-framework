package com.huazie.fleaframework.auth.base.function.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea菜单SV层接口定义
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuSV extends IAbstractFleaJPASV<FleaMenu> {

    /**
     * 获取用户授权的指定系统的菜单
     *
     * @param systemRelMenuIdList 系统关联菜单列表
     * @param menuIdList          用户授权菜单列表
     * @return 用于授权的指定系统的菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllAccessibleMenus(List<Long> systemRelMenuIdList, List<Long> menuIdList) throws CommonException;

    /**
     * 根据菜单编号，获取有效的Flea菜单数据
     *
     * @param menuId 菜单编号
     * @return Flea菜单数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaMenu queryValidMenu(Long menuId) throws CommonException;

    /**
     * 根据菜单编码、菜单名称、菜单等级和父菜单编号，查询有效的Flea菜单数据集
     *
     * @param menuCode  菜单编码
     * @param menuName  菜单名称
     * @param menuLevel 菜单等级
     * @param parentId  父菜单编号
     * @return 有效的Flea菜单数据集
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryValidMenus(String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException;

    /**
     * 获取所有有效的菜单数据集
     *
     * @return 所有有效的菜单数据集
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllValidMenus() throws CommonException;

    /**
     * 保存Flea菜单数据
     *
     * @param fleaMenuPOJO flea菜单POJO对象
     * @return Flea菜单数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaMenu saveFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException;
}