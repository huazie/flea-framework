package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupSV;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleSV;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaLoginLogSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.cache.bean.FleaAuthCache;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaLoginLogPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaUserModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthManager;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.object.FleaObjectFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.HttpUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.POJOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Flea用户管理服务实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaUserModuleSV")
public class FleaUserModuleSVImpl implements IFleaUserModuleSV {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaUserModuleSVImpl.class);

    private FleaAuthCache fleaAuthCache; // Flea 授权缓存

    private IFleaLoginLogSV fleaLoginLogSV; // Flea登录日志服务

    private IFleaAccountSV fleaAccountSV; // Flea账户服务

    private IFleaUserSV fleaUserSV; // Flea用户服务

    private IFleaUserGroupSV fleaUserGroupSV; // Flea用户组服务

    private IFleaAccountAttrSV fleaAccountAttrSV; // Flea账户扩展属性服务

    private IFleaUserAttrSV fleaUserAttrSV; // Flea用户扩展属性服务

    private IFleaUserRelSV fleaUserRelSV; // Flea用户关联服务

    private IFleaUserGroupRelSV fleaUserGroupRelSV; // Flea用户组关联服务

    private IFleaRoleSV fleaRoleSV; // Flea角色服务

    private IFleaRoleGroupSV fleaRoleGroupSV; // Flea角色组服务

    @Resource(type = FleaAuthCache.class)
    public void setFleaAuthCache(FleaAuthCache fleaAuthCache) {
        this.fleaAuthCache = fleaAuthCache;
    }

    @Resource(name = "fleaLoginLogSV")
    public void setFleaLoginLogSV(IFleaLoginLogSV fleaLoginLogSV) {
        this.fleaLoginLogSV = fleaLoginLogSV;
    }

    @Resource(name = "fleaAccountSV")
    public void setFleaAccountSV(IFleaAccountSV fleaAccountSV) {
        this.fleaAccountSV = fleaAccountSV;
    }

    @Resource(name = "fleaUserSV")
    public void setFleaUserSV(IFleaUserSV fleaUserSV) {
        this.fleaUserSV = fleaUserSV;
    }

    @Resource(name = "fleaUserGroupSV")
    public void setFleaUserGroupSV(IFleaUserGroupSV fleaUserGroupSV) {
        this.fleaUserGroupSV = fleaUserGroupSV;
    }

    @Resource(name = "fleaAccountAttrSV")
    public void setFleaAccountAttrSV(IFleaAccountAttrSV fleaAccountAttrSV) {
        this.fleaAccountAttrSV = fleaAccountAttrSV;
    }

    @Resource(name = "fleaUserAttrSV")
    public void setFleaUserAttrSV(IFleaUserAttrSV fleaUserAttrSV) {
        this.fleaUserAttrSV = fleaUserAttrSV;
    }

    @Resource(name = "fleaUserRelSV")
    public void setFleaUserRelSV(IFleaUserRelSV fleaUserRelSV) {
        this.fleaUserRelSV = fleaUserRelSV;
    }

    @Resource(name = "fleaUserGroupRelSV")
    public void setFleaUserGroupRelSV(IFleaUserGroupRelSV fleaUserGroupRelSV) {
        this.fleaUserGroupRelSV = fleaUserGroupRelSV;
    }

    @Resource(name = "fleaRoleSV")
    public void setFleaRoleSV(IFleaRoleSV fleaRoleSV) {
        this.fleaRoleSV = fleaRoleSV;
    }

    @Resource(name = "fleaRoleGroupSV")
    public void setFleaRoleGroupSV(IFleaRoleGroupSV fleaRoleGroupSV) {
        this.fleaRoleGroupSV = fleaRoleGroupSV;
    }

    @Override
    public void initUserInfo(Long accountId, Long systemAccountId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) throws CommonException {
        // 获取操作用户模块数据
        FleaUserModuleData operationUser = this.fleaAuthCache.getFleaUserModuleData(accountId);
        // 校验操作用户模块数据
        FleaAuthCheck.checkFleaUserModuleData(operationUser, StringUtils.valueOf(accountId));

        // 获取系统用户模块数据
        FleaUserModuleData systemUser = this.fleaAuthCache.getFleaUserModuleData(systemAccountId);
        // 校验系统用户模块数据
        FleaAuthCheck.checkFleaUserModuleData(systemUser, StringUtils.valueOf(systemAccountId));

        // 初始化用户信息
        IFleaUser fleaUser = FleaAuthManager.initUserInfo(accountId, operationUser, systemAccountId, systemUser, otherAttrs, fleaObjectFactory);

        // 获取所有可以访问的菜单
        List<FleaMenu> fleaMenuList = this.fleaAuthCache.queryAllAccessibleMenus(accountId, systemAccountId);
        FleaAuthManager.initFleaMenuTree(fleaUser, fleaMenuList);

        // 初始化Flea对象信息
        fleaObjectFactory.initObject(fleaUser);

    }

    @Override
    public FleaAccount login(FleaUserLoginPOJO fleaUserLoginPOJO) throws CommonException {

        // 校验用户登录信息对象是否为空
        FleaAuthCheck.checkEmpty(fleaUserLoginPOJO, FleaUserLoginPOJO.class.getSimpleName());

        // 校验账号是否为空
        String accountCode = fleaUserLoginPOJO.getAccountCode();
        FleaAuthCheck.checkAccountCode(accountCode);

        // 校验密码是否为空
        String accountPwd = fleaUserLoginPOJO.getAccountPwd();
        FleaAuthCheck.checkAccountPwd(accountPwd);

        FleaAccount fleaAccount = this.fleaAccountSV.queryAccount(accountCode, accountPwd);
        // 校验登录账号和密码是否正确
        FleaAuthCheck.checkAccountCodeAndPwdCorrect(fleaAccount);

        return fleaAccount;
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public FleaAccount register(FleaUserRegisterPOJO fleaUserRegisterPOJO) throws CommonException {

        // 校验用户注册信息对象是否为空
        FleaAuthCheck.checkEmpty(fleaUserRegisterPOJO, FleaUserRegisterPOJO.class.getSimpleName());

        // 校验账号是否为空
        String accountCode = fleaUserRegisterPOJO.getAccountCode();
        FleaAuthCheck.checkAccountCode(accountCode);

        // 校验待注册账户是否已存在
        FleaAccount oldFleaAccount = this.fleaAccountSV.queryValidAccount(accountCode);
        FleaAuthCheck.checkExist(oldFleaAccount, accountCode);

        // 校验密码是否为空
        String accountPwd = fleaUserRegisterPOJO.getAccountPwd();
        FleaAuthCheck.checkAccountPwd(accountPwd);

        // 如果没有设置过用户名，则根据账号来设置
        fleaUserRegisterPOJO.setUserNameByAccountCode();

        // 添加区分用户和账户类型的扩展属性，系统（操作）用户 和 系统（操作）账户
        if (fleaUserRegisterPOJO.isSystemRegister()) {
            // 添加系统用户的用户扩展属性
            fleaUserRegisterPOJO.addUserAttrList(CollectionUtils.newArrayList(FleaAuthPOJOUtils.newSystemUserAttr()));
            // 添加系统账户的账户扩展属性
            fleaUserRegisterPOJO.addAccountAttrList(CollectionUtils.newArrayList(FleaAuthPOJOUtils.newSystemAccountAttr()));
        } else {
            // 添加操作用户的用户扩展属性
            fleaUserRegisterPOJO.addUserAttrList(CollectionUtils.newArrayList(FleaAuthPOJOUtils.newOperatorUserAttr()));
            // 添加操作账户的账户扩展属性
            fleaUserRegisterPOJO.addAccountAttrList(CollectionUtils.newArrayList(FleaAuthPOJOUtils.newOperatorAccountAttr()));
        }

        // 新建Flea用户
        FleaUser fleaUser = this.fleaUserSV.saveFleaUser(fleaUserRegisterPOJO.newFleaUserPOJO());

        Long userId = fleaUser.getUserId();
        // 新建Flea账户
        FleaAccount newFleaAccount = this.fleaAccountSV.saveFleaAccount(fleaUserRegisterPOJO.newFleaAccountPOJO(userId));

        // 用户扩展属性批量设置用户编号
        fleaUserRegisterPOJO.setUserId(userId);
        // 添加用户扩展属性
        this.fleaUserAttrSV.saveFleaUserAttrs(fleaUserRegisterPOJO.getUserAttrList());

        // 账户扩展属性批量设置账户编号
        fleaUserRegisterPOJO.setAccountId(newFleaAccount.getAccountId());
        // 添加账户扩展属性
        this.fleaAccountAttrSV.saveFleaAccountAttrs(fleaUserRegisterPOJO.getAccountAttrList());

        return newFleaAccount;
    }

    @Override
    public Long addUserGroup(FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException {
        return this.fleaUserGroupSV.saveUserGroup(fleaUserGroupPOJO).getUserGroupId();
    }

    @Override
    public void modifyFleaUserGroup(Long userGroupId, FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException {
        // 校验用户组编号
        FleaAuthCheck.checkUserGroupId(userGroupId);

        // 校验Flea用户组POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaUserGroupPOJO, FleaUserGroupPOJO.class.getSimpleName());

        // 查询在用的用户组数据
        FleaUserGroup fleaUserGroup = this.fleaUserGroupSV.queryUserGroupInUse(userGroupId);
        // 校验Flea用户组是否存在
        FleaAuthCheck.checkFleaUserGroupExist(fleaUserGroup, StringUtils.valueOf(userGroupId));

        // 将Flea用户组POJO对象中非空的数据，复制到Flea用户组数据中
        POJOUtils.copyNotEmpty(fleaUserGroupPOJO, fleaUserGroup);

        // 更新Flea用户组数据
        this.fleaUserGroupSV.update(fleaUserGroup);
    }

    @Override
    public void userRelRole(Long userId, Long roleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验用户编号
        FleaAuthCheck.checkUserId(userId);

        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 查询有效的用户信息
        FleaUser fleaUser = this.fleaUserSV.queryValidUser(userId);
        // 校验Flea用户是否存在
        FleaAuthCheck.checkFleaUserExist(fleaUser, StringUtils.valueOf(userId));
        // 用户名称
        String userName = fleaUser.getUserName();

        // 查询在用的角色数据
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));
        // 角色名称
        String roleName = fleaRole.getRoleName();

        // 新建用户关联角色POJO对象
        FleaUserRelPOJO userRelRolePOJO = FleaAuthPOJOUtils.newUserRelRolePOJO(userId, userName, roleId, roleName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, userRelRolePOJO);

        // 保存用户关联角色
        this.fleaUserRelSV.saveUserRel(userRelRolePOJO);
    }

    @Override
    public void userRelRoleGroup(Long userId, Long roleGroupId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验用户编号
        FleaAuthCheck.checkUserId(userId);

        // 校验角色组编号
        FleaAuthCheck.checkRoleGroupId(roleGroupId);

        // 查询有效的用户信息
        FleaUser fleaUser = this.fleaUserSV.queryValidUser(userId);
        // 校验Flea用户是否存在
        FleaAuthCheck.checkFleaUserExist(fleaUser, StringUtils.valueOf(userId));
        // 用户名称
        String userName = fleaUser.getUserName();

        // 查询在用的角色组数据
        FleaRoleGroup fleaRoleGroup = this.fleaRoleGroupSV.queryRoleGroupInUse(roleGroupId);
        // 校验Flea角色组是否存在
        FleaAuthCheck.checkFleaRoleGroupExist(fleaRoleGroup, StringUtils.valueOf(roleGroupId));
        // 角色组名称
        String roleGroupName = fleaRoleGroup.getRoleGroupName();

        // 用户关联角色组POJO对象
        FleaUserRelPOJO userRelRoleGroupPOJO = FleaAuthPOJOUtils.newUserRelRoleGroupPOJO(userId, userName, roleGroupId, roleGroupName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, userRelRoleGroupPOJO);

        // 保存用户关联角色
        this.fleaUserRelSV.saveUserRel(userRelRoleGroupPOJO);
    }

    @Override
    public void userGroupRelRole(Long userGroupId, Long roleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验用户组编号
        FleaAuthCheck.checkUserGroupId(userGroupId);

        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 查询在用的用户组数据
        FleaUserGroup fleaUserGroup = this.fleaUserGroupSV.queryUserGroupInUse(userGroupId);
        // 校验Flea用户组是否存在
        FleaAuthCheck.checkFleaUserGroupExist(fleaUserGroup, StringUtils.valueOf(userGroupId));
        // 用户组名称
        String userGroupName = fleaUserGroup.getUserGroupName();

        // 查询在用的角色数据
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));
        // 角色名称
        String roleName = fleaRole.getRoleName();

        // 新建用户组关联角色POJO对象
        FleaUserGroupRelPOJO userGroupRelRolePOJO = FleaAuthPOJOUtils.newUserGroupRelRolePOJO(userGroupId, userGroupName, roleId, roleName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, userGroupRelRolePOJO);

        // 保存用户组关联角色
        this.fleaUserGroupRelSV.saveUserGroupRel(userGroupRelRolePOJO);
    }

    @Override
    public void userGroupRelRoleGroup(Long userGroupId, Long roleGroupId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验用户组编号
        FleaAuthCheck.checkUserGroupId(userGroupId);

        // 校验角色组编号
        FleaAuthCheck.checkRoleGroupId(roleGroupId);

        // 查询在用的用户组数据
        FleaUserGroup fleaUserGroup = this.fleaUserGroupSV.queryUserGroupInUse(userGroupId);
        // 校验Flea用户组是否存在
        FleaAuthCheck.checkFleaUserGroupExist(fleaUserGroup, StringUtils.valueOf(userGroupId));
        // 用户组名称
        String userGroupName = fleaUserGroup.getUserGroupName();

        // 查询在用的角色组数据
        FleaRoleGroup fleaRoleGroup = this.fleaRoleGroupSV.queryRoleGroupInUse(roleGroupId);
        // 校验Flea角色组是否存在
        FleaAuthCheck.checkFleaRoleGroupExist(fleaRoleGroup, StringUtils.valueOf(roleGroupId));
        // 角色组名称
        String roleGroupName = fleaRoleGroup.getRoleGroupName();

        // 新建用户组关联角色POJO对象
        FleaUserGroupRelPOJO userGroupRelRoleGroupPOJO = FleaAuthPOJOUtils.newUserGroupRelRoleGroupPOJO(userGroupId, userGroupName, roleGroupId, roleGroupName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, userGroupRelRoleGroupPOJO);

        // 保存用户组关联角色
        this.fleaUserGroupRelSV.saveUserGroupRel(userGroupRelRoleGroupPOJO);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void userGroupRelUser(Long userGroupId, Long userId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验用户组编号
        FleaAuthCheck.checkUserGroupId(userGroupId);

        // 校验用户编号
        FleaAuthCheck.checkUserId(userId);

        // 查询在用的用户组数据
        FleaUserGroup fleaUserGroup = this.fleaUserGroupSV.queryUserGroupInUse(userGroupId);
        // 校验Flea用户组是否存在
        FleaAuthCheck.checkFleaUserGroupExist(fleaUserGroup, StringUtils.valueOf(userGroupId));
        // 用户组名称
        String userGroupName = fleaUserGroup.getUserGroupName();

        // 查询有效的用户信息
        FleaUser fleaUser = this.fleaUserSV.queryValidUser(userId);
        // 校验Flea用户是否存在
        FleaAuthCheck.checkFleaUserExist(fleaUser, StringUtils.valueOf(userId));
        // 用户名称
        String userName = fleaUser.getUserName();

        // 用户组编号不为正数，说明用户第一次被用户组关联
        if (!NumberUtils.isPositiveNumber(fleaUser.getGroupId())) {
            // 更新Flea用户信息中的用户组编号
            fleaUser.setGroupId(userGroupId);
            fleaUser.setDoneDate(DateUtils.getCurrentTime());
            this.fleaUserSV.update(fleaUser);
        }

        // 新建用户组关联用户POJO对象
        FleaUserGroupRelPOJO userGroupRelUserPOJO = FleaAuthPOJOUtils.newUserGroupRelUserPOJO(userGroupId, userGroupName, userId, userName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, userGroupRelUserPOJO);

        // 保存用户组关联用户
        this.fleaUserGroupRelSV.saveUserGroupRel(userGroupRelUserPOJO);
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
                FleaLoginLogPOJO fleaLoginLogPOJO = new FleaLoginLogPOJO(accountId, ip4, ip6, address);
                this.fleaLoginLogSV.saveLoginLog(fleaLoginLogPOJO);
            } catch (Exception e) {
                LOGGER.error1(new Object() {}, "Exception occurs when saving login log : ", e);
            }
        }
    }

    @Override
    public void saveQuitLog(Long accountId) {
        if (NumberUtils.isPositiveNumber(accountId)) {
            try {
                this.fleaLoginLogSV.saveQuitLog(accountId);
            } catch (CommonException e) {
                LOGGER.error1(new Object() {}, "Exception occurs when saving quit log : ", e);
            }
        }
    }
}
