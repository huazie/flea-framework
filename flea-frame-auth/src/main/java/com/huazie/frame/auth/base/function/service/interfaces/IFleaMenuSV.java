package com.huazie.frame.auth.base.function.service.interfaces;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea菜单SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaMenuSV extends IAbstractFleaJPASV<FleaMenu> {

    /**
     * <p> 获取用户授权的指定系统的菜单 </p>
     *
     * @param systemRelMenuIdList 系统关联菜单列表
     * @param menuIdList          用户授权菜单列表
     * @return 用于授权的指定系统的菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllAccessibleMenus(List<Long> systemRelMenuIdList, List<Long> menuIdList) throws CommonException;

    /**
     * <p> 获取有效的菜单信息 </p>
     *
     * @param menuId    菜单编号
     * @param menuCode  菜单编码
     * @param menuName  菜单名称
     * @param menuLevel 菜单等级
     * @param parentId  父菜单编号
     * @return 有效的菜单信息列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryValidMenus(Long menuId, String menuCode, String menuName, Integer menuLevel, Long parentId) throws CommonException;

    /**
     * <p> 获取所有有效的菜单信息 </p>
     *
     * @return 所有有效的菜单信息列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllValidMenus() throws CommonException;

    /**
     * <p> 保存Flea菜单 </p>
     *
     * @param fleaMenuPOJO flea菜单POJO对象
     * @return Flea菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaMenu saveFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException;
}