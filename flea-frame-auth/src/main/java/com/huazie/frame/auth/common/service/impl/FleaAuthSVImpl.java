package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.base.function.entity.FleaMenu;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.frame.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.frame.auth.common.AuthRelTypeEnum;
import com.huazie.frame.auth.common.FleaAuthConstants;
import com.huazie.frame.auth.common.exception.FleaAuthCommonException;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    private IFleaAccountSV fleaAccountSV; // Flea账户信息服务

    private IFleaUserSV fleaUserSV; // Flea用户信息服务

    private IFleaUserGroupRelSV fleaUserGroupRelSV; // Flea用户组关联服务

    @Autowired
    @Qualifier("fleaLoginLogSV")
    public void setFleaLoginLogSV(IFleaLoginLogSV fleaLoginLogSV) {
        this.fleaLoginLogSV = fleaLoginLogSV;
    }

    @Autowired
    @Qualifier("fleaAccountSV")
    public void setFleaAccountSV(IFleaAccountSV fleaAccountSV) {
        this.fleaAccountSV = fleaAccountSV;
    }

    @Autowired
    @Qualifier("fleaUserSV")
    public void setFleaUserSV(IFleaUserSV fleaUserSV) {
        this.fleaUserSV = fleaUserSV;
    }

    @Autowired
    @Qualifier("fleaUserGroupRelSV")
    public void setFleaUserGroupRelSV(IFleaUserGroupRelSV fleaUserGroupRelSV) {
        this.fleaUserGroupRelSV = fleaUserGroupRelSV;
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

    @Override
    @Cacheable(value = "fleaauthmenu", key = "#accountId + '_' + #systemAcctId")
    public List<FleaMenu> getAllAccessibleMenus(Long accountId, Long systemAcctId) throws CommonException {

        // 校验操作账户编号
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(accountId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", "accountId");

        // 校验系统账户编号
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(systemAcctId, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", "systemAcctId");

        // 根据操作帐户编号accountId查询帐户信息
        FleaAccount fleaAccount = fleaAccountSV.query(accountId);
        // 账户【account_id = {0}】不存在！
        ObjectUtils.checkEmpty(fleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000006", accountId);

        Long userId = fleaAccount.getUserId();
        // 根据操作用户编号userId查询用户信息
        FleaUser fleaUser = fleaUserSV.query(userId);
        // 用户【user_id = {0}】不存在！
        ObjectUtils.checkEmpty(fleaUser, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000007", userId);

        Long groupId = fleaUser.getGroupId(); // 获取用户组编号
        if (CommonConstants.NumeralConstants.MINUS_ONE != groupId) { // 用户关联了用户组
            // 获取用户组关联的角色组
            List<FleaUserGroupRel> userGroupRelRoleGroups = fleaUserGroupRelSV.getUserGroupRelList(groupId, AuthRelTypeEnum.USER_GROUP_REL_ROLE_GROUP.getRelType());

            // 获取用户组关联的角色
            List<FleaUserGroupRel> userGroupRelRoles = fleaUserGroupRelSV.getUserGroupRelList(groupId, AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType());

        }

        // 获取用户关联的角色组


        // 获取用户关联的角色


        return null;
    }
}
