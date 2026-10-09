package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaAccountPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.object.FleaObjectFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Flea用户管理服务接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaUserModuleSV {

    /**
     * 初始化用户信息
     *
     * @param accountId       账户编号
     * @param systemAccountId 系统账户编号
     * @param otherAttrs      其他属性
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void initUserInfo(Long accountId, Long systemAccountId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) throws CommonException;

    /**
     * 用户登录验证
     *
     * @param fleaUserLoginPOJO Flea用户登录信息
     * @return 账户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount login(FleaUserLoginPOJO fleaUserLoginPOJO) throws CommonException;

    /**
     * 用户注册
     *
     * @param fleaUserRegisterPOJO 用户注册信息
     * @return 账户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount register(FleaUserRegisterPOJO fleaUserRegisterPOJO) throws CommonException;

    /**
     * 添加Flea用户组数据
     *
     * @param fleaUserGroupPOJO Flea用户组POJO对象
     * @return 用户组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addUserGroup(FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException;

    /**
     * 修改Flea用户组数据
     *
     * @param userGroupId       用户组编号
     * @param fleaUserGroupPOJO Flea用户组POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaUserGroup(Long userGroupId, FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException;

    /**
     * 用户关联角色【REL_TYPE = USER_REL_ROLE】
     *
     * @param userId             用户编号
     * @param roleId             角色编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void userRelRole(Long userId, Long roleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 用户关联角色组【REL_TYPE = USER_REL_ROLE_GROUP】
     *
     * @param userId             用户编号
     * @param roleGroupId        角色组编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void userRelRoleGroup(Long userId, Long roleGroupId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 用户组关联角色【REL_TYPE = USER_GROUP_REL_ROLE】
     *
     * @param userGroupId        用户组编号
     * @param roleId             角色编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void userGroupRelRole(Long userGroupId, Long roleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 用户组关联角色组【REL_TYPE = USER_GROUP_REL_ROLE_GROUP】
     *
     * @param userGroupId        用户组编号
     * @param roleGroupId        角色组编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void userGroupRelRoleGroup(Long userGroupId, Long roleGroupId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 用户组关联用户【REL_TYPE = USER_GROUP_REL_USER】
     *
     * @param userGroupId        用户组编号
     * @param userId             用户编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void userGroupRelUser(Long userGroupId, Long userId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 保存用户登录日志
     *
     * @param accountId 账户编号
     * @param request   HTTP请求对象
     * @since 1.0.0
     */
    void saveLoginLog(Long accountId, HttpServletRequest request);

    /**
     * 保存用户退出日志
     *
     * @param accountId 账户编号
     * @since 1.0.0
     */
    void saveQuitLog(Long accountId);

    /**
     * 查询有效的账户数据
     *
     * @param accountId 账户编号
     * @return 有效的账户数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaAccount queryValidAccount(Long accountId) throws CommonException;

    /**
     * 查询所有的账户数据
     *
     * @param orderField 排序字段（可为空，为空时不排序）
     * @param orderType  排序类型（asc/desc，可为空）
     * @return 账户数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaAccount> queryAllAccounts(String orderField, String orderType) throws CommonException;

    /**
     * 查询有效的用户数据
     *
     * @param userId 用户编号
     * @return 有效的用户数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUser queryValidUser(Long userId) throws CommonException;

    /**
     * 查询所有的用户数据
     *
     * @param orderField 排序字段（可为空，为空时不排序）
     * @param orderType  排序类型（asc/desc，可为空）
     * @return 用户数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaUser> queryAllUsers(String orderField, String orderType) throws CommonException;

    /**
     * 查询所有的用户组数据
     *
     * @param orderField 排序字段（可为空，为空时不排序）
     * @param orderType  排序类型（asc/desc，可为空）
     * @return 用户组数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaUserGroup> queryAllUserGroups(String orderField, String orderType) throws CommonException;

    /**
     * 查询在用的用户组数据
     *
     * @param userGroupId 用户组编号
     * @return 在用的用户组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUserGroup queryUserGroupInUse(Long userGroupId) throws CommonException;

    /**
     * 变更账户数据
     *
     * <p> 将 {@link FleaAccountPOJO} 中非空的数据合并到在用的账户数据中；
     * 密码不为空时，内部统一加密后落库。 </p>
     *
     * @param accountId        账户编号
     * @param fleaAccountPOJO  账户数据POJO对象（仅携带需变更的字段）
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaAccount(Long accountId, FleaAccountPOJO fleaAccountPOJO) throws CommonException;

    /**
     * 变更用户数据
     *
     * <p> 将 {@link FleaUserPOJO} 中非空的数据合并到有效的用户数据中。 </p>
     *
     * @param userId        用户编号
     * @param fleaUserPOJO  用户数据POJO对象（仅携带需变更的字段）
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyFleaUser(Long userId, FleaUserPOJO fleaUserPOJO) throws CommonException;

    /**
     * 查询用户授权关联数据
     *
     * @param userId   用户编号
     * @param authRelType 授权关联类型（可为空，为空表示不限制）
     * @return 用户授权关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaUserRel> getUserRelList(Long userId, String authRelType) throws CommonException;

    /**
     * 撤销用户授权关联
     *
     * @param userId      用户编号
     * @param relId       关联对象编号
     * @param authRelType 授权关联类型
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void removeUserRel(Long userId, Long relId, String authRelType) throws CommonException;

    /**
     * 查询用户组授权关联数据
     *
     * @param userGroupId 用户组编号
     * @param authRelType 授权关联类型（可为空，为空表示不限制）
     * @return 用户组授权关联数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, String authRelType) throws CommonException;

    /**
     * 撤销用户组授权关联
     *
     * @param userGroupId 用户组编号
     * @param relId       关联对象编号
     * @param authRelType 授权关联类型
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void removeUserGroupRel(Long userGroupId, Long relId, String authRelType) throws CommonException;
}
