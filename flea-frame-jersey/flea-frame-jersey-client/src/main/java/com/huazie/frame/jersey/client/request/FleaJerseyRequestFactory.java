package com.huazie.frame.jersey.client.request;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.jersey.common.exception.FleaJerseyClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Flea Jersey 请求工厂 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyRequestFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyRequestFactory.class);

    private static volatile FleaJerseyRequestFactory factory;

    private FleaJerseyRequestFactory() {
    }

    /**
     * <p> 获取请求工厂实例 </p>
     *
     * @return
     */
    public static FleaJerseyRequestFactory getInstance() {
        if (ObjectUtils.isEmpty(factory)) {
            synchronized (FleaJerseyRequestFactory.class) {
                if (ObjectUtils.isEmpty(factory)) {
                    factory = new FleaJerseyRequestFactory();
                }
            }
        }
        return factory;
    }

    /**
     * <p> 构建一个Flea请求 </p>
     *
     * @param config 请求配置
     * @return Flea请求
     * @throws Exception
     * @since 1.0.0
     */
    public Request buildFleaRequest(RequestConfig config) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyRequestFactory##buildFleaRequest(RequestConfig) Start");
            LOGGER.debug("FleaJerseyRequestFactory##buildFleaRequest(RequestConfig) RequestConfig = {}", config.getConfig());
        }

        if (ObjectUtils.isEmpty(config) || config.isEmpty()) {
            // 未初始化请求配置，请检查！！！
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000000");
        }

        // 获取请求方式
        String requestMode = config.getRequestMode();
        if (StringUtils.isBlank(requestMode)) {
            // 【{0}】未配置，请检查！！！
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000008", RequestConfigEnum.REQUEST_MODE.getKey());
        }

        // 获取请求模式类型枚举
        RequestModeEnum requestModeEnum = null;

        try {
            requestModeEnum = RequestModeEnum.valueOf(requestMode.toUpperCase());
        } catch (IllegalArgumentException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("FleaJerseyRequestFactory##buildFleaRequest(RequestConfig) Exception = {}", e.getMessage());
            }
        }

        Request request = null;

        if (ObjectUtils.isNotEmpty(requestModeEnum)) {
            Object requestObj = ReflectUtils.newInstance(requestModeEnum.getImplClass(), config);
            if (ObjectUtils.isNotEmpty(requestObj) && requestObj instanceof Request) {
                request = (Request) requestObj;
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyRequestFactory##buildFleaRequest(RequestConfig) Request = {}", request.getClass().getName());
            LOGGER.debug("FleaJerseyRequestFactory##buildFleaRequest(RequestConfig) End");
        }

        return request;
    }
}
