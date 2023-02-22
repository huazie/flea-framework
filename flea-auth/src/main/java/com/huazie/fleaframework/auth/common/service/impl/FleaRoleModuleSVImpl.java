package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeGroupSV;
import com.huazie.fleaframework.auth.base.privilege.service.interfaces.IFleaPrivilegeSV;
import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupRelSV;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleGroupSV;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleRelSV;
import com.huazie.fleaframework.auth.base.role.service.interfaces.IFleaRoleSV;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleGroupRelPOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRolePOJO;
import com.huazie.fleaframework.auth.common.pojo.role.FleaRoleRelPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaRoleModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthPOJOUtils;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.POJOUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.jpa.transaction.FleaTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Flea角色管理服务层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaRoleModuleSV")
public class FleaRoleModuleSVImpl implements IFleaRoleModuleSV {

    private IFleaRoleSV fleaRoleSV; // Flea角色服务

    private IFleaRoleRelSV fleaRoleRelSV; // Flea角色关联服务

    private IFleaRoleGroupSV fleaRoleGroupSV; // Flea角色组服务

    private IFleaRoleGroupRelSV fleaRoleGroupRelSV; // Flea角色组关联服务

    private IFleaPrivilegeSV fleaPrivilegeSV; // Flea权限服务

    private IFleaPrivilegeGroupSV fleaPrivilegeGroupSV; // Flea权限组服务

    @Resource(name = "fleaRoleSV")
    public void setFleaRoleSV(IFleaRoleSV fleaRoleSV) {
        this.fleaRoleSV = fleaRoleSV;
    }

    @Resource(name = "fleaRoleRelSV")
    public void setFleaRoleRelSV(IFleaRoleRelSV fleaRoleRelSV) {
        this.fleaRoleRelSV = fleaRoleRelSV;
    }

    @Resource(name = "fleaRoleGroupSV")
    public void setFleaRoleGroupSV(IFleaRoleGroupSV fleaRoleGroupSV) {
        this.fleaRoleGroupSV = fleaRoleGroupSV;
    }

    @Resource(name = "fleaRoleGroupRelSV")
    public void setFleaRoleGroupRelSV(IFleaRoleGroupRelSV fleaRoleGroupRelSV) {
        this.fleaRoleGroupRelSV = fleaRoleGroupRelSV;
    }

    @Resource(name = "fleaPrivilegeSV")
    public void setFleaPrivilegeSV(IFleaPrivilegeSV fleaPrivilegeSV) {
        this.fleaPrivilegeSV = fleaPrivilegeSV;
    }

    @Resource(name = "fleaPrivilegeGroupSV")
    public void setFleaPrivilegeGroupSV(IFleaPrivilegeGroupSV fleaPrivilegeGroupSV) {
        this.fleaPrivilegeGroupSV = fleaPrivilegeGroupSV;
    }

    @Override
    public Long addFleaRole(FleaRolePOJO fleaRolePOJO) throws CommonException {
        return this.fleaRoleSV.saveRole(fleaRolePOJO).getRoleId();
    }

    @Override
    public void modifyFleaRole(Long roleId, FleaRolePOJO fleaRolePOJO) throws CommonException {
        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 校验Flea角色组POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaRolePOJO, FleaRolePOJO.class.getSimpleName());

        // 查询在用的角色数据
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));

        // 将Flea角色POJO对象中非空的数据，复制到Flea角色数据中
        POJOUtils.copyNotEmpty(fleaRolePOJO, fleaRole);

        // 更新Flea角色数据
        this.fleaRoleSV.update(fleaRole);
    }

    @Override
    public Long addFleaRoleGroup(FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException {
        return this.fleaRoleGroupSV.saveRoleGroup(fleaRoleGroupPOJO).getRoleGroupId();
    }

    @Override
    public void modifyFleaRoleGroup(Long roleGroupId, FleaRoleGroupPOJO fleaRoleGroupPOJO) throws CommonException {
        // 校验角色组编号
        FleaAuthCheck.checkRoleGroupId(roleGroupId);

        // 校验Flea角色组POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaRoleGroupPOJO, FleaRoleGroupPOJO.class.getSimpleName());

        // 查询在用的角色组数据
        FleaRoleGroup fleaRoleGroup = this.fleaRoleGroupSV.queryRoleGroupInUse(roleGroupId);
        // 校验Flea角色组是否存在
        FleaAuthCheck.checkFleaRoleGroupExist(fleaRoleGroup, StringUtils.valueOf(roleGroupId));

        // 将Flea角色组POJO对象中非空的数据，复制到Flea角色组数据中
        POJOUtils.copyNotEmpty(fleaRoleGroupPOJO, fleaRoleGroup);

        // 更新Flea角色组数据
        this.fleaRoleGroupSV.update(fleaRoleGroup);
    }

    @Override
    public void roleRelRole(Long roleId, Long relRoleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 校验关联角色编号
        FleaAuthCheck.checkRelRoleId(relRoleId);

        // 查询在用的角色数据
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));
        // 角色名称
        String roleName = fleaRole.getRoleName();

        // 查询在用的关联角色数据
        FleaRole fleaRelRole = this.fleaRoleSV.queryRoleInUse(relRoleId);
        // 校验关联的Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRelRole, StringUtils.valueOf(relRoleId));
        // 关联角色名称
        String relRoleName = fleaRole.getRoleName();

        // 新建角色关联角色POJO对象
        FleaRoleRelPOJO roleRelRolePOJO = FleaAuthPOJOUtils.newRoleRelRolePOJO(roleId, roleName, relRoleId, relRoleName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, roleRelRolePOJO);

        // 保存角色关联角色
        this.fleaRoleRelSV.saveRoleRel(roleRelRolePOJO);
    }

    @Override
    public void roleRelPrivilege(Long roleId, Long privilegeId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 校验权限编号
        FleaAuthCheck.checkPrivilegeId(privilegeId);

        // 查询在用的角色数据
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));
        // 角色名称
        String roleName = fleaRole.getRoleName();

        // 查询在用的权限数据
        FleaPrivilege fleaPrivilege = this.fleaPrivilegeSV.queryPrivilegeInUse(privilegeId);
        // 校验Flea权限是否存在
        FleaAuthCheck.checkFleaPrivilegeExist(fleaPrivilege, StringUtils.valueOf(privilegeId));
        // 权限名称
        String privilegeName = fleaPrivilege.getPrivilegeName();

        // 新建角色关联权限POJO对象
        FleaRoleRelPOJO roleRelPrivilegePOJO = FleaAuthPOJOUtils.newRoleRelPrivilegePOJO(roleId, roleName, privilegeId, privilegeName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, roleRelPrivilegePOJO);

        // 保存角色关联权限
        this.fleaRoleRelSV.saveRoleRel(roleRelPrivilegePOJO);
    }

    @Override
    public void roleRelPrivilegeGroup(Long roleId, Long privilegeGroupId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 校验权限组编号
        FleaAuthCheck.checkPrivilegeGroupId(privilegeGroupId);

        // 查询在用的角色数据
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));
        // 角色名称
        String roleName = fleaRole.getRoleName();

        // 查询在用的权限组数据
        FleaPrivilegeGroup fleaPrivilegeGroup = this.fleaPrivilegeGroupSV.queryPrivilegeGroupInUse(privilegeGroupId);
        // 校验Flea权限组是否存在
        FleaAuthCheck.checkFleaPrivilegeGroupExist(fleaPrivilegeGroup, StringUtils.valueOf(privilegeGroupId));
        // 权限组名称
        String privilegeGroupName = fleaPrivilegeGroup.getPrivilegeGroupName();

        // 新建角色关联权限组POJO对象
        FleaRoleRelPOJO roleRelPrivilegeGroupPOJO = FleaAuthPOJOUtils.newRoleRelPrivilegeGroupPOJO(roleId, roleName, privilegeGroupId, privilegeGroupName);

        // 复制授权关联扩展数据
        POJOUtils.copyAll(fleaAuthRelExtPOJO, roleRelPrivilegeGroupPOJO);

        // 保存角色关联权限组
        this.fleaRoleRelSV.saveRoleRel(roleRelPrivilegeGroupPOJO);
    }

    @Override
    @FleaTransactional(value = "fleaAuthTransactionManager", unitName = "fleaauth")
    public void roleGroupRelRole(Long roleGroupId, Long roleId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验角色组编号
        FleaAuthCheck.checkRoleGroupId(roleGroupId);

        // 校验角色编号
        FleaAuthCheck.checkRoleId(roleId);

        // 查询在用的角色组数据
        FleaRoleGroup fleaRoleGroup = this.fleaRoleGroupSV.queryRoleGroupInUse(roleGroupId);
        // 校验Flea角色组是否存在
        FleaAuthCheck.checkFleaRoleGroupExist(fleaRoleGroup, StringUtils.valueOf(roleGroupId));
        // 角色组名称
        String roleGroupName = fleaRoleGroup.getRoleGroupName();

        // 查询在用的角色信息
        FleaRole fleaRole = this.fleaRoleSV.queryRoleInUse(roleId);
        // 校验Flea角色是否存在
        FleaAuthCheck.checkFleaRoleExist(fleaRole, StringUtils.valueOf(roleId));
        // 角色名称
        String roleName = fleaRole.getRoleName();

        // 角色组编号不为正数，说明角色第一次被角色组关联
        if (!NumberUtils.isPositiveNumber(fleaRole.getGroupId())) {
            // 更新Flea角色数据中的角色组编号
            fleaRole.setGroupId(roleGroupId);
            fleaRole.setDoneDate(DateUtils.getCurrentTime());
            this.fleaRoleSV.update(fleaRole);
        }

        // 新建角色组关联角色POJO对象
        FleaRoleGroupRelPOJO roleGroupRelRolePOJO = FleaAuthPOJOUtils.newRoleGroupRelRolePOJO(roleGroupId, roleGroupName, roleId, roleName);

        // 复制授权关联扩展信息
        POJOUtils.copyAll(fleaAuthRelExtPOJO, roleGroupRelRolePOJO);

        this.fleaRoleGroupRelSV.saveRoleGroupRel(roleGroupRelRolePOJO);
    }
}
