package com.huazie.frame.auth.base.user.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.common.exception.CommonException;
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
 * <p> Flea帐户信息SV层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAccountSV")
public class FleaAccountSVImpl extends AbstractFleaJPASVImpl<FleaAccount> implements IFleaAccountSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAccountSVImpl.class);

    private final IFleaAccountDAO fleaAccountDao;

    @Autowired
    public FleaAccountSVImpl(@Qualifier("fleaAccountDAO") IFleaAccountDAO fleaAccountDao) {
        this.fleaAccountDao = fleaAccountDao;
    }

    @Override
    public FleaAccount saveFleaAccount(Long userId, String accountCode, String accountPwd, Integer accountState, String remarks) throws CommonException {

        FleaAccount fleaAccount = new FleaAccount(userId, accountCode, encrypt(accountPwd), accountState, remarks);

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

    @Override
    protected IAbstractFleaJPADAO<FleaAccount> getDAO() {
        return fleaAccountDao;
    }
}