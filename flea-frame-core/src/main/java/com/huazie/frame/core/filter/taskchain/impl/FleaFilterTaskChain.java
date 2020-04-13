package com.huazie.frame.core.filter.taskchain.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.core.filter.task.IFilterTask;
import com.huazie.frame.core.filter.taskchain.IFilterTaskChain;
import com.huazie.frame.core.request.config.FilterTask;
import com.huazie.frame.core.request.config.FleaRequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea过滤器任务链 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFilterTaskChain implements IFilterTaskChain{

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaFilterTaskChain.class);

    private List<IFilterTask> filterTaskList; // 过滤器任务

    private int currentPosition = 0; // 当前执行的过滤器任务

    public FleaFilterTaskChain() {
        initFleaFilterTaskChain();
    }

    /**
     * <p> 初始化过滤器任务链 </p>
     *
     * @since 1.0.0
     */
    private void initFleaFilterTaskChain() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaFilterTaskChain##initFleaFilterTaskChain() Start");
        }

        filterTaskList = convert(FleaRequestConfig.getFilterTasks());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaFilterTaskChain##initFleaFilterTaskChain() End");
        }
    }

    /**
     * <p> 过滤器链配置集合 转化成对应过滤器链实现类集合 </p>
     *
     * @param filterTasks 过滤器链配置
     * @return 过滤器链实现类集合
     * @since 1.0.0
     */
    private List<IFilterTask> convert(List<FilterTask> filterTasks) {
        List<IFilterTask> fleaFilterTaskList = null;
        if (CollectionUtils.isNotEmpty(filterTasks)) {
            fleaFilterTaskList = new ArrayList<IFilterTask>();
            for (FilterTask filterTask : filterTasks) {
                if (ObjectUtils.isNotEmpty(filterTask)) {
                    IFilterTask fleaFilterTask = (IFilterTask) ReflectUtils.newInstance(filterTask.getClazz());
                    if (ObjectUtils.isNotEmpty(fleaFilterTask)) {
                        fleaFilterTaskList.add(fleaFilterTask);
                    }
                }
            }
        }
        return fleaFilterTaskList;
    }

    /**
     * <p> 执行过滤器任务链 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException {
        if (currentPosition < filterTaskList.size()) {
            IFilterTask filterTask = filterTaskList.get(currentPosition);
            ++currentPosition;
            filterTask.doFilterTask(servletRequest, servletResponse, this);
        }
    }

}
