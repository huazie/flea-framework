package com.huazie.fleaframework.common.pool;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.Closeable;

/**
 * Flea Object Pool
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaObjectPool<T> implements Closeable {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaObjectPool.class);

    protected GenericObjectPool<T> fleaObjectPool; // 内部Flea对象池对象

    /**
     * 外部可调用initFleaObjectPool方法初始化
     */
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
     * 是否对象池实例已经关闭
     *
     * @return <code>true</code> : 对象池实例已关闭
     */
    public boolean isClosed() {
        return fleaObjectPool.isClosed();
    }

    /**
     * 初始化对象池
     *
     * @param poolConfig 对象池配置
     * @param factory    池化对象工厂类
     * @since 1.0.0
     */
    public void initFleaObjectPool(final GenericObjectPoolConfig poolConfig, PooledObjectFactory<T> factory) {
        if (ObjectUtils.isNotEmpty(fleaObjectPool)) {
            closeFleaObjectPool();
        }
        fleaObjectPool = new GenericObjectPool<>(factory, poolConfig);
    }

    /**
     * 从对象池中获取一个对象实例
     *
     * @return 池化的对象实例
     * @since 1.0.0
     */
    public T getFleaObject() {
        T object = null;
        try {
            object = fleaObjectPool.borrowObject();
        } catch (Exception e) {
            LOGGER.error("Could not get a object instance from the pool, Exception :\n", e);
        }
        return object;
    }

    /**
     * 将对象实例归还给对象池
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
            LOGGER.error1(new Object() {}, "Could not return the object instance to the pool, Exception :\n", e);
        }
    }

    /**
     * 将对象实例归还给对象池
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
     * 关闭对象池
     *
     * @since 1.0.0
     */
    protected void closeFleaObjectPool() {
        try {
            if (ObjectUtils.isNotEmpty(fleaObjectPool)) {
                fleaObjectPool.close();
            }
        } catch (Exception e) {
            LOGGER.error("Could not close the pool, Exception :\n", e);
        }
    }

    /**
     * 获取当前外部在用的实例数目（即从对象池中已借出未归还的实例数目）
     *
     * @return 当前外部在用的实例数目；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public int getNumActive() {
        return isPoolInActive() ? -1 : fleaObjectPool.getNumActive();
    }

    /**
     * 获取对象池中空闲的实例数目
     *
     * @return 对象池中空闲的实例数目；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public int getNumIdle() {
        return isPoolInActive() ? -1 : fleaObjectPool.getNumIdle();
    }

    /**
     * 获取对象池中当前阻塞等待的线程数的估计值
     *
     * @return 等待的线程数；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public int getNumWaiters() {
        return isPoolInActive() ? -1 : fleaObjectPool.getNumWaiters();
    }

    /**
     * 返回线程从对象池中获取对象所花费的平均等待时间
     *
     * @return 平均等待时间（单位：ms）；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public long getMeanBorrowWaitTimeMillis() {
        return isPoolInActive() ? -1 : fleaObjectPool.getMeanBorrowWaitTimeMillis();
    }

    /**
     * 返回线程从对象池中获取对象所花费的最大等待时间
     *
     * @return 最大等待时间（单位：ms）；如果对象池没有创建或已关闭，返回 -1。
     * @since 1.0.0
     */
    public long getMaxBorrowWaitTimeMillis() {
        return isPoolInActive() ? -1 : fleaObjectPool.getMaxBorrowWaitTimeMillis();
    }

    /**
     * 对象池是否是未创建或已关闭
     *
     * @return <code>true</code>, 对象池没有创建或已关闭
     */
    private boolean isPoolInActive() {
        return ObjectUtils.isEmpty(fleaObjectPool) || isClosed();
    }

    /**
     * 添加空闲对象
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
            LOGGER.error1(new Object() {}, "Error trying to add idle objects, Exception :\n", e);
        }
    }

}
