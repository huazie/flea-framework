package com.huazie.frame.jersey.client.request;

import com.huazie.frame.common.FleaConfig;

/**
 * <p> 请求配置 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RequestConfig extends FleaConfig {

    /**
     * <p> 添加 客户端编码 配置 </p>
     *
     * @param clientCode 客户端编码
     * @since 1.0.0
     */
    public void addClientCode(String clientCode) {
        put(RequestConfigEnum.CLIENT_CODE.getKey(), clientCode);
    }

    /**
     * <p> 获取 客户端编码 配置</p>
     *
     * @return 客户端编码
     * @since 1.0.0
     */
    public String getClientCode() {
        return get(RequestConfigEnum.CLIENT_CODE.getKey(), String.class);
    }

    /**
     * <p> 添加 业务入参实例对象 配置 </p>
     *
     * @param input 业务入参实例对象
     * @since 1.0.0
     */
    public void addInputObj(Object input) {
        put(RequestConfigEnum.INPUT_OBJECT.getKey(), input);
    }

    /**
     * <p> 获取 业务入参实例对象 配置 </p>
     *
     * @return 业务入参实例对象
     * @since 1.0.0
     */
    public Object getInputObj() {
        return get(RequestConfigEnum.INPUT_OBJECT.getKey());
    }

}
