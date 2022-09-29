package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaElementSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaResourceSV;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionOtherPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.element.FleaElementPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.POJOUtils;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Flea功能管理服务层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaFunctionModuleSV")
public class FleaFunctionModuleSVImpl implements IFleaFunctionModuleSV {

    private IFleaMenuSV fleaMenuSV; // Flea菜单服务

    private IFleaOperationSV fleaOperationSV; // Flea操作服务

    private IFleaElementSV fleaElementSV; // Flea元素服务

    private IFleaResourceSV fleaResourceSV; // Flea资源服务

    private IFleaFunctionAttrSV fleaFunctionAttrSV; // Flea扩展属性服务

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeRelSV fleaPrivilegeRelSV; // Flea权限关联服务

    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV; // Flea权限组服务

    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV; // 权限组关联服务

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

    @Resource(name = "fleaFunctionAttrSV")
    public void setFleaFunctionAttrSV(IFleaFunctionAttrSV fleaFunctionAttrSV) {
        this.fleaFunctionAttrSV = fleaFunctionAttrSV;
    }

    @Resource(name = "fleaPrivilegeSV")
    public void setFleaPrivilegeSV(IFleaPrivilegeSV fleaPrivilegeSV) {
        this.fleaPrivilegeSV = fleaPrivilegeSV;
    }

    @Resource(name = "fleaPrivilegeRelSV")
    public void setFleaPrivilegeRelSV(IFleaPrivilegeRelSV fleaPrivilegeRelSV) {
        this.fleaPrivilegeRelSV = fleaPrivilegeRelSV;
    }

    @Resource(name = "fleaPrivilegeGroupSV")
    public void setFleaPrivilegeGroupSV(IFleaPrivilegeGroupSV fleaPrivilegeGroupSV) {
        this.fleaPrivilegeGroupSV = fleaPrivilegeGroupSV;
    }

    @Resource(name = "fleaPrivilegeGroupRelSV")
    public void setFleaPrivilegeGroupRelSV(IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV) {
        this.fleaPrivilegeGroupRelSV = fleaPrivilegeGroupRelSV;
    }

    @Override
    public List<FleaMenu> queryValidMenus(FleaMenuPOJO fleaMenuPOJO) throws CommonException {
        List<FleaMenu> menuList;

        if (ObjectUtils.isEmpty(fleaMenuPOJO)) {
            menuList = this.fleaMenuSV.queryAllValidMenus();
        } else {
            menuList = this.fleaMenuSV.queryValidMenus(fleaMenuPOJO.getMenuCode(), fleaMenuPOJO.getMenuName(),
                    fleaMenuPOJO.getMenuLevel(), fleaMenuPOJO.getParentId());
        }

        return menuList;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public Long addFleaMenu(FleaMenuPOJO fleaMenuPOJO) throws CommonException {
        // 保存Flea菜单
        FleaMenu fleaMenu = this.fleaMenuSV.saveFleaMenu(fleaMenuPOJO);

        // 取菜单编号
        Long menuId = fleaMenu.getMenuId();

        // 设置菜单扩展属性信息的功能编号和功能类型
        fleaMenuPOJO.setFunctionIdAndType(menuId, FunctionTypeEnum.MENU.getType());

        // 保存菜单扩展属性信息
        addFleaFunctionAttr(fleaMenuPOJO);

        // 菜单名称
        String menuName = fleaMenu.getMenuName();

        // 获取菜单访问相关的主权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.MENU.getType());

        // 添加权限【访问《XXX》菜单】
        FleaPrivilegePOJO fleaPrivilegePOJO = FleaAuthPOJOUtils.newFleaPrivilegePOJOForMenu(menuName, fleaPrivilegeGroup);
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO);

        // 添加权限关联【访问《XXX》菜单】
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelMenuPOJO(fleaPrivilege.getPrivilegeId(), menuId, menuName);
        this.fleaPrivilegeRelSV.savePrivilegeRel(fleaPrivilegeRelPOJO);

        // 添加权限组关联权限
        this.fleaPrivilegeGroupRelSV.savePrivilegeGroup(fleaPrivilegeGroup, fleaPrivilege);

        return menuId;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void modifyFleaMenu(Long menuId, FleaMenuPOJO fleaMenuPOJO) throws CommonException {
        // 校验菜单编号
        FleaAuthCheck.checkMenuId(menuId);

        // 校验Flea菜单POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaMenuPOJO, FleaMenuPOJO.class.getSimpleName());

        // 查询有效的Flea菜单数据
        FleaMenu fleaMenu = this.fleaMenuSV.queryValidMenu(menuId);
        // 校验Flea菜单是否存在
        FleaAuthCheck.checkFleaMenuExist(fleaMenu, menuId);

        // 将Flea菜单POJO对象中非空的数据，复制到Flea菜单数据中
        POJOUtils.copyNotEmpty(fleaMenuPOJO, fleaMenu);

        // 更新Flea菜单数据
        this.fleaMenuSV.update(fleaMenu);

        // 修改菜单扩展属性
        this.modifyFleaFunctionAttrs(fleaMenuPOJO.getFunctionAttrPOJOList());
    }

    @Override
    public List<FleaOperation> queryValidOperations(FleaOperationPOJO fleaOperationPOJO) throws CommonException {
        List<FleaOperation> operationList;

        if (ObjectUtils.isEmpty(fleaOperationPOJO)) {
            operationList = this.fleaOperationSV.queryAllValidOperations();
        } else {
            operationList = this.fleaOperationSV.queryValidOperations(fleaOperationPOJO.getOperationCode(), fleaOperationPOJO.getOperationName());
        }

        return operationList;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public Long addFleaOperation(FleaOperationPOJO fleaOperationPOJO) throws CommonException {
        // 保存Flea操作
        FleaOperation fleaOperation = this.fleaOperationSV.saveOperation(fleaOperationPOJO);

        // 取操作编号
        Long operationId = fleaOperation.getOperationId();

        // 设置操作扩展属性信息的功能编号和功能类型
        fleaOperationPOJO.setFunctionIdAndType(operationId, FunctionTypeEnum.OPERATION.getType());

        // 保存操作扩展属性信息
        addFleaFunctionAttr(fleaOperationPOJO);

        // 操作名称
        String operationName = fleaOperation.getOperationName();

        // 获取操作执行相关的主权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.OPERATION.getType());

        // 添加权限【执行《XXX》操作】
        FleaPrivilegePOJO fleaPrivilegePOJO = FleaAuthPOJOUtils.newFleaPrivilegePOJOForOperation(operationName, fleaPrivilegeGroup);
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO);

        // 添加权限关联【执行《XXX》操作】
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelOperationPOJO(fleaPrivilege.getPrivilegeId(), operationId, operationName);
        this.fleaPrivilegeRelSV.savePrivilegeRel(fleaPrivilegeRelPOJO);

        // 添加权限组关联权限
        this.fleaPrivilegeGroupRelSV.savePrivilegeGroup(fleaPrivilegeGroup, fleaPrivilege);

        return operationId;
    }

    @Override
    public void modifyFleaOperation(Long operationId, FleaOperationPOJO fleaOperationPOJO) throws CommonException {
        // 校验操作编号
        FleaAuthCheck.checkOperationId(operationId);

        // 校验Flea操作POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaOperationPOJO, FleaOperationPOJO.class.getSimpleName());

        // 查询有效的Flea操作数据
        FleaOperation fleaOperation = this.fleaOperationSV.queryValidOperation(operationId);
        // 校验Flea操作是否存在
        FleaAuthCheck.checkFleaOperationExist(fleaOperation, operationId);

        // 将Flea操作POJO对象中非空的数据，复制到Flea操作数据中
        POJOUtils.copyNotEmpty(fleaOperationPOJO, fleaOperation);

        // 更新Flea操作数据
        this.fleaOperationSV.update(fleaOperation);

        // 修改操作扩展属性
        this.modifyFleaFunctionAttrs(fleaOperationPOJO.getFunctionAttrPOJOList());
    }

    @Override
    public List<FleaElement> queryValidElements(FleaElementPOJO fleaElementPOJO) throws CommonException {
        List<FleaElement> elementList;

        if (ObjectUtils.isEmpty(fleaElementPOJO)) {
            elementList = this.fleaElementSV.queryAllValidElements();
        } else {
            elementList = this.fleaElementSV.queryValidElements(fleaElementPOJO.getElementCode(), fleaElementPOJO.getElementName(),
                    fleaElementPOJO.getElementType(), fleaElementPOJO.getElementContent());
        }

        return elementList;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public Long addFleaElement(FleaElementPOJO fleaElementPOJO) throws CommonException {
        // 保存Flea元素
        FleaElement fleaElement = this.fleaElementSV.saveElement(fleaElementPOJO);

        // 取元素编号
        Long elementId = fleaElement.getElementId();

        // 设置元素扩展属性信息的功能编号和功能类型
        fleaElementPOJO.setFunctionIdAndType(elementId, FunctionTypeEnum.ELEMENT.getType());

        // 保存元素扩展属性信息
        addFleaFunctionAttr(fleaElementPOJO);

        // 元素名称
        String elementName = fleaElement.getElementName();

        // 获取元素展示相关的主权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.ELEMENT.getType());

        // 添加权限【展示《XXX》元素】
        FleaPrivilegePOJO fleaPrivilegePOJO = FleaAuthPOJOUtils.newFleaPrivilegePOJOForElement(elementName, fleaPrivilegeGroup);
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO);

        // 添加权限关联【展示《XXX》元素】
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelElementPOJO(fleaPrivilege.getPrivilegeId(), elementId, elementName);
        this.fleaPrivilegeRelSV.savePrivilegeRel(fleaPrivilegeRelPOJO);

        // 添加权限组关联权限
        this.fleaPrivilegeGroupRelSV.savePrivilegeGroup(fleaPrivilegeGroup, fleaPrivilege);

        return elementId;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void modifyFleaElement(Long elementId, FleaElementPOJO fleaElementPOJO) throws CommonException {
        // 校验元素编号
        FleaAuthCheck.checkElementId(elementId);

        // 校验Flea元素POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaElementPOJO, FleaElementPOJO.class.getSimpleName());

        // 查询有效的Flea元素数据
        FleaElement fleaElement = this.fleaElementSV.queryValidElement(elementId);
        // 校验Flea元素是否存在
        FleaAuthCheck.checkFleaElementExist(fleaElement, elementId);

        // 将Flea元素POJO对象中非空的数据，复制到Flea元素数据中
        POJOUtils.copyNotEmpty(fleaElementPOJO, fleaElement);

        // 更新Flea元素数据
        this.fleaElementSV.update(fleaElement);

        // 修改元素扩展属性
        this.modifyFleaFunctionAttrs(fleaElementPOJO.getFunctionAttrPOJOList());
    }

    @Override
    public List<FleaResource> queryValidResources(FleaResourcePOJO fleaResourcePOJO) throws CommonException {
        List<FleaResource> resourceList;

        if (ObjectUtils.isEmpty(fleaResourcePOJO)) {
            resourceList = this.fleaResourceSV.queryAllValidResources();
        } else {
            resourceList = this.fleaResourceSV.queryValidResources(fleaResourcePOJO.getResourceCode(), fleaResourcePOJO.getResourceName());
        }

        return resourceList;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public Long addFleaResource(FleaResourcePOJO fleaResourcePOJO) throws CommonException {
        // 保存Flea资源
        FleaResource fleaResource = this.fleaResourceSV.saveResource(fleaResourcePOJO);

        // 取资源编号
        Long resourceId = fleaResource.getResourceId();

        // 设置资源扩展属性信息的功能编号和功能类型
        fleaResourcePOJO.setFunctionIdAndType(resourceId, FunctionTypeEnum.RESOURCE.getType());

        // 保存资源扩展属性信息
        addFleaFunctionAttr(fleaResourcePOJO);

        // 资源名称
        String resourceName = fleaResource.getResourceName();

        // 获取资源调用相关的主权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryMainPrivilegeGroupInUse(FunctionTypeEnum.RESOURCE.getType());

        // 添加权限【调用《XXX》资源】
        FleaPrivilegePOJO fleaPrivilegePOJO = FleaAuthPOJOUtils.newFleaPrivilegePOJOForResource(resourceName, fleaPrivilegeGroup);
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO);

        // 添加权限关联【调用《XXX》资源】
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = FleaAuthPOJOUtils.newFleaPrivilegeRelResourcePOJO(fleaPrivilege.getPrivilegeId(), resourceId, resourceName);
        this.fleaPrivilegeRelSV.savePrivilegeRel(fleaPrivilegeRelPOJO);

        // 添加权限组关联权限
        this.fleaPrivilegeGroupRelSV.savePrivilegeGroup(fleaPrivilegeGroup, fleaPrivilege);

        return resourceId;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void modifyFleaResource(Long resourceId, FleaResourcePOJO fleaResourcePOJO) throws CommonException {
        // 校验资源编号
        FleaAuthCheck.checkResourceId(resourceId);

        // 校验Flea资源POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaResourcePOJO, FleaResourcePOJO.class.getSimpleName());

        // 查询有效的Flea资源数据
        FleaResource fleaResource = this.fleaResourceSV.queryValidResource(resourceId);
        // 校验Flea资源是否存在
        FleaAuthCheck.checkFleaResourceExist(fleaResource, resourceId);

        // 将Flea资源POJO对象中非空的数据，复制到Flea资源数据中
        POJOUtils.copyNotEmpty(fleaResourcePOJO, fleaResource);

        // 更新Flea资源数据
        this.fleaResourceSV.update(fleaResource);

        // 修改资源扩展属性
        this.modifyFleaFunctionAttrs(fleaResourcePOJO.getFunctionAttrPOJOList());
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void addFleaFunctionAttr(FleaFunctionOtherPOJO fleaFunctionOtherPOJO) throws CommonException {
        if (ObjectUtils.isEmpty(fleaFunctionOtherPOJO)) return;

        List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList = fleaFunctionOtherPOJO.getFunctionAttrPOJOList();
        if (CollectionUtils.isEmpty(fleaFunctionAttrPOJOList)) return;

        for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : fleaFunctionAttrPOJOList) {
            // 校验功能扩展属性POJO对象
            FleaAuthCheck.checkFleaFunctionAttrPOJO(fleaFunctionAttrPOJO);

            // 校验功能编号
            FleaAuthCheck.checkFunctionId(fleaFunctionAttrPOJO.getFunctionId());
        }

        // 保存功能扩展属性信息
        this.fleaFunctionAttrSV.saveFunctionAttrs(fleaFunctionAttrPOJOList);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void modifyFleaFunctionAttr(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException {
        // 校验功能扩展属性POJO对象
        FleaAuthCheck.checkFleaFunctionAttrPOJO(fleaFunctionAttrPOJO);

        // 校验属性编号
        Long attrId = fleaFunctionAttrPOJO.getAttrId();
        FleaAuthCheck.checkAttrId(attrId);

        // 根据属性编号，功能类型，查询有效的功能扩展属性信息
        String functionType = fleaFunctionAttrPOJO.getFunctionType();
        FleaFunctionAttr fleaFunctionAttr = this.fleaFunctionAttrSV.queryValidFunctionAttr(attrId, functionType);
        // 校验功能扩展属性
        FleaAuthCheck.checkFleaFunctionAttrExist(fleaFunctionAttr, attrId, functionType);

        // 将Flea功能扩展属性POJO对象中非空的数据，复制到Flea功能扩展属性信息中
        POJOUtils.copyNotEmpty(fleaFunctionAttrPOJO, fleaFunctionAttr);

        // 更新Flea功能扩展属性
        this.fleaFunctionAttrSV.update(fleaFunctionAttr);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void modifyFleaFunctionAttrs(List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOList) throws CommonException {
        if (CollectionUtils.isEmpty(fleaFunctionAttrPOJOList)) return;

        for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : fleaFunctionAttrPOJOList) {
            modifyFleaFunctionAttr(fleaFunctionAttrPOJO);
        }
    }
}
