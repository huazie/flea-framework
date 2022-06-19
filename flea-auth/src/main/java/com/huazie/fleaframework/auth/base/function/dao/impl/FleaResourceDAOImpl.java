package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaResourceDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea资源DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Repository("fleaResourceDAO")
public class FleaResourceDAOImpl extends FleaAuthDAOImpl<FleaResource> implements IFleaResourceDAO {

    @Override
    public FleaResource queryValidResource(Long resourceId) throws CommonException {
        List<FleaResource> fleaResourceList = this.getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_ID, resourceId)
                .getResultList();

        return CollectionUtils.getFirstElement(fleaResourceList, FleaResource.class);
    }

    @Override
    public List<FleaResource> queryValidResources(String resourceCode, String resourceName) throws CommonException {
        return this.getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_CODE, resourceCode)
                .like(FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_NAME, resourceName)
                .getResultList();
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery() throws CommonException {
        Date currentDate = DateUtils.getCurrentTime();

        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate);
    }
}