package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaLoginLogDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.auth.common.LoginStateEnum;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaLoginLogPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea登录日志SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaLoginLogSV")
public class FleaLoginLogSVImpl extends AbstractFleaJPASVImpl<FleaLoginLog> implements IFleaLoginLogSV {

    private IFleaLoginLogDAO fleaLoginLogDao;

    @Autowired
    @Qualifier("fleaLoginLogDAO")
    public void setFleaLoginLogDao(IFleaLoginLogDAO fleaLoginLogDao) {
        this.fleaLoginLogDao = fleaLoginLogDao;
    }

    @Override
    public FleaLoginLog queryLastUserLoginLog(Long accountId) throws CommonException {
        return fleaLoginLogDao.queryLastUserLoginLog(accountId);
    }

    @Override
    public void saveLoginLog(FleaLoginLogPOJO fleaLoginLogPOJO) throws CommonException {
        FleaLoginLog fleaLoginLog = newFleaLoginLog(fleaLoginLogPOJO);
        // 分表场景，主动获取登录日志编号【主键】
        fleaLoginLog.setLoginLogId((Long) this.getFleaNextValue(fleaLoginLog));
        // 保存Flea登录日志数据
        this.save(fleaLoginLog);
    }

    /**
     * 新建Flea登录日志数据
     *
     * @param fleaLoginLogPOJO Flea登录日志POJO对象
     * @return Flea登录日志数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaLoginLog newFleaLoginLog(FleaLoginLogPOJO fleaLoginLogPOJO) throws CommonException {
        // 校验Flea登录日志POJO对象
        FleaAuthCheck.checkFleaLoginLogPOJO(fleaLoginLogPOJO);

        return new FleaLoginLog(fleaLoginLogPOJO.getAccountId(),
                fleaLoginLogPOJO.getLoginIp4(),
                fleaLoginLogPOJO.getLoginIp6(),
                fleaLoginLogPOJO.getLoginArea(),
                fleaLoginLogPOJO.getRemarks());
    }

    @Override
    public void saveQuitLog(Long accountId) throws CommonException {
        // 获取当月用户最近一次的登录日志
        FleaLoginLog fleaLoginLog = this.queryLastUserLoginLog(accountId);
        if (null != fleaLoginLog) {
            // 更新当月用户最近一次的登录日志的登录状态（2：已退出）
            fleaLoginLog.setLoginState(LoginStateEnum.QUITTED.getState());
            fleaLoginLog.setLogoutTime(DateUtils.getCurrentTime());
            fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
            // AUTH-COMMON0000000001 用户已登出
            fleaLoginLog.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-COMMON0000000001"));
            this.update(fleaLoginLog);
        }
    }

    @Override
    protected IAbstractFleaJPADAO<FleaLoginLog> getDAO() {
        return fleaLoginLogDao;
    }
}