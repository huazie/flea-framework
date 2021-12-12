package com.huazie.fleaframework.core.filter.taskchain.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.core.filter.task.IFilterTask;
import com.huazie.fleaframework.core.filter.taskchain.IFilterTaskChain;
import com.huazie.fleaframework.core.request.FleaRequestContext;
import com.huazie.fleaframework.core.request.config.FilterTask;
import com.huazie.fleaframework.core.request.config.FleaRequestConfig;

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

    private List<IFilterTask> filterTaskList; // 过滤器任务

    private static ThreadLocal<Integer> sCurrentPosition = new ThreadLocal<>(); // 过滤器任务执行位置

    public FleaFilterTaskChain() {
        init();
    }

    /**
     * <p> 初始化过滤器任务链 </p>
     *
     * @since 1.0.0
     */
    private void init() {
        filterTaskList = convert(FleaRequestConfig.getFilterTasks());
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
            fleaFilterTaskList = new ArrayList<>();
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
