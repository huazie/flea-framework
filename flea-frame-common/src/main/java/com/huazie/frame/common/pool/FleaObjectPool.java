package com.huazie.frame.common.pool;

import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * <p> Flea Object Pool </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaObjectPool<T> implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaObjectPool.class);

    protected GenericObjectPool<T> fleaObjectPool; // 内部Flea对象池对象

    public FleaObjectPool() {
    }

    public FleaObjectPool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        initFleaObjectPool(poolConfig, factory);
    }

    @Override
    public void close() {
        closeFleaObjectPool();
    }

    /**
     * <p> 是否对象池实例已经关闭 </p>
     *
     * @return <code>true</code> : 对象池实例已关闭
     */
    public boolean isClosed() {
        return fleaObjectPool.isClosed();
    }

    /**
     * <p> 初始化对象池 </p>
     *
     * @param poolConfig 对象池配置
     * @param factory    池化对象工厂类
     * @since 1.0.0
     */
    public void initFleaObjectPool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        if (ObjectUtils.isNotEmpty(fleaObjectPool)) {
            closeFleaObjectPool();
        }
        fleaObjectPool = new GenericObjectPool<T>(factory, poolConfig);
    }

    /**
     * <p> 从对象池中获取一个对象实例 </p>
     *
     * @return 池化的对象实例
     * @since 1.0.0
     */
    public T getFleaObject() {
        T object = null;
        try {
            object = fleaObjectPool.borrowObject();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Could not get a object instance from the pool, Exception :\n", e);
            }
        }
        return object;
    }

    /**
     * <p> 将对象实例归还给对象池 </p>
     *
     * @param object 对象实例
     * @since 1.0.0
     */
    protected void returnObject(final T object) {
        if (ObjectUtils.isEmpty(object)) {
            return;
        }
        try {
            fleaObjectPool.returnObject(object);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Could not return the object instance to the pool, Exception :\n", e);
            }
        }
    }

    /**
     * <p> 将对象实例归还给对象池 </p>
     *
     * @param object 对象实例
     * @since 1.0.0
     */
    protected void returnFleaObject(final T object) {
        if (ObjectUtils.isNotEmpty(object)) {
            returnObject(object);
        }
    }

    /**
     * <p> 关闭对象池 </p>
     *
     * @since 1.0.0
     */
    protected void closeFleaObjectPool() {
        try {
            if (ObjectUtils.isNotEmpty(fleaObjectPool)) {
                fleaObjectPool.close();
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Could not close the pool, Exception :\n", e);
            }
        }
    }

    /**
     * <p> 获取当前外部在用的实例数目（即从对象池中已借出未归还的实例数目） </p>
     *
     * @return 当前外部在用的实例数目；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public int getNumActive() {
        return isPoolInActive() ? -1 : fleaObjectPool.getNumActive();
    }

    /**
     * <p> 获取对象池中空闲的实例数目 </p>
     *
     * @return 对象池中空闲的实例数目；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public int getNumIdle() {
        return isPoolInActive() ? -1 : fleaObjectPool.getNumIdle();
    }

    /**
     * <p> 获取对象池中当前阻塞等待的线程数的估计值 </p>
     *
     * @return 等待的线程数；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public int getNumWaiters() {
        return isPoolInActive() ? -1 : fleaObjectPool.getNumWaiters();
    }

    /**
     * <p> 返回线程从对象池中获取对象所花费的平均等待时间 </p>
     *
     * @return 平均等待时间（单位：ms）；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public long getMeanBorrowWaitTimeMillis() {
        return isPoolInActive() ? -1 : fleaObjectPool.getMeanBorrowWaitTimeMillis();
    }

    /**
     * <p> 返回线程从对象池中获取对象所花费的最大等待时间 </p>
     *
     * @return 最大等待时间（单位：ms）；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public long getMaxBorrowWaitTimeMillis() {
        return isPoolInActive() ? -1 : fleaObjectPool.getMaxBorrowWaitTimeMillis();
    }

    /**
     * <p> 对象池是否是未创建或已关闭 </p>
     *
     * @return <code>true</code>, 对象池没有创建或已关闭
     */
    private boolean isPoolInActive() {
        return ObjectUtils.isEmpty(fleaObjectPool) || isClosed();
    }

    /**
     * <p> 添加空闲对象 </p>
     *
     * @param count 添加数目
     * @since 1.0.0
     */
    public void addFleaObjects(int count) {
        try {
            for (int i = 0; i < count; i++) {
                fleaObjectPool.addObject();
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error trying to add idle objects, Exception :\n", e);
            }
        }
    }

}
