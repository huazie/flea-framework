package com.huazie.fleaframework.core.base.cfgdata.service.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceLogDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResServiceLog;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceLogSV;
import com.huazie.fleaframework.core.common.FleaCoreCommonException;
import com.huazie.fleaframework.core.common.pojo.FleaJerseyResServiceLogPOJO;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea Jersey资源服务调用日志SV层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("resServiceLogSV")
public class FleaJerseyResServiceLogSVImpl extends AbstractFleaJPASVImpl<FleaJerseyResServiceLog> implements IFleaJerseyResServiceLogSV {

    private IFleaJerseyResServiceLogDAO fleaJerseyResServiceLogDao;

    @Autowired
    @Qualifier("resServiceLogDAO")
    public void setFleaJerseyResServiceLogDao(IFleaJerseyResServiceLogDAO fleaJerseyResServiceLogDao) {
        this.fleaJerseyResServiceLogDao = fleaJerseyResServiceLogDao;
    }

    @Override
    public void saveResServiceLog(FleaJerseyResServiceLogPOJO resServiceLogPOJO) throws CommonException {
        this.save(newResServiceLog(resServiceLogPOJO));
    }

    private FleaJerseyResServiceLog newResServiceLog(FleaJerseyResServiceLogPOJO resServiceLogPOJO) throws CommonException {

        // 校验资源服务调用日志POJO对象不能为空
        // ERROR-CORE-COMMON0000000001 【{0}】不能为空，请检查
        ObjectUtils.checkEmpty(resServiceLogPOJO, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", FleaJerseyResServiceLogPOJO.class.getSimpleName());

        // 校验资源编码不能为空
        String resourceCode = resServiceLogPOJO.getResourceCode();
        StringUtils.checkBlank(resourceCode, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "resourceCode");

        // 校验服务编码不能为空
        String serviceCode = resServiceLogPOJO.getServiceCode();
        StringUtils.checkBlank(serviceCode, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "serviceCode");

        Long accountId = resServiceLogPOJO.getAccountId();
        // 【{0}】必须是正数！
        NumberUtils.checkNonPositiveNumber(accountId, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000002", "accountId");

        Long systemAccountId = resServiceLogPOJO.getSystemAccountId();
        // 【{0}】必须是正数！
        NumberUtils.checkNonPositiveNumber(systemAccountId, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000002", "systemAccountId");

        return new FleaJerseyResServiceLog(resourceCode, serviceCode,
                resServiceLogPOJO.getInput(),
                resServiceLogPOJO.getOutput(),
                resServiceLogPOJO.getResultCode(),
                resServiceLogPOJO.getResultMess(),
                accountId, systemAccountId,
                resServiceLogPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaJerseyResServiceLog> getDAO() {
        return fleaJerseyResServiceLogDao;
    }
}