package com.huazie.fleaframework.auth.util.pojo;

import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;

/**
 * Flea角色相关POJO工具类
 *
 * <p>该类包含角色、角色组相关的POJO创建方法。</p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRoleModulePOJOUtils {

    private FleaRoleModulePOJOUtils() {
    }

    /**
     * 新建角色关联权限POJO对象
     *
     * @param roleId        角色编号
     * @param roleName      角色名称
     * @param privilegeId   权限编号
     * @param privilegeName 权限名称
     * @return 角色关联权限POJO对象
     * @since 2.0.0
     */
    public static FleaRoleRelPOJO newRoleRelPrivilegePOJO(Long roleId, String roleName, Long privilegeId, String privilegeName) {
        FleaRoleRelPOJO fleaRoleRelPOJO = new FleaRoleRelPOJO();
        fleaRoleRelPOJO.setRoleId(roleId);
        fleaRoleRelPOJO.setRelId(privilegeId);
        fleaRoleRelPOJO.setRelType(AuthRelTypeEnum.ROLE_REL_PRIVILEGE.getRelType()); // 角色关联权限
        // 【{0}】角色绑定【{1}】权限
        fleaRoleRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000001", new String[]{roleName, privilegeName}));
        return fleaRoleRelPOJO;
    }

    /**
     * 新建角色关联权限组POJO对象
     *
     * @param roleId             角色编号
     * @param roleName           角色名称
     * @param privilegeGroupId   权限组编号
     * @param privilegeGroupName 权限组名称
     * @return 角色关联权限组POJO对象
     * @since 2.0.0
     */
    public static FleaRoleRelPOJO newRoleRelPrivilegeGroupPOJO(Long roleId, String roleName, Long privilegeGroupId, String privilegeGroupName) {
        FleaRoleRelPOJO fleaRoleRelPOJO = new FleaRoleRelPOJO();
        fleaRoleRelPOJO.setRoleId(roleId);
        fleaRoleRelPOJO.setRelId(privilegeGroupId);
        fleaRoleRelPOJO.setRelType(AuthRelTypeEnum.ROLE_REL_PRIVILEGE_GROUP.getRelType()); // 角色关联权限组
        // 【{0}】角色绑定【{1}】权限组
        fleaRoleRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000002", new String[]{roleName, privilegeGroupName}));
        return fleaRoleRelPOJO;
    }

    /**
     * 新建角色关联角色POJO对象
     *
     * @param roleId      角色编号
     * @param roleName    角色名称
     * @param relRoleId   关联角色编号
     * @param relRoleName 关联角色名称
     * @return 角色关联角色POJO对象
     * @since 2.0.0
     */
    public static FleaRoleRelPOJO newRoleRelRolePOJO(Long roleId, String roleName, Long relRoleId, String relRoleName) {
        FleaRoleRelPOJO fleaRoleRelPOJO = new FleaRoleRelPOJO();
        fleaRoleRelPOJO.setRoleId(roleId);
        fleaRoleRelPOJO.setRelId(relRoleId);
        fleaRoleRelPOJO.setRelType(AuthRelTypeEnum.ROLE_REL_ROLE.getRelType()); // 角色关联角色
        // 【{0}】角色关联【{1}】角色
        fleaRoleRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000003", new String[]{roleName, relRoleName}));
        return fleaRoleRelPOJO;
    }

    /**
     * 新建角色组关联角色POJO对象
     *
     * @param roleGroupId   角色组编号
     * @param roleGroupName 角色组名称
     * @param roleId        角色编号
     * @param roleName      角色名称
     * @return 角色组关联角色POJO对象
     * @since 2.0.0
     */
    public static FleaRoleGroupRelPOJO newRoleGroupRelRolePOJO(Long roleGroupId, String roleGroupName, Long roleId, String roleName) {
        FleaRoleGroupRelPOJO fleaRoleGroupRelPOJO = new FleaRoleGroupRelPOJO();
        fleaRoleGroupRelPOJO.setRoleGroupId(roleGroupId);
        fleaRoleGroupRelPOJO.setRelId(roleId);
        fleaRoleGroupRelPOJO.setRelType(AuthRelTypeEnum.ROLE_GROUP_REL_ROLE.getRelType()); // 角色组关联角色
        // 【{0}】角色组关联【{1}】角色
        fleaRoleGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-ROLE0000000004", new String[]{roleGroupName, roleName}));
        return fleaRoleGroupRelPOJO;
    }
}
