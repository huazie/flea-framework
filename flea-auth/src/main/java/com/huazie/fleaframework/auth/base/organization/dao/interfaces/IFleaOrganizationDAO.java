package com.huazie.fleaframework.auth.base.organization.dao.interfaces;

import com.huazie.fleaframework.auth.base.organization.entity.FleaOrganization;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea组织DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaOrganizationDAO extends IAbstractFleaJPADAO<FleaOrganization> {

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
     * 根据组织编号，查询其组织子树（不含自身，含所有层级的下级组织，仅有效数据）
     *
     * <p> 内部基于 parent_id 逐层递归展开 </p>
     *
     * @param orgId 组织编号
     * @return 组织子树数据集合
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaOrganization> querySubTree(Long orgId) throws CommonException;
}
