package com.huazie.fleaframework.auth.common.service.impl;

import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.auth.base.organization.service.interfaces.IFleaOrganizationSV;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserOrgRel;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserOrgRelSV;
import com.huazie.fleaframework.auth.base.user.service.interfaces.IFleaUserSV;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.exceptions.FleaAuthCommonException;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.organization.FleaOrganizationPOJO;
import com.huazie.fleaframework.auth.common.service.interfaces.IFleaOrgModuleSV;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Flea组织管理服务实现类
 *
 * <p> 组织是用户的归属维度，不做权限授权主体，用户关联组织走 flea_user_org_rel 关系表。 </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Service("fleaOrgModuleSV")
public class FleaOrgModuleSVImpl implements IFleaOrgModuleSV {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaOrgModuleSVImpl.class);

    /**
     * 根组织编号（无父组织）
     */
    private static final long ROOT_ORG_ID = -1L;

    private IFleaOrganizationSV fleaOrganizationSV; // Flea组织服务

    private IFleaUserOrgRelSV fleaUserOrgRelSV; // Flea用户组织关联服务

    private IFleaUserSV fleaUserSV; // Flea用户服务

    @Resource(name = "fleaOrganizationSV")
    public void setFleaOrganizationSV(IFleaOrganizationSV fleaOrganizationSV) {
        this.fleaOrganizationSV = fleaOrganizationSV;
    }

    @Resource(name = "fleaUserOrgRelSV")
    public void setFleaUserOrgRelSV(IFleaUserOrgRelSV fleaUserOrgRelSV) {
        this.fleaUserOrgRelSV = fleaUserOrgRelSV;
    }

    @Resource(name = "fleaUserSV")
    public void setFleaUserSV(IFleaUserSV fleaUserSV) {
        this.fleaUserSV = fleaUserSV;
    }

    @Override
    public Long addOrganization(FleaOrganizationPOJO fleaOrganizationPOJO) throws CommonException {
        // 校验Flea组织POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaOrganizationPOJO, FleaOrganizationPOJO.class.getSimpleName());

        // 校验组织编码不能为空
        FleaAuthCheck.checkBlank(fleaOrganizationPOJO.getOrgCode(), FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_CODE);
        // 校验组织名称不能为空
        FleaAuthCheck.checkBlank(fleaOrganizationPOJO.getOrgName(), FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_NAME);
        // 校验组织类型不能为空
        FleaAuthCheck.checkEmpty(fleaOrganizationPOJO.getOrgType(), FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_TYPE);

        // 组织编码全局唯一，已被其他有效组织使用时报错
        FleaOrganization existedOrg = this.queryValidOrganizationByCode(fleaOrganizationPOJO.getOrgCode());
        if (ObjectUtils.isNotEmpty(existedOrg)) {
            // ERROR-AUTH-COMMON0000000026 组织编码【org_code = {0}】已被其他组织使用！
            throw new FleaAuthCommonException("ERROR-AUTH-COMMON0000000026", fleaOrganizationPOJO.getOrgCode());
        }

        FleaOrganization fleaOrganization = new FleaOrganization();
        fleaOrganization.setOrgCode(fleaOrganizationPOJO.getOrgCode());
        fleaOrganization.setOrgName(fleaOrganizationPOJO.getOrgName());
        fleaOrganization.setOrgDesc(fleaOrganizationPOJO.getOrgDesc());
        fleaOrganization.setOrgType(fleaOrganizationPOJO.getOrgType());
        fleaOrganization.setRemarks(fleaOrganizationPOJO.getRemarks());
        // 新组织默认在用状态，补齐无参构造器缺失的审计字段
        fleaOrganization.setOrgState(EntityStateEnum.IN_USE.getState());
        fleaOrganization.setCreateDate(DateUtils.getCurrentTime());

        Long parentId = fleaOrganizationPOJO.getParentId();
        if (ObjectUtils.isNotEmpty(parentId) && parentId > 0) {
            // 指定父组织时，校验父组织存在，层级为父组织层级 + 1
            FleaOrganization parentOrg = fleaOrganizationSV.queryValidOrganization(parentId);
            FleaAuthCheck.checkFleaOrganizationExist(parentOrg, StringUtils.valueOf(parentId));
            fleaOrganization.setParentId(parentId);
            fleaOrganization.setOrgLevel(parentOrg.getOrgLevel() + 1);
        } else {
            // 未指定父组织时挂到根下
            fleaOrganization.setParentId(ROOT_ORG_ID);
            fleaOrganization.setOrgLevel(1);
        }

        // 保存组织数据
        fleaOrganizationSV.save(fleaOrganization);

        // 组织编码唯一，保存后按编码取回生成的组织编号
        FleaOrganization savedOrg = this.queryValidOrganizationByCode(fleaOrganizationPOJO.getOrgCode());
        return savedOrg == null ? null : savedOrg.getOrgId();
    }

    @Override
    public void modifyOrganization(Long orgId, FleaOrganizationPOJO fleaOrganizationPOJO) throws CommonException {
        // 校验组织编号
        FleaAuthCheck.checkOrgId(orgId);

        // 校验Flea组织POJO对象不能为空
        FleaAuthCheck.checkEmpty(fleaOrganizationPOJO, FleaOrganizationPOJO.class.getSimpleName());

        // 校验组织名称不能为空
        FleaAuthCheck.checkBlank(fleaOrganizationPOJO.getOrgName(), FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_NAME);
        // 校验组织类型不能为空
        FleaAuthCheck.checkEmpty(fleaOrganizationPOJO.getOrgType(), FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_TYPE);

        // 查询在用的组织数据
        FleaOrganization fleaOrganization = fleaOrganizationSV.queryValidOrganization(orgId);
        // 校验Flea组织是否存在
        FleaAuthCheck.checkFleaOrganizationExist(fleaOrganization, StringUtils.valueOf(orgId));

        // 处理父组织变更（含层级重算）；组织编码不支持变更
        Long targetParentId = fleaOrganizationPOJO.getParentId();
        if (ObjectUtils.isNotEmpty(targetParentId) && !targetParentId.equals(fleaOrganization.getParentId())) {

            int newLevel;
            if (targetParentId > 0) {
                // 指定新父组织时，校验其存在
                FleaOrganization parentOrg = fleaOrganizationSV.queryValidOrganization(targetParentId);
                FleaAuthCheck.checkFleaOrganizationExist(parentOrg, StringUtils.valueOf(targetParentId));

                // 防成环：新父组织沿父链向上回溯，不允许出现自身（新父为自身或自身的子组织均会成环）
                Long cursorId = targetParentId;
                while (cursorId != null && cursorId > 0) {
                    if (cursorId.equals(orgId)) {
                        // ERROR-AUTH-COMMON0000000027 父组织不能是自身或自身的子组织！
                        throw new FleaAuthCommonException("ERROR-AUTH-COMMON0000000027");
                    }
                    FleaOrganization cursorOrg = fleaOrganizationSV.queryValidOrganization(cursorId);
                    if (ObjectUtils.isEmpty(cursorOrg)) {
                        break;
                    }
                    cursorId = cursorOrg.getParentId();
                }

                fleaOrganization.setParentId(targetParentId);
                newLevel = parentOrg.getOrgLevel() + 1;
            } else {
                // 未指定有效父组织时挂到根下
                fleaOrganization.setParentId(ROOT_ORG_ID);
                newLevel = 1;
            }

            // 层级差值非零时，同步重算自身及整棵子树的层级
            int levelDelta = newLevel - fleaOrganization.getOrgLevel();
            fleaOrganization.setOrgLevel(newLevel);

            if (levelDelta != 0) {
                List<FleaOrganization> subTreeList = fleaOrganizationSV.querySubTree(orgId);
                if (CollectionUtils.isNotEmpty(subTreeList)) {
                    for (FleaOrganization subOrg : subTreeList) {
                        if (ObjectUtils.isEmpty(subOrg)) {
                            continue;
                        }
                        subOrg.setOrgLevel(subOrg.getOrgLevel() + levelDelta);
                        subOrg.setDoneDate(DateUtils.getCurrentTime());
                        fleaOrganizationSV.update(subOrg);
                    }
                }
            }
        }

        // 更新可编辑字段
        fleaOrganization.setOrgName(fleaOrganizationPOJO.getOrgName());
        fleaOrganization.setOrgDesc(fleaOrganizationPOJO.getOrgDesc());
        fleaOrganization.setOrgType(fleaOrganizationPOJO.getOrgType());
        fleaOrganization.setRemarks(fleaOrganizationPOJO.getRemarks());
        fleaOrganization.setDoneDate(DateUtils.getCurrentTime());

        // 更新组织数据
        fleaOrganizationSV.update(fleaOrganization);
    }

    @Override
    public FleaOrganization queryValidOrganization(Long orgId) throws CommonException {
        // 校验组织编号
        FleaAuthCheck.checkOrgId(orgId);
        return fleaOrganizationSV.queryValidOrganization(orgId);
    }

    @Override
    public FleaOrganization queryValidOrganizationByCode(String orgCode) throws CommonException {
        // 校验组织编码不能为空
        FleaAuthCheck.checkBlank(orgCode, FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_CODE);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_CODE, orgCode);

        List<FleaOrganization> orgList = fleaOrganizationSV.query(queryMap);
        if (CollectionUtils.isEmpty(orgList)) {
            return null;
        }

        for (FleaOrganization fleaOrganization : orgList) {
            if (ObjectUtils.isNotEmpty(fleaOrganization) && fleaOrganization.getOrgState() != null
                    && fleaOrganization.getOrgState() == EntityStateEnum.IN_USE.getState()) {
                return fleaOrganization;
            }
        }

        return null;
    }

    @Override
    public List<FleaOrganization> queryAllValidOrganizations() throws CommonException {

        List<FleaOrganization> validOrgList = new ArrayList<FleaOrganization>();

        List<FleaOrganization> orgList = fleaOrganizationSV.queryAll(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_ID, "asc");
        if (CollectionUtils.isEmpty(orgList)) {
            return validOrgList;
        }

        for (FleaOrganization fleaOrganization : orgList) {
            if (ObjectUtils.isNotEmpty(fleaOrganization) && fleaOrganization.getOrgState() != null
                    && fleaOrganization.getOrgState() == EntityStateEnum.IN_USE.getState()) {
                validOrgList.add(fleaOrganization);
            }
        }

        return validOrgList;
    }

    @Override
    public List<Long> getRelUserIds(Long orgId) throws CommonException {
        // 校验组织编号
        FleaAuthCheck.checkOrgId(orgId);

        List<Long> userIdList = new ArrayList<Long>();

        List<FleaUserOrgRel> userOrgRelList = fleaUserOrgRelSV.getUserOrgRelList(null, orgId);
        if (CollectionUtils.isEmpty(userOrgRelList)) {
            return userIdList;
        }

        for (FleaUserOrgRel fleaUserOrgRel : userOrgRelList) {
            if (ObjectUtils.isNotEmpty(fleaUserOrgRel) && fleaUserOrgRel.getUserId() != null) {
                userIdList.add(fleaUserOrgRel.getUserId());
            }
        }

        return userIdList;
    }

    @Override
    public boolean userRelOrganization(Long userId, Long orgId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException {
        // 校验用户编号
        FleaAuthCheck.checkUserId(userId);

        // 校验组织编号
        FleaAuthCheck.checkOrgId(orgId);

        // 查询有效的用户信息
        FleaUser fleaUser = fleaUserSV.queryValidUser(userId);
        // 校验Flea用户是否存在
        FleaAuthCheck.checkFleaUserExist(fleaUser, StringUtils.valueOf(userId));

        // 查询在用的组织数据
        FleaOrganization fleaOrganization = fleaOrganizationSV.queryValidOrganization(orgId);
        // 校验Flea组织是否存在
        FleaAuthCheck.checkFleaOrganizationExist(fleaOrganization, StringUtils.valueOf(orgId));

        // 用户与组织的关联唯一（UNIQUE_USER_ORG 不含关联状态），需区分三种情况：
        // 1. 已存在有效关联 → 幂等跳过；2. 存在已逻辑删除的关联 → 复活；
        // 3. 无任何关联 → 新建。直接插新行会撞唯一键。
        Map<String, Object> relQueryMap = new HashMap<String, Object>();
        relQueryMap.put(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId);
        relQueryMap.put(FleaAuthEntityConstants.OrganizationEntityConstants.E_ORG_ID, orgId);

        List<FleaUserOrgRel> allRelList = fleaUserOrgRelSV.query(relQueryMap);
        if (CollectionUtils.isNotEmpty(allRelList)) {
            for (FleaUserOrgRel fleaUserOrgRel : allRelList) {
                if (ObjectUtils.isEmpty(fleaUserOrgRel)) {
                    continue;
                }
                if (fleaUserOrgRel.getRelState() != null
                        && fleaUserOrgRel.getRelState() == EntityStateEnum.IN_USE.getState()) {
                    return false;
                }
            }
            for (FleaUserOrgRel fleaUserOrgRel : allRelList) {
                if (ObjectUtils.isEmpty(fleaUserOrgRel)) {
                    continue;
                }
                // 复活已逻辑删除的关联
                fleaUserOrgRel.setRelState(EntityStateEnum.IN_USE.getState());
                fleaUserOrgRel.setDoneDate(null);
                fleaUserOrgRelSV.update(fleaUserOrgRel);
                return true;
            }
        }

        // 关联备注：优先取授权关联扩展数据中的备注，未提供时使用默认备注
        String remarks = fleaAuthRelExtPOJO == null ? null : fleaAuthRelExtPOJO.getRemarks();
        if (StringUtils.isBlank(remarks)) {
            remarks = "【" + fleaOrganization.getOrgName() + "】组织关联用户，用户关联组织时自动生成";
        }

        // 保存用户组织关联（四参构造器自动补齐关联状态等审计字段）
        fleaUserOrgRelSV.save(new FleaUserOrgRel(userId, orgId, 0, remarks));

        return true;
    }

    @Override
    public int cancelUserOrganization(Long userId, Long orgId) throws CommonException {
        // 校验用户编号
        FleaAuthCheck.checkUserId(userId);

        // 校验组织编号
        FleaAuthCheck.checkOrgId(orgId);

        int count = 0;

        List<FleaUserOrgRel> userOrgRelList = fleaUserOrgRelSV.getUserOrgRelList(userId, orgId);
        if (CollectionUtils.isEmpty(userOrgRelList)) {
            return count;
        }

        for (FleaUserOrgRel fleaUserOrgRel : userOrgRelList) {
            if (ObjectUtils.isEmpty(fleaUserOrgRel)) {
                continue;
            }
            // 逻辑删除用户组织关联
            fleaUserOrgRel.setRelState(EntityStateEnum.BE_DELETED.getState());
            fleaUserOrgRel.setDoneDate(DateUtils.getCurrentTime());
            fleaUserOrgRelSV.update(fleaUserOrgRel);
            count++;
        }

        return count;
    }
}
