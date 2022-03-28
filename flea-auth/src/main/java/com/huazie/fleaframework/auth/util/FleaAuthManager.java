package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.object.FleaObjectFactory;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Flea 授权管理器
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaAuthManager {

    /**
     * 初始化用户信息
     *
     * @param accountId              操作账户编号
     * @param operatorUserModuleData 操作用户模块信息
     * @param systemAccountId        系统账户编号
     * @param systemUserModuleData   系统用户模块信息
     * @param otherAttrs             其他属性信息
     * @param fleaObjectFactory      用户信息对象工厂类
     * @return 用户信息接口
     * @since 2.0.0
     */
    public static IFleaUser initUserInfo(Long accountId, FleaUserModuleData operatorUserModuleData, Long systemAccountId, FleaUserModuleData systemUserModuleData, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) {
        IFleaUser fleaUser = fleaObjectFactory.newObject().getObject();

        if (ObjectUtils.isNotEmpty(accountId)) {
            fleaUser.setAccountId(accountId);
        }

        // 处理操作用户模块信息
        initOperationUserData(fleaUser, operatorUserModuleData);

        if (ObjectUtils.isNotEmpty(systemAccountId)) {
            fleaUser.setSystemAccountId(systemAccountId);
        }

        // 处理系统用户模块信息
        initSystemUserData(fleaUser, systemUserModuleData);

        if (MapUtils.isNotEmpty(otherAttrs)) {
            Set<String> attrKeySet = otherAttrs.keySet();
            for (String key : attrKeySet) {
                Object value = otherAttrs.get(key);
                fleaUser.set(key, value);
            }
        }

        FleaSessionManager.setUserInfo(fleaUser);

        return fleaUser;
    }

    /**
     * 初始化操作用户模块信息
     *
     * @param fleaUser     用户信息接口
     * @param operatorUser 操作用户模块信息
     * @since 2.0.0
     */
    public static void initOperationUserData(IFleaUser fleaUser, FleaUserModuleData operatorUser) {
        FleaUser user = operatorUser.getFleaUser();
        fleaUser.setUserId(user.getUserId());
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

        FleaAccount fleaAccount = operatorUser.getFleaAccount();
        // 操作账号
        fleaUser.set(FleaAuthConstants.UserConstants.ACCOUNT_CODE, fleaAccount.getAccountCode());
    }

    /**
     * 初始化系统用户模块信息
     *
     * @param fleaUser   用户信息接口
     * @param systemUser 系统用户模块信息
     * @since 2.0.0
     */
    public static void initSystemUserData(IFleaUser fleaUser, FleaUserModuleData systemUser) {
        FleaUser user = systemUser.getFleaUser();
        // 系统用户名
        fleaUser.set(FleaAuthConstants.UserConstants.SYSTEM_USER_NAME, user.getUserName());
        FleaAccount fleaAccount = systemUser.getFleaAccount();
        // 系统账号
        fleaUser.set(FleaAuthConstants.UserConstants.SYSTEM_ACCOUNT_CODE, fleaAccount.getAccountCode());
    }

    /**
     * 初始化菜单树信息
     *
     * @param fleaUser     用户信息接口
     * @param fleaMenuList 菜单列表
     * @since 2.0.0
     */
    public static void initFleaMenuTree(IFleaUser fleaUser, List<FleaMenu> fleaMenuList) {
        FleaMenuTree fleaMenuTree = new FleaMenuTree("FleaAuth");
        fleaMenuTree.addAll(fleaMenuList);
        // 操作账号accountId在系统账户systemAccountId下可以访问的所有菜单
        fleaUser.set(FleaMenuTree.MENU_TREE, fleaMenuTree);
    }
}
