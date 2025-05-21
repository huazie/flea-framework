package com.huazie.fleaframework.auth.base.user.service.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.fleaframework.auth.common.pojo.user.FleaAccountPOJO;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.SecurityUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.fleaframework.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Flea账户信息SV层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaAccountSV")
public class FleaAccountSVImpl extends AbstractFleaJPASVImpl<FleaAccount> implements IFleaAccountSV {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAccountSVImpl.class);

    private IFleaAccountDAO fleaAccountDao;

    @Autowired
    @Qualifier("fleaAccountDAO")
    public void setFleaAccountDao(IFleaAccountDAO fleaAccountDao) {
        this.fleaAccountDao = fleaAccountDao;
    }

    @Override
    public FleaAccount queryValidAccount(Long accountId) throws CommonException {
        return fleaAccountDao.queryValidAccount(accountId);
    }

    @Override
    public FleaAccount queryAccount(String accountCode, String accountPwd) throws CommonException {
        // 用户密码需要加密后，与数据库进行比对
        return fleaAccountDao.queryValidAccount(accountCode, encrypt(accountPwd));
    }

    @Override
    public FleaAccount queryValidAccount(String accountCode) throws CommonException {
        return fleaAccountDao.queryValidAccount(accountCode);
    }

    @Override
    public String encrypt(String originalAccountPwd) {
        String encryptedAccountPwd = originalAccountPwd;
        Object obj = new Object() {};
        LOGGER.debug1(obj, "originalAccountPwd = {}", originalAccountPwd);
        // TODO 暂时使用 SHA
        if (StringUtils.isNotBlank(originalAccountPwd)) {
            encryptedAccountPwd = SecurityUtils.encryptToSHA(originalAccountPwd);
        }
        LOGGER.debug1(obj, "encryptedAccountPwd = {}", encryptedAccountPwd);
        return encryptedAccountPwd;
    }

    @Override
    public FleaAccount saveFleaAccount(FleaAccountPOJO fleaAccountPOJO) throws CommonException {

        FleaAccount fleaAccount = newFleaAccount(fleaAccountPOJO);

        // 系统账号生成时指定账户编号
        Long accountId = fleaAccountPOJO.getAccountId();
        if (null != accountId && accountId > CommonConstants.NumeralConstants.ZERO) {
            fleaAccount.setAccountId(accountId);
        }

        // 保存Flea账户信息
        fleaAccountDao.save(fleaAccount);

        return fleaAccount;
    }

    /**
     * 新建一个Flea账户信息
     *
     * @param fleaAccountPOJO Flea账户POJO类实例
     * @return Flea账户
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaAccount newFleaAccount(FleaAccountPOJO fleaAccountPOJO) throws CommonException {
        // 校验Flea账户POJO对象
        FleaAuthCheck.checkFleaAccountPOJO(fleaAccountPOJO);

        String accountPwd = encrypt(fleaAccountPOJO.getAccountPwd());

        return new FleaAccount(fleaAccountPOJO.getUserId(),
                fleaAccountPOJO.getAccountCode(), accountPwd,
                fleaAccountPOJO.getAccountState(),
                fleaAccountPOJO.getEffectiveDate(),
                fleaAccountPOJO.getExpiryDate(),
                fleaAccountPOJO.getRemarks());
    }

    @Override
    protected IAbstractFleaJPADAO<FleaAccount> getDAO() {
        return fleaAccountDao;
    }
}