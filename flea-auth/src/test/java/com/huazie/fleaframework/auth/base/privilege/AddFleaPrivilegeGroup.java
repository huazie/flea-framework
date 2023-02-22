package com.huazie.fleaframework.auth.base.privilege;

import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaPrivilegeModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 添加Flea权限组数据
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class AddFleaPrivilegeGroup {

    private IFleaPrivilegeModuleSV fleaPrivilegeModuleSV;

    public AddFleaPrivilegeGroup(IFleaPrivilegeModuleSV fleaPrivilegeModuleSV) {
        this.fleaPrivilegeModuleSV = fleaPrivilegeModuleSV;
    }

    public void addFleaPrivilegeGroup() throws CommonException {
        int isMain = FleaAuthConstants.PrivilegeModuleConstants.MAIN;
        addPrivilegeGroupForMenu(isMain);
        addPrivilegeGroupForOperation(isMain);
        addPrivilegeGroupForElement(isMain);
        addPrivilegeGroupForResource(isMain);
    }

    private void addPrivilegeGroupForMenu(int isMain) throws CommonException {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForMenu("菜单访问", "", isMain, "");
        fleaPrivilegeModuleSV.addFleaPrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    private void addPrivilegeGroupForOperation(int isMain) throws CommonException {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForOperation("操作执行", "", isMain, "");
        fleaPrivilegeModuleSV.addFleaPrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    private void addPrivilegeGroupForElement(int isMain) throws CommonException {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForElement("元素展示", "", isMain, "");
        fleaPrivilegeModuleSV.addFleaPrivilegeGroup(fleaPrivilegeGroupPOJO);
    }

    private void addPrivilegeGroupForResource(int isMain) throws CommonException {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = FleaAuthPOJOUtils.newFleaPrivilegeGroupPOJOForResource("资源调用", "", isMain, "");
        fleaPrivilegeModuleSV.addFleaPrivilegeGroup(fleaPrivilegeGroupPOJO);
    }
}
