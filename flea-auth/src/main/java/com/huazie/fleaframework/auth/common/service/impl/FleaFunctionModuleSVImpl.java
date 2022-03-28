package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Flea功能管理服务层实现类
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
    public Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException {

        // 保存Flea菜单
        FleaMenu fleaMenu = fleaMenuSV.saveFleaMenu(fleaMenuPOJO);
        // 将Flea菜单持久化到数据库中，否则同一事物下，无法获取menuId
        fleaMenuSV.flush();

        // 取菜单编号
        Long menuId = fleaMenu.getMenuId();

        List<FleaFunctionAttrPOJO> functionAttrPOJOList = fleaMenuPOJO.getFunctionAttrPOJOList();
        if (ObjectUtils.isNotEmpty(functionAttrPOJOList)) {
            for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : functionAttrPOJOList) {
                if (ObjectUtils.isNotEmpty(fleaFunctionAttrPOJO)) {
                    fleaFunctionAttrPOJO.setFunctionId(menuId);
                    fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
                    // 保存功能扩展属性
                    fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
                }
            }
        }

        String[] values = new String[]{fleaMenu.getMenuName()};
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
     * 新建Flea权限POJO对象
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
     * 新建Flea权限关联POJO对象
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

    @Override
    public List<FleaMenu> queryValidMenus(FleaMenuPOJO fleaMenuPOJO) throws CommonException {

        List<FleaMenu> menuList;

        if (ObjectUtils.isNotEmpty(fleaMenuPOJO)) {
            menuList = fleaMenuSV.queryValidMenus(null,
                    fleaMenuPOJO.getMenuCode(), fleaMenuPOJO.getMenuName(),
                    fleaMenuPOJO.getMenuLevel(), fleaMenuPOJO.getParentId());
        } else {
            menuList = fleaMenuSV.queryAllValidMenus();
        }

        return menuList;
    }
}
