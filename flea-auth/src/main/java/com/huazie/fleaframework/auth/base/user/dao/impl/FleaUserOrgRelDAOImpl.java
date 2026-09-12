package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserOrgRelDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserOrgRel;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea用户组织关联DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Repository("fleaUserOrgRelDAO")
public class FleaUserOrgRelDAOImpl extends FleaAuthDAOImpl<FleaUserOrgRel> implements IFleaUserOrgRelDAO {

    @Override
    public List<FleaUserOrgRel> getUserOrgRelList(Long userId, Long orgId) throws CommonException {
        FleaJPAQuery query = this.getQuery(null);
        if (ObjectUtils.isNotEmpty(userId)) {
            query.equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId);
        }
        if (ObjectUtils.isNotEmpty(orgId)) {
            query.equal(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_ID, orgId);
        }
        return query.equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }
}
