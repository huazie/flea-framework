package org.eclipse.persistence.internal.sessions;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.VersionLockingPolicy;
import org.eclipse.persistence.exceptions.OptimisticLockException;
import org.eclipse.persistence.internal.descriptors.ObjectBuilder;
import org.eclipse.persistence.internal.identitymaps.CacheKey;
import org.eclipse.persistence.internal.localization.ExceptionLocalization;
import org.eclipse.persistence.queries.DoesExistQuery;

/**
 * Flea合并管理器，用于管理工作单元中两个对象的合并
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaMergeManager extends MergeManager {

    public FleaMergeManager(AbstractSession session) {
        super(session);
    }

    @Override
    protected Object mergeChangesOfCloneIntoWorkingCopy(Object rmiClone) {

        Object registeredObject = null;
        if (isForRefresh) {
            UnitOfWorkImpl unitOfWork = (UnitOfWorkImpl) this.session;
            // refreshing UOW instance so only merge if already registered
            ClassDescriptor descriptor = unitOfWork.getDescriptor(rmiClone);
            Object primaryKey = descriptor.getObjectBuilder().extractPrimaryKeyFromObject(rmiClone, unitOfWork, true);
            if (primaryKey != null) {
                registeredObject = unitOfWork.getIdentityMapAccessorInstance().getFromIdentityMap(primaryKey, null, descriptor.getJavaClass(), false, descriptor);
            }
            if (registeredObject == null) {
                return unitOfWork.internalRegisterObject(rmiClone, descriptor, false);
            }
        } else {
            registeredObject = registerObjectForMergeCloneIntoWorkingCopy(rmiClone, shouldForceCascade());
        }

        // adding isAlreadyMerged/recoredMerge check to prevent the uow clone from being merged into twice from the same tree
        // bug 404171
        if ((registeredObject == rmiClone || isAlreadyMerged(registeredObject, this.session)) && !shouldForceCascade()) {
            // need to find better better fix.  prevents merging into itself.
            return registeredObject;
        }
        recordMerge(registeredObject, registeredObject, this.session);

        ClassDescriptor descriptor = this.session.getDescriptor(rmiClone);
        try {
            ObjectBuilder builder = descriptor.getObjectBuilder();

            if (!isForRefresh && registeredObject != rmiClone && descriptor.usesVersionLocking() && !mergedNewObjects.containsKey(registeredObject)) {
                VersionLockingPolicy policy = (VersionLockingPolicy) descriptor.getOptimisticLockingPolicy();
                if (policy.isStoredInObject()) {
                    Object currentValue = builder.extractValueFromObjectForField(registeredObject, policy.getWriteLockField(), session);

                    if (policy.isNewerVersion(currentValue, rmiClone, session.keyFromObject(rmiClone, descriptor), session)) {
                        throw OptimisticLockException.objectChangedSinceLastMerge(rmiClone);
                    }
                }
            }

            // Toggle change tracking during the merge.
            descriptor.getObjectChangePolicy().dissableEventProcessing(registeredObject);

            boolean cascadeOnly = false;
            if (registeredObject == rmiClone || mergedNewObjects.containsKey(registeredObject)) {
                // GF#1139 Cascade merge operations to relationship mappings even if already registered
                cascadeOnly = true;
            }

            // Merge into the clone from the original and use the clone as
            // backup as anything different should be merged.
            builder.mergeIntoObject(registeredObject, null, false, rmiClone, this, this.session, cascadeOnly, false, false);
            if (isForRefresh) {
                Object primaryKey = builder.extractPrimaryKeyFromObject(registeredObject, session);
                descriptor.getObjectChangePolicy().revertChanges(registeredObject, descriptor, (UnitOfWorkImpl) this.session, ((UnitOfWorkImpl) this.session).getCloneMapping(), true);
                CacheKey uowCacheKey = this.session.getIdentityMapAccessorInstance().getCacheKeyForObjectForLock(primaryKey, registeredObject.getClass(), descriptor);
                CacheKey parentCacheKey = session.getParentIdentityMapSession(descriptor, false, false).getIdentityMapAccessorInstance().getCacheKeyForObject(primaryKey, registeredObject.getClass(), descriptor, false);
                if (descriptor.usesOptimisticLocking()) {
                    descriptor.getOptimisticLockingPolicy().mergeIntoParentCache(uowCacheKey, parentCacheKey);
                }
                // Check for null because when there is NoIdentityMap, CacheKey will be null
                if ((parentCacheKey != null) && (uowCacheKey != null)) {
                    uowCacheKey.setReadTime(parentCacheKey.getReadTime());
                }
            }

        } finally {
            descriptor.getObjectChangePolicy().enableEventProcessing(registeredObject);
        }

        return registeredObject;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected Object registerObjectForMergeCloneIntoWorkingCopy(Object clone, boolean shouldForceCascade) {
        UnitOfWorkImpl unitOfWork = (UnitOfWorkImpl) this.session;
        ClassDescriptor descriptor = unitOfWork.getDescriptor(clone);
        Object primaryKey = descriptor.getObjectBuilder().extractPrimaryKeyFromObject(clone, unitOfWork, true);

        // Must use the java class as this may be a bean that we are merging and it may not have the same class as the
        // objects in the cache.  As of EJB 2.0.
        Object objectFromCache = null;
        if (primaryKey != null) {
            objectFromCache = unitOfWork.getIdentityMapAccessorInstance().getFromIdentityMap(primaryKey, null, descriptor.getJavaClass(), false, descriptor);
        }
        if (objectFromCache == null) {
            // Ensure we return the working copy if this has already been registered.
            objectFromCache = unitOfWork.checkIfAlreadyRegistered(clone, descriptor);
        }
        if (objectFromCache != null) {
            // gf830 - merging a removed entity should throw exception.
            if (!isForRefresh && unitOfWork.isObjectDeleted(objectFromCache)) {
                if (shouldMergeCloneIntoWorkingCopy() || shouldMergeCloneWithReferencesIntoWorkingCopy()) {
                    throw new IllegalArgumentException(ExceptionLocalization.buildMessage("cannot_merge_removed_entity", new Object[]{clone}));
                }
            }
            return objectFromCache;
        }

        DoesExistQuery existQuery = descriptor.getQueryManager().getDoesExistQuery();
        // Optimize cache option to avoid executing the does exist query.
        if (existQuery.shouldCheckCacheForDoesExist()) {
            checkNewObjectLockVersion(clone, primaryKey, descriptor, unitOfWork);
            Object registeredObject = unitOfWork.internalRegisterObject(clone, descriptor, false);
            if (unitOfWork.hasNewObjects() && unitOfWork.getNewObjectsOriginalToClone().containsKey(clone)) {
                this.mergedNewObjects.put(registeredObject, registeredObject);
            }
            return registeredObject;
        }

        // Check early return to check if it is a new object, i.e. null primary key.
        Boolean doesExist = Boolean.FALSE;
        if (primaryKey != null) {
            doesExist = (Boolean) existQuery.checkEarlyReturn(clone, primaryKey, unitOfWork, null);
        }
        if (doesExist == Boolean.FALSE) {
            checkNewObjectLockVersion(clone, primaryKey, descriptor, unitOfWork);
            Object registeredObject = unitOfWork.internalRegisterObject(clone, descriptor, shouldForceCascade);//should use cloneAndRegisterNewObject to avoid the exist check
            this.mergedNewObjects.put(registeredObject, registeredObject);
            return registeredObject;
        }

        // Otherwise it is existing and not in the cache so it must be read.
        Object object = unitOfWork.readObject(clone);
        if (object == null) {
            checkNewObjectLockVersion(clone, primaryKey, descriptor, unitOfWork);
            // bug6180972: avoid internal register's existence check and be sure to put the new object in the mergedNewObjects collection
            object = unitOfWork.cloneAndRegisterNewObject(clone, shouldForceCascade);
            this.mergedNewObjects.put(object, object);
        }
        return object;
    }
}
