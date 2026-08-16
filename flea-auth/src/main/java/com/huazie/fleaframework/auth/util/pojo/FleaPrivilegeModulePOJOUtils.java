package com.huazie.fleaframework.auth.util.pojo;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FunctionTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeRelPOJO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * Flea权限相关POJO工具类
 *
 * <p>该类包含权限、权限组相关的POJO创建方法。</p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaPrivilegeModulePOJOUtils {

    private FleaPrivilegeModulePOJOUtils() {
    }

    /**
     * 新建Flea权限POJO对象
     *
     * @param privilegeName 权限名称
     * @param privilegeDesc 权限描述
     * @param remarks       备注
     * @return Flea权限POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJO(String privilegeName, String privilegeDesc, Long groupId, String remarks) {
        FleaPrivilegePOJO fleaPrivilegePOJO = new FleaPrivilegePOJO();
        fleaPrivilegePOJO.setPrivilegeName(privilegeName);
        String[] values = new String[]{privilegeName};
        if (StringUtils.isBlank(privilegeDesc)) {
            // 与【{0}】相关的权限
            privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000001", values);
        }
        fleaPrivilegePOJO.setPrivilegeDesc(privilegeDesc);
        if (StringUtils.isBlank(remarks)) {
            // 该权限可以用来【{0}】
            remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000002", values);
        }
        fleaPrivilegePOJO.setGroupId(groupId);
        fleaPrivilegePOJO.setRemarks(remarks);
        return fleaPrivilegePOJO;
    }

    /**
     * 新建Flea权限组POJO对象
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param remarks            备注
     * @return Flea权限组POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJO(String privilegeGroupName, String privilegeGroupDesc, String remarks) {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = new FleaPrivilegeGroupPOJO();
        fleaPrivilegeGroupPOJO.setPrivilegeGroupName(privilegeGroupName);
        String[] values = new String[]{privilegeGroupName};
        if (StringUtils.isBlank(privilegeGroupDesc)) {
            // 与【{0}】相关的权限归属的权限组
            privilegeGroupDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000003", values);
        }
        fleaPrivilegeGroupPOJO.setPrivilegeGroupDesc(privilegeGroupDesc);
        if (StringUtils.isBlank(remarks)) {
            // 该权限组包含了【{0}】相关的权限
            remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000004", values);
        }
        fleaPrivilegeGroupPOJO.setRemarks(remarks);
        return fleaPrivilegeGroupPOJO;
    }

    /**
     * 新建Flea权限组POJO对象【与菜单访问相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与菜单访问相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForMenu(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.MENU.getType());
        return fleaPrivilegeGroupPOJO;
    }

    /**
     * 新建Flea权限组POJO对象【与执行操作相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForOperation(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.OPERATION.getType());
        return fleaPrivilegeGroupPOJO;
    }

    /**
     * 新建Flea权限组POJO对象【与展示元素相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForElement(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.ELEMENT.getType());
        return fleaPrivilegeGroupPOJO;
    }

    /**
     * 新建Flea权限组POJO对象【与调用资源相关】
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param isMain             是否为主权限组（0：不是 1：是）
     * @param remarks            备注
     * @return Flea权限组POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupPOJO newFleaPrivilegeGroupPOJOForResource(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String remarks) {
        FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO = newFleaPrivilegeGroupPOJO(privilegeGroupName, privilegeGroupDesc, remarks);
        fleaPrivilegeGroupPOJO.setIsMain(isMain);
        fleaPrivilegeGroupPOJO.setFunctionType(FunctionTypeEnum.RESOURCE.getType());
        return fleaPrivilegeGroupPOJO;
    }

    /**
     * 新建权限组关联权限POJO对象
     *
     * @param privilegeGroupId   权限组编号
     * @param privilegeGroupName 权限组名称
     * @param privilegeId        权限编号
     * @param privilegeName      权限名称
     * @return 权限组关联权限POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupRelPOJO newPrivilegeGroupRelPrivilegePOJO(Long privilegeGroupId, String privilegeGroupName, Long privilegeId, String privilegeName) {
        FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO = new FleaPrivilegeGroupRelPOJO();
        fleaPrivilegeGroupRelPOJO.setPrivilegeGroupId(privilegeGroupId);
        fleaPrivilegeGroupRelPOJO.setRelId(privilegeId);
        fleaPrivilegeGroupRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType()); // 权限组关联权限
        // 【{0}】权限组关联【{1}】权限
        fleaPrivilegeGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000005", new String[]{privilegeGroupName, privilegeName}));
        return fleaPrivilegeGroupRelPOJO;
    }

    /**
     * 新建权限组关联权限POJO对象
     *
     * @param fleaPrivilegeGroup Flea权限组数据
     * @param fleaPrivilege      Flea权限数据
     * @return 权限组关联权限POJO对象
     * @since 2.0.0
     */
    public static FleaPrivilegeGroupRelPOJO newPrivilegeGroupRelPrivilegePOJO(FleaPrivilegeGroup fleaPrivilegeGroup, FleaPrivilege fleaPrivilege) {
        FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO = null;
        // 添加权限组关联权限
        if (ObjectUtils.isNotEmpty(fleaPrivilegeGroup) && ObjectUtils.isNotEmpty(fleaPrivilege)) {
            // 新建权限组关联权限POJO对象
            Long privilegeGroupId = fleaPrivilegeGroup.getPrivilegeGroupId();
            String privilegeGroupName = fleaPrivilegeGroup.getPrivilegeGroupName();
            Long privilegeId = fleaPrivilege.getPrivilegeId();
            String privilegeName = fleaPrivilege.getPrivilegeName();
            fleaPrivilegeGroupRelPOJO = newPrivilegeGroupRelPrivilegePOJO(privilegeGroupId, privilegeGroupName, privilegeId, privilegeName);
        }
        return fleaPrivilegeGroupRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与访问菜单相关】
     *
     * @param menuName 菜单名称
     * @return Flea权限POJO对象【与访问菜单相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForMenu(String menuName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{menuName};
        // 访问《{0}》菜单
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000006", values);
        // 拥有可以访问《{0}》菜单的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000007", values);
        // 【访问《{0}》菜单】权限对应【{0}】菜单，新增菜单时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000008", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
    }

    /**
     * 新建Flea权限关联POJO对象【与访问菜单相关】
     *
     * @param privilegeId 权限编号
     * @param relId       关联编号【这里是菜单编号】
     * @param menuName    菜单名称
     * @return Flea权限关联POJO对象【与访问菜单相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelMenuPOJO(Long privilegeId, Long relId, String menuName) {
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType());
        // 【{0}】菜单绑定【访问《{0}》菜单】权限, 新增菜单时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000009", new String[]{menuName}));
        return fleaPrivilegeRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与执行操作相关】
     *
     * @param operationName 操作名称
     * @return Flea权限POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForOperation(String operationName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{operationName};
        // 执行《{0}》操作
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000010", values);
        // 拥有可以执行《{0}》操作的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000011", values);
        // 【执行《{0}》操作】权限对应【{0}】操作，新增操作时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000012", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
    }

    /**
     * 新建Flea权限关联POJO对象【与执行操作相关】
     *
     * @param privilegeId   权限编号
     * @param relId         关联编号【这里是操作编号】
     * @param operationName 操作名称
     * @return Flea权限关联POJO对象【与执行操作相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelOperationPOJO(Long privilegeId, Long relId, String operationName) {
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_OPERATION.getRelType());
        // 【{0}】操作绑定【执行《{0}》操作】权限，新增操作时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000013", new String[]{operationName}));
        return fleaPrivilegeRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与展示元素相关】
     *
     * @param elementName 元素名称
     * @return Flea权限POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForElement(String elementName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{elementName};
        // 展示《{0}》元素
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000014", values);
        // 拥有可以展示《{0}》元素的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000015", values);
        //【展示《{0}》元素】权限对应【{0}】元素，新增元素时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000016", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
    }

    /**
     * 新建Flea权限关联POJO对象【与展示元素相关】
     *
     * @param privilegeId 权限编号
     * @param relId       关联编号【这里是元素编号】
     * @param elementName 元素名称
     * @return Flea权限关联POJO对象【与展示元素相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelElementPOJO(Long privilegeId, Long relId, String elementName) {
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_ELEMENT.getRelType());
        //【{0}】元素绑定【展示《{0}》元素】权限，新增元素时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000017", new String[]{elementName}));
        return fleaPrivilegeRelPOJO;
    }

    /**
     * 新建Flea权限POJO对象【与调用资源相关】
     *
     * @param resourceName 资源名称
     * @return Flea权限POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegePOJO newFleaPrivilegePOJOForResource(String resourceName, FleaPrivilegeGroup fleaPrivilegeGroup) {
        String[] values = new String[]{resourceName};
        // 调用《{0}》资源
        String privilegeName = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000018", values);
        // 拥有可以调用《{0}》资源的权限
        String privilegeDesc = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000019", values);
        //【调用《{0}》资源】权限对应【{0}】资源，新增资源时自动生成
        String remarks = FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000020", values);
        return newFleaPrivilegePOJO(privilegeName, privilegeDesc, getPrivilegeGroupId(fleaPrivilegeGroup), remarks);
    }

    /**
     * 获取权限组编号
     *
     * @param fleaPrivilegeGroup Flea权限组数据
     * @return 权限组编号
     * @since 2.0.0
     */
    private static Long getPrivilegeGroupId(FleaPrivilegeGroup fleaPrivilegeGroup) {
        if (ObjectUtils.isNotEmpty(fleaPrivilegeGroup))
            return fleaPrivilegeGroup.getPrivilegeGroupId();
        else
            return null;
    }

    /**
     * 新建Flea权限关联POJO对象【与调用资源相关】
     *
     * @param privilegeId  权限编号
     * @param relId        关联编号【这里是资源编号】
     * @param resourceName 资源名称
     * @return Flea权限关联POJO对象【与调用资源相关】
     * @since 2.0.0
     */
    public static FleaPrivilegeRelPOJO newFleaPrivilegeRelResourcePOJO(Long privilegeId, Long relId, String resourceName) {
        FleaPrivilegeRelPOJO fleaPrivilegeRelPOJO = new FleaPrivilegeRelPOJO();
        fleaPrivilegeRelPOJO.setPrivilegeId(privilegeId);
        fleaPrivilegeRelPOJO.setRelId(relId);
        fleaPrivilegeRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_REL_RESOURCE.getRelType());
        //【{0}】资源绑定【调用《{0}》资源】权限，新增资源时自动生成
        fleaPrivilegeRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000021", new String[]{resourceName}));
        return fleaPrivilegeRelPOJO;
    }
}
