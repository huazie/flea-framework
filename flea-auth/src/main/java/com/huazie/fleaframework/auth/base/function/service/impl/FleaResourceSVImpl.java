package com.huazie.fleaframework.auth.base.function.service.impl;

import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaResourceDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaResourceSV;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea资源SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaResourceSV")
public class FleaResourceSVImpl extends AbstractFleaJPASVImpl<FleaResource> implements IFleaResourceSV {

    private IFleaResourceDAO fleaResourceDao;

    @Autowired
    @Qualifier("fleaResourceDAO")
    public void setFleaResourceDao(IFleaResourceDAO fleaResourceDao) {
        this.fleaResourceDao = fleaResourceDao;
    }

    @Override
    public FleaResource queryValidResource(Long resourceId) throws CommonException {
        return this.fleaResourceDao.queryValidResource(resourceId);
    }

    @Override
    public List<FleaResource> queryValidResources(String resourceCode, String resourceName) throws CommonException {
        return this.fleaResourceDao.queryValidResources(resourceCode, resourceName);
    }

    @Override
    public List<FleaResource> queryAllValidResources() throws CommonException {
        return this.fleaResourceDao.queryValidResources(null, null);
    }

    @Override
    public FleaResource saveResource(FleaResourcePOJO fleaResourcePOJO) throws CommonException {
        FleaResource fleaResource = newFleaResource(fleaResourcePOJO);
        // 保存Flea资源数据
        this.save(fleaResource);
        return fleaResource;
    }

    /**
     * 新建Flea资源数据
     *
     * @param fleaResourcePOJO Flea资源POJO对象
     * @return Flea资源数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaResource newFleaResource(FleaResourcePOJO fleaResourcePOJO) throws CommonException {
        // 校验Flea资源POJO对象
        FleaAuthCheck.checkFleaResourcePOJO(fleaResourcePOJO);

        return new FleaResource(fleaResourcePOJO.getResourceCode(), fleaResourcePOJO.getResourceName(),
                fleaResourcePOJO.getResourceDesc(), fleaResourcePOJO.getEffectiveDate(),
                fleaResourcePOJO.getExpiryDate(), fleaResourcePOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaResource> getDAO() {
        return fleaResourceDao;
    }
}