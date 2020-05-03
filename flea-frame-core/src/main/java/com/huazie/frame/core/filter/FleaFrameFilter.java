package com.huazie.frame.core.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.request.FleaRequestUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p> Flea Frame Filter </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFrameFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            // 过滤器任务链执行
            FleaRequestUtil.doFilterTask(servletRequest, servletResponse);
        } catch (CommonException e) {
            try {
                // 跳转至错误页面
                FleaRequestUtil.sendRedirectToErrorPage(servletRequest, servletResponse, e);
            } catch (CommonException ex) {
                throw new IOException(ex);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
