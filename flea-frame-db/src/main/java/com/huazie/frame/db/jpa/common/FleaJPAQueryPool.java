package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.pool.FleaObjectPool;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * <p> Flea JPA查询对象池 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJPAQueryPool extends FleaObjectPool<FleaJPAQuery> {

    /**
     * <p> 对象池配置 </p>
     *
     * @param poolConfig 对象池配置
     * @since 1.0.0
     */
    public FleaJPAQueryPool(GenericObjectPoolConfig poolConfig) {
        super(poolConfig, new FleaJPAQueryFactory());
    }

    /**
     * <p> 获取Flea JPA查询对象 </p>
     *
     * @return Flea JPA查询对象
     * @since 1.0.0
     */
    @Override
    public FleaJPAQuery getFleaObject() {
        FleaJPAQuery query = super.getFleaObject();
        query.setFleaObjectPool(this);
        return query;
    }

    @Override
    protected void returnFleaObject(FleaJPAQuery object) {
        if (ObjectUtils.isNotEmpty(object)) {
            object.reset();
            returnObject(object);
        }
    }

    /**
     * <p> Flea JPA查询对象池化工厂类 </p>
     */
    private static class FleaJPAQueryFactory implements PooledObjectFactory<FleaJPAQuery> {

        @Override
        public PooledObject<FleaJPAQuery> makeObject() throws Exception {
            FleaJPAQuery query = new FleaJPAQuery();
            return new DefaultPooledObject<FleaJPAQuery>(query);
        }

        @Override
        public void destroyObject(PooledObject<FleaJPAQuery> p) throws Exception {
            final FleaJPAQuery query = p.getObject();
            if (ObjectUtils.isNotEmpty(query)) {
                query.reset();
            }
        }

        @Override
        public boolean validateObject(PooledObject<FleaJPAQuery> p) {
            return false;
        }

        @Override
        public void activateObject(PooledObject<FleaJPAQuery> p) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject<FleaJPAQuery> p) throws Exception {

        }
    }


}
