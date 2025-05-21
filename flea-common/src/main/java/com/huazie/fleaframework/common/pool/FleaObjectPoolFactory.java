package com.huazie.fleaframework.common.pool;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaConfigManager;
import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea对象池工厂
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaObjectPoolFactory {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaObjectPoolFactory.class);

    private static final ConcurrentMap<String, FleaObjectPool> fleaObjectPools = new ConcurrentHashMap<>();

    private static final Object objectPoolLock = new Object();

    private FleaObjectPoolFactory() {
    }

    /**
     * 默认Flea对象池（指定对象Class）
     *
     * @param objClazz     对象Class
     * @param objPoolClazz 对象池Class
     * @return 默认对象池
     * @since 1.0.0
     */
    public static <T extends FleaObjectPool> T getFleaObjectPool(Class<?> objClazz, Class<T> objPoolClazz) {
        return getFleaObjectPool(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME, objClazz, objPoolClazz);
    }

    /**
     * 指定对象池名的Flea对象池（指定对象Class）
     *
     * @param poolName     对象池名
     * @param objClazz     对象Class
     * @param objPoolClazz 对象池Class
     * @return 指定对象池名的Flea对象池
     * @since 1.0.0
     */
    public static <T extends FleaObjectPool> T getFleaObjectPool(String poolName, Class<?> objClazz, Class<T> objPoolClazz) {
        if (StringUtils.isBlank(poolName) || ObjectUtils.isEmpty(objClazz)) {
            return null;
        }
        String poolNameKey = poolName + CommonConstants.SymbolConstants.UNDERLINE + objClazz.getName();
        LOGGER.debug1(new Object() {}, "Pool Name Key = {}", poolNameKey);
        if (!fleaObjectPools.containsKey(poolNameKey)) {
            synchronized (objectPoolLock) {
                if (!fleaObjectPools.containsKey(poolNameKey)) {
                    fleaObjectPools.put(poolNameKey, build(poolName, objClazz));
                }
            }
        }

        Object objPool = fleaObjectPools.get(poolNameKey);
        if (objPoolClazz.isInstance(objPool)) {
            return objPoolClazz.cast(objPool);
        } else {
            return null;
        }
    }

    /**
     * 初始化Flea对象池创建
     *
     * @param poolName 对象池名
     * @param objClazz 指定对象Class
     * @since 1.0.0
     */
    private static FleaObjectPool build(String poolName, Class<?> objClazz) {
        String className = objClazz.getSimpleName();
        Object obj = new Object() {};
        LOGGER.debug1(obj, "Object Simple Name = {}", className);
        ConfigItem configItem = FleaConfigManager.getConfigItem(CommonConstants.FleaPoolConstants.FLEA_OBJECT_POOL, className);
        if (ObjectUtils.isEmpty(configItem)) {
            LOGGER.debug1(obj, "Can not find the builder by the <config-item key=\"{}\"> from <config-items key=\"{}\"> in [flea-config.xml]",
                        className, CommonConstants.FleaPoolConstants.FLEA_OBJECT_POOL);
            return null;
        }

        String builderImpl = configItem.getValue();
        if (StringUtils.isBlank(builderImpl)) {
            LOGGER.debug1(obj, "The builder is empty, found by the <config-item key=\"{}\"> from <config-items key=\"{}\"> in [flea-config.xml]",
                        className, CommonConstants.FleaPoolConstants.FLEA_OBJECT_POOL);
            return null;
        }

        FleaObjectPool fleaObjectPool = null;

        IFleaObjectPoolBuilder fleaObjectPoolBuilder = (IFleaObjectPoolBuilder) ReflectUtils.newInstance(builderImpl);
        if (ObjectUtils.isNotEmpty(fleaObjectPoolBuilder)) {
            // 调用指定的类，创建Flea对象池
            fleaObjectPool = fleaObjectPoolBuilder.build(poolName);
        }

        return fleaObjectPool;
    }
}
