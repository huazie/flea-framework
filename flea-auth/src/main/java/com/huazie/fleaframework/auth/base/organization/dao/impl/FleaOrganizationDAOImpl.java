package com.huazie.fleaframework.auth.base.organization.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.organization.dao.interfaces.IFleaOrganizationDAO;
import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea组织DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Repository("fleaOrganizationDAO")
public class FleaOrganizationDAOImpl extends FleaAuthDAOImpl<FleaOrganization> implements IFleaOrganizationDAO {

    @Override
    public FleaOrganization queryValidOrganization(Long orgId) throws CommonException {
        List<FleaOrganization> orgList = this.getQuery(null)
                .equal(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_ID, orgId)
                .equal(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
        return CollectionUtils.getFirstElement(orgList, FleaOrganization.class);
    }

    @Override
    public List<FleaOrganization> querySubTree(Long orgId) throws CommonException {
        // 组织子树集合
        List<FleaOrganization> subTreeList = new ArrayList<>();

        // 逐层向下展开子树（BFS）
        List<FleaOrganization> currentLevelList = queryChildren(orgId);
        while (CollectionUtils.isNotEmpty(currentLevelList)) {
            subTreeList.addAll(currentLevelList);

            // 收集当前层级的组织编号，作为下一层级的父组织编号集
            List<Long> parentIds = new ArrayList<>();
            for (FleaOrganization fleaOrganization : currentLevelList) {
                if (ObjectUtils.isEmpty(fleaOrganization)) continue;
                CollectionUtils.distinctAdd(parentIds, fleaOrganization.getOrgId());
            }

            currentLevelList = queryChildren(parentIds);
        }

        return subTreeList;
    }

    /**
     * 查询指定父组织编号下的有效子组织
     *
     * @param parentId 父组织编号
     * @return 有效子组织集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private List<FleaOrganization> queryChildren(Long parentId) throws CommonException {
        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.OrganizationEntityConstants.E_PARENT_ID, parentId)
                .equal(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }

    /**
     * 查询指定父组织编号集下的有效子组织
     *
     * @param parentIds 父组织编号集合
     * @return 有效子组织集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private List<FleaOrganization> queryChildren(List<Long> parentIds) throws CommonException {
        if (CollectionUtils.isEmpty(parentIds)) {
            return new ArrayList<>();
        }
        return this.getQuery(null)
                .in(FleaAuthEntityConstants.OrganizationEntityConstants.E_PARENT_ID, parentIds)
                .equal(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }
}
