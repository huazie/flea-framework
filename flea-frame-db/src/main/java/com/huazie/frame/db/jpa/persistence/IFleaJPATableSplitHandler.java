package com.huazie.frame.db.jpa.persistence;

import com.huazie.frame.common.exception.CommonException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * <p> Flea JPA 分表处理者接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJPATableSplitHandler {

    /**
     * <p> 使用标准化查询时，处理分表信息 </p>
     *
     * @param criteriaQuery 标准化查询对象实例
     * @param entity        实体类对象实例
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void handle(CriteriaQuery criteriaQuery, Object entity) throws CommonException;

    /**
     * <p> 使用持久化接口时，处理分表信息 </p>
     *
     * @param entityManager 持久化接口对象实例
     * @param entity        实体类对象实例
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void handle(EntityManager entityManager, Object entity, boolean isRead) throws CommonException;
}
