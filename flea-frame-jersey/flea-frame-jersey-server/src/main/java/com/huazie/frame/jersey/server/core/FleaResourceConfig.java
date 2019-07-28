package com.huazie.frame.jersey.server.core;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * <p> REST应用入口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaResourceConfig extends ResourceConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaResourceConfig.class);

    /**
     * <p> 无参构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaResourceConfig() {
        init();
    }

    /**
     * <p> 资源包扫描初始化 </p>
     *
     * @since 1.0.0
     */
    private void init() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaResourceConfig#init() Start");
        }
        // 获取所有的资源包名
        List<String> resourcePackages = null;
        try {
            // 获取Web应用上下文对象
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            FleaConfigDataSpringBean springBean = webApplicationContext.getBean(FleaConfigDataSpringBean.class);
            resourcePackages = springBean.getResourcePackages();
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("获取资源包名出现异常：", e);
            }
        }

        if (CollectionUtils.isNotEmpty(resourcePackages)) {
            packages(resourcePackages.toArray(new String[0]));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaResourceConfig#init() End");
        }
    }
}