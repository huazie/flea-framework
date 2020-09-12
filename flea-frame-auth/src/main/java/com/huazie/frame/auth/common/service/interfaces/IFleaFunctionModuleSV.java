package com.huazie.frame.auth.common.service.interfaces;

import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.common.exception.CommonException;

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
}
