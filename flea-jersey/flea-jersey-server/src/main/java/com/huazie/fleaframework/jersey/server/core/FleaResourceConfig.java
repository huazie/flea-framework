package com.huazie.fleaframework.jersey.server.core;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Flea 资源配置类，作为 Jersey 应用的资源入口，用于配置 Web 应用程序。
 *
 * <p> 该类初始化时，从 Flea Jersey 资源表中，获取定义的所有资源包名；
 * 并将所有资源包都添加到扫描组件中，以待被递归扫描（包括所有嵌套包）。
 *
 * <p> 另外每个接入 Flea Jersey 的应用，都需创建 Flea 资源配置的子类，
 * 作为其发布的资源的入口；并在该类上标记注解 {@code ApplicationPath}，
 * 其值为该应用对外发布的资源的相对访问路径。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaResourceConfig extends ResourceConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaResourceConfig.class);

    /**
     * 无参构造方法，用于初始化待扫描的资源包
     *
     * @since 1.0.0
     */
    public FleaResourceConfig() {
        init();
    }

    /**
     * 初始化待扫描的资源包
     *
     * @since 1.0.0
     */
    private void init() {
        // 获取所有的资源包名
        List<String> resourcePackages = null;
        try {
            // 获取Web应用上下文对象
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            FleaConfigDataSpringBean springBean = webApplicationContext.getBean(FleaConfigDataSpringBean.class);
            resourcePackages = springBean.getResourcePackages();
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "Exception occurs when getting resource packages : \n", e);
        }

        if (CollectionUtils.isNotEmpty(resourcePackages)) {
            LOGGER.debug1(new Object() {}, "scan packages : {}", resourcePackages);
            packages(resourcePackages.toArray(new String[0]));
        }
        // 服务端注册MultiPartFeature组件，用于支持 multipart/form-data 媒体类型
        register(MultiPartFeature.class);
    }
}