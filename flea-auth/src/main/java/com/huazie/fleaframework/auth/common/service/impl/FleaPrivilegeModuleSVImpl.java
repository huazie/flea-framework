package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaElementSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaResourceSV;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaPrivilegeModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.POJOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea权限管理服务层
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeModuleSV")
public class FleaPrivilegeModuleSVImpl implements IFleaPrivilegeModuleSV {

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV; // Flea权限组服务

    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV; // 权限组关联服务

    private IFleaPrivilegeRelSV fleaPrivilegeRelSV; // 权限关联服务

    private IFleaMenuSV fleaMenuSV; // Flea菜单服务

    private IFleaOperationSV fleaOperationSV; // Flea操作服务

    private IFleaElementSV fleaElementSV; // Flea元素服务

    private IFleaResourceSV fleaResourceSV; // Flea资源服务

    @Resource(name = "fleaPrivilegeSV")
    public void setFleaPrivilegeSV(IFleaPrivilegeSV fleaPrivilegeSV) {
        this.fleaPrivilegeSV = fleaPrivilegeSV;
    }

    @Resource(name = "fleaPrivilegeGroupSV")
    public void setFleaPrivilegeGroupSV(IFleaPrivilegeGroupSV fleaPrivilegeGroupSV) {
        this.fleaPrivilegeGroupSV = fleaPrivilegeGroupSV;
    }

    @Resource(name = "fleaPrivilegeGroupRelSV")
    public void setFleaPrivilegeGroupRelSV(IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV) {
        this.fleaPrivilegeGroupRelSV = fleaPrivilegeGroupRelSV;
    }

    @Resource(name = "fleaPrivilegeRelSV")
    public void setFleaPrivilegeRelSV(IFleaPrivilegeRelSV fleaPrivilegeRelSV) {
        this.fleaPrivilegeRelSV = fleaPrivilegeRelSV;
    }

    @Resource(name = "fleaMenuSV")
    public void setFleaMenuSV(IFleaMenuSV fleaMenuSV) {
        this.fleaMenuSV = fleaMenuSV;
    }

    @Resource(name = "fleaOperationSV")
    public void setFleaOperationSV(IFleaOperationSV fleaOperationSV) {
        this.fleaOperationSV = fleaOperationSV;
    }

    @Resource(name = "fleaElementSV")
    public void setFleaElementSV(IFleaElementSV fleaElementSV) {
        this.fleaElementSV = fleaElementSV;
    }

    @Resource(name = "fleaResourceSV")
    public void setFleaResourceSV(IFleaResourceSV fleaResourceSV) {
        this.fleaResourceSV = fleaResourceSV;
    }

    @Override
    public Long addFleaPrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        return this.fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO).getPrivilegeId();
    }

    @Override
    public void modifyFleaPrivilege(Long privilegeId, FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 校验Flea权限POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaPrivilegePOJO, FleaPrivilegePOJO.class.getSimpleName());

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));

        // 将Flea权限POJO对象中非 null 的数据，复制到Flea权限数据中（空串支持显式清空）
        POJOUtils.copyNonNull(fleaPrivilegePOJO, fleaPrivilege);

        // 更新Flea权限数据
        this.fleaPrivilegeSV.update(fleaPrivilege);
    }

    @Override
    public Long addFleaPrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        return this.fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO).getPrivilegeGroupId();
    }

    @Override
    public void modifyFleaPrivilegeGroup(Long privilegeGroupId, FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        // 校验权限组编号
        FleaAuthCheck.checkPrivilegeGroupId(privilegeGroupId);

        // 校验Flea权限组POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaPrivilegeGroupPOJO, FleaPrivilegeGroupPOJO.class.getSimpleName());

        // 查询在用的权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(privilegeGroupId);
        // 校验Flea权限组是否存在
        FleaAuthCheck.checkFleaPrivilegeGroupExist(fleaPrivilegeGroup, StringUtils.valueOf(privilegeGroupId));

        // 将Flea权限组POJO对象中非 null 的数据，复制到Flea权限组数据中（空串支持显式清空）
        POJOUtils.copyNonNull(fleaPrivilegeGroupPOJO, fleaPrivilegeGroup);

        // 更新Flea权限组数据
        this.fleaPrivilegeGroupSV.update(fleaPrivilegeGroup);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void privilegeGroupRelPrivilege(Long privilegeGroupId, Long privilegeId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验权限组编号
        FleaAuthCheck.checkPrivilegeGroupId(privilegeGroupId);

        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 查询在用的权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(privilegeGroupId);
        // 校验Flea权限组是否存在
        FleaAuthCheck.checkFleaPrivilegeGroupExist(fleaPrivilegeGroup, StringUtils.valueOf(privilegeGroupId));
        // 权限组名称
        String privilegeGroupName = fleaPrivilegeGroup.getPrivilegeGroupName();

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));
        // 权限名称
        String privilegeName = fleaPrivilege.getPrivilegeName();

        // 权限组编号不为正数，说明权限第一次被权限组关联
        if (!NumberUtils.isPositiveNumber(fleaPrivilege.getGroupId())) {
            // 更新Flea权限数据中权限组编号
            fleaPrivilege.setGroupId(privilegeGroupId);
            fleaPrivilege.setDoneDate(DateUtils.getCurrentTime());
            this.fleaPrivilegeSV.update(fleaPrivilege);
        }

        // 新建权限组关联权限POJO对象
        FleaPrivilegeGroupRelPOJO privilegeGroupRelPOJO = FleaAuthPOJOUtils.newPrivilegeGroupRelPrivilegePOJO(privilegeGroupId, privilegeGroupName, privilegeId, privilegeName);

        // 复制授权关联扩展数据
        POJOUtils.copyNotEmpty(fleaAuthRelExtPOJO, privilegeGroupRelPOJO);

        // 保存Flea权限组关联
        this.fleaPrivilegeGroupRelSV.saveFleaPrivilegeGroupRel(privilegeGroupRelPOJO);
    }

    @Override
    public FleaPrivilege queryPrivilegeInUse(Long privilegeId) throws CommonException {
        return this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
    }

    @Override
    public List<FleaPrivilege> queryPrivilegesInUse(String privilegeName, Long groupId) throws CommonException {
        return this.fleaPrivilegeSV.queryPrivilegesInUse(privilegeName, groupId);
    }

    @Override
    public FleaPrivilegeGroup queryPrivilegeGroupInUse(Long privilegeGroupId) throws CommonException {
        return this.fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(privilegeGroupId);
    }

    @Override
    public List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse(String privilegeGroupName, Integer isMain, String functionType) throws CommonException {
        return this.fleaPrivilegeGroupSV.queryPrivilegeGroupsInUse(privilegeGroupName, isMain, functionType);
    }

    @Override
    public List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException {
        return this.fleaPrivilegeRelSV.getPrivilegeRelList(privilegeId, authRelType);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void removePrivilegeRel(Long privilegeId, Long relId, String authRelType) throws CommonException {
        this.fleaPrivilegeRelSV.removePrivilegeRel(privilegeId, relId, authRelType);
    }

    @Override
    public List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException {
        return this.fleaPrivilegeGroupRelSV.getPrivilegeGroupRelList(privilegeGroupId, authRelType);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void removePrivilegeGroupRel(Long privilegeGroupId, Long relId, String authRelType) throws CommonException {
        this.fleaPrivilegeGroupRelSV.removePrivilegeGroupRel(privilegeGroupId, relId, authRelType);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void privilegeRelMenu(Long privilegeId, Long menuId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 校验菜单编号
        FleaAuthCheck.checkMenuId(menuId);

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));

        // 查询有效的菜单数据
        FleaMenu fleaMenu = this.fleaMenuSV.queryValidMenu(menuId);
        // 校验Flea菜单是否存在
        FleaAuthCheck.checkFleaMenuExist(fleaMenu, menuId);
        // 菜单名称
        String menuName = fleaMenu.getMenuName();

        // 新建权限关联菜单POJO对象
        FleaPrivilegeRelPOJO privilegeRelMenuPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelMenuPOJO(privilegeId, menuId, menuName);

        // 复制授权关联扩展数据
        POJOUtils.copyNotEmpty(fleaAuthRelExtPOJO, privilegeRelMenuPOJO);

        // 保存Flea权限关联
        this.fleaPrivilegeRelSV.savePrivilegeRel(privilegeRelMenuPOJO);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void privilegeRelOperation(Long privilegeId, Long operationId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 校验操作编号
        FleaAuthCheck.checkOperationId(operationId);

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));

        // 查询有效的操作数据
        FleaOperation fleaOperation = this.fleaOperationSV.queryValidOperation(operationId);
        // 校验Flea操作是否存在
        FleaAuthCheck.checkFleaOperationExist(fleaOperation, operationId);
        // 操作名称
        String operationName = fleaOperation.getOperationName();

        // 新建权限关联操作POJO对象
        FleaPrivilegeRelPOJO privilegeRelOperationPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelOperationPOJO(privilegeId, operationId, operationName);

        // 复制授权关联扩展数据
        POJOUtils.copyNotEmpty(fleaAuthRelExtPOJO, privilegeRelOperationPOJO);

        // 保存Flea权限关联
        this.fleaPrivilegeRelSV.savePrivilegeRel(privilegeRelOperationPOJO);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void privilegeRelElement(Long privilegeId, Long elementId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 校验元素编号
        FleaAuthCheck.checkElementId(elementId);

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));

        // 查询有效的元素数据
        FleaElement fleaElement = this.fleaElementSV.queryValidElement(elementId);
        // 校验Flea元素是否存在
        FleaAuthCheck.checkFleaElementExist(fleaElement, elementId);
        // 元素名称
        String elementName = fleaElement.getElementName();

        // 新建权限关联元素POJO对象
        FleaPrivilegeRelPOJO privilegeRelElementPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelElementPOJO(privilegeId, elementId, elementName);

        // 复制授权关联扩展数据
        POJOUtils.copyNotEmpty(fleaAuthRelExtPOJO, privilegeRelElementPOJO);

        // 保存Flea权限关联
        this.fleaPrivilegeRelSV.savePrivilegeRel(privilegeRelElementPOJO);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void privilegeRelResource(Long privilegeId, Long resourceId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 校验资源编号
        FleaAuthCheck.checkResourceId(resourceId);

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));

        // 查询有效的资源数据
        FleaResource fleaResource = this.fleaResourceSV.queryValidResource(resourceId);
        // 校验Flea资源是否存在
        FleaAuthCheck.checkFleaResourceExist(fleaResource, resourceId);
        // 资源名称
        String resourceName = fleaResource.getResourceName();

        // 新建权限关联资源POJO对象
        FleaPrivilegeRelPOJO privilegeRelResourcePOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelResourcePOJO(privilegeId, resourceId, resourceName);

        // 复制授权关联扩展数据
        POJOUtils.copyNotEmpty(fleaAuthRelExtPOJO, privilegeRelResourcePOJO);

        // 保存Flea权限关联
        this.fleaPrivilegeRelSV.savePrivilegeRel(privilegeRelResourcePOJO);
    }
}
