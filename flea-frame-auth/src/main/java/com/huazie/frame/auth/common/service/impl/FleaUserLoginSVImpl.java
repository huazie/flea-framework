package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.frame.auth.common.service.interfaces.IFleaUserLoginSV;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.HttpUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaUserLoginSVImpl.class);

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
    public FleaAccount login(FleaUserLoginPOJO fleaUserLoginPOJO) throws CommonException {

        // 校验用户登录信息对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserLoginPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", new String[]{"FleaUserLoginPOJO"});

        // 校验账号是否为空
        // ERROR-AUTH-COMMON0000000002 账号不能为空！
        String accountCode = fleaUserLoginPOJO.getAccountCode();
        StringUtils.checkBlank(accountCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000002");

        // 校验密码是否为空
        // ERROR-AUTH-COMMON0000000003 密码不能为空！
        String accountPwd = fleaUserLoginPOJO.getAccountPwd();
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

            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog(accountId, ip4, ip6, address, "");
                // 保存用户登录信息
                fleaLoginLogSV.save(fleaLoginLog);
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Exception occurs when saving login log : ", e);
                }
            }
        }
    }

    @Override
    public void saveQuitLog(Long accountId) throws CommonException {

        if (ObjectUtils.isNotEmpty(accountId) && accountId > CommonConstants.NumeralConstants.ZERO) {
            // 获取当月用户最近一次的登录日志
            FleaLoginLog fleaLoginLog = fleaLoginLogSV.queryLastUserLoginLog(accountId);
            if (null != fleaLoginLog) {
                fleaLoginLog.setLoginState(FleaLoginLog.LOGIN_STATE_2);
                fleaLoginLog.setLogoutTime(DateUtils.getCurrentTime());
                fleaLoginLog.setDoneDate(fleaLoginLog.getLoginTime());
                fleaLoginLog.setRemarks("用户已退出");
                // 更新当月用户最近一次的登录日志的登录状态（2：已退出）
                fleaLoginLogSV.update(fleaLoginLog);
            }
        }
    }
}
