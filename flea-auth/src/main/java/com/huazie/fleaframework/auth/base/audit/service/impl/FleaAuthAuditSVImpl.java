package com.huazie.fleaframework.auth.base.audit.service.impl;

import com.huazie.fleaframework.auth.base.audit.dao.interfaces.IFleaAuthAuditDAO;
import com.huazie.fleaframework.auth.base.audit.entity.FleaAuthAuditLog;
import com.huazie.fleaframework.auth.base.audit.service.interfaces.IFleaAuthAuditSV;
import com.huazie.fleaframework.auth.util.FleaAuthAsyncTask;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Flea授权操作审计日志SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaAuthAuditSV")
public class FleaAuthAuditSVImpl extends AbstractFleaJPASVImpl<FleaAuthAuditLog> implements IFleaAuthAuditSV {

    private IFleaAuthAuditDAO fleaAuthAuditDAO;

    @Autowired
    @Qualifier("fleaAuthAuditDAO")
    public void setFleaAuthAuditDAO(IFleaAuthAuditDAO fleaAuthAuditDAO) {
        this.fleaAuthAuditDAO = fleaAuthAuditDAO;
    }

    @Override
    public void recordAuthLog(FleaAuthAuditLog auditLog) throws CommonException {
        // 校验审计日志数据不能为空
        FleaAuthCheck.checkEmpty(auditLog, FleaAuthAuditLog.class.getSimpleName());

        // 校验操作类型不能为空
        FleaAuthCheck.checkBlank(auditLog.getOpType(), FleaAuthEntityConstants.AuthAuditLogEntityConstants.E_OP_TYPE);

        // 异步落库，不阻塞主流程
        FleaAuthAsyncTask.asyncRecordAuthLog(this, auditLog);
    }

    /**
     * 保存审计日志数据【由异步任务调用，禁止外部直接调用】
     *
     * @param auditLog 审计日志数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public void doRecordAuthLog(FleaAuthAuditLog auditLog) throws CommonException {
        // 保存Flea授权操作审计日志数据
        this.save(auditLog);
    }

    @Override
    public List<FleaAuthAuditLog> getAuditLogList(Long userId, String opType) throws CommonException {
        return fleaAuthAuditDAO.getAuditLogList(userId, opType);
    }

    @Override
    protected IAbstractFleaJPADAO<FleaAuthAuditLog> getDAO() {
        return fleaAuthAuditDAO;
    }
}
