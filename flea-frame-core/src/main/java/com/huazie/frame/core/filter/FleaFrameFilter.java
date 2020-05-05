package com.huazie.frame.core.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.core.request.FleaRequestContext;
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
        // 空实现
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FleaRequestContext fleaRequestContext = new FleaRequestContext(servletRequest, servletResponse);
        try {
            // 过滤器任务链执行
            FleaRequestUtil.doFilterTask(fleaRequestContext);
            // 不存在重定向标识，才继续执行其他过滤器
            if (ObjectUtils.isEmpty(fleaRequestContext.get(FleaRequestContext.REDIRECT_FLAG))) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (CommonException e) {
            try {
                // 跳转至错误页面
                FleaRequestUtil.sendRedirectToErrorPage(fleaRequestContext, e);
            } catch (CommonException ex) {
                throw new IOException(ex);
            }
        }
    }

    @Override
    public void destroy() {
        // 空实现
    }
}
