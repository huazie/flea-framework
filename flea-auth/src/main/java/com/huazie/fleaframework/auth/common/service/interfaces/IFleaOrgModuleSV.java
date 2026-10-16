package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelExtPOJO;
import com.huazie.fleaframework.auth.common.pojo.organization.FleaOrganizationPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;

import java.util.List;

/**
 * Flea组织管理服务接口
 *
 * <p> 组织是用户的归属维度，不做权限授权主体，用户关联组织走 flea_user_org_rel 关系表。 </p>
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaOrgModuleSV {

    /**
     * 添加Flea组织数据
     *
     * <p> 组织编码全局唯一；未指定父组织时挂到根（parentId = -1）下，层级为 1，
     * 否则层级为父组织层级 + 1 </p>
     *
     * @param fleaOrganizationPOJO Flea组织POJO对象
     * @return 组织编号
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    Long addOrganization(FleaOrganizationPOJO fleaOrganizationPOJO) throws CommonException;

    /**
     * 修改Flea组织数据
     *
     * <p> 组织编码与父组织（含层级）不支持变更，防止组织编码被篡改以及组织树成环 </p>
     *
     * @param orgId                组织编号
     * @param fleaOrganizationPOJO Flea组织POJO对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    void modifyOrganization(Long orgId, FleaOrganizationPOJO fleaOrganizationPOJO) throws CommonException;

    /**
     * 根据组织编号，查询有效的组织数据
     *
     * @param orgId 组织编号
     * @return 有效的组织数据，不存在返回 {@code null}
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaOrganization queryValidOrganization(Long orgId) throws CommonException;

    /**
     * 根据组织编码，查询有效的组织数据
     *
     * @param orgCode 组织编码
     * @return 有效的组织数据，不存在返回 {@code null}
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaOrganization queryValidOrganizationByCode(String orgCode) throws CommonException;

    /**
     * 查询全部有效的组织数据（按组织编号升序）
     *
     * @return 有效的组织数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaOrganization> queryAllValidOrganizations() throws CommonException;

    /**
     * 查询已关联指定组织的用户编号集（取自有效的用户组织关联数据）
     *
     * @param orgId 组织编号
     * @return 已关联的用户编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getRelUserIds(Long orgId) throws CommonException;

    /**
     * 用户关联组织
     *
     * <p> 用户与组织的关联唯一，已存在有效关联时幂等跳过 </p>
     *
     * @param userId             用户编号
     * @param orgId              组织编号
     * @param fleaAuthRelExtPOJO 授权关联扩展数据
     * @return true-本次新增关联；false-关联已存在，跳过
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    boolean userRelOrganization(Long userId, Long orgId, FleaAuthRelExtPOJO fleaAuthRelExtPOJO) throws CommonException;

    /**
     * 用户取消关联组织（逻辑删除用户组织关联）
     *
     * @param userId 用户编号
     * @param orgId  组织编号
     * @return 本次逻辑删除的关联条数
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    int cancelUserOrganization(Long userId, Long orgId) throws CommonException;
}
