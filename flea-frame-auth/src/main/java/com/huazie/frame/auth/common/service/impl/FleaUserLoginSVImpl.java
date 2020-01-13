package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.common.exception.FleaAuthException;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserLoginSV;
import com.huazie.frame.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p> Flea用户登录服务实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserLoginSV")
public class FleaUserLoginSVImpl implements IFleaUserLoginSV {

    private final IFleaAccountSV fleaAccountSV; // Flea账户信息服务

    @Autowired
    public FleaUserLoginSVImpl(@Qualifier("fleaAccountDAO") IFleaAccountSV fleaAccountSV) {
        this.fleaAccountSV = fleaAccountSV;
    }

    @Override
    public FleaAccount login(String accountCode, String accountPwd) throws FleaAuthException {

        FleaAccount fleaAccount = null;

        if (StringUtils.isBlank(accountCode)) {
            // 账号不能为空！
            throw new FleaAuthException("ERROR-AUTH-COMMON0000000001");
        }

        if (StringUtils.isBlank(accountPwd)) {
            // 密码不能为空！
            throw new FleaAuthException("ERROR-AUTH-COMMON0000000002");
        }


        return fleaAccount;
    }

}
