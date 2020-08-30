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
    List<FleaMenu> getAllAccessibleMenus(List<Long> systemRelMenuIdList, List<Long> menuIdList) throws CommonException;

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