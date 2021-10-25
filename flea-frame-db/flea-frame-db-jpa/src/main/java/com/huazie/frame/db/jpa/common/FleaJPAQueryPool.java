package com.huazie.frame.db.jpa.common;

import com.huazie.frame.common.pool.FleaObjectPool;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * <p> Flea JPA查询对象池 </p>
 * <pre>使用:
 *  // 获取Flea JPA查询对象池实例 （使用默认连接池名"default"即可）
 *  FleaJPAQueryPool pool = FleaObjectPoolFactory.getFleaObjectPool(FleaJPAQuery.class, FleaJPAQueryPool.class);
 *  // 获取Flea JPA查询对象实例
 *  FleaJPAQuery query = pool.getFleaObject();
 * </pre>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJPAQueryPool extends FleaObjectPool<FleaJPAQuery> {

    private String poolName;

    /**
     * <p> Flea JPA查询对象池构造方法 </p>
     *
     * @param poolConfig 对象池配置
     * @since 1.0.0
     */
    public FleaJPAQueryPool(GenericObjectPoolConfig poolConfig) {
        super(poolConfig, new FleaJPAQueryFactory());
    }

    /**
     * <p> Flea JPA查询对象池构造方法 </p>
     *
     * @param poolConfig 对象池配置
     * @since 1.0.0
     */
    public FleaJPAQueryPool(String poolName, GenericObjectPoolConfig poolConfig) {
        this(poolConfig);
        this.poolName = poolName;
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
     * <p> 获取对象池名 </p>
     *
     * @return 对象池名
     * @since 1.0.0
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * <p> 设置对象池名 </p>
     * @param poolName 对象池名
     * @since 1.0.0
     */
    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    /**
     * <p> Flea JPA查询对象池化工厂类 </p>
     *
     * @author huazie
     * @version 1.0.0
     * @since 1.0.0
     */
    private static class FleaJPAQueryFactory implements PooledObjectFactory<FleaJPAQuery> {

        @Override
        public PooledObject<FleaJPAQuery> makeObject() {
            FleaJPAQuery query = new FleaJPAQuery();
            return new DefaultPooledObject<>(query);
        }

        @Override
        public void destroyObject(PooledObject<FleaJPAQuery> p) {
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
        public void activateObject(PooledObject<FleaJPAQuery> p) {

        }

        @Override
        public void passivateObject(PooledObject<FleaJPAQuery> p) {

        }
    }
}
