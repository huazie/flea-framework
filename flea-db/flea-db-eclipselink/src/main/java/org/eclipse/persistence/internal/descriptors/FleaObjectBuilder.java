package org.eclipse.persistence.internal.descriptors;

import com.huazie.fleaframework.db.common.DBConstants;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.InheritancePolicy;
import org.eclipse.persistence.exceptions.QueryException;
import org.eclipse.persistence.internal.queries.JoinedAttributeManager;
import org.eclipse.persistence.internal.sessions.AbstractRecord;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.eclipse.persistence.queries.ObjectBuildingQuery;

/**
 * Flea对象构建器是附加到描述符的行为类之一。
 * 它负责构建对象、行，并从对象和行中提取主键。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
@SuppressWarnings("serial")
public class FleaObjectBuilder extends ObjectBuilder {

    public FleaObjectBuilder(ClassDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public Object buildObject(ObjectBuildingQuery query, AbstractRecord databaseRow, JoinedAttributeManager joinManager,
                              AbstractSession session, ClassDescriptor concreteDescriptor, InheritancePolicy inheritancePolicy,
                              boolean isUnitOfWork, boolean shouldCacheQueryResults, boolean shouldUseWrapperPolicy) {
        Object domainObject;
        Object primaryKey = extractPrimaryKeyFromRow(databaseRow, session);
        primaryKey = generateCacheKey(primaryKey, descriptor);
        if ((primaryKey == null) && (!query.hasPartialAttributeExpressions()) && (!this.descriptor.isAggregateCollectionDescriptor())) {
            // BUG 3168689: EJBQL: "Select Distinct s.customer from SpouseBean s"
            // BUG 3168699: EJBQL: "Select s.customer from SpouseBean s where s.id = '6'"
            if (query.shouldBuildNullForNullPk()) {
                return null;
            } else {
                throw QueryException.nullPrimaryKeyInBuildingObject(query, databaseRow);
            }
        }
        if ((inheritancePolicy != null) && inheritancePolicy.shouldReadSubclasses()) {
            Class classValue = inheritancePolicy.classFromRow(databaseRow, session);
            concreteDescriptor = inheritancePolicy.getDescriptor(classValue);
            if ((concreteDescriptor == null) && query.hasPartialAttributeExpressions()) {
                concreteDescriptor = this.descriptor;
            }
            if (concreteDescriptor == null) {
                throw QueryException.noDescriptorForClassFromInheritancePolicy(query, classValue);
            }
        }
        if (isUnitOfWork) {
            domainObject = buildObjectInUnitOfWork(query, joinManager, databaseRow, (UnitOfWorkImpl) session, primaryKey, concreteDescriptor);
        } else {
            domainObject = buildObject(false, query, databaseRow, session, primaryKey, concreteDescriptor, joinManager);
            if (shouldCacheQueryResults) {
                query.cacheResult(domainObject);
            }

            if (shouldUseWrapperPolicy) {
                domainObject = concreteDescriptor.getObjectBuilder().wrapObject(domainObject, session);
            }
        }

        return domainObject;
    }

    /**
     * 分表场景，重新生成缓存KEY
     *
     * @param primaryKey 主键值
     * @param descriptor 实体类的描述符信息
     * @return 缓存KEY
     * @since 1.2.0
     */
    private Object generateCacheKey(Object primaryKey, ClassDescriptor descriptor) {
        Object cacheKey = primaryKey;
        Object isSplitTable = descriptor.getProperty(DBConstants.LibTableSplitConstants.IS_SPLIT_TABLE);
        if (isSplitTable instanceof Boolean && (Boolean) isSplitTable) {
            cacheKey = descriptor.getTableName() + "#" + primaryKey;
        }
        return cacheKey;
    }
}
