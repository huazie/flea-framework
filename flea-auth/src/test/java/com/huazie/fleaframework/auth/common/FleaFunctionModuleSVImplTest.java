package com.huazie.fleaframework.auth.common;

import com.huazie.fleaframework.auth.base.function.menu.AddFleaMenu;
import com.huazie.fleaframework.auth.base.function.operation.FleaFSFileOperation;
import com.huazie.fleaframework.auth.base.function.operation.FleaFSFileOperationAttr;
import com.huazie.fleaframework.auth.base.function.resource.FleaFSResource;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaFunctionModuleSVImplTest {

    @Resource(name = "fleaFunctionModuleSV")
    private IFleaFunctionModuleSV fleaFunctionModuleSV;

    @Test
    public void queryValidMenus() throws CommonException {
        fleaFunctionModuleSV.queryValidMenus(null);
    }

    @Test
    public void addFleaMenu() throws Exception {
        AddFleaMenu addFleaMenu = new AddFleaMenu(fleaFunctionModuleSV);
        addFleaMenu.addAuthMgmtMenu();
    }

    @Test
    public void modifyFleaMenu() throws CommonException {
        FleaMenuPOJO fleaMenuPOJO = new FleaMenuPOJO();
        fleaMenuPOJO.setMenuCode("console"); // 菜单编码
        fleaMenuPOJO.setMenuName("控制台"); // 菜单名称
        fleaMenuPOJO.setMenuIcon("dashboard"); // 菜单FontAwesome小图标
        fleaMenuPOJO.setMenuSort(1);  // 菜单展示顺序(同一个父菜单下)
        fleaMenuPOJO.setMenuView("mgmt/console.html"); // 菜单对应页面（非叶子菜单的可以为空）
        fleaMenuPOJO.setMenuLevel(MenuLevelEnum.LEVEL_ONE.getLevel()); // 菜单层级（1：一级菜单 2；二级菜单 3：三级菜单 4：四级菜单）
        fleaMenuPOJO.setRemarks("控制台，展示收藏夹，快捷菜单入口");
        fleaFunctionModuleSV.modifyFleaMenu(1000L, fleaMenuPOJO);
    }

    @Test
    public void queryValidOperations() throws CommonException {
        fleaFunctionModuleSV.queryValidOperations(null);
    }

    @Test
    public void addFleaOperation() throws CommonException {
        FleaFSFileOperation fleaFSFileOperation = new FleaFSFileOperation(fleaFunctionModuleSV);
        fleaFSFileOperation.addFleaOperation();
    }

    @Test
    public void modifyFleaOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("VERSION");
        fleaOperationPOJO.setOperationName("版本管理");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件版本管理功能");
        fleaOperationPOJO.setRemarks("该操作用于定义文件版本管理功能");
        fleaFunctionModuleSV.modifyFleaOperation(1005L, fleaOperationPOJO);
    }

    @Test
    public void queryValidElements() throws CommonException { // TODO
        fleaFunctionModuleSV.queryValidElements(null);
    }

    @Test
    public void addFleaElement() { // TODO
    }

    @Test
    public void modifyFleaElement() { // TODO
    }

    @Test
    public void queryValidResources() throws CommonException {
        fleaFunctionModuleSV.queryValidResources(null);
    }

    @Test
    public void addFleaResource() throws CommonException {
        FleaFSResource fleaFSResource = new FleaFSResource(fleaFunctionModuleSV);
        fleaFSResource.addFleaFSResource();
    }

    @Test
    public void modifyFleaResource() throws CommonException {
        FleaResourcePOJO fleaResourcePOJO = new FleaResourcePOJO();
        fleaResourcePOJO.setResourceCode("upload");
        fleaResourcePOJO.setResourceName("上传资源");
        fleaResourcePOJO.setResourceDesc("该资源用于提供上传鉴权，文件上传等服务");
        fleaResourcePOJO.setRemarks("该资源用于提供上传鉴权，文件上传等服务");
        fleaFunctionModuleSV.modifyFleaResource(1000L, fleaResourcePOJO);
    }

    @Test
    public void addFleaFunctionAttr() throws CommonException {
        FleaFSFileOperationAttr fleaFSFileOperationAttr = new FleaFSFileOperationAttr(fleaFunctionModuleSV);
        fleaFSFileOperationAttr.addFleaOperationAttr();
    }

    @Test
    public void modifyFleaFunctionAttr() throws CommonException {
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setAttrId(1L);
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.RESOURCE.getType());
        fleaFunctionAttrPOJO.setAttrCode("SYSTEM_IN_USE");
        fleaFunctionAttrPOJO.setAttrDesc("归属系统【Flea文件服务器】");
        fleaFunctionAttrPOJO.setRemarks("【Flea文件服务器】正在使用中");
        fleaFunctionModuleSV.modifyFleaFunctionAttr(fleaFunctionAttrPOJO);
    }

    @Test
    public void modifyFleaFunctionAttrs() throws CommonException {
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setAttrId(1L);
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.RESOURCE.getType());
        fleaFunctionAttrPOJO.setAttrCode("SYSTEM_IN_USE");
        fleaFunctionAttrPOJO.setAttrDesc("归属系统【Flea文件服务器】");
        fleaFunctionAttrPOJO.setRemarks("【Flea文件服务器】正在使用中");

        FleaFunctionAttrPOJO fleaFunctionAttrPOJO1 = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO1.setAttrId(2L);
        fleaFunctionAttrPOJO1.setFunctionType(FunctionTypeEnum.RESOURCE.getType());
        fleaFunctionAttrPOJO1.setAttrCode("SYSTEM_IN_USE");
        fleaFunctionAttrPOJO1.setAttrDesc("归属系统【Flea文件服务器】");
        fleaFunctionAttrPOJO1.setRemarks("【Flea文件服务器】正在使用中");

        List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOS = CollectionUtils.newArrayList(fleaFunctionAttrPOJO);
        fleaFunctionAttrPOJOS.add(fleaFunctionAttrPOJO1);

        fleaFunctionModuleSV.modifyFleaFunctionAttrs(fleaFunctionAttrPOJOS);
    }
}