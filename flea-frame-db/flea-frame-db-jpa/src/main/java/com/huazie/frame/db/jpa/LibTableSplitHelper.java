package com.huazie.frame.db.jpa;

import com.huazie.frame.common.FleaConfigManager;
import com.huazie.frame.common.config.ConfigItem;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.jpa.handler.IFleaJPASplitHandler;

/**
 * <p> 分表工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LibTableSplitHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LibTableSplitHelper.class);

    private static volatile IFleaJPASplitHandler fleaJPASplitHandler;

    private static Boolean isTableSplitHandlerInit = Boolean.FALSE;

    private LibTableSplitHelper() {
    }

    /**
     * <p> 获取分表处理者实现类 </p>
     *
     * @return Flea JPA分表处理者实现类
     * @since 1.0.0
     */
    public static IFleaJPASplitHandler findTableSplitHandle() {

        if (isTableSplitHandlerInit.equals(Boolean.FALSE)) {
            synchronized (isTableSplitHandlerInit) {
                if (isTableSplitHandlerInit.equals(Boolean.FALSE)) {
                    try {
                        fleaJPASplitHandler = newTableSplitHandle();
                        isTableSplitHandlerInit = Boolean.TRUE;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return fleaJPASplitHandler;
    }

    /**
     * <p> 新生成一个Flea JPA 分表处理者实现类 </p>
     *
     * @return 分表处理者实现类
     * @since 1.0.0
     */
    private static IFleaJPASplitHandler newTableSplitHandle() {

        ConfigItem configItem = FleaConfigManager.getConfigItem(DBConstants.FleaJPAConstants.FLEA_JPA, DBConstants.FleaJPAConstants.FLEA_SPLIT_HANDLER);

        if (ObjectUtils.isEmpty(configItem) || StringUtils.isBlank(configItem.getValue())) {
            throw new NullPointerException("请检查flea-config.xml中配置项【<config-items key=\"flea-jpa\" > <config-item key=\"flea_split_handler\" >】");
        }
        // 获取分表处理者实现类配置
        String handlerClassStr = configItem.getValue();
        // 获取分表处理者实现类实例
        IFleaJPASplitHandler tableSplitHandler = (IFleaJPASplitHandler) ReflectUtils.newInstance(handlerClassStr);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Table Split Handler = {}", tableSplitHandler);
        }

        return tableSplitHandler;
    }

}
