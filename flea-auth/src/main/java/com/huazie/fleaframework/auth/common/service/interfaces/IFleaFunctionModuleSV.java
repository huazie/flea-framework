package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.common.exception.CommonException;

import java.util.List;

/**
 * Flea功能管理服务层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFunctionModuleSV {

    /**
     * 添加菜单
     *
     * @param fleaMenuPOJO Flea菜单POJO类对象
     * @return 菜单编号
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException;

    /**
     * 获取菜单信息列表
     *
     * @param fleaMenuPOJO Flea菜单POJO类对象
     * @return 菜单信息列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryValidMenus(FleaMenuPOJO fleaMenuPOJO) throws CommonException;
}
