package com.huazie.fleaframework.auth.base.audit;

import com.huazie.fleaframework.auth.base.audit.entity.FleaAuthAuditLog;
import com.huazie.fleaframework.auth.base.audit.service.interfaces.IFleaAuthAuditSV;
import com.huazie.fleaframework.auth.common.OpTypeEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Flea授权操作审计日志SV层实现类测试
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaAuthAuditSVImplTest {

    @Resource(name = "fleaAuthAuditSV")
    private IFleaAuthAuditSV fleaAuthAuditSV;

    @Test
    public void recordAuthLog() throws CommonException {
        // 新建审计日志数据（op_result: 1 表示成功）
        FleaAuthAuditLog auditLog = new FleaAuthAuditLog(1000L, 1000L, OpTypeEnum.LOGIN.getType(), "account_1000", "用户登录成功", 1, "2408:8207:184b:2a40::1", "req-test-0001", "审计日志测试数据");
        // 异步落库
        fleaAuthAuditSV.recordAuthLog(auditLog);
    }

    @Test
    public void getAuditLogList() throws CommonException {
        fleaAuthAuditSV.getAuditLogList(1000L, OpTypeEnum.LOGIN.getType());
    }
}
