package com.huazie.fleaframework.auth.base.function;

import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaFunctionAttrSVImplTest {

    @Resource(name = "fleaFunctionAttrSV")
    private IFleaFunctionAttrSV fleaFunctionAttrSV;

    @Test
    public void queryValidFunctionAttr() throws CommonException {
        fleaFunctionAttrSV.queryValidFunctionAttr(1L, FunctionTypeEnum.MENU.getType());
    }

    @Test
    public void queryValidFunctionAttrs() throws CommonException {
        fleaFunctionAttrSV.queryValidFunctionAttrs(null, FunctionTypeEnum.MENU.getType(), FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE);
    }

    @Test
    public void querySystemFunctionAttrs() throws CommonException {
        fleaFunctionAttrSV.querySystemRelFunctionIds(FunctionTypeEnum.MENU.getType(), 1001L);
    }

    @Test
    public void isExistSystemRelFunction() throws CommonException {
        fleaFunctionAttrSV.isExistSystemRelFunction(1000L, "MENU", 1001L);
    }

    @Test
    public void saveMenuAttr() throws CommonException {

        String systemId = "1001";
        String systemName = "Flea管家";
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = FleaAuthPOJOUtils.newSystemInUseAttr(systemId, systemName);

        // 功能编号
        fleaFunctionAttrPOJO.setFunctionId(1000L);
        // 功能类型(菜单、操作、元素、资源)
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());

        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
    }

    @Test
    public void saveOperationAttr() throws CommonException { // TODO

        String attrCode = "OPERATION_ATTR";
        String attrValue = "OPERATION_ATTR";
        String attrDesc = "OPERATION_ATTR";
        String remarks = "OPERATION_ATTR";
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = FleaAuthPOJOUtils.newFleaFunctionAttrPOJO(attrCode, attrValue, attrDesc, remarks);

        // 功能编号
        fleaFunctionAttrPOJO.setFunctionId(1000L);
        // 功能类型(菜单、操作、元素、资源)
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.OPERATION.getType());

        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
    }

    @Test
    public void saveElementAttr() throws CommonException { // TODO

        String attrCode = "ELEMENT_ATTR";
        String attrValue = "ELEMENT_ATTR";
        String attrDesc = "ELEMENT_ATTR";
        String remarks = "ELEMENT_ATTR";
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = FleaAuthPOJOUtils.newFleaFunctionAttrPOJO(attrCode, attrValue, attrDesc, remarks);

        // 功能编号
        fleaFunctionAttrPOJO.setFunctionId(1000L);
        // 功能类型(菜单、操作、元素、资源)
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.ELEMENT.getType());

        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
    }

    @Test
    public void saveResourceAttr() throws CommonException {

        String systemId = "1002";
        String systemName = "Flea文件服务器";
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = FleaAuthPOJOUtils.newSystemInUseAttr(systemId, systemName);

        // 功能编号
        fleaFunctionAttrPOJO.setFunctionId(1000L);
        // 功能类型(菜单、操作、元素、资源)
        fleaFunctionAttrPOJO.setFunctionType(FunctionTypeEnum.RESOURCE.getType());

        fleaFunctionAttrSV.saveFunctionAttr(fleaFunctionAttrPOJO);
    }

}
