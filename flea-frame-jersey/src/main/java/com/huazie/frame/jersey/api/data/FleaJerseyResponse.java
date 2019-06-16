package com.huazie.frame.jersey.api.data;

/**
 * <p> Flea Jersey 响应对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyResponse {

    private FleaJerseyResponseData responseData;

    public FleaJerseyResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(FleaJerseyResponseData responseData) {
        this.responseData = responseData;
    }
}
