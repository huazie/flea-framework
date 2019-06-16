package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;

/**
 * <p> Flea Jersey 过滤器接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyFilter {

    void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception;

}
