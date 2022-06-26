package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaElementSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaFunctionAttrSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaMenuSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaOperationSV;
import com.huazie.fleaframework.auth.base.function.service.interfaces.IFleaResourceSV;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeRelSV;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleRelSV;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserAttr;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountAttrSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaAccountSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaRealNameInfoSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserAttrSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserGroupRelSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserRelSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FleaAuthConstants;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaAuthSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Flea 授权服务实现类，对外提供可缓存的授权数据查询API。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaAuthSV")
public class FleaAuthSVImpl implements IFleaAuthSV {

    private IFleaAccountSV fleaAccountSV; // Flea账户服务

    private IFleaUserSV fleaUserSV; // Flea用户服务

    private IFleaAccountAttrSV fleaAccountAttrSV; // Flea账户扩展属性服务

    private IFleaUserAttrSV fleaUserAttrSV; // Flea用户扩展属性服务

    private IFleaRealNameInfoSV fleaRealNameInfoSV; // Flea实名服务

    private IFleaUserGroupRelSV fleaUserGroupRelSV; // Flea用户组关联服务

    private IFleaUserRelSV fleaUserRelSV; // Flea用户关联服务

    private IFleaRoleGroupRelSV fleaRoleGroupRelSV; // Flea角色组关联服务

    private IFleaRoleRelSV fleaRoleRelSV; // Flea角色关联服务

    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV; // Flea权限组关联服务

    private IFleaPrivilegeRelSV fleaPrivilegeRelSV; // Flea权限关联服务

    private IFleaMenuSV fleaMenuSV; // Flea菜单服务

    private IFleaOperationSV fleaOperationSV; // Flea操作服务

    private IFleaElementSV fleaElementSV; // Flea元素服务

    private IFleaResourceSV fleaResourceSV; // Flea资源服务

    private IFleaFunctionAttrSV fleaFunctionAttrSV; // Flea扩展属性服务

    @Resource(name = "fleaAccountCacheSV")
    public void setFleaAccountSV(IFleaAccountSV fleaAccountSV) {
        this.fleaAccountSV = fleaAccountSV;
    }

    @Resource(name = "fleaUserSV")
    public void setFleaUserSV(IFleaUserSV fleaUserSV) {
        this.fleaUserSV = fleaUserSV;
    }

    @Resource(name = "fleaAccountAttrSV")
    public void setFleaAccountAttrSV(IFleaAccountAttrSV fleaAccountAttrSV) {
        this.fleaAccountAttrSV = fleaAccountAttrSV;
    }

    @Resource(name = "fleaUserAttrSV")
    public void setFleaUserAttrSV(IFleaUserAttrSV fleaUserAttrSV) {
        this.fleaUserAttrSV = fleaUserAttrSV;
    }

    @Resource(name = "fleaRealNameInfoSV")
    public void setFleaRealNameInfoSV(IFleaRealNameInfoSV fleaRealNameInfoSV) {
        this.fleaRealNameInfoSV = fleaRealNameInfoSV;
    }

    @Resource(name = "fleaUserGroupRelSV")
    public void setFleaUserGroupRelSV(IFleaUserGroupRelSV fleaUserGroupRelSV) {
        this.fleaUserGroupRelSV = fleaUserGroupRelSV;
    }

    @Resource(name = "fleaUserRelSV")
    public void setFleaUserRelSV(IFleaUserRelSV fleaUserRelSV) {
        this.fleaUserRelSV = fleaUserRelSV;
    }

    @Resource(name = "fleaRoleGroupRelSV")
    public void setFleaRoleGroupRelSV(IFleaRoleGroupRelSV fleaRoleGroupRelSV) {
        this.fleaRoleGroupRelSV = fleaRoleGroupRelSV;
    }

    @Resource(name = "fleaRoleRelSV")
    public void setFleaRoleRelSV(IFleaRoleRelSV fleaRoleRelSV) {
        this.fleaRoleRelSV = fleaRoleRelSV;
    }

    @Resource(name = "fleaPrivilegeGroupRelSV")
    public void setFleaPrivilegeGroupRelSV(IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV) {
        this.fleaPrivilegeGroupRelSV = fleaPrivilegeGroupRelSV;
    }

    @Resource(name = "fleaPrivilegeRelSV")
    public void setFleaPrivilegeRelSV(IFleaPrivilegeRelSV fleaPrivilegeRelSV) {
        this.fleaPrivilegeRelSV = fleaPrivilegeRelSV;
    }

    @Resource(name = "fleaMenuSV")
    public void setFleaMenuSV(IFleaMenuSV fleaMenuSV) {
        this.fleaMenuSV = fleaMenuSV;
    }

    @Resource(name = "fleaOperationSV")
    public void setFleaOperationSV(IFleaOperationSV fleaOperationSV) {
        this.fleaOperationSV = fleaOperationSV;
    }

    @Resource(name = "fleaElementSV")
    public void setFleaElementSV(IFleaElementSV fleaElementSV) {
        this.fleaElementSV = fleaElementSV;
    }

    @Resource(name = "fleaResourceCacheSV")
    public void setFleaResourceSV(IFleaResourceSV fleaResourceSV) {
        this.fleaResourceSV = fleaResourceSV;
    }

    @Resource(name = "fleaFunctionAttrSV")
    public void setFleaFunctionAttrSV(IFleaFunctionAttrSV fleaFunctionAttrSV) {
        this.fleaFunctionAttrSV = fleaFunctionAttrSV;
    }

    @Override
    public FleaUserModuleData getFleaUserModuleData(Long accountId) throws CommonException {

        FleaUserModuleData fleaUserModuleData = new FleaUserModuleData();

        // 校验账户编号是否是正数
        FleaAuthCheck.checkNonPositiveNumber(accountId, FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID);

        // 获取有效的账户信息
        FleaAccount fleaAccount = this.fleaAccountSV.queryValidAccount(accountId);

        Long userId = 0L;
        List<FleaAccountAttr> fleaAccountAttrs = null;
        if (ObjectUtils.isNotEmpty(fleaAccount)) {
            userId = fleaAccount.getUserId();
            fleaAccountAttrs = this.fleaAccountAttrSV.queryValidAccountAttrs(accountId);
        }

        // 获取有效的用户信息
        FleaUser fleaUser = null;
        List<FleaUserAttr> fleaUserAttrs = null;
        FleaRealNameInfo fleaRealNameInfo = null;
        if (NumberUtils.isPositiveNumber(userId)) {
            fleaUser = this.fleaUserSV.queryValidUser(userId);
            fleaUserAttrs = this.fleaUserAttrSV.queryValidUserAttrs(userId);
            // 从用户扩展属性中获取用户关联实名信息
            if (CollectionUtils.isNotEmpty(fleaUserAttrs)) {
                for (FleaUserAttr fleaUserAttr : fleaUserAttrs) {
                    if (FleaAuthConstants.AttrCodeConstants.ATTR_CODE_REAL_NAME_ID.equals(fleaUserAttr.getAttrCode())) {
                        long realNameId = Long.parseLong(fleaUserAttr.getAttrValue());
                        fleaRealNameInfo = this.fleaRealNameInfoSV.queryValidRealNameInfo(realNameId);
                        break;
                    }
                }
            }
        }

        fleaUserModuleData.setFleaUser(fleaUser);
        fleaUserModuleData.setFleaAccount(fleaAccount);
        fleaUserModuleData.setFleaUserAttrs(fleaUserAttrs);
        fleaUserModuleData.setFleaAccountAttrs(fleaAccountAttrs);
        fleaUserModuleData.setFleaRealNameInfo(fleaRealNameInfo);

        return fleaUserModuleData;
    }

    @Override
    public List<Long> getUserRoles(Long userId) throws CommonException {
        // 角色编号集
        List<Long> roleIdList = new ArrayList<>();

        // 处理用户组关联数据
        handleUserGroupRel(roleIdList, userId);

        // 处理用户关联数据
        handleUserRel(roleIdList, userId);

        return roleIdList;
    }

    @Override
    public List<Long> getUserPrivileges(Long userId) throws CommonException {
        // 角色编号集
        List<Long> roleIdList = this.getUserRoles(userId);

        // 权限编号集
        List<Long> privilegeIdList = new ArrayList<>();

        // 处理角色关联数据
        handleRoleRel(privilegeIdList, roleIdList);

        return privilegeIdList;
    }

    /**
     * 处理用户组关联数据，获取其关联的角色数据
     *
     * @param roleIdList 角色编号集合
     * @param userId     用户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private void handleUserGroupRel(List<Long> roleIdList, Long userId) throws CommonException {
        // 获取用户组关联用户数据
        List<FleaUserGroupRel> userGroupRelUsers = this.fleaUserGroupRelSV.getUserGroupRelList(null, userId, AuthRelTypeEnum.USER_GROUP_REL_USER.getRelType());
        if (CollectionUtils.isEmpty(userGroupRelUsers)) return;
        for (FleaUserGroupRel fleaUserGroupRel : userGroupRelUsers) {
            if (ObjectUtils.isEmpty(fleaUserGroupRel)) continue;
            Long groupId = fleaUserGroupRel.getUserGroupId();
            // 获取用户组关联角色组数据
            List<FleaUserGroupRel> userGroupRelRoleGroups = this.fleaUserGroupRelSV.getUserGroupRelList(groupId, null, AuthRelTypeEnum.USER_GROUP_REL_ROLE_GROUP.getRelType());
            // 处理用户组关联角色组数据
            handleUserGroupRel(roleIdList, userGroupRelRoleGroups, AuthRelTypeEnum.USER_GROUP_REL_ROLE_GROUP.getRelType());

            // 获取用户组关联角色数据
            List<FleaUserGroupRel> userGroupRelRoles = this.fleaUserGroupRelSV.getUserGroupRelList(groupId, null, AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType());
            // 处理用户组关联角色数据
            handleUserGroupRel(roleIdList, userGroupRelRoles, AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType());
        }
    }

    /**
     * 处理用户组关联数据
     *
     * @param roleIdList       角色编号集合
     * @param userGroupRelList 用户组关联数据
     * @param authRelType      授权关联类型
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private void handleUserGroupRel(List<Long> roleIdList, List<FleaUserGroupRel> userGroupRelList, String authRelType) throws CommonException {
        if (CollectionUtils.isEmpty(userGroupRelList)) return;
        for (FleaUserGroupRel userGroupRel : userGroupRelList) {
            if (ObjectUtils.isEmpty(userGroupRel)) continue;
            if (AuthRelTypeEnum.USER_GROUP_REL_ROLE_GROUP.getRelType().equals(authRelType)) { // 用户组关联角色组
                handleRoleGroupRel(userGroupRel.getRelExtA(), userGroupRel.getRelId(), roleIdList);
            } else if (AuthRelTypeEnum.USER_GROUP_REL_ROLE.getRelType().equals(authRelType)) { // 用户组关联角色
                // 用户组关联角色，直接取关联编号rel_id即可
                CollectionUtils.distinctAdd(roleIdList, userGroupRel.getRelId());
            }
        }
    }

    /**
     * 处理用户关联数据，获取其关联的角色数据
     *
     * @param roleIdList 角色编号集合
     * @param userId     用户编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private void handleUserRel(List<Long> roleIdList, Long userId) throws CommonException {
        // 获取用户关联角色组数据
        List<FleaUserRel> userRelRoleGroups = this.fleaUserRelSV.getUserRelList(userId, AuthRelTypeEnum.USER_REL_ROLE_GROUP.getRelType());
        // 处理用户关联角色组数据
        handleUserRel(roleIdList, userRelRoleGroups, AuthRelTypeEnum.USER_REL_ROLE_GROUP.getRelType());

        // 获取用户关联角色数据
        List<FleaUserRel> userRelRoles = this.fleaUserRelSV.getUserRelList(userId, AuthRelTypeEnum.USER_REL_ROLE.getRelType());
        // 处理用户关联角色数据
        handleUserRel(roleIdList, userRelRoles, AuthRelTypeEnum.USER_REL_ROLE.getRelType());
    }

    /**
     * 处理用户关联数据
     *
     * @param roleIdList  角色编号集合
     * @param userRelList 用户关联数据
     * @param authRelType 授权关联类型
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private void handleUserRel(List<Long> roleIdList, List<FleaUserRel> userRelList, String authRelType) throws CommonException {
        if (CollectionUtils.isEmpty(userRelList)) return;
        for (FleaUserRel userRel : userRelList) {
            if (ObjectUtils.isEmpty(userRel)) continue;
            if (AuthRelTypeEnum.USER_REL_ROLE_GROUP.getRelType().equals(authRelType)) { // 用户关联角色组
                handleRoleGroupRel(userRel.getRelExtA(), userRel.getRelId(), roleIdList);
            } else if (AuthRelTypeEnum.USER_REL_ROLE.getRelType().equals(authRelType)) { // 用户关联角色
                // 用户关联角色，直接取关联编号rel_id即可
                CollectionUtils.distinctAdd(roleIdList, userRel.getRelId());
            }
        }
    }

    /**
     * 处理角色组关联数据
     *
     * @param relExtA     扩展字符按A
     * @param roleGroupId 角色组编号
     * @param roleIdList  角色编号集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private void handleRoleGroupRel(String relExtA, Long roleGroupId, List<Long> roleIdList) throws CommonException {
        // 用户（组）关联中rel_ext_a用于指定用户（组）关联的角色组中实际指定的角色编号【存在多个，以逗号分隔】
        if (StringUtils.isNotBlank(relExtA)) {
            CollectionUtils.distinctAddWithComma(roleIdList, relExtA);
        } else { // 用户（组）关联中rel_ext_a为空，表示关联其角色组【rel_id = 角色组编号】下所有角色
            // 获取角色组关联的所有角色
            List<FleaRoleGroupRel> roleGroupRelRoles = this.fleaRoleGroupRelSV.getRoleGroupRelList(roleGroupId, AuthRelTypeEnum.ROLE_GROUP_REL_ROLE.getRelType());
            if (CollectionUtils.isEmpty(roleGroupRelRoles)) return;
            for (FleaRoleGroupRel roleGroupRel : roleGroupRelRoles) {
                if (ObjectUtils.isEmpty(roleGroupRel)) continue;
                CollectionUtils.distinctAdd(roleIdList, roleGroupRel.getRelId());
            }
        }
    }

    /**
     * 处理角色关联数据，获取其关联的权限数据
     *
     * @param privilegeIdList 权限编号集合
     * @param roleIdList      角色编号集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private void handleRoleRel(List<Long> privilegeIdList, List<Long> roleIdList) throws CommonException {
        if (CollectionUtils.isEmpty(roleIdList)) return;
        for (Long roleId : roleIdList) {
            // 获取角色关联权限组数据
            List<FleaRoleRel> roleRelPrivilegeGroups = this.fleaRoleRelSV.getRoleRelList(roleId, AuthRelTypeEnum.ROLE_REL_PRIVILEGE_GROUP.getRelType());
            // 处理角色关联权限组数据
            handleRoleRel(privilegeIdList, roleRelPrivilegeGroups, AuthRelTypeEnum.ROLE_REL_PRIVILEGE_GROUP.getRelType());

            // 获取角色关联权限数据
            List<FleaRoleRel> roleRelPrivileges = this.fleaRoleRelSV.getRoleRelList(roleId, AuthRelTypeEnum.ROLE_REL_PRIVILEGE.getRelType());
            // 处理角色关联权限数据
            handleRoleRel(privilegeIdList, roleRelPrivileges, AuthRelTypeEnum.ROLE_REL_PRIVILEGE.getRelType());
        }
    }

    /**
     * 处理角色关联数据
     *
     * @param privilegeIdList 权限编号集合
     * @param roleRelList     角色关联数据
     * @param authRelType     授权关联类型
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private void handleRoleRel(List<Long> privilegeIdList, List<FleaRoleRel> roleRelList, String authRelType) throws CommonException {
        if (CollectionUtils.isEmpty(roleRelList)) return;
        for (FleaRoleRel roleRel : roleRelList) {
            if (ObjectUtils.isEmpty(roleRel)) continue;
            if (AuthRelTypeEnum.ROLE_REL_PRIVILEGE_GROUP.getRelType().equals(authRelType)) { // 角色关联权限组
                // 角色关联中rel_ext_a用于指定角色关联的权限组中实际指定的权限编号【存在多个，以逗号分隔】
                String relExtA = roleRel.getRelExtA();
                if (StringUtils.isNotBlank(relExtA)) {
                    CollectionUtils.distinctAddWithComma(privilegeIdList, relExtA);
                } else { // 角色关联中rel_ext_a为空，则表示关联其权限组【rel_id = 权限组编号】下所有权限
                    handlePrivilegeGroupRel(roleRel.getRelId(), privilegeIdList);
                }
            } else if (AuthRelTypeEnum.ROLE_REL_PRIVILEGE.getRelType().equals(authRelType)) { // 角色关联权限
                CollectionUtils.distinctAdd(privilegeIdList, roleRel.getRelId());
            }
        }
    }

    /**
     * 处理权限组关联数据
     *
     * @param privilegeGroupId 权限组编号
     * @param privilegeIdList  权权限编号集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private void handlePrivilegeGroupRel(Long privilegeGroupId, List<Long> privilegeIdList) throws CommonException {
        // 获取权限组关联的所有权限
        List<FleaPrivilegeGroupRel> privilegeGroupRelPrivileges = this.fleaPrivilegeGroupRelSV.getPrivilegeGroupRelList(privilegeGroupId, AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType());
        if (CollectionUtils.isEmpty(privilegeGroupRelPrivileges)) return;
        for (FleaPrivilegeGroupRel privilegeGroupRel : privilegeGroupRelPrivileges) {
            if (ObjectUtils.isEmpty(privilegeGroupRel)) continue;
            CollectionUtils.distinctAdd(privilegeIdList, privilegeGroupRel.getRelId());
        }
    }

    @Override
    public List<FleaMenu> queryAllAccessibleMenus(Long accountId, Long systemAccountId) throws CommonException {

        // 校验操作账户编号不能为空
        FleaAuthCheck.checkEmpty(accountId, FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID);

        // 校验系统账户编号不能为空
        FleaAuthCheck.checkEmpty(systemAccountId, FleaAuthEntityConstants.UserEntityConstants.E_SYSTEM_ACCOUNT_ID);

        // 根据操作帐户编号accountId查询帐户信息
        FleaAccount fleaAccount = this.fleaAccountSV.queryValidAccount(accountId);
        // 校验操作账户是否存在
        FleaAuthCheck.checkFleaAccountExist(fleaAccount, StringUtils.valueOf(accountId));

        // 获取用户下的权限编号集
        List<Long> privilegeIdList = this.getUserPrivileges(fleaAccount.getUserId());

        // 获取权限关联的菜单编号集
        List<Long> menuIdList = this.fleaPrivilegeRelSV.getPrivilegeRelIdList(privilegeIdList, AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());

        // 获取系统账户下关联的菜单
        // 取 function_type = MENU, attr_code = SYSTEM_IN_USE, attr_value = systemAccountId
        List<Long> systemRelMenuIdList = this.fleaFunctionAttrSV.querySystemRelFunctionIds(FunctionTypeEnum.MENU.getType(), systemAccountId);

        return fleaMenuSV.queryAllAccessibleMenus(systemRelMenuIdList, menuIdList);
    }

    @Override
    public boolean checkResourceAuth(Long accountId, Long systemAccountId, String resourceCode) throws CommonException {

        // 校验账户编号不能为空
        FleaAuthCheck.checkEmpty(accountId, FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID);

        // 校验系统账户编号不能为空
        FleaAuthCheck.checkEmpty(systemAccountId, FleaAuthEntityConstants.UserEntityConstants.E_SYSTEM_ACCOUNT_ID);

        // 校验资源编码不能为空
        FleaAuthCheck.checkBlank(resourceCode, FleaAuthEntityConstants.FunctionEntityConstants.E_RESOURCE_CODE);

        // 根据帐户编号accountId查询帐户信息
        FleaAccount fleaAccount = this.fleaAccountSV.queryValidAccount(accountId);
        // 校验账户是否存在
        FleaAuthCheck.checkFleaAccountExist(fleaAccount, StringUtils.valueOf(accountId));

        // 根据资源编码resourceCode查询资源数据
        List<FleaResource> fleaResourceList = this.fleaResourceSV.queryValidResources(resourceCode, null);
        FleaResource fleaResource = CollectionUtils.getFirstElement(fleaResourceList, FleaResource.class);
        // 校验Flea资源是否存在
        FleaAuthCheck.checkFleaResourceExist(fleaResource, resourceCode);

        Long resourceId = fleaResource.getResourceId();

        // 判断指定系统下，是否存在对应的资源
        boolean isExist = this.fleaFunctionAttrSV.isExistSystemRelFunction(resourceId, FunctionTypeEnum.RESOURCE.getType(), systemAccountId);
        FleaAuthCheck.checkIsExistSystemRelResource(isExist, resourceCode, systemAccountId);

        // 获取用户下的权限编号集
        List<Long> privilegeIdList = this.getUserPrivileges(fleaAccount.getUserId());
        // 用户下无权限，直接返回校验不通过
        if (CollectionUtils.isEmpty(privilegeIdList)) return false;

        // 获取权限关联的资源编号集
        List<Long> resourceIdList = this.fleaPrivilegeRelSV.getPrivilegeRelIdList(privilegeIdList, AuthRelTypeEnum.PRIVILEGE_REL_RESOURCE.getRelType());

        return resourceIdList.contains(resourceId);
    }

}
