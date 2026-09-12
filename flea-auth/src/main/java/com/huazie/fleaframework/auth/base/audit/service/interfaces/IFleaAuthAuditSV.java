package com.huazie.fleaframework.auth.base.audit.service.interfaces;

import com.huazie.fleaframework.auth.base.audit.entity.FleaAuthAuditLog;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea授权操作审计日志SV层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaAuthAuditSV extends IAbstractFleaJPASV<FleaAuthAuditLog> {

    /**
     * 记录授权操作审计日志（异步落库，不阻塞主流程）
     *
     * @param auditLog 审计日志数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void recordAuthLog(FleaAuthAuditLog auditLog) throws CommonException;

    /**
     * 根据用户编号和操作类型，查询审计日志数据
     *
     * <p> userId 和 opType 均可为空，为空表示不限制该条件 </p>
     *
     * @param userId 用户编号
     * @param opType 操作类型
     * @return 审计日志数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaAuthAuditLog> getAuditLogList(Long userId, String opType) throws CommonException;
}
