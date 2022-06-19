package com.huazie.fleaframework.auth.common.pojo.function.attr;

import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;

import java.util.List;

/**
 * 功能其他数据POJO类，包含功能扩展属性集
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaFunctionOtherPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = 8512946007783045308L;

    private List<FleaFunctionAttrPOJO> functionAttrPOJOList; // 功能扩展属性集合

    public List<FleaFunctionAttrPOJO> getFunctionAttrPOJOList() {
        return functionAttrPOJOList;
    }

    public void setFunctionAttrPOJOList(List<FleaFunctionAttrPOJO> functionAttrPOJOList) {
        this.functionAttrPOJOList = functionAttrPOJOList;
    }

    /**
     * 统一设置功能扩展属性列表中功能扩展属性信息的功能编号和功能类型
     *
     * @param functionId   功能编号
     * @param functionType 功能类型
     * @since 2.0.0
     */
    public void setFunctionIdAndType(Long functionId, String functionType) {
        if (CollectionUtils.isNotEmpty(functionAttrPOJOList)) {
            for (FleaFunctionAttrPOJO fleaFunctionAttrPOJO : functionAttrPOJOList) {
                if (ObjectUtils.isNotEmpty(fleaFunctionAttrPOJO)) {
                    fleaFunctionAttrPOJO.setFunctionId(functionId);
                    fleaFunctionAttrPOJO.setFunctionType(functionType);
                }
            }
        }
    }
}
