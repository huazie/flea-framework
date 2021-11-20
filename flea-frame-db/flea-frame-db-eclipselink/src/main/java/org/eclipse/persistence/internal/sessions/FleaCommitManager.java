package org.eclipse.persistence.internal.sessions;

import com.huazie.frame.db.eclipselink.util.EclipseLinkUtils;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.queries.DeleteObjectQuery;
import org.eclipse.persistence.queries.InsertObjectQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Flea 提交管理器，用于维护提交堆栈并解析循环引用
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaCommitManager extends CommitManager {

    public FleaCommitManager(AbstractSession session) {
        super(session);
    }

    /**
     * 删除具有匹配类的所有对象。
     *
     * @param theClass 实体类Class对象
     * @param objects  待删除实体列表
     * @param session  数据库会话
     * @since 1.2.0
     */
    @Override
    public void deleteAllObjects(Class theClass, List objects, AbstractSession session) {
        ClassDescriptor descriptor = getDescriptor(theClass, objects, session);

        if (((UnitOfWorkImpl) session).shouldOrderUpdates()) {
            objects = sort(theClass, objects, descriptor);
        }

        int size = objects.size();
        for (int index = 0; index < size; index++) {
            Object objectToDelete = objects.get(index);
            if (objectToDelete.getClass() == theClass) {
                // PERF: Get the descriptor query, to avoid extra query creation.
                DeleteObjectQuery deleteQuery = descriptor.getQueryManager().getDeleteQuery();
                if (deleteQuery == null) {
                    deleteQuery = new DeleteObjectQuery();
                    deleteQuery.setDescriptor(descriptor);
                } else {
                    // Ensure original query has been prepared.
                    deleteQuery.checkPrepare(session, deleteQuery.getTranslationRow());
                    deleteQuery = (DeleteObjectQuery) deleteQuery.clone();
                }
                deleteQuery.setIsExecutionClone(true);
                deleteQuery.setObject(objectToDelete);
                session.executeQuery(deleteQuery);
            }
        }
    }

    /**
     * 获取实体类的持久化信息描述符，支持分表场景
     *
     * @param theClass 实体类Class对象
     * @param objects  待删除实体列表
     * @param session  数据库会话
     * @since 1.2.0
     */
    private ClassDescriptor getDescriptor(Class theClass, List objects, AbstractSession session) {
        ClassDescriptor descriptor = session.getDescriptor(theClass);
        int size = objects.size();
        for (int index = 0; index < size; index++) {
            Object objectToDelete = objects.get(index);
            if (objectToDelete.getClass() == theClass) {
                // 获取分表对应的实体类的持久化信息描述符
                descriptor = EclipseLinkUtils.getSplitDescriptor(descriptor, session, objectToDelete);
                break;
            }
        }
        return descriptor;
    }

    /**
     * 根据实体主键对实体进行升序排序
     *
     * @param theClass   实体类Class对象
     * @param objects    待删除实体列表
     * @param descriptor 实体类的持久化信息描述符
     * @since 1.2.0
     */
    private List sort(Class theClass, List objects, ClassDescriptor descriptor) {
        int size = objects.size();
        TreeMap<Object, Object> sortedObjects = new TreeMap<>();
        for (int index = 0; index < size; index++) {
            Object objectToDelete = objects.get(index);
            if (objectToDelete.getClass() == theClass) {
                sortedObjects.put(descriptor.getObjectBuilder().extractPrimaryKeyFromObject(objectToDelete, session), objectToDelete);
            }
        }
        return new ArrayList<>(sortedObjects.values());
    }

    /**
     * 提交变更集中该类类型的所有对象。 这允许以最佳方式处理类的顺序
     *
     * @param uowChangeSet 工作单元更改集
     * @param theClass     实体类Class对象
     * @since 1.2.0
     */
    @Override
    protected void commitNewObjectsForClassWithChangeSet(UnitOfWorkChangeSet uowChangeSet, Class theClass) {
        Map<ObjectChangeSet, ObjectChangeSet> newObjectChangesList = uowChangeSet.getNewObjectChangeSets().get(theClass);
        if (newObjectChangesList != null) { // may be no changes for that class type.
            AbstractSession session = getSession();
            ClassDescriptor descriptor = null;
            List<ObjectChangeSet> newChangeSets = new ArrayList<>(newObjectChangesList.values());
            int size = newChangeSets.size();
            for (int index = 0; index < size; index++) {
                ObjectChangeSet changeSetToWrite = newChangeSets.get(index);
                Object objectToWrite = changeSetToWrite.getUnitOfWorkClone();
                if (!isProcessedCommit(objectToWrite)) {
                    if (descriptor == null) {
                        descriptor = session.getDescriptor(objectToWrite);
                    }
                    // PERF: Get the descriptor query, to avoid extra query creation.
                    InsertObjectQuery commitQuery = descriptor.getQueryManager().getInsertQuery();
                    if (commitQuery == null) {
                        commitQuery = new InsertObjectQuery();
                        commitQuery.setDescriptor(descriptor);
                    } else {
                        // Ensure original query has been prepared.
                        commitQuery.checkPrepare(session, commitQuery.getTranslationRow());
                        commitQuery = (InsertObjectQuery) commitQuery.clone();
                    }
                    commitQuery.setIsExecutionClone(true);
                    commitQuery.setObjectChangeSet(changeSetToWrite);
                    commitQuery.setObject(objectToWrite);
                    commitQuery.cascadeOnlyDependentParts();
                    commitQuery.setModifyRow(null);
                    session.executeQuery(commitQuery);
                }
                uowChangeSet.putNewObjectInChangesList(changeSetToWrite, session);
            }
        }
    }

}
