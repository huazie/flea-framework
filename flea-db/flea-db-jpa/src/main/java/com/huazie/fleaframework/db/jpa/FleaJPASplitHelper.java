package com.huazie.fleaframework.db.jpa;

import com.huazie.fleaframework.common.FleaConfigManager;
import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.exceptions.FleaDBException;
import com.huazie.fleaframework.db.jpa.handler.IFleaJPASplitHandler;

/**
 * Flea JPA 分库分表工具类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaJPASplitHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJPASplitHelper.class);

    private static volatile IFleaJPASplitHandler fleaJPASplitHandler;

    private static final Object fleaJPASplitHandlerInitLock = new Object();

    private FleaJPASplitHelper() {
    }

    /**
     * 获取JPA分库分表处理者实现类
     *
     * @return Flea JPA分表处理者实现类
     * @since 1.0.0
     */
    public static IFleaJPASplitHandler getHandler() {
        if (ObjectUtils.isEmpty(fleaJPASplitHandler)) {
            synchronized (fleaJPASplitHandlerInitLock) {
                if (ObjectUtils.isEmpty(fleaJPASplitHandler)) {
                    fleaJPASplitHandler = newFleaJPASplitHandle();
                }
            }
        }
        return fleaJPASplitHandler;
    }

    /**
     * 新生成一个Flea JPA 分库分表处理者实现类
     *
     * @return 分表处理者实现类
     * @since 1.0.0
     */
    private static IFleaJPASplitHandler newFleaJPASplitHandle() {

        ConfigItem configItem = FleaConfigManager.getConfigItem(DBConstants.FleaJPAConstants.FLEA_JPA, DBConstants.FleaJPAConstants.FLEA_SPLIT_HANDLER);

        if (ObjectUtils.isEmpty(configItem) || StringUtils.isBlank(configItem.getValue())) {
            ExceptionUtils.throwFleaException(FleaDBException.class, "请检查flea-config.xml中配置项【<config-items key=\"flea-jpa\" > <config-item key=\"flea_split_handler\" >】");
        }
        // 获取分表处理者实现类配置
        String handlerClassStr = configItem.getValue();
        // 获取分表处理者实现类实例
        IFleaJPASplitHandler tableSplitHandler = (IFleaJPASplitHandler) ReflectUtils.newInstance(handlerClassStr);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Flea Split Handler = {}", tableSplitHandler);
        }

        return tableSplitHandler;
    }

}
