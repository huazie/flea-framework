package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupRelSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegeGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.privilege.FleaPrivilegePOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaPrivilegeModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.POJOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Flea权限管理服务层
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Service("fleaPrivilegeModuleSV")
public class FleaPrivilegeModuleSVImpl implements IFleaPrivilegeModuleSV {

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV; // Flea权限组服务

    private IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV; // 权限组关联服务

    @Resource(name = "fleaPrivilegeSV")
    public void setFleaPrivilegeSV(IFleaPrivilegeSV fleaPrivilegeSV) {
        this.fleaPrivilegeSV = fleaPrivilegeSV;
    }

    @Resource(name = "fleaPrivilegeGroupSV")
    public void setFleaPrivilegeGroupSV(IFleaPrivilegeGroupSV fleaPrivilegeGroupSV) {
        this.fleaPrivilegeGroupSV = fleaPrivilegeGroupSV;
    }

    @Resource(name = "fleaPrivilegeGroupRelSV")
    public void setFleaPrivilegeGroupRelSV(IFleaPrivilegeGroupRelSV fleaPrivilegeGroupRelSV) {
        this.fleaPrivilegeGroupRelSV = fleaPrivilegeGroupRelSV;
    }

    @Override
    public Long addFleaPrivilege(FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        return this.fleaPrivilegeSV.savePrivilege(fleaPrivilegePOJO).getPrivilegeId();
    }

    @Override
    public void modifyFleaPrivilege(Long privilegeId, FleaPrivilegePOJO fleaPrivilegePOJO) throws CommonException {
        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 校验Flea权限POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaPrivilegePOJO, FleaPrivilegePOJO.class.getSimpleName());

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));

        // 将Flea权限POJO对象中非空的数据，复制到Flea权限数据中
        POJOUtils.copyNotEmpty(fleaPrivilegePOJO, fleaPrivilege);

        // 更新Flea权限数据
        this.fleaPrivilegeSV.update(fleaPrivilege);
    }

    @Override
    public Long addFleaPrivilegeGroup(FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        return this.fleaPrivilegeGroupSV.savePrivilegeGroup(fleaPrivilegeGroupPOJO).getPrivilegeGroupId();
    }

    @Override
    public void modifyFleaPrivilegeGroup(Long privilegeGroupId, FleaPrivilegeGroupPOJO fleaPrivilegeGroupPOJO) throws CommonException {
        // 校验权限组编号
        FleaAuthCheck.checkPrivilegeGroupId(privilegeGroupId);

        // 校验Flea权限组POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaPrivilegeGroupPOJO, FleaPrivilegeGroupPOJO.class.getSimpleName());

        // 查询在用的权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(privilegeGroupId);
        // 校验Flea权限组是否存在
        FleaAuthCheck.checkFleaPrivilegeGroupExist(fleaPrivilegeGroup, StringUtils.valueOf(privilegeGroupId));

        // 将Flea权限组POJO对象中非空的数据，复制到Flea权限组数据中
        POJOUtils.copyNotEmpty(fleaPrivilegeGroupPOJO, fleaPrivilegeGroup);

        // 更新Flea权限组数据
        this.fleaPrivilegeGroupSV.update(fleaPrivilegeGroup);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void privilegeGroupRelPrivilege(Long privilegeGroupId, Long privilegeId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验权限组编号
        FleaAuthCheck.checkPrivilegeGroupId(privilegeGroupId);

        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 查询在用的权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(privilegeGroupId);
        // 校验Flea权限组是否存在
        FleaAuthCheck.checkFleaPrivilegeGroupExist(fleaPrivilegeGroup, StringUtils.valueOf(privilegeGroupId));
        // 权限组名称
        String privilegeGroupName = fleaPrivilegeGroup.getPrivilegeGroupName();

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));
        // 权限名称
        String privilegeName = fleaPrivilege.getPrivilegeName();

        // 权限组编号不为正数，说明权限第一次被权限组关联
        if (!NumberUtils.isPositiveNumber(fleaPrivilege.getGroupId())) {
            // 更新Flea权限数据中权限组编号
            fleaPrivilege.setGroupId(privilegeGroupId);
            fleaPrivilege.setDoneDate(DateUtils.getCurrentTime());
            this.fleaPrivilegeSV.update(fleaPrivilege);
        }

        // 新建权限组关联权限POJO对象
        FleaPrivilegeGroupRelPOJO privilegeGroupRelPOJO = FleaAuthPOJOUtils.newPrivilegeGroupRelPrivilegePOJO(privilegeGroupId, privilegeGroupName, privilegeId, privilegeName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, privilegeGroupRelPOJO);

        // 保存Flea权限组关联
        this.fleaPrivilegeGroupRelSV.saveFleaPrivilegeGroupRel(privilegeGroupRelPOJO);
    }

}
