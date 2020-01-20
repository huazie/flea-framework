package com.huazie.frame.core.filter.task;

import com.huazie.frame.common.exception.CommonException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p> Flea Filter Task </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaFilterTask {


    void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException;

}
