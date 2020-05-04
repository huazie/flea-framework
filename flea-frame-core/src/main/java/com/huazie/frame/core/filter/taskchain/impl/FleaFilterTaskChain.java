package com.huazie.frame.core.filter.taskchain.impl;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.core.filter.task.IFilterTask;
import com.huazie.frame.core.filter.taskchain.IFilterTaskChain;
import com.huazie.frame.core.request.FleaRequestContext;
import com.huazie.frame.core.request.config.FilterTask;
import com.huazie.frame.core.request.config.FleaRequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea过滤器任务链 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaFilterTaskChain implements IFilterTaskChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaFilterTaskChain.class);

    private List<IFilterTask> filterTaskList; // 过滤器任务

    private static ThreadLocal<Integer> sCurrentPosition = new ThreadLocal<Integer>(); // 过滤器任务执行位置

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
     * @param fleaRequestContext Flea请求上下文
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public void doFilterTask(FleaRequestContext fleaRequestContext) throws CommonException {
        Integer currentPosition = getCurrentPosition();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("***************************************************************************************");
            LOGGER.debug("FleaFilterTaskChain##doFilterTask(ServletRequest, ServletResponse) CurrentPosition = {}", currentPosition);
            LOGGER.debug("***************************************************************************************");
        }
        if (currentPosition < filterTaskList.size()) {
            IFilterTask filterTask = filterTaskList.get(currentPosition);
            setCurrentPosition(++currentPosition);
            filterTask.doFilterTask(fleaRequestContext, this);
        }
    }

    /**
     * <p> 获取当前过滤器任务链中处理的过滤器任务位置 </p>
     *
     * @return 返回当前过滤器任务链中处理的过滤器任务位置
     * @since 1.0.0
     */
    private Integer getCurrentPosition() {
        Integer currentPosition = sCurrentPosition.get();
        if (ObjectUtils.isEmpty(currentPosition)) {
            currentPosition = CommonConstants.NumeralConstants.INT_ZERO;
        }
        return currentPosition;
    }

    /**
     * <p> 设置当前过滤器任务链中处理的过滤器任务位置 </p>
     *
     * @param currentPosition 过滤器任务位置
     * @since 1.0.0
     */
    private void setCurrentPosition(Integer currentPosition) {
        sCurrentPosition.set(currentPosition);
    }

    /**
     * <p> 过滤器任务链执行完毕，当前线程重置过滤器任务执行位置 </p>
     *
     * @since 1.0.0
     */
    public void reset() {
        setCurrentPosition(CommonConstants.NumeralConstants.INT_ZERO);
    }

}
