package org.eclipse.persistence.internal.descriptors;

import org.eclipse.persistence.descriptors.RelationalDescriptor;

/**
 * Flea实体类描述符
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaRelationalDescriptor extends RelationalDescriptor {

    public FleaRelationalDescriptor() {
        super();
        objectBuilder = new FleaObjectBuilder(this);
    }
}
