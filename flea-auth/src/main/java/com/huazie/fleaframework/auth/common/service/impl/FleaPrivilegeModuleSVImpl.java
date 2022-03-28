package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaPrivilegeModuleSV;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Flea权限管理服务层
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeModuleSV")
public class FleaPrivilegeModuleSVImpl implements IFleaPrivilegeModuleSV {

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV; // Flea权限组服务

    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV; // 权限组关联服务

    @Autowired
    @Qualifier("fleaPrivilegeSV")
    public void setFleaPrivilegeSV(IFleaPrivilegeSV fleaPrivilegeSV) {
        this.fleaPrivilegeSV = fleaPrivilegeSV;
    }

    @Autowired
    @Qualifier("fleaPrivilegeGroupSV")
    public void setFleaPrivilegeGroupSV(IFleaPrivilegeGroupSV fleaPrivilegeGroupSV) {
        this.fleaPrivilegeGroupSV = fleaPrivilegeGroupSV;
    }

    @Autowired
    @Qualifier("fleaPrivilegeGroupRelSV")
    public void setFleaPrivilegeGroupRelSV(IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV) {
        this.fleaPrivilegeGroupRelSV = fleaPrivilegeGroupRelSV;
    }

    @Override
    @Transactional(value = "fleaAuthTransactionManager", rollbackFor = Exception.class)
    public void addPrivilegeGroupRel(FleaPrivilegeGroupRelPOJO fleaPrivilegeGroupRelPOJO) throws CommonException {
        if (ObjectUtils.isNotEmpty(fleaPrivilegeGroupRelPOJO)) {
            // 权限组名称为空时，根据权限组编号查询获取
            Long privilegeGroupId = fleaPrivilegeGroupRelPOJO.getPrivilegeGroupId();
            String privilegeGroupName = fleaPrivilegeGroupRelPOJO.getPrivilegeGroupName();
            if (StringUtils.isEmpty(privilegeGroupName) && NumberUtils.isPositiveNumber(privilegeGroupId)) {
                FleaPrivilegeGroup fleaPrivilegeGroup = fleaPrivilegeGroupSV.query(privilegeGroupId);
                if (ObjectUtils.isNotEmpty(fleaPrivilegeGroup)) {
                    privilegeGroupName = fleaPrivilegeGroup.getPrivilegeGroupName();
                    fleaPrivilegeGroupRelPOJO.setPrivilegeGroupName(privilegeGroupName);
                }
            }
            // 权限名称为空时，根据权限编号查询获取
            Long privilegeId = fleaPrivilegeGroupRelPOJO.getRelId();
            String privilegeName = fleaPrivilegeGroupRelPOJO.getRelName();
            if (NumberUtils.isPositiveNumber(privilegeId)) {
                FleaPrivilege fleaPrivilege = fleaPrivilegeSV.query(privilegeId);
                if (ObjectUtils.isNotEmpty(fleaPrivilege)) {
                    privilegeName = fleaPrivilege.getPrivilegeName();
                    fleaPrivilegeGroupRelPOJO.setRelName(privilegeName);

                    // 更新Flea权限中权限组编号
                    fleaPrivilege.setGroupId(privilegeGroupId);
                    fleaPrivilege.setDoneDate(DateUtils.getCurrentTime());
                    fleaPrivilegeSV.update(fleaPrivilege);
                }
            }
            // 权限组关联权限
            fleaPrivilegeGroupRelPOJO.setRelType(AuthRelTypeEnum.PRIVILEGE_GROUP_REL_PRIVILEGE.getRelType());
            // 添加备注信息
            // 【{0}】权限组关联【{1}】权限
            fleaPrivilegeGroupRelPOJO.setRemarks(FleaI18nHelper.i18nForAuth("AUTH-PRIVILEGE0000000005", new String[]{privilegeGroupName, privilegeName}));

        }
        // 保存Flea权限组关联
        fleaPrivilegeGroupRelSV.saveFleaPrivilegeGroupRel(fleaPrivilegeGroupRelPOJO);
    }

}
