package com.huazie.fleaframework.jersey.client.response;

import com.huazie.fleaframework.common.pojo.OutputCommonData;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 响应结果，封装Flea Jersey资源服务请求响应数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Response<T> extends OutputCommonData {

    private static final long serialVersionUID = 2498307147680858074L;

    private T output; // 业务出参

    public Response() {
        super(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_SUCCESS, FleaJerseyConstants.ResponseResultConstants.RESULT_MESS_SUCCESS);
    }

    public T getOutput() {
        return output;
    }

    public void setOutput(T output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
