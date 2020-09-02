package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.frame.auth.common.FunctionTypeEnum;
import com.huazie.frame.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaFunctionmgmtSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.i18n.FleaI18nResEnum;
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

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeRelSV fleaPrivilegeRelSV; // Flea权限关联服务

    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV; // Flea权限组关联服务

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

    @Autowired
    @Qualifier("fleaPrivilegeSV")
    public void setFleaPrivilegeSV(IFleaPrivilegeSV fleaPrivilegeSV) {
        this.fleaPrivilegeSV = fleaPrivilegeSV;
    }

    @Autowired
    @Qualifier("fleaPrivilegeRelSV")
    public void setFleaPrivilegeRelSV(IFleaPrivilegeRelSV fleaPrivilegeRelSV) {
        this.fleaPrivilegeRelSV = fleaPrivilegeRelSV;
    }

    @Autowired
    @Qualifier("fleaPrivilegeGroupRelSV")
    public void setFleaPrivilegeGroupRelSV(IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV) {
        this.fleaPrivilegeGroupRelSV = fleaPrivilegeGroupRelSV;
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

        String menuName = fleaMenu.getMenuName();
        String[] values = new String[]{menuName};
        // 添加菜单访问权限
        FleaPrivilegePOJO fleaPrivilegePOJO = new FleaPrivilegePOJO();
        fleaPrivilegePOJO.setPrivilegeName(FleaI18nHelper.i18n("AUTH-PRIVILEGE0000000001", values, FleaI18nResEnum.AUTH.getResName()));
        fleaPrivilegePOJO.setPrivilegeDesc(FleaI18nHelper.i18n("AUTH-PRIVILEGE0000000002", values, FleaI18nResEnum.AUTH.getResName()));
        fleaPrivilegePOJO.setRemarks(FleaI18nHelper.i18n("AUTH-PRIVILEGE0000000003", values, FleaI18nResEnum.AUTH.getResName()));
        FleaPrivilege fleaPrivilege = fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO);

        Long menuId = fleaMenu.getMenuId();
        Long privilegeId = fleaPrivilege.getPrivilegeId();
        // 添加权限关联

        // 添加权限组关联

        return null;
    }
}
