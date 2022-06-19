package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.object.FleaObjectFactory;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @param operatorUserModuleData 操作用户模块数据
     * @param systemAccountId        系统账户编号
     * @param systemUserModuleData   系统用户模块数据
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

        // 处理操作用户模块数据
        initOperationUserData(fleaUser, operatorUserModuleData);

        if (ObjectUtils.isNotEmpty(systemAccountId)) {
            fleaUser.setSystemAccountId(systemAccountId);
        }

        // 处理系统用户模块数据
        initSystemUserData(fleaUser, systemUserModuleData);

        // 添加其他属性信息
        fleaUser.addAll(otherAttrs);

        FleaSessionManager.setUserInfo(fleaUser);

        return fleaUser;
    }

    /**
     * 初始化操作用户模块数据
     *
     * @param fleaUser     用户信息接口
     * @param operatorUser 操作用户模块信息
     * @since 2.0.0
     */
    public static void initOperationUserData(IFleaUser fleaUser, FleaUserModuleData operatorUser) {
        FleaUser user = operatorUser.getFleaUser();
        fleaUser.setUserId(user.getUserId());
        // 昵称
        fleaUser.set(FleaAuthConstants.UserModuleConstants.USER_NAME, user.getUserName());
        // 性别
        Integer userSex = user.getUserSex();
        if (ObjectUtils.isNotEmpty(userSex)) {
            fleaUser.set(FleaAuthConstants.UserModuleConstants.USER_SEX, userSex);
        }
        // 生日
        Date userBirthday = user.getUserBirthday();
        if (ObjectUtils.isNotEmpty(userBirthday)) {
            fleaUser.set(FleaAuthConstants.UserModuleConstants.USER_BIRTHDAY, DateUtils.date2String(userBirthday));
        }
        // 住址
        String userAddress = user.getUserAddress();
        if (StringUtils.isNotBlank(userAddress)) {
            fleaUser.set(FleaAuthConstants.UserModuleConstants.USER_ADDRESS, userAddress);
        }
        // 邮箱
        String userEmail = user.getUserEmail();
        if (StringUtils.isNotBlank(userEmail)) {
            fleaUser.set(FleaAuthConstants.UserModuleConstants.USER_EMAIL, userEmail);
        }
        // 手机
        String userPhone = user.getUserPhone();
        if (StringUtils.isNotBlank(userPhone)) {
            fleaUser.set(FleaAuthConstants.UserModuleConstants.USER_PHONE, userPhone);
        }

        // 添加操作用户扩展属性信息
        fleaUser.addAll(operatorUser.toUserAttrMap(FleaAuthConstants.UserModuleConstants.USER_ATTR));

        FleaAccount account = operatorUser.getFleaAccount();
        // 操作账号
        fleaUser.set(FleaAuthConstants.UserModuleConstants.ACCOUNT_CODE, account.getAccountCode());

        // 添加操作账户扩展属性信息
        fleaUser.addAll(operatorUser.toAccountAttrMap(FleaAuthConstants.UserModuleConstants.ACCOUNT_ATTR));
    }

    /**
     * 初始化系统用户模块信息
     *
     * @param fleaUser   用户信息接口
     * @param systemUser 系统用户模块信息
     * @since 2.0.0
     */
    public static void initSystemUserData(IFleaUser fleaUser, FleaUserModuleData systemUser) {
        FleaUser sysUser = systemUser.getFleaUser();
        // 系统用户名
        fleaUser.set(FleaAuthConstants.UserModuleConstants.SYSTEM_USER_NAME, sysUser.getUserName());
        // 添加系统用户扩展属性信息
        fleaUser.addAll(systemUser.toUserAttrMap(FleaAuthConstants.UserModuleConstants.SYSTEM_USER_ATTR));

        FleaAccount sysAccount = systemUser.getFleaAccount();
        // 系统账号
        fleaUser.set(FleaAuthConstants.UserModuleConstants.SYSTEM_ACCOUNT_CODE, sysAccount.getAccountCode());
        // 添加系统账户扩展属性信息
        fleaUser.addAll(systemUser.toAccountAttrMap(FleaAuthConstants.UserModuleConstants.SYSTEM_ACCOUNT_ATTR));
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

    /**
     * 生成属性Map的键
     *
     * @param prefix   前缀
     * @param attrCode 属性码
     * @return 属性Map的键
     * @since 2.0.0
     */
    public static String generateAttrMapKey(String prefix, String attrCode) {
        if (StringUtils.isBlank(prefix))
            return attrCode;
        return prefix + CommonConstants.SymbolConstants.UNDERLINE + attrCode;
    }
}
