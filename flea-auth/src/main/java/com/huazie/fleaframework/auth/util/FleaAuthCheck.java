package com.huazie.fleaframework.auth.util;

import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserAttr;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exception.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.FleaAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.attr.FleaFunctionAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.element.FleaElementPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.menu.FleaMenuPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaAccountPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaRealNameInfoPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaLoginLogPOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * 权限校验工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaAuthCheck {

    private FleaAuthCheck() {
    }

    /**
     * 校验 obj 是否为空，如果为空，则抛出相应的异常。
     *
     * @param obj     待判断对象实例
     * @param objName 对象名称
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkEmpty(Object obj, String objName) throws CommonException {
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        ObjectUtils.checkEmpty(obj, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", objName);
    }

    /**
     * 检验 str 是否为空，如果为空，则抛出相应的异常。
     *
     * @param str     待判断字符串对象实例
     * @param strName 字符串对象名称
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkBlank(String str, String strName) throws CommonException {
        // ERROR-AUTH-COMMON0000000001 【{0}】不能为空
        StringUtils.checkBlank(str, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000001", strName);
    }

    /**
     * 校验账号不能为空，如果为空，则抛出相应的异常。
     *
     * @param accountCode 账号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkAccountCode(String accountCode) throws CommonException {
        // ERROR-AUTH-COMMON0000000002 账号不能为空！
        StringUtils.checkBlank(accountCode, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000002");
    }

    /**
     * 校验账户密码不能为空，如果为空，则抛出相应的异常。
     *
     * @param accountPwd 密码
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkAccountPwd(String accountPwd) throws CommonException {
        // ERROR-AUTH-COMMON0000000003 密码不能为空！
        StringUtils.checkBlank(accountPwd, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000003");
    }

    /**
     * 校验登录账号和密码是否正确，如果 fleaAccount 不存在，则抛出相应的异常。
     *
     * @param fleaAccount Flea账户
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkAccountCodeAndPwdCorrect(FleaAccount fleaAccount) throws CommonException {
        // ERROR-AUTH-COMMON0000000004 账号或者密码错误！
        ObjectUtils.checkEmpty(fleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000004");
    }

    /**
     * 校验 obj 是否存在，如果存在，则抛出相应的异常
     *
     * @param obj     待校验对象
     * @param objName 对象名称
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkExist(Object obj, String objName) throws CommonException {
        // ERROR-AUTH-COMMON0000000005 【{0}】已存在！
        ObjectUtils.checkNotEmpty(obj, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000005", objName);
    }

    /**
     * 校验 number 是否为正整数，如果不是，则抛出相应的异常。
     *
     * @param number  待校验的数字类型对象
     * @param objName 对象名称
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkNonPositiveNumber(Number number, String objName) throws CommonException {
        // ERROR-AUTH-COMMON0000000006 【{0}】必须是正数！
        NumberUtils.checkNonPositiveNumber(number, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000006", objName);
    }

    /**
     * 校验Flea用户是否存在，如果不存在，则抛出相应的异常。
     *
     * @param fleaUser Flea用户
     * @param userId   用户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserExist(FleaUser fleaUser, String userId) throws CommonException {
        // ERROR-AUTH-COMMON0000000007 用户【user_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaUser, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000007", userId);
    }

    /**
     * 校验Flea账户是否存在，如果不存在，则抛出相应的异常。
     *
     * @param fleaAccount Flea账户
     * @param accountId   账户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaAccountExist(FleaAccount fleaAccount, String accountId) throws CommonException {
        // ERROR-AUTH-COMMON0000000008 账户【account_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaAccount, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000008", accountId);
    }

    /**
     * 检验Flea用户组是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaUserGroup Flea用户组
     * @param userGroupId   用户组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserGroupExist(FleaUserGroup fleaUserGroup, String userGroupId) throws CommonException {
        // ERROR-AUTH-COMMON0000000009 用户组【user_group_id = {0}】不存在或已删除！
        ObjectUtils.checkEmpty(fleaUserGroup, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000009", userGroupId);
    }

    /**
     * 检验Flea角色是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaRole Flea角色
     * @param roleId   角色编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRoleExist(FleaRole fleaRole, String roleId) throws CommonException {
        // ERROR-AUTH-COMMON0000000010 角色【role_id = {0}】不存在或已删除！
        ObjectUtils.checkEmpty(fleaRole, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000010", roleId);
    }

    /**
     * 检验Flea角色组是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaRoleGroup Flea角色组
     * @param roleGroupId   角色组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRoleGroupExist(FleaRoleGroup fleaRoleGroup, String roleGroupId) throws CommonException {
        // ERROR-AUTH-COMMON0000000011 角色组【role_group_id = {0}】不存在或已删除！
        ObjectUtils.checkEmpty(fleaRoleGroup, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000011", roleGroupId);
    }

    /**
     * 检验Flea权限是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaPrivilege Flea权限
     * @param privilegeId   权限编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaPrivilegeExist(FleaPrivilege fleaPrivilege, String privilegeId) throws CommonException {
        // ERROR-AUTH-COMMON0000000012 权限【privilege_id = {0}】不存在或已删除！
        ObjectUtils.checkEmpty(fleaPrivilege, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000012", privilegeId);
    }

    /**
     * 检验Flea权限组是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaPrivilegeGroup Flea权限组
     * @param privilegeGroupId   权限组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaPrivilegeGroupExist(FleaPrivilegeGroup fleaPrivilegeGroup, String privilegeGroupId) throws CommonException {
        // ERROR-AUTH-COMMON0000000013 权限组【privilege_group_id = {0}】不存在或已删除！
        ObjectUtils.checkEmpty(fleaPrivilegeGroup, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000013", privilegeGroupId);
    }

    /**
     * 检验Flea菜单是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaMenu Flea菜单
     * @param menuId   菜单编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaMenuExist(FleaMenu fleaMenu, Long menuId) throws CommonException {
        // ERROR-AUTH-COMMON0000000014 菜单【menu_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaMenu, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000014", menuId);
    }

    /**
     * 检验Flea操作是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaOperation Flea操作
     * @param operationId   操作编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaOperationExist(FleaOperation fleaOperation, Long operationId) throws CommonException {
        // ERROR-AUTH-COMMON0000000015 操作【operation_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaOperation, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000015", operationId);
    }

    /**
     * 检验Flea元素是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaElement Flea元素
     * @param elementId   元素编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaElementExist(FleaElement fleaElement, Long elementId) throws CommonException {
        // ERROR-AUTH-COMMON0000000016 元素【element_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaElement, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000016", elementId);
    }

    /**
     * 检验Flea资源是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaResource Flea资源
     * @param resourceId   资源编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaResourceExist(FleaResource fleaResource, Long resourceId) throws CommonException {
        // ERROR-AUTH-COMMON0000000017 资源【resource_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaResource, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000017", resourceId);
    }

    /**
     * 校验Flea用户属性是否存在，如果不存在，抛出相应的异常
     *
     * @param fleaUserAttr Flea用户属性
     * @param attrId       属性编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserAttrExist(FleaUserAttr fleaUserAttr, Long attrId) throws CommonException {
        // ERROR-AUTH-COMMON0000000018 用户属性【attr_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaUserAttr, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000018", attrId);
    }

    /**
     * 校验Flea账户属性是否存在，如果不存在，抛出相应的异常
     *
     * @param fleaAccountAttr Flea账户属性
     * @param attrId          属性编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaAccountAttrExist(FleaAccountAttr fleaAccountAttr, Long attrId) throws CommonException {
        // ERROR-AUTH-COMMON0000000019 账户属性【attr_id = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaAccountAttr, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000019", attrId);
    }

    /**
     * 校验Flea功能属性是否存在，如果不存在，抛出相应的异常
     *
     * @param fleaFunctionAttr Flea功能属性
     * @param attrId           属性编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaFunctionAttrExist(FleaFunctionAttr fleaFunctionAttr, Long attrId, String functionType) throws CommonException {
        // ERROR-AUTH-COMMON0000000020 功能属性【attr_id = {0}，function_type = {1}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaFunctionAttr, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000020", attrId, functionType);
    }

    /**
     * 检验Flea资源是否存在，如果不存在，抛出相应的异常。
     *
     * @param fleaResource Flea资源
     * @param resourceCode 资源编码
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaResourceExist(FleaResource fleaResource, String resourceCode) throws CommonException {
        // ERROR-AUTH-COMMON0000000021 资源【resource_code = {0}】不存在或已失效！
        ObjectUtils.checkEmpty(fleaResource, FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000021", resourceCode);
    }

    /**
     * 资源授权校验，如果校验不通过，则抛出相应的异常
     *
     * @param checkFlag       校验标识，true：表示通过，其他：表示不通过
     * @param resServiceSysId 资源服务归属的系统账户编号
     * @param accountId       调用资源的系统账号或操作账号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkResourceAuth(boolean checkFlag, Long resServiceSysId, Long accountId, String resourceCode) throws CommonException {
        if (!checkFlag) {
            // ERROR-AUTH-COMMON0000000022 账户【account_id = {0}】没有权限调用归属于系统【system_account_id = {1}】的资源【{2}】
            ExceptionUtils.throwCommonException(FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000022", accountId, resServiceSysId, resourceCode);
        }
    }

    /**
     * 校验是否存在系统关联资源数据，如果不存在，则抛出相应的异常
     *
     * @param isExist         是否存在系统关联资源标识，true：存在 false: 不存在
     * @param resourceCode    资源编码
     * @param systemAccountId 系统账号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkIsExistSystemRelResource(boolean isExist, String resourceCode, Long systemAccountId) throws CommonException {
        if (!isExist) {
            // ERROR-AUTH-COMMON0000000023 当前资源【{0}】不属于指定系统【system_account_id = {1}】，请确认！
            ExceptionUtils.throwCommonException(FleaAuthCommonException.class, "ERROR-AUTH-COMMON0000000023", resourceCode, systemAccountId);
        }
    }

    /**
     * 校验账户编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param accountId 账户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkAccountId(Long accountId) throws CommonException {
        // 校验账户编号不能为空
        checkEmpty(accountId, FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID);
        // 校验账户编号必须为正数
        checkNonPositiveNumber(accountId, FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID);
    }

    /**
     * 校验用户编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param userId 用户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkUserId(Long userId) throws CommonException {
        // 校验用户编号不能为空
        checkEmpty(userId, FleaAuthEntityConstants.UserEntityConstants.E_USER_ID);
        // 校验用户编号必须为正数
        checkNonPositiveNumber(userId, FleaAuthEntityConstants.UserEntityConstants.E_USER_ID);
    }

    /**
     * 校验用户组编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param userGroupId 用户组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkUserGroupId(Long userGroupId) throws CommonException {
        // 校验用户组编号不能为空
        checkEmpty(userGroupId, FleaAuthEntityConstants.UserEntityConstants.E_USER_GROUP_ID);
        // 校验用户组编号必须为正数
        checkNonPositiveNumber(userGroupId, FleaAuthEntityConstants.UserEntityConstants.E_USER_GROUP_ID);
    }

    /**
     * 校验角色编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param roleId 角色编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkRoleId(Long roleId) throws CommonException {
        // 校验角色编号不能为空
        checkEmpty(roleId, FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_ID);
        // 校验角色编号必须为正数
        checkNonPositiveNumber(roleId, FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_ID);
    }

    /**
     * 校验关联角色编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param relRoleId 关联角色编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkRelRoleId(Long relRoleId) throws CommonException {
        // 校验关联角色编号不能为空
        checkEmpty(relRoleId, FleaAuthEntityConstants.RoleEntityConstants.E_REL_ROLE_ID);
        // 校验关联角色编号必须为正数
        checkNonPositiveNumber(relRoleId, FleaAuthEntityConstants.RoleEntityConstants.E_REL_ROLE_ID);
    }

    /**
     * 校验角色组编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param roleGroupId 角色组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkRoleGroupId(Long roleGroupId) throws CommonException {
        // 校验角色组编号不能为空
        checkEmpty(roleGroupId, FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_GROUP_ID);
        // 校验角色组编号必须为正数
        checkNonPositiveNumber(roleGroupId, FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_GROUP_ID);
    }

    /**
     * 校验权限编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param privilegeId 权限编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkPrivilegeId(Long privilegeId) throws CommonException {
        // 校验权限编号不能为空
        checkEmpty(privilegeId, FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID);
        // 校验权限编号必须为正数
        checkNonPositiveNumber(privilegeId, FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID);
    }

    /**
     * 校验权限组编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param privilegeGroupId 权限组编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkPrivilegeGroupId(Long privilegeGroupId) throws CommonException {
        // 校验权限组编号不能为空
        checkEmpty(privilegeGroupId, FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_GROUP_ID);
        // 校验权限组编号必须为正数
        checkNonPositiveNumber(privilegeGroupId, FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_GROUP_ID);
    }

    /**
     * 校验菜单编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param menuId 菜单编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkMenuId(Long menuId) throws CommonException {
        // 校验菜单编号不能为空
        checkEmpty(menuId, FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_ID);
        // 校验菜单编号必须为正数
        checkNonPositiveNumber(menuId, FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_ID);
    }

    /**
     * 校验操作编号编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param operationId 操作编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkOperationId(Long operationId) throws CommonException {
        // 校验操作编号不能为空
        checkEmpty(operationId, FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_ID);
        // 校验操作编号必须为正数
        checkNonPositiveNumber(operationId, FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_ID);
    }

    /**
     * 校验元素编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param elementId 元素编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkElementId(Long elementId) throws CommonException {
        // 校验元素编号不能为空
        checkEmpty(elementId, FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_ID);
        // 校验元素编号必须为正数
        checkNonPositiveNumber(elementId, FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_ID);
    }

    /**
     * 校验资源编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param resourceId 资源编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkResourceId(Long resourceId) throws CommonException {
        // 校验资源编号不能为空
        checkEmpty(resourceId, FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_ID);
        // 校验资源编号必须为正数
        checkNonPositiveNumber(resourceId, FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_ID);
    }

    /**
     * 校验功能编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param functionId 功能编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFunctionId(Long functionId) throws CommonException {
        // 校验功能编号不能为空
        checkEmpty(functionId, FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_ID);
        // 校验功能编号必须为正数
        checkNonPositiveNumber(functionId, FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_ID);
    }

    /**
     * 校验属性编号，如果为空或者不是正数，则抛出相应的异常。
     *
     * @param attrId 属性编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkAttrId(Long attrId) throws CommonException {
        // 校验属性编号不能为空
        checkEmpty(attrId, FleaAuthEntityConstants.E_ATTR_ID);
        // 校验属性编号必须为正数
        checkNonPositiveNumber(attrId, FleaAuthEntityConstants.E_ATTR_ID);
    }

    /**
     * 校验Flea属性POJO对象
     *
     * @param fleaAttrPOJO Flea属性POJO对象实例
     * @param objName      对象名称
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaAttrPOJO(FleaAttrPOJO fleaAttrPOJO, String objName) throws CommonException {
        // 校验 fleaAttrPOJO 不能为空
        checkEmpty(fleaAttrPOJO, objName);

        // 校验属性码不能为空
        checkBlank(fleaAttrPOJO.getAttrCode(), FleaAuthEntityConstants.E_ATTR_CODE);
    }

    /**
     * 校验权限关联POJO对象
     *
     * @param fleaAuthRelPOJO 权限关联POJO类对象
     * @param objName         对象名称
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void checkAuthRelPOJO(FleaAuthRelPOJO fleaAuthRelPOJO, String objName) throws CommonException {
        // 校验 fleaAuthRelPOJO 不能为空
        checkEmpty(fleaAuthRelPOJO, objName);

        // 校验关联编号不能为空
        checkEmpty(fleaAuthRelPOJO.getRelId(), FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_ID);

        // 校验关联类型不能为空
        checkBlank(fleaAuthRelPOJO.getRelType(), FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE);
    }

    /**
     * 校验Flea元素POJO对象
     *
     * @param fleaElementPOJO Flea元素POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaElementPOJO(FleaElementPOJO fleaElementPOJO) throws CommonException {
        // 校验Flea元素POJO对象不能为空
        checkEmpty(fleaElementPOJO, FleaElementPOJO.class.getSimpleName());

        // 校验元素编码不能为空
        checkBlank(fleaElementPOJO.getElementCode(), FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_CODE);

        // 校验元素名称不能为空
        checkBlank(fleaElementPOJO.getElementName(), FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_NAME);

        // 校验元素类型不能为空
        checkEmpty(fleaElementPOJO.getElementType(), FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_TYPE);
    }

    /**
     * 校验Flea菜单POJO对象
     *
     * @param fleaMenuPOJO Flea菜单POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaMenuPOJO(FleaMenuPOJO fleaMenuPOJO) throws CommonException {
        // 校验Flea菜单POJO对象不能为空
        checkEmpty(fleaMenuPOJO, FleaMenuPOJO.class.getSimpleName());

        // 校验菜单编码不能为空
        checkBlank(fleaMenuPOJO.getMenuCode(), FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_CODE);

        // 校验菜单名称不能为空
        checkBlank(fleaMenuPOJO.getMenuName(), FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_NAME);

        // 校验菜单FontAwesome小图标不能为空
        checkBlank(fleaMenuPOJO.getMenuIcon(), FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_ICON);

        // 校验菜单顺序不能为空
        checkEmpty(fleaMenuPOJO.getMenuSort(), FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_SORT);

        // 校验菜单层级必须是正数
        checkNonPositiveNumber(fleaMenuPOJO.getMenuLevel(), FleaAuthEntityConstants.FunctionEntityConstants.E_MENU_LEVEL);
    }

    /**
     * 校验Flea操作POJO对象
     *
     * @param fleaOperationPOJO Flea操作POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaOperationPOJO(FleaOperationPOJO fleaOperationPOJO) throws CommonException {
        // 校验Flea操作POJO对象不能为空
        checkEmpty(fleaOperationPOJO, FleaOperationPOJO.class.getSimpleName());

        // 校验操作编码不能为空
        checkBlank(fleaOperationPOJO.getOperationCode(), FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_CODE);

        // 校验操作名称不能为空
        checkBlank(fleaOperationPOJO.getOperationName(), FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_NAME);
    }

    /**
     * 校验Flea资源POJO对象
     *
     * @param fleaResourcePOJO Flea资源POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaResourcePOJO(FleaResourcePOJO fleaResourcePOJO) throws CommonException {
        // 校验Flea资源POJO对象不能为空
        checkEmpty(fleaResourcePOJO, FleaResourcePOJO.class.getSimpleName());

        // 校验资源编码不能为空
        checkBlank(fleaResourcePOJO.getResourceCode(), FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_CODE);

        // 校验资源名称不能为空
        checkBlank(fleaResourcePOJO.getResourceName(), FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_NAME);
    }

    /**
     * 校验Flea功能扩展属性POJO对象
     *
     * @param fleaFunctionAttrPOJO Flea功能扩展属性POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaFunctionAttrPOJO(FleaFunctionAttrPOJO fleaFunctionAttrPOJO) throws CommonException {
        // 校验Flea功能扩展属性POJO对象不能为空
        checkFleaAttrPOJO(fleaFunctionAttrPOJO, FleaFunctionAttrPOJO.class.getSimpleName());

        // 校验功能类型不能为空
        checkBlank(fleaFunctionAttrPOJO.getFunctionType(), FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_TYPE);
    }

    /**
     * 校验Flea权限POJO对象
     *
     * @param fleaPrivilegePOJO Flea权限POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaPrivilegePOJO(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        // 校验Flea权限POJO对象不能为空
        checkEmpty(fleaPrivilegePOJO, FleaPrivilegePOJO.class.getSimpleName());

        // 校验权限名称不能为空
        checkBlank(fleaPrivilegePOJO.getPrivilegeName(), FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_NAME);
    }

    /**
     * 校验Flea权限组POJO对象
     *
     * @param fleaPrivilegeGroupPOJO Flea权限组POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaPrivilegeGroupPOJO(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        // 校验Flea权限组POJO对象不能为空
        checkEmpty(fleaPrivilegeGroupPOJO, FleaPrivilegeGroupPOJO.class.getSimpleName());

        // 校验权限组名称不能为空
        checkBlank(fleaPrivilegeGroupPOJO.getPrivilegeGroupName(), FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_GROUP_NAME);
    }

    /**
     * 校验Flea权限关联POJO对象
     *
     * @param fleaPrivilegeRelPOJO Flea权限关联POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaPrivilegeRelPOJO(FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO) throws CommonException {
        // 校验权限关联POJO对象不能为空
        checkAuthRelPOJO(fleaPrivilegeRelPOJO, FleaPrivilegeRelPOJO.class.getSimpleName());

        // 校验权限编号
        checkPrivilegeId(fleaPrivilegeRelPOJO.getPrivilegeId());
    }

    /**
     * 校验权限关联POJO对象
     *
     * @param fleaPrivilegeGroupRelPOJO Flea权限关联POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaPrivilegeGroupRelPOJO(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException {
        // 校验权限关联POJO对象不能为空
        checkAuthRelPOJO(fleaPrivilegeGroupRelPOJO, FleaPrivilegeGroupRelPOJO.class.getSimpleName());

        // 校验权限组编号
        checkPrivilegeGroupId(fleaPrivilegeGroupRelPOJO.getPrivilegeGroupId());
    }

    /**
     * 校验Fla角色POJO对象
     *
     * @param fleaRolePOJO Flea角色POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRolePOJO(FleaRolePOJO fleaRolePOJO) throws CommonException {
        // 校验Flea角色POJO对象不能为空
        checkEmpty(fleaRolePOJO, FleaRolePOJO.class.getSimpleName());

        // 校验角色名称不能为空
        checkBlank(fleaRolePOJO.getRoleName(), FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_NAME);
    }

    /**
     * 校验Fla角色组POJO对象
     *
     * @param fleaRoleGroupPOJO Flea角色组POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRoleGroupPOJO(FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException {
        // 校验Flea角色组POJO对象不能为空
        checkEmpty(fleaRoleGroupPOJO, FleaRoleGroupPOJO.class.getSimpleName());

        // 校验角色组名称不能为空
        checkBlank(fleaRoleGroupPOJO.getRoleGroupName(), FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_GROUP_NAME);
    }

    /**
     * 校验Flea角色关联POJO对象
     *
     * @param fleaRoleRelPOJO Flea角色关联POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRoleRelPOJO(FleaRoleRelPOJO fleaRoleRelPOJO) throws CommonException {
        // 校验角色关联POJO对象不能为空
        checkAuthRelPOJO(fleaRoleRelPOJO, FleaRoleRelPOJO.class.getSimpleName());

        // 校验角色编号
        checkRoleId(fleaRoleRelPOJO.getRoleId());
    }

    /**
     * 校验角色组关联POJO对象
     *
     * @param fleaRoleGroupRelPOJO Flea角色组关联POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRoleGroupRelPOJO(FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO) throws CommonException {
        // 校验角色组关联POJO对象不能为空
        checkAuthRelPOJO(fleaRoleGroupRelPOJO, FleaRoleGroupRelPOJO.class.getSimpleName());

        // 校验角色组编号不能为空
        checkRoleGroupId(fleaRoleGroupRelPOJO.getRoleGroupId());
    }

    /**
     * 校验Flea账户POJO对象
     *
     * @param fleaAccountPOJO Flea账户POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaAccountPOJO(FleaAccountPOJO fleaAccountPOJO) throws CommonException {
        // 校验Flea账户POJO对象不能为空
        checkEmpty(fleaAccountPOJO, FleaAccountPOJO.class.getSimpleName());

        // 校验账号不能为空
        checkAccountCode(fleaAccountPOJO.getAccountCode());

        // 校验用户编号不能为空
        checkUserId(fleaAccountPOJO.getUserId());

        // 校验密码不能为空
        checkAccountPwd(fleaAccountPOJO.getAccountPwd());
    }

    /**
     * 校验Flea用户POJO对象
     *
     * @param fleaUserPOJO Flea用户POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserPOJO(FleaUserPOJO fleaUserPOJO) throws CommonException {
        // 校验Flea用户POJO对象不能为空
        checkEmpty(fleaUserPOJO, FleaUserPOJO.class.getSimpleName());

        // 校验用户昵称不能为空
        checkBlank(fleaUserPOJO.getUserName(), FleaAuthEntityConstants.UserEntityConstants.E_USER_NAME);
    }

    /**
     * 校验Flea用户组POJO对象
     *
     * @param fleaUserGroupPOJO Flea用户组POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserGroupPOJO(FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException {
        // 校验Flea用户组POJO对象不能为空
        checkEmpty(fleaUserGroupPOJO, FleaUserGroupPOJO.class.getSimpleName());

        // 校验用户组名称不能为空
        checkBlank(fleaUserGroupPOJO.getUserGroupName(), FleaAuthEntityConstants.UserEntityConstants.E_USER_GROUP_NAME);
    }

    /**
     * 校验Flea用户关联POJO对象
     *
     * @param fleaUserRelPOJO Flea用户关联POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserRelPOJO(FleaUserRelPOJO fleaUserRelPOJO) throws CommonException {
        // 校验用户关联POJO对象不能为空
        checkAuthRelPOJO(fleaUserRelPOJO, FleaUserRelPOJO.class.getSimpleName());

        // 校验用户编号不能为空
        checkUserId(fleaUserRelPOJO.getUserId());
    }

    /**
     * 校验用户组关联POJO对象
     *
     * @param fleaUserGroupRelPOJO Flea用户组关联POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserGroupRelPOJO(FleaUserGroupRelPOJO fleaUserGroupRelPOJO) throws CommonException {
        // 校验用户组关联POJO对象不能为空
        checkAuthRelPOJO(fleaUserGroupRelPOJO, FleaUserGroupRelPOJO.class.getSimpleName());

        // 校验用户组编号不能为空
        checkEmpty(fleaUserGroupRelPOJO.getUserGroupId(), FleaAuthEntityConstants.UserEntityConstants.E_USER_GROUP_ID);
    }

    /**
     * 校验Flea账户扩展属性POJO对象
     *
     * @param fleaAccountAttrPOJO Flea账户扩展属性POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaAccountAttrPOJO(FleaAccountAttrPOJO fleaAccountAttrPOJO) throws CommonException {
        // 校验Flea账户扩展属性POJO对象不能为空
        checkFleaAttrPOJO(fleaAccountAttrPOJO, FleaAccountAttrPOJO.class.getSimpleName());

        // 校验账户编号是否为空
        checkAccountId(fleaAccountAttrPOJO.getAccountId());
    }

    /**
     * 校验Flea用户扩展属性POJO对象
     *
     * @param fleaUserAttrPOJO Flea用户扩展属性POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserAttrPOJO(FleaUserAttrPOJO fleaUserAttrPOJO) throws CommonException {
        // 校验Flea用户扩展属性POJO对象不能为空
        checkFleaAttrPOJO(fleaUserAttrPOJO, FleaUserAttrPOJO.class.getSimpleName());

        // 校验用户编号是否为空
        checkUserId(fleaUserAttrPOJO.getUserId());
    }

    /**
     * 校验登录日志POJO对象
     *
     * @param fleaLoginLogPOJO Flea登录日志POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaLoginLogPOJO(FleaLoginLogPOJO fleaLoginLogPOJO) throws CommonException {
        // 校验Flea登录日志POJO对象不能为空
        checkEmpty(fleaLoginLogPOJO, FleaLoginLogPOJO.class.getSimpleName());

        // 校验账户编号不能为空
        checkAccountId(fleaLoginLogPOJO.getAccountId());

        // 校验ip4地址不能为空
        checkBlank(fleaLoginLogPOJO.getLoginIp4(), FleaAuthEntityConstants.UserEntityConstants.E_LOGIN_IP4);
    }

    /**
     * 校验Flea实名信息POJO对象
     *
     * @param fleaRealNameInfoPOJO Flea实名信息POJO
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaRealNameInfoPOJO(FleaRealNameInfoPOJO fleaRealNameInfoPOJO) throws CommonException {
        // 校验Flea实名信息POJO对象不能为空
        checkEmpty(fleaRealNameInfoPOJO, FleaRealNameInfoPOJO.class.getSimpleName());

        // 校验证件类型不能为空
        checkEmpty(fleaRealNameInfoPOJO.getCertType(), FleaAuthEntityConstants.UserEntityConstants.E_CERT_TYPE);

        // 校验证件号码不能为空
        checkEmpty(fleaRealNameInfoPOJO.getCertCode(), FleaAuthEntityConstants.UserEntityConstants.E_CERT_CODE);

        // 校验证件名称不能为空
        checkEmpty(fleaRealNameInfoPOJO.getCertName(), FleaAuthEntityConstants.UserEntityConstants.E_CERT_NAME);
    }

    /**
     * 校验Flea用户模块数据
     *
     * @param fleaUserModuleData Flea用户模块数据
     * @param accountId          账户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    public static void checkFleaUserModuleData(FleaUserModuleData fleaUserModuleData, String accountId) throws CommonException {
        // 校验Flea用户模块数据不能为空
        checkEmpty(fleaUserModuleData, FleaUserModuleData.class.getSimpleName());

        FleaAccount fleaAccount = fleaUserModuleData.getFleaAccount();
        // 校验Flea账户是否存在
        checkFleaAccountExist(fleaAccount, accountId);

        // 校验Flea用户是否存在
        checkFleaUserExist(fleaUserModuleData.getFleaUser(), StringUtils.valueOf(fleaAccount.getUserId()));
    }

}
