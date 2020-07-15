package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.object.FleaObjectFactory;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.HttpUtils;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * <p> Flea 授权服务实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaAuthSV")
public class FleaAuthSVImpl implements IFleaAuthSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAuthSVImpl.class);

    private IFleaLoginLogSV fleaLoginLogSV; // Flea登录日志服务

    @Autowired
    @Qualifier("fleaLoginLogSV")
    public void setFleaLoginLogSV(IFleaLoginLogSV fleaLoginLogSV) {
        this.fleaLoginLogSV = fleaLoginLogSV;
    }

    @Override
    public void initUserInfo(Long userId, Long acctId, Long systemAcctId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) {

        IFleaUser fleaUser = fleaObjectFactory.newObject().getObject();

        if (ObjectUtils.isNotEmpty(userId)) {
            fleaUser.setUserId(userId);
        }

        if (ObjectUtils.isNotEmpty(acctId)) {
            fleaUser.setAcctId(acctId);
        }

        if (ObjectUtils.isNotEmpty(systemAcctId)) {
            fleaUser.setSystemAcctId(systemAcctId);
        }

        if (MapUtils.isNotEmpty(otherAttrs)) {
            Set<String> attrKeySet = otherAttrs.keySet();
            for (String key : attrKeySet) {
                Object value = otherAttrs.get(key);
                fleaUser.set(key, value);
            }
        }

        FleaSessionManager.setUserInfo(fleaUser);

        // 初始化Flea对象信息
        fleaObjectFactory.initObject();

    }

    @Override
    public void saveLoginLog(Long accountId, HttpServletRequest request) {

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
    public void saveQuitLog(Long accountId) {

        if (ObjectUtils.isNotEmpty(accountId) && accountId > CommonConstants.NumeralConstants.ZERO) {
            try {
                // 获取当月用户最近一次的登录日志
                FleaLoginLog fleaLoginLog = fleaLoginLogSV.queryLastUserLoginLog(accountId);
                if (null != fleaLoginLog) {
                    fleaLoginLog.setLoginState(FleaAuthConstants.UserConstants.LOGIN_STATE_2);
                    fleaLoginLog.setLogoutTime(DateUtils.getCurrentTime());
                    fleaLoginLog.setDoneDate(fleaLoginLog.getLoginTime());
                    fleaLoginLog.setRemarks("用户已退出");
                    // 更新当月用户最近一次的登录日志的登录状态（2：已退出）
                    fleaLoginLogSV.update(fleaLoginLog);
                }
            } catch (CommonException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Exception occurs when saving quit log : ", e);
                }
            }
        }
    }

}
