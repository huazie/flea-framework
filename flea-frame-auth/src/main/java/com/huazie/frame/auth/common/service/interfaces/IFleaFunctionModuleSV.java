package com.huazie.frame.auth.common.service.interfaces;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.common.exception.CommonException;

import java.util.List;

/**
 * <p> 功能管理服务层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionModuleSV {

    /**
     * <p> 添加菜单 </p>
     *
     * @param fleaMenuPOJO Flea菜单POJO类对象
     * @return 菜单编号
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException;

    /**
     * <p> 获取菜单信息列表 </p>
     *
     * @param fleaMenuPOJO Flea菜单POJO类对象
     * @return 菜单信息列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryValidMenus(FleaMenuPOJO fleaMenuPOJO) throws CommonException;
}
