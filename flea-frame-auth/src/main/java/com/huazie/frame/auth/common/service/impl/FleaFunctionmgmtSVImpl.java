package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.auth.common.FunctionTypeEnum;
import com.huazie.frame.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaFunctionmgmtSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> Flea功能管理服务层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaFunctionmgmtSV")
public class FleaFunctionmgmtSVImpl implements IFleaFunctionmgmtSV {

    private IFleaMenuSV fleaMenuSV; // Flea菜单服务

    private IFleaFunctionAttrSV fleaFunctionAttrSV; // Flea扩展属性服务

    @Autowired
    @Qualifier("fleaMenuSV")
    public void setFleaMenuSV(IFleaMenuSV fleaMenuSV) {
        this.fleaMenuSV = fleaMenuSV;
    }

    @Autowired
    @Qualifier("fleaFunctionAttrSV")
    public void setFleaFunctionAttrSV(IFleaFunctionAttrSV fleaFunctionAttrSV) {
        this.fleaFunctionAttrSV = fleaFunctionAttrSV;
    }

    @Override
    @Transactional(value = "fleaAuthTransactionManager", rollbackFor = Exception.class)
    public Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO, List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException {

        // 保存菜单
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        if (ObjectUtils.isNotEmpty(fleaFunctionAttrPOJOList)) {
            for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : fleaFunctionAttrPOJOList) {
                if (ObjectUtils.isNotEmpty(fleaFunctionAttrPOJO)) {
                    fleaFunctionAttrPOJO.setFunctionId(fleaMenu.getMenuId());
                    fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
                    // 保存功能扩展属性
                    fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
                }
            }
        }

        // 添加菜单访问权限

        return null;
    }
}
