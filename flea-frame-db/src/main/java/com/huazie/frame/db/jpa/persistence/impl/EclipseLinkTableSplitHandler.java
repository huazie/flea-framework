package com.huazie.frame.db.jpa.persistence.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.table.pojo.SplitTable;
import com.huazie.frame.db.common.util.EntityUtils;
import com.huazie.frame.db.jpa.persistence.IFleaJPATableSplitHandler;
import org.eclipse.persistence.internal.jpa.metamodel.EntityTypeImpl;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.internal.sessions.RepeatableWriteUnitOfWork;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Set;

/**
 * <p> Eclipse Link 分表处理者实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class EclipseLinkTableSplitHandler implements IFleaJPATableSplitHandler {

    @Override
    public void handle(CriteriaQuery criteriaQuery, Object entity) throws CommonException {

        if (ObjectUtils.isEmpty(criteriaQuery) || ObjectUtils.isEmpty(entity)) {
            return;
        }

        // 获取分表信息（包括主表名 和 分表名 【如果存在分表返回】）
        SplitTable splitTable = EntityUtils.getSplitTable(entity);

        // 存在分表，需要查询指定分表
        if (StringUtils.isNotBlank(splitTable.getSplitTableName())) {
            Set<Root<?>> roots = criteriaQuery.getRoots();
            if (CollectionUtils.isNotEmpty(roots)) {
                // 重新设置 查询的分表表名
                ((EntityTypeImpl<?>) roots.toArray(new Root<?>[0])[0].getModel()).getDescriptor().setTableName(splitTable.getSplitTableName());
            }
        }
    }

    @Override
    public void handle(EntityManager entityManager, Object entity, boolean isRead) throws CommonException {

        if (ObjectUtils.isEmpty(entityManager) || ObjectUtils.isEmpty(entity)) {
            return;
        }

        // 获取分表信息（包括主表名 和 分表名 【如果存在分表返回】）
        SplitTable splitTable = EntityUtils.getSplitTable(entity);

        // 存在分表，则需要操作具体分表
        if (StringUtils.isNotBlank(splitTable.getSplitTableName())) {
            // 获取可用的数据库会话对象
            AbstractSession session;
            if (isRead) {
                session = entityManager.unwrap(AbstractSession.class);
            } else {
                session = entityManager.unwrap(RepeatableWriteUnitOfWork.class);
            }
            // 重新设置 查询的分表表名
            session.getDescriptor(entity.getClass()).setTableName(splitTable.getSplitTableName());
        }
    }
}
