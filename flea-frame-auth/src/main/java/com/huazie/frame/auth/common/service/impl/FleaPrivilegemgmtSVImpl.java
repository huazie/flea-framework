package com.huazie.frame.auth.common.service.impl;

import com.huazie.frame.auth.common.AuthRelTypeEnum;
import com.huazie.frame.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.i18n.FleaI18nResEnum;

/**
 * <p> Flea权限管理服务层 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPrivilegemgmtSVImpl {

    /**
     * <p> 新建Flea权限组关联POJO对象 </p>
     *
     * @param privilegeGroupId 权限组编号
     * @param privilegeGroupName 权限组名称
     * @param relId            关联编号【这里是权限编号】
     * @param relName 关联名称【这里是权限名称】
     * @return Flea权限组关联POJO对象实例
     */
    private FleaPrivilegeGroupRelPOJO newFleaPrivilegeGroupRelPOJO(Long privilegeGroupId, String privilegeGroupName, Long relId, String relName) {
        FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO = new FleaPrivilegeGroupRelPOJO();
        fleaPrivilegeGroupRelPOJO.setPrivilegeGroupId(privilegeGroupId);
        fleaPrivilegeGroupRelPOJO.setRelId(relId);
        fleaPrivilegeGroupRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType());
        // 【{0}】权限组关联【{1}】权限
        fleaPrivilegeGroupRelPOJO.setRemarks(FleaI18nHelper.i18n("AUTH-PRIVILEGE0000000005", new String[] {privilegeGroupName, relName}, FleaI18nResEnum.AUTH.getResName()));
        return fleaPrivilegeGroupRelPOJO;
    }

}
