package com.huazie.fleaframework.auth.base.function.operation;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;

import java.util.List;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaFSFileOperation {

    private IFleaFunctionModuleSV fleaFunctionModuleSV;

    public FleaFSFileOperation(IFleaFunctionModuleSV fleaFunctionModuleSV) {
        this.fleaFunctionModuleSV = fleaFunctionModuleSV;
    }

    public void addFleaOperation() throws CommonException {
        addFleaFSUploadOperation();
        addFleaFSDownloadOperation();
        addFleaFSUpdateOperation();
        addFleaFSDeleteOperation();
        addFleaFSSearchOperation();
        addFleaFSVersionOperation();
    }

    // 添加 “文件上传” 操作
    private void addFleaFSUploadOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("UPLOAD");
        fleaOperationPOJO.setOperationName("文件上传");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件上传功能");
        fleaOperationPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaOperationPOJO.setRemarks("该操作用于定义文件上传功能");
        fleaFunctionModuleSV.addFleaOperation(fleaOperationPOJO);
    }

    // 添加 “文件下载” 操作
    private void addFleaFSDownloadOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("DOWNLOAD");
        fleaOperationPOJO.setOperationName("文件下载");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件下载功能");
        fleaOperationPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaOperationPOJO.setRemarks("该操作用于定义文件下载功能");
        fleaFunctionModuleSV.addFleaOperation(fleaOperationPOJO);
    }

    // 添加 “文件更新” 操作
    private void addFleaFSUpdateOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("UPDATE");
        fleaOperationPOJO.setOperationName("文件更新");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件更新功能");
        fleaOperationPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaOperationPOJO.setRemarks("该操作用于定义文件更新功能");
        fleaFunctionModuleSV.addFleaOperation(fleaOperationPOJO);
    }

    // 添加 “文件删除” 操作
    private void addFleaFSDeleteOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("DELETE");
        fleaOperationPOJO.setOperationName("文件删除");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件删除功能");
        fleaOperationPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaOperationPOJO.setRemarks("该操作用于定义文件删除功能");
        fleaFunctionModuleSV.addFleaOperation(fleaOperationPOJO);
    }

    // 添加 “文件搜索” 操作
    private void addFleaFSSearchOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("SEARCH");
        fleaOperationPOJO.setOperationName("文件搜索");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件搜索功能");
        fleaOperationPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaOperationPOJO.setRemarks("该操作用于定义文件搜索功能");
        fleaFunctionModuleSV.addFleaOperation(fleaOperationPOJO);
    }

    // 添加 “版本管理” 操作
    private void addFleaFSVersionOperation() throws CommonException {
        FleaOperationPOJO fleaOperationPOJO = new FleaOperationPOJO();
        fleaOperationPOJO.setOperationCode("VERSION");
        fleaOperationPOJO.setOperationName("版本管理");
        fleaOperationPOJO.setOperationDesc("该操作用于定义文件版本管理功能");
        fleaOperationPOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaOperationPOJO.setRemarks("该操作用于定义文件版本管理功能");
        fleaFunctionModuleSV.addFleaOperation(fleaOperationPOJO);
    }

    // 添加 操作扩展属性【归属系统】
    private List<FleaFunctionAttrPOJO> addFleaFunctionAttrPOJOList() {
        String systemId = "1002";
        String systemName = "Flea文件服务器";
        return CollectionUtils.newArrayList(FleaAuthPOJOUtils.newSystemInUseAttr(systemId, systemName));
    }
}
