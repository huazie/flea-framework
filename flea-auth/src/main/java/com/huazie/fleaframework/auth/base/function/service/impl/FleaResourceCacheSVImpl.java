package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea资源SV层缓存实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaResourceCacheSV")
public class FleaResourceCacheSVImpl extends FleaResourceSVImpl {

    @Override
    @Cacheable(value = "fleaauthresource", key = "'resourceId_' + #resourceId")
    public FleaResource queryValidResource(Long resourceId) throws CommonException {
        return super.queryValidResource(resourceId);
    }

    @Override
    @Cacheable(value = "fleaauthresource", key = "#resourceCode + '_' + #resourceName")
    public List<FleaResource> queryValidResources(String resourceCode, String resourceName) throws CommonException {
        return super.queryValidResources(resourceCode, resourceName);
    }

    @Override
    @Cacheable(value = "fleaauthresource", key = "'all'")
    public List<FleaResource> queryAllValidResources() throws CommonException {
        return super.queryValidResources(null, null);
    }

}