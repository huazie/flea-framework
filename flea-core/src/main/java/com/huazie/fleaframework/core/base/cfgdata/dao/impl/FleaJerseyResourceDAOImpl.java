package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResourceDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea Jersey 资源DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resourceDAO")
public class FleaJerseyResourceDAOImpl extends FleaConfigDAOImpl<FleaJerseyResource> implements IFleaJerseyResourceDAO {

    @Override
    public List<FleaJerseyResource> getResource(String resourceCode) throws CommonException {
        return getQuery(null)
                .equal(FleaConfigEntityConstants.E_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState()) // 查询在用状态的资源定义数据
                .getResultList();
    }

    @Override
    public List<String> getResourcePackages() throws CommonException {

        // 获取所有的资源定义
        List<FleaJerseyResource> resourceList = getResource(null);

        List<String> resPackageList = null;
        // 遍历资源
        if (CollectionUtils.isNotEmpty(resourceList)) {
            resPackageList = new ArrayList<>();
            for (FleaJerseyResource resource : resourceList) {
                if (ObjectUtils.isNotEmpty(resource)) {
                    // 获取资源包名
                    String resPackages = resource.getResourcePackages();
                    // 如果存在多个，以逗号分隔
                    String[] resPackagesArr = StringUtils.split(resPackages, CommonConstants.SymbolConstants.COMMA);
                    if (ArrayUtils.isNotEmpty(resPackagesArr)) {
                        for (String resPackage : resPackagesArr) {
                            if (StringUtils.isNotBlank(resPackage) && !resPackageList.contains(resPackage)) {
                                resPackageList.add(resPackage);
                            }
                        }
                    }
                }
            }
        }

        return resPackageList;
    }
}
