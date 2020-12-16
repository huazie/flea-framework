package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResourceDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaConfigEntityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea Jersey 资源DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resourceDAO")
public class FleaJerseyResourceDAOImpl extends FleaConfigDAOImpl<FleaJerseyResource> implements IFleaJerseyResourceDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResourceDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaJerseyResource> getResource(String resourceCode) throws CommonException {

        List<FleaJerseyResource> resourceList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState()) // 查询在用状态的资源定义数据
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Resource List={}", resourceList);
            LOGGER.debug("Resource Count={}", resourceList.size());
        }

        return resourceList;
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

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Resource Package List={}", resPackageList);
            LOGGER.debug("Resource Package Count={}", resPackageList == null ? 0 : resourceList.size());
        }

        return resPackageList;
    }
}
