package com.huazie.frame.jersey.api.filter.impl;

import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.filter.IFleaJerseyErrorFilter;

/**
 * <p> 异常过滤器实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorFilter implements IFleaJerseyErrorFilter {

    @Override
    public void doFilter(FleaJerseyResponse response, Throwable throwable) {

    }
}
