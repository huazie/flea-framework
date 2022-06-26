package com.huazie.fleaframework.auth.base.function.resource;

import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaFunctionModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;

import java.util.List;

/**
 * FleaFS资源
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaFSResource {

    private IFleaFunctionModuleSV fleaFunctionModuleSV;

    public FleaFSResource(IFleaFunctionModuleSV fleaFunctionModuleSV) {
        this.fleaFunctionModuleSV = fleaFunctionModuleSV;
    }

    public void addFleaFSResource() throws CommonException {
        addUploadResource();

        addDownloadResource();
    }

    // 添加 上传资源
    private void addUploadResource() throws CommonException {
        FleaResourcePOJO fleaResourcePOJO = new FleaResourcePOJO();
        fleaResourcePOJO.setResourceCode("upload");
        fleaResourcePOJO.setResourceName("上传资源");
        fleaResourcePOJO.setResourceDesc("该资源用于提供上传鉴权，文件上传等服务");
        fleaResourcePOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaResourcePOJO.setRemarks("该资源用于提供上传鉴权，文件上传等服务");
        fleaFunctionModuleSV.addFleaResource(fleaResourcePOJO);
    }

    // 添加 下载资源
    private void addDownloadResource() throws CommonException {
        FleaResourcePOJO fleaResourcePOJO = new FleaResourcePOJO();
        fleaResourcePOJO.setResourceCode("download");
        fleaResourcePOJO.setResourceName("下载资源");
        fleaResourcePOJO.setResourceDesc("该资源用于提供下载鉴权，文件下载等服务");
        fleaResourcePOJO.setFunctionAttrPOJOList(addFleaFunctionAttrPOJOList());
        fleaResourcePOJO.setRemarks("该资源用于提供下载鉴权，文件下载等服务");
        fleaFunctionModuleSV.addFleaResource(fleaResourcePOJO);
    }

    // 添加 资源扩展属性【归属系统】
    private List<FleaFunctionAttrPOJO> addFleaFunctionAttrPOJOList() {
        String systemId = "1002";
        String systemName = "Flea文件服务器";
        return CollectionUtils.newArrayList(FleaAuthPOJOUtils.newSystemInUseAttr(systemId, systemName));
    }
}
