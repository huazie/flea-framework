package com.huazie.fleaframework.db.common.lib.split;

import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 分库转换接口定义
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public interface ILibSplit {

    /**
     * 分库转换
     *
     * @param splitLibObj 分库对象
     * @param count       分库总数
     * @return 分库转换值
     * @throws CommonException 通用异常
     */
    String convert(Object splitLibObj, int count) throws CommonException;
}
