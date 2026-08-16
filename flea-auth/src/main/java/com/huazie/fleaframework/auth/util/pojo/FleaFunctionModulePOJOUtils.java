package com.huazie.fleaframework.auth.util.pojo;

import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * Flea功能相关POJO工具类
 *
 * <p>该类包含功能(菜单、操作、元素、资源)相关的POJO创建方法。</p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaFunctionModulePOJOUtils {

    private FleaFunctionModulePOJOUtils() {
    }

    /**
     * 新建功能扩展属性POJO对象
     *
     * @param attrCode  属性码
     * @param attrValue 属性值
     * @param attrDesc  属性描述
     * @param remarks   备注
     * @return 功能扩展属性POJO对象
     * @since 2.0.0
     */
    public static FleaFunctionAttrPOJO newFleaFunctionAttrPOJO(String attrCode, String attrValue, String attrDesc, String remarks) {
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = new FleaFunctionAttrPOJO();
        fleaFunctionAttrPOJO.setAttrCode(attrCode);
        fleaFunctionAttrPOJO.setAttrValue(attrValue);
        fleaFunctionAttrPOJO.setAttrDesc(attrDesc);
        if (StringUtils.isBlank(remarks)) {
            remarks = FleaI18nHelper.i18nForAuth("AUTH-FUNCTION0000000003", new String[]{attrDesc});
        }
        fleaFunctionAttrPOJO.setRemarks(remarks);
        return fleaFunctionAttrPOJO;
    }

    /**
     * 新建功能扩展属性【归属系统】
     *
     * @param systemId   系统编号
     * @param systemName 系统名称
     * @return 功能扩展属性【归属系统】
     * @since 2.0.0
     */
    public static FleaFunctionAttrPOJO newSystemInUseAttr(String systemId, String systemName) {
        String[] values = new String[]{systemName};
        String attrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_SYSTEM_IN_USE;
        // 归属系统【{0}】
        String attrDesc = FleaI18nHelper.i18nForAuth("AUTH-FUNCTION0000000001", values);
        // 【{0}】正在使用中
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-FUNCTION0000000002", values);
        return newFleaFunctionAttrPOJO(attrCode, systemId, attrDesc, remarks);
    }

    /**
     * 新建功能扩展属性【操作类型】
     *
     * @param attrValue 属性值
     * @param attrDesc  属性描述
     * @return 功能扩展属性【操作类型】
     * @since 2.0.0
     */
    public static FleaFunctionAttrPOJO newOperationTypeAttr(String attrValue, String attrDesc) {
        String attrCode = FleaAuthConstants.AttrCodeConstants.ATTR_CODE_OPERATION_TYPE;
        return newFleaFunctionAttrPOJO(attrCode, attrValue, attrDesc, null);
    }
}
