package com.huazie.fleaframework.auth.base.audit.dao.interfaces;

import com.huazie.fleaframework.auth.base.audit.entity.FleaAuthAuditLog;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea授权操作审计日志DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaAuthAuditDAO extends IAbstractFleaJPADAO<FleaAuthAuditLog> {

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
