package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginInfo;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserLoginSV;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.HttpUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Flea用户登录服务实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserLoginSV")
public class FleaUserLoginSVImpl implements IFleaUserLoginSV {

    private IFleaAccountSV fleaAccountSV; // Flea账户信息服务

    private IFleaLoginLogSV fleaLoginLogSV; // Flea登录日志服务

    @Autowired
    @Qualifier("fleaAccountSV")
    public void setFleaAccountSV(IFleaAccountSV fleaAccountSV) {
        this.fleaAccountSV = fleaAccountSV;
    }

    @Autowired
    @Qualifier("fleaLoginLogSV")
    public void setFleaLoginLogSV(IFleaLoginLogSV fleaLoginLogSV) {
        this.fleaLoginLogSV = fleaLoginLogSV;
    }

    @Override
    public FleaAccount login(FleaUserLoginInfo fleaUserLoginInfo) throws CommonException {

        // 校验用户登录信息对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserLoginInfo, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"FleaUserLoginInfo"});

        // 校验账号是否为空
        // ERROR-AUTH-COMMON0000000002 账号不能为空！
        String accountCode = fleaUserLoginInfo.getAccountCode();
        StringUtils.checkBlank(accountCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000002");

        // 校验密码是否为空
        // ERROR-AUTH-COMMON0000000003 密码不能为空！
        String accountPwd = fleaUserLoginInfo.getAccountPwd();
        StringUtils.checkBlank(accountPwd, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000003");

        FleaAccount fleaAccount = fleaAccountSV.queryAccount(accountCode, accountPwd);
        // 校验登录账号和密码是否正确
        // ERROR-AUTH-COMMON0000000004 账号或者密码错误！
        ObjectUtils.checkEmpty(fleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000004");

        return fleaAccount;
    }

    @Override
    public void saveLoginLog(Long accountId, HttpServletRequest request) throws CommonException {

        if (ObjectUtils.isNotEmpty(accountId) && accountId > CommonConstants.NumeralConstants.ZERO) {
            // 获取用户登录的ip4地址
            String ip4 = HttpUtils.getIp(request);

            // TODO 获取用户登录的ip6地址
            String ip6 = "";

            // 获取用户登录的地市地址
            String address = HttpUtils.getAddressByTaoBao(ip4);

            FleaLoginLog fleaLoginLog = new FleaLoginLog();
            fleaLoginLog.setAccountId(accountId);
            fleaLoginLog.setSystemAccountId(FleaSessionManager.getSystemAcctId());
            fleaLoginLog.setLoginIp4(ip4);
            fleaLoginLog.setLoginIp6(ip6);
            fleaLoginLog.setLoginArea(address);
            fleaLoginLog.setLoginState(1);
            fleaLoginLog.setLoginTime(DateUtils.getCurrentTime());
            fleaLoginLog.setCreateDate(DateUtils.getCurrentTime());
            // 保存用户登录信息
            fleaLoginLogSV.save(fleaLoginLog);
        }

    }

    @Override
    public void saveQuitLog(Long accountId) throws CommonException {

    }
}
