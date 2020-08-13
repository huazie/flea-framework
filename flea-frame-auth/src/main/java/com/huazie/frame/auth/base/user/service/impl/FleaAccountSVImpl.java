package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.account.FleaAccountPOJO;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.SecurityUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;
import com.huazie.frame.db.jpa.service.impl.AbstractFleaJPASVImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea账户信息SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAccountSV")
public class FleaAccountSVImpl extends AbstractFleaJPASVImpl<FleaAccount> implements IFleaAccountSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAccountSVImpl.class);

    private IFleaAccountDAO fleaAccountDao;

    @Autowired
    @Qualifier("fleaAccountDAO")
    public void setFleaAccountDao(IFleaAccountDAO fleaAccountDao) {
        this.fleaAccountDao = fleaAccountDao;
    }

    @Override
    public FleaAccount saveFleaAccount(FleaAccountPOJO fleaAccountPOJO) throws CommonException {

        FleaAccount fleaAccount = newFleaAccount(fleaAccountPOJO);

        // 系统账号生成时指定账户编号
        Long accountId = fleaAccountPOJO.getAccountId();
        if (null != accountId && accountId > CommonConstants.NumeralConstants.ZERO) {
            fleaAccount.setAccountId(accountId);
        }

        // 保存Flea账户
        fleaAccountDao.save(fleaAccount);

        return fleaAccount;
    }

    @Override
    public FleaAccount queryAccount(String accountCode, String accountPwd) throws CommonException {
        // 用户密码需要加密后，与数据库进行比对
        return fleaAccountDao.queryAccount(accountCode, encrypt(accountPwd));
    }

    @Override
    public FleaAccount queryValidAccount(String accountCode) throws CommonException {
        return fleaAccountDao.queryValidAccount(accountCode);
    }

    @Override
    public String encrypt(String originalAccountPwd) {
        String encryptedAccountPwd = originalAccountPwd;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAccountSVImpl#encrypt(String) originalAccountPwd = {}", originalAccountPwd);
        }
        // TODO 暂时使用 SHA
        if (StringUtils.isNotBlank(originalAccountPwd)) {
            encryptedAccountPwd = SecurityUtils.encryptToSHA(originalAccountPwd);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAccountSVImpl#encrypt(String) encryptedAccountPwd = {}", encryptedAccountPwd);
        }
        return encryptedAccountPwd;
    }

    /**
     * <p> 新建一个Flea账户 </p>
     *
     * @param fleaAccountPOJO Flea账户POJO类实例
     * @return Flea账户
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private FleaAccount newFleaAccount(FleaAccountPOJO fleaAccountPOJO) throws CommonException {

        // 校验Flea账户POJO类对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaAccountPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAccountPOJO.class.getSimpleName());

        // 校验账号是否为空
        // ERROR-AUTH-COMMON0000000002 账号不能为空！
        String accountCode = fleaAccountPOJO.getAccountCode();
        StringUtils.checkBlank(accountCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000002");

        // 校验用户编号是否为空
        Long userId = fleaAccountPOJO.getUserId();
        ObjectUtils.checkEmpty(userId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaAuthEntityConstants.UserEntityConstants.E_USER_ID);

        // 校验密码是否为空
        // ERROR-AUTH-COMMON0000000003 密码不能为空！
        String accountPwd = fleaAccountPOJO.getAccountPwd();
        StringUtils.checkBlank(accountPwd, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000003");

        return new FleaAccount(userId, accountCode, encrypt(accountPwd),
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