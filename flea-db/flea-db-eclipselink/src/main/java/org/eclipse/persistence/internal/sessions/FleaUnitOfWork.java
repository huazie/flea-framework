package org.eclipse.persistence.internal.sessions;

import com.huazie.fleaframework.db.eclipselink.util.EclipseLinkUtils;
import org.eclipse.persistence.config.ReferenceMode;
import org.eclipse.persistence.descriptors.ClassDescriptor;

/**
 * Flea工作单元，主要重写获取实体的描述符的方法，以支持分表场景
 *
 * @author huazie
 * @version 1.2.0
 * @see RepeatableWriteUnitOfWork
 * @since 1.2.0
 */
public class FleaUnitOfWork extends RepeatableWriteUnitOfWork {

    private static final long serialVersionUID = 6405298020328002642L;

    public FleaUnitOfWork(AbstractSession parentSession, ReferenceMode referenceMode) {
        super(parentSession, referenceMode);
    }

    /**
     * 获取实体类的描述信息
     *
     * @param domainObject 实体类
     * @return 实体类的描述信息
     * @since 1.2.0
     */
    @Override
    public ClassDescriptor getDescriptor(Object domainObject) {
        ClassDescriptor descriptor = super.getDescriptor(domainObject);
        // 获取分表对应的实体类的持久化信息描述符
        descriptor = EclipseLinkUtils.getSplitDescriptor(descriptor, this, domainObject);
        return descriptor;
    }


    public Object mergeCloneWithReferences(Object rmiClone, int cascadePolicy, boolean forceCascade) {
        Object returnValue;
        try{
            MergeManager manager = new FleaMergeManager(this);
            manager.mergeCloneWithReferencesIntoWorkingCopy();
            manager.setCascadePolicy(cascadePolicy);
            manager.setForceCascade(forceCascade);
            mergeManagerForActiveMerge = manager;
            returnValue= mergeCloneWithReferences(rmiClone, manager);
        } finally {
            mergeManagerForActiveMerge = null;
        }
        return returnValue;
    }
}