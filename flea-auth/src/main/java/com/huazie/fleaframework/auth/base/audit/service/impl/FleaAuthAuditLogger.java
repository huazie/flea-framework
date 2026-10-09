package com.huazie.fleaframework.auth.base.audit.service.impl;

import com.huazie.fleaframework.auth.base.audit.entity.FleaAuthAuditLog;
import com.huazie.fleaframework.auth.base.audit.service.interfaces.IFleaAuthAuditSV;
import com.huazie.fleaframework.auth.common.OpTypeEnum;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.HttpUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;

/**
 * Flea 授权操作审计日志记录器
 *
 * <p> 供业务层在操作成功后记录审计日志，内部通过
 * {@link IFleaAuthAuditSV#recordAuthLog(FleaAuthAuditLog)} 异步落库，
 * 不阻塞主流程；记录失败仅打印异常日志，不影响业务结果。 </p>
 *
 * <p> 操作类型 opType 采用字符串传参，框架默认值见 {@link OpTypeEnum}，
 * 业务方可自行拓展，直接传入自定义操作类型字符串即可。 </p>
 *
 * <p> 操作人与客户端 IP 需在当前请求线程内获取（异步落库线程中无请求上下文）：
 * 传入 {@link HttpServletRequest} 时自动提取客户端 IP；无请求上下文场景
 * （如定时任务）可使用不携带 request 的重载，客户端 IP 置空。 </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaAuthAuditLogger")
public class FleaAuthAuditLogger {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAuthAuditLogger.class);

    private IFleaAuthAuditSV fleaAuthAuditSV;

    @Autowired
    @Qualifier("fleaAuthAuditSV")
    public void setFleaAuthAuditSV(IFleaAuthAuditSV fleaAuthAuditSV) {
        this.fleaAuthAuditSV = fleaAuthAuditSV;
    }

    /**
     * 记录新增操作审计日志（默认操作类型 {@link OpTypeEnum#CREATE}）
     *
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @param request  HTTP请求对象（可为空，非空时自动提取客户端IP）
     * @since 2.0.0
     */
    public void recordCreate(String opTarget, String opDesc, HttpServletRequest request) {
        record(OpTypeEnum.CREATE.getType(), opTarget, opDesc, request);
    }

    /**
     * 记录变更操作审计日志（默认操作类型 {@link OpTypeEnum#UPDATE}）
     *
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @param request  HTTP请求对象（可为空，非空时自动提取客户端IP）
     * @since 2.0.0
     */
    public void recordUpdate(String opTarget, String opDesc, HttpServletRequest request) {
        recordUpdate(opTarget, opDesc, null, request);
    }

    /**
     * 记录变更操作审计日志（默认操作类型 {@link OpTypeEnum#UPDATE}），备注记录具体修改内容
     *
     * <p> 备注可传「昵称：张三→李四；账户状态：1→2」形式的变更描述，
     * 可借助 {@link com.huazie.fleaframework.auth.base.audit.util.FleaAuthAuditDiffUtils}
     * 对比变更前实体与变更入参 POJO 生成。 </p>
     *
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @param remarks  备注信息（可为空，建议记录具体修改内容）
     * @param request  HTTP请求对象（可为空，非空时自动提取客户端IP）
     * @since 2.0.0
     */
    public void recordUpdate(String opTarget, String opDesc, String remarks, HttpServletRequest request) {
        record(OpTypeEnum.UPDATE.getType(), null, null, opTarget, opDesc,
                CommonConstants.NumeralConstants.INT_ONE,
                ObjectUtils.isEmpty(request) ? null : HttpUtils.getIp(request), null, remarks);
    }

    /**
     * 记录授权操作审计日志（默认操作类型 {@link OpTypeEnum#GRANT}）
     *
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @param request  HTTP请求对象（可为空，非空时自动提取客户端IP）
     * @since 2.0.0
     */
    public void recordGrant(String opTarget, String opDesc, HttpServletRequest request) {
        record(OpTypeEnum.GRANT.getType(), opTarget, opDesc, request);
    }

    /**
     * 记录撤销授权操作审计日志（默认操作类型 {@link OpTypeEnum#REVOKE}）
     *
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @param request  HTTP请求对象（可为空，非空时自动提取客户端IP）
     * @since 2.0.0
     */
    public void recordRevoke(String opTarget, String opDesc, HttpServletRequest request) {
        record(OpTypeEnum.REVOKE.getType(), opTarget, opDesc, request);
    }

    /**
     * 记录授权操作审计日志
     *
     * <p> 操作人取当前登录会话，操作结果默认为成功；request 非空时自动提取客户端 IP。 </p>
     *
     * @param opType   操作类型（框架默认值见 {@link OpTypeEnum}，可自行拓展）
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @param request  HTTP请求对象（可为空，如定时任务等无请求上下文场景）
     * @since 2.0.0
     */
    public void record(String opType, String opTarget, String opDesc, HttpServletRequest request) {
        record(opType, null, null, opTarget, opDesc,
                CommonConstants.NumeralConstants.INT_ONE,
                ObjectUtils.isEmpty(request) ? null : HttpUtils.getIp(request), null, null);
    }

    /**
     * 记录授权操作审计日志（无请求上下文场景，客户端 IP 为空）
     *
     * <p> 操作人取当前登录会话，操作结果默认为成功。 </p>
     *
     * @param opType   操作类型（框架默认值见 {@link OpTypeEnum}，可自行拓展）
     * @param opTarget 操作对象
     * @param opDesc   操作描述
     * @since 2.0.0
     */
    public void record(String opType, String opTarget, String opDesc) {
        record(opType, null, null, opTarget, opDesc,
                CommonConstants.NumeralConstants.INT_ONE, null, null, null);
    }

    /**
     * 记录授权操作审计日志【完全自定义】
     *
     * <p> userId 和 accountId 为空时取当前登录会话；操作结果 0:失败 1:成功。 </p>
     *
     * @param opType    操作类型（框架默认值见 {@link OpTypeEnum}，可自行拓展）
     * @param userId    用户编号（可为空）
     * @param accountId 账户编号（可为空）
     * @param opTarget  操作对象
     * @param opDesc    操作描述
     * @param opResult  操作结果(0:失败 1:成功)
     * @param ipAddr    客户端IP(支持IPv6，可为空)
     * @param requestId 请求追踪编号（可为空）
     * @param remarks   备注信息（可为空）
     * @since 2.0.0
     */
    public void record(String opType, Long userId, Long accountId, String opTarget, String opDesc,
                       Integer opResult, String ipAddr, String requestId, String remarks) {
        try {
            FleaAuthAuditLog auditLog = new FleaAuthAuditLog(
                    ObjectUtils.isEmpty(userId) ? FleaSessionManager.getUserId() : userId,
                    ObjectUtils.isEmpty(accountId) ? FleaSessionManager.getAccountId() : accountId,
                    opType, opTarget, opDesc, opResult, ipAddr, requestId, remarks);

            // 异步落库，不阻塞主流程
            fleaAuthAuditSV.recordAuthLog(auditLog);
        } catch (Exception e) {
            // 审计日志记录失败不影响主业务
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Record auth audit log occurs exception", e);
            }
        }
    }
}
