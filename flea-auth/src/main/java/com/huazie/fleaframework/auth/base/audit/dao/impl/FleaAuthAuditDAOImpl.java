package com.huazie.fleaframework.auth.base.audit.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.audit.dao.interfaces.IFleaAuthAuditDAO;
import com.huazie.fleaframework.auth.base.audit.entity.FleaAuthAuditLog;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea授权操作审计日志DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Repository("fleaAuthAuditDAO")
public class FleaAuthAuditDAOImpl extends FleaAuthDAOImpl<FleaAuthAuditLog> implements IFleaAuthAuditDAO {

    @Override
    public List<FleaAuthAuditLog> getAuditLogList(Long userId, String opType) throws CommonException {
        FleaJPAQuery query = this.getQuery(null);
        if (ObjectUtils.isNotEmpty(userId)) {
            query.equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId);
        }
        if (StringUtils.isNotBlank(opType)) {
            query.equal(FleaAuthEntityConstants.AuthAuditLogEntityConstants.E_OP_TYPE, opType);
        }
        return query.getResultList();
    }
}
