package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.auth.util.FleaMenuTree;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.object.FleaObjectFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.HttpUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p> Flea用户管理服务实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaUserModuleSV")
public class FleaUserModuleSVImpl implements IFleaUserModuleSV {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaUserModuleSVImpl.class);

    private IFleaAuthSV fleaAuthSV; // Flea授权模块

    private IFleaLoginLogSV fleaLoginLogSV; // Flea登录日志服务

    private IFleaAccountSV fleaAccountSV; // Flea账户信息服务

    private IFleaUserSV fleaUserSV; // Flea用户信息服务

    private IFleaAccountAttrSV fleaAccountAttrSV; // Flea账户扩展属性服务

    private IFleaUserAttrSV fleaUserAttrSV; // Flea用户扩展属性服务

    @Autowired
    @Qualifier("fleaAuthSV")
    public void setFleaAuthSV(IFleaAuthSV fleaAuthSV) {
        this.fleaAuthSV = fleaAuthSV;
    }

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
    @Qualifier("fleaAccountAttrSV")
    public void setFleaAccountAttrSV(IFleaAccountAttrSV fleaAccountAttrSV) {
        this.fleaAccountAttrSV = fleaAccountAttrSV;
    }

    @Autowired
    @Qualifier("fleaUserAttrSV")
    public void setFleaUserAttrSV(IFleaUserAttrSV fleaUserAttrSV) {
        this.fleaUserAttrSV = fleaUserAttrSV;
    }

    @Override
    public void initUserInfo(Long userId, Long accountId, Long systemAccountId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) {

        IFleaUser fleaUser = fleaObjectFactory.newObject().getObject();

        if (ObjectUtils.isNotEmpty(userId)) {
            fleaUser.setUserId(userId);
        }

        if (ObjectUtils.isNotEmpty(accountId)) {
            fleaUser.setAccountId(accountId);
        }

        if (ObjectUtils.isNotEmpty(systemAccountId)) {
            fleaUser.setSystemAccountId(systemAccountId);
        }

        if (MapUtils.isNotEmpty(otherAttrs)) {
            Set<String> attrKeySet = otherAttrs.keySet();
            for (String key : attrKeySet) {
                Object value = otherAttrs.get(key);
                fleaUser.set(key, value);
            }
        }

        // 操作账号accountId在系统账户systemAccountId下可以访问的所有菜单
        fleaUser.set(FleaMenuTree.MENU_TREE, toFleaMenuTree(accountId, systemAccountId));

        // 处理操作账户信息
        handleOperationUserData(fleaUser, accountId);

        // 处理系统账户信息
        handleSystemUserData(fleaUser, systemAccountId);

        FleaSessionManager.setUserInfo(fleaUser);

        // 初始化Flea对象信息
        fleaObjectFactory.initObject();

    }

    /**
     * <p> 获取所有可以访问的菜单，并返回菜单树</p>
     *
     * @param accountId       操作账户编号
     * @param systemAccountId 系统账户编号
     * @return 所有可以访问的菜单树
     * @since 1.0.0
     */
    private FleaMenuTree toFleaMenuTree(Long accountId, Long systemAccountId) {
        FleaMenuTree fleaMenuTree = new FleaMenuTree("FleaAuth");
        try {
            // 获取所有可以访问的菜单
            List<FleaMenu> fleaMenuList = fleaAuthSV.queryAllAccessibleMenus(accountId, systemAccountId);
            fleaMenuTree.addAll(fleaMenuList);
        } catch (CommonException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Getting All Accessible Menus Occurs Exception : \n", e);
            }
        }
        return fleaMenuTree;
    }

    /**
     * <p> 处理操作账户信息 </p>
     *
     * @param fleaUser 用户信息接口
     * @param accountId   操作账户编号
     * @since 1.0.0
     */
    private void handleOperationUserData(IFleaUser fleaUser, Long accountId) {
        try {
            // 获取操作账户信息
            FleaUserModuleData operationUser = fleaAuthSV.getFleaUserModuleData(accountId);
            FleaUser user = operationUser.getFleaUser();
            // 昵称
            fleaUser.set(FleaAuthConstants.UserConstants.USER_NAME, user.getUserName());
            // 性别
            Integer userSex = user.getUserSex();
            if (ObjectUtils.isNotEmpty(userSex)) {
                fleaUser.set(FleaAuthConstants.UserConstants.USER_SEX, userSex);
            }
            // 生日
            Date userBirthday = user.getUserBirthday();
            if (ObjectUtils.isNotEmpty(userBirthday)) {
                fleaUser.set(FleaAuthConstants.UserConstants.USER_BIRTHDAY, DateUtils.date2String(userBirthday));
            }
            // 住址
            String userAddress = user.getUserAddress();
            if (StringUtils.isNotBlank(userAddress)) {
                fleaUser.set(FleaAuthConstants.UserConstants.USER_ADDRESS, userAddress);
            }
            // 邮箱
            String userEmail = user.getUserEmail();
            if (StringUtils.isNotBlank(userEmail)) {
                fleaUser.set(FleaAuthConstants.UserConstants.USER_EMAIL, userEmail);
            }
            // 手机
            String userPhone = user.getUserPhone();
            if (StringUtils.isNotBlank(userPhone)) {
                fleaUser.set(FleaAuthConstants.UserConstants.USER_PHONE, userPhone);
            }

            FleaAccount fleaAccount = operationUser.getFleaAccount();
            // 账号
            fleaUser.set(FleaAuthConstants.UserConstants.ACCOUNT_CODE, fleaAccount.getAccountCode());

        } catch (CommonException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Getting Operation User Occurs Exception : \n", e);
            }
        }
    }

    /**
     * <p> 处理系统账户信息 </p>
     *
     * @param fleaUser     用户信息接口
     * @param systemAccountId 系统账户编号
     * @since 1.0.0
     */
    private void handleSystemUserData(IFleaUser fleaUser, Long systemAccountId) {
        try {
            // 获取操作账户信息
            FleaUserModuleData systemUser = fleaAuthSV.getFleaUserModuleData(systemAccountId);
            FleaUser user = systemUser.getFleaUser();
            fleaUser.set(FleaAuthConstants.UserConstants.SYSTEM_USER_NAME, user.getUserName());
        } catch (CommonException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {}, "Getting System User Occurs Exception : \n", e);
            }
        }
    }

    @Override
    public FleaAccount login(FleaUserLoginPOJO fleaUserLoginPOJO) throws CommonException {

        // 校验用户登录信息对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserLoginPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaUserLoginPOJO.class.getSimpleName());

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
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public FleaAccount register(FleaUserRegisterPOJO fleaUserRegisterPOJO) throws CommonException {

        // 校验用户注册信息对象是否为空
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(fleaUserRegisterPOJO, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", FleaUserRegisterPOJO.class.getSimpleName());

        // 校验账号是否为空
        // ERROR-AUTH-COMMON0000000002 账号不能为空！
        String accountCode = fleaUserRegisterPOJO.getAccountCode();
        StringUtils.checkBlank(accountCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000002");

        // 校验待注册账户是否已存在
        // ERROR-AUTH-COMMON0000000003 【{0}】已存在！
        FleaAccount oldFleaAccount = fleaAccountSV.queryValidAccount(accountCode);
        ObjectUtils.checkNotEmpty(oldFleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000005", accountCode);

        // 校验密码是否为空
        // ERROR-AUTH-COMMON0000000003 密码不能为空！
        String accountPwd = fleaUserRegisterPOJO.getAccountPwd();
        StringUtils.checkBlank(accountPwd, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000003");

        // 新建一个flea用户
        FleaUser fleaUser = fleaUserSV.saveFleaUser(fleaUserRegisterPOJO.newFleaUserPOJO());

        Long userId = fleaUser.getUserId();
        // 新建一个flea账户
        FleaAccount newFleaAccount = fleaAccountSV.saveFleaAccount(fleaUserRegisterPOJO.newFleaAccountPOJO(userId));

        // 用户扩展属性批量设置用户编号
        fleaUserRegisterPOJO.setUserId(userId);
        // 添加用户扩展属性
        fleaUserAttrSV.saveFleaUserAttrs(fleaUserRegisterPOJO.getUserAttrList());

        // 账户扩展属性批量设置账户编号
        fleaUserRegisterPOJO.setAccountId(newFleaAccount.getAccountId());
        // 添加账户扩展属性
        fleaAccountAttrSV.saveFleaAccountAttrs(fleaUserRegisterPOJO.getAccountAttrList());

        return newFleaAccount;
    }

    @Override
    public void saveLoginLog(Long accountId, HttpServletRequest request) {

        if (NumberUtils.isPositiveNumber(accountId)) {
            // 获取用户登录的ip4地址
            String ip4 = HttpUtils.getIp(request);

            // TODO 获取用户登录的ip6地址
            String ip6 = "";

            // 获取用户登录的地市地址
            String address = HttpUtils.getAddressByTaoBao(ip4);

            try {
                FleaLoginLog fleaLoginLog = new FleaLoginLog(accountId, ip4, ip6, address, "");
                fleaLoginLog.setLoginLogId((Long) fleaLoginLogSV.getFleaNextValue(fleaLoginLog));
                // 保存用户登录信息
                fleaLoginLogSV.save(fleaLoginLog);
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error1(new Object() {}, "Exception occurs when saving login log : ", e);
                }
            }
        }
    }

    @Override
    public void saveQuitLog(Long accountId) {

        if (NumberUtils.isPositiveNumber(accountId)) {
            try {
                // 获取当月用户最近一次的登录日志
                FleaLoginLog fleaLoginLog = fleaLoginLogSV.queryLastUserLoginLog(accountId);
                if (null != fleaLoginLog) {
                    fleaLoginLog.setLoginState(FleaAuthConstants.UserConstants.LOGIN_STATE_2);
                    fleaLoginLog.setLogoutTime(DateUtils.getCurrentTime());
                    fleaLoginLog.setDoneDate(DateUtils.getCurrentTime());
                    // AUTH-COMMON0000000001 用户已登出
                    fleaLoginLog.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-COMMON0000000001"));
                    // 更新当月用户最近一次的登录日志的登录状态（2：已退出）
                    fleaLoginLogSV.update(fleaLoginLog);
                }
            } catch (CommonException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error1(new Object() {}, "Exception occurs when saving quit log : ", e);
                }
            }
        }
    }
}
