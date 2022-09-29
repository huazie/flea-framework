package com.huazie.fleaframework.auth.base.function.operation;

import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionOtherPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;

import java.util.List;

/**
 * FleaFS 文件操作属性
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaFSFileOperationAttr {

    private IFleaFunctionModuleSV fleaFunctionModuleSV;

    public FleaFSFileOperationAttr(IFleaFunctionModuleSV fleaFunctionModuleSV) {
        this.fleaFunctionModuleSV = fleaFunctionModuleSV;
    }

    public void addFleaOperationAttr() throws CommonException {
        addFleaFSUploadOperationTypeAttr();
        addFleaFSDownloadOperationTypeAttr();
        addFleaFSUpdateOperationTypeAttr();
        addFleaFSDeleteOperationTypeAttr();
        addFleaFSSearchOperationTypeAttr();
        addFleaFSVersionOperationTypeAttr();
    }

    // 添加 “文件上传” 操作类型属性
    private void addFleaFSUploadOperationTypeAttr() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("UPLOAD");
        List<FleaOperation> fleaOperationList = fleaFunctionModuleSV.queryValidOperations(fleaOperationPOJO);
        if (CollectionUtils.isNotEmpty(fleaOperationList)) {
            Long functionId = fleaOperationList.get(0).getOperationId();
            String attrDesc = "《文件上传》操作类型";
            FleaFunctionOtherPOJO fleaFunctionOtherPOJO = newFleaFunctionOtherPOJOForOperation(functionId, "1", attrDesc);
            fleaFunctionModuleSV.addFleaFunctionAttr(fleaFunctionOtherPOJO);
        }
    }

    // 添加 “文件下载” 操作类型属性
    private void addFleaFSDownloadOperationTypeAttr() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("DOWNLOAD");
        List<FleaOperation> fleaOperationList = fleaFunctionModuleSV.queryValidOperations(fleaOperationPOJO);
        if (CollectionUtils.isNotEmpty(fleaOperationList)) {
            Long functionId = fleaOperationList.get(0).getOperationId();
            String attrDesc = "《文件下载》操作类型";
            FleaFunctionOtherPOJO fleaFunctionOtherPOJO = newFleaFunctionOtherPOJOForOperation(functionId, "2", attrDesc);
            fleaFunctionModuleSV.addFleaFunctionAttr(fleaFunctionOtherPOJO);
        }
    }

    // 添加 “文件更新” 操作类型属性
    private void addFleaFSUpdateOperationTypeAttr() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("UPDATE");
        List<FleaOperation> fleaOperationList = fleaFunctionModuleSV.queryValidOperations(fleaOperationPOJO);
        if (CollectionUtils.isNotEmpty(fleaOperationList)) {
            Long functionId = fleaOperationList.get(0).getOperationId();
            String attrDesc = "《文件更新》操作类型";
            FleaFunctionOtherPOJO fleaFunctionOtherPOJO = newFleaFunctionOtherPOJOForOperation(functionId, "3", attrDesc);
            fleaFunctionModuleSV.addFleaFunctionAttr(fleaFunctionOtherPOJO);
        }
    }

    // 添加 “文件删除” 操作类型属性
    private void addFleaFSDeleteOperationTypeAttr() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("DELETE");
        List<FleaOperation> fleaOperationList = fleaFunctionModuleSV.queryValidOperations(fleaOperationPOJO);
        if (CollectionUtils.isNotEmpty(fleaOperationList)) {
            Long functionId = fleaOperationList.get(0).getOperationId();
            String attrDesc = "《文件删除》操作类型";
            FleaFunctionOtherPOJO fleaFunctionOtherPOJO = newFleaFunctionOtherPOJOForOperation(functionId, "4", attrDesc);
            fleaFunctionModuleSV.addFleaFunctionAttr(fleaFunctionOtherPOJO);
        }
    }

    // 添加 “文件搜索” 操作类型属性
    private void addFleaFSSearchOperationTypeAttr() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("SEARCH");
        List<FleaOperation> fleaOperationList = fleaFunctionModuleSV.queryValidOperations(fleaOperationPOJO);
        if (CollectionUtils.isNotEmpty(fleaOperationList)) {
            Long functionId = fleaOperationList.get(0).getOperationId();
            String attrDesc = "《文件搜索》操作类型";
            FleaFunctionOtherPOJO fleaFunctionOtherPOJO = newFleaFunctionOtherPOJOForOperation(functionId, "5", attrDesc);
            fleaFunctionModuleSV.addFleaFunctionAttr(fleaFunctionOtherPOJO);
        }
    }

    // 添加 “版本管理” 操作类型属性
    private void addFleaFSVersionOperationTypeAttr() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("VERSION");
        List<FleaOperation> fleaOperationList = fleaFunctionModuleSV.queryValidOperations(fleaOperationPOJO);
        if (CollectionUtils.isNotEmpty(fleaOperationList)) {
            Long functionId = fleaOperationList.get(0).getOperationId();
            String attrDesc = "《版本管理》操作类型";
            FleaFunctionOtherPOJO fleaFunctionOtherPOJO = newFleaFunctionOtherPOJOForOperation(functionId, "6", attrDesc);
            fleaFunctionModuleSV.addFleaFunctionAttr(fleaFunctionOtherPOJO);
        }
    }

    // 添加 ”操作类型“ 属性
    private FleaFunctionOtherPOJO newFleaFunctionOtherPOJOForOperation(Long functionId, String attrValue, String attrDesc) {
        FleaFunctionAttrPOJO fleaFunctionAttrPOJO = FleaAuthPOJOUtils.newOperationTypeAttr(attrValue, attrDesc);
        List<FleaFunctionAttrPOJO> fleaFunctionAttrPOJOS = CollectionUtils.newArrayList(fleaFunctionAttrPOJO);
        FleaFunctionOtherPOJO fleaFunctionOtherPOJO = new FleaFunctionOtherPOJO();
        fleaFunctionOtherPOJO.setFunctionAttrPOJOList(fleaFunctionAttrPOJOS);
        fleaFunctionOtherPOJO.setFunctionIdAndType(functionId, FunctionTypeEnum.OPERATION.getType());
        return fleaFunctionOtherPOJO;
    }
}
