package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.frame.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.frame.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.frame.auth.common.AuthRelTypeEnum;
import com.huazie.frame.auth.common.FunctionTypeEnum;
import com.huazie.frame.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.frame.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nHelper;
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
@Service("fleaFunctionModuleSV")
public class FleaFunctionModuleSVImpl implements IFleaFunctionModuleSV {

    private IFleaMenuSV fleaMenuSV; // Flea菜单服务

    private IFleaFunctionAttrSV fleaFunctionAttrSV; // Flea扩展属性服务

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeRelSV fleaPrivilegeRelSV; // Flea权限关联服务

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

    @Override
    @Transactional(value = "fleaAuthTransactionManager", rollbackFor = Exception.class)
    public Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO, List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException {

        // 保存Flea菜单
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);
        // 将Flea菜单持久化到数据库中，否则同一事物下，无法获取menuId
        fleaMenuSV.flush();

        // 取菜单编号
        Long menuId = fleaMenu.getMenuId();

        if (ObjectUtils.isNotEmpty(fleaFunctionAttrPOJOList)) {
            for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : fleaFunctionAttrPOJOList) {
                if (ObjectUtils.isNotEmpty(fleaFunctionAttrPOJO)) {
                    fleaFunctionAttrPOJO.setFunctionId(menuId);
                    fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
                    // 保存功能扩展属性
                    fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
                }
            }
        }

        String menuName = fleaMenu.getMenuName();
        String[] values = new String[]{menuName};
        // 添加权限【访问《XXX》菜单】
        FleaPrivilege fleaPrivilege = fleaPrivilegeSV.savePrivilege(newFleaPrivilegePOJOForMenu(values));
        // 将Flea权限持久化到数据库中，否则同一事物下，无法获取privilegeId
        fleaPrivilegeSV.flush();

        Long privilegeId = fleaPrivilege.getPrivilegeId();
        // 添加权限关联
        fleaPrivilegeRelSV.savePrivilegeRel(newFleaPrivilegeRelPOJOForMenu(privilegeId, menuId, values));

        return menuId;
    }

    /**
     * <p> 新建Flea权限POJO对象 </p>
     *
     * @param values auth国际码资源数据参数信息
     * @return Flea权限POJO对象
     * @since 1.0.0
     */
    private FleaPrivilegePOJO newFleaPrivilegePOJOForMenu(String[] values) {
        FleaPrivilegePOJO fleaPrivilegePOJO = new FleaPrivilegePOJO();
        // 访问《{0}》菜单
        fleaPrivilegePOJO.setPrivilegeName(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000001", values));
        // 拥有可以访问《{0}》菜单的权限
        fleaPrivilegePOJO.setPrivilegeDesc(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000002", values));
        // 【访问《{0}》菜单】权限对应【{0}】菜单，新增菜单时自动生成
        fleaPrivilegePOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000003", values));
        return fleaPrivilegePOJO;
    }

    /**
     * <p> 新建Flea权限关联POJO对象 </p>
     *
     * @param privilegeId 权限编号
     * @param relId       关联编号【这里是菜单编号】
     * @param values      auth国际码资源数据参数信息
     * @return Flea权限关联POJO对象实例
     * @since 1.0.0
     */
    private FleaPrivilegeRelPOJO newFleaPrivilegeRelPOJOForMenu(Long privilegeId, Long relId, String[] values) {
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
        // 【{0}】菜单绑定【访问《{0}》菜单】权限, 新增菜单时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000004", values));
        return fleaPrivilegeRelPOJO;
    }

}
