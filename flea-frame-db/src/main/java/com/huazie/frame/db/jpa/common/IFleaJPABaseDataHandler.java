package com.huazie.frame.db.jpa.common;

import java.util.List;
import java.util.Map;

/**
 * <p> 数据处理的基本方法接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJPABaseDataHandler<T> {

    /**
     * <p> 根据主键查询 </p>
     *
     * @param entityId 主键
     * @return 实体对象
     * @throws Exception
     * @since 1.0.0
     */
    T query(long entityId) throws Exception;

    /**
     * <p> 根据主键查询 </p>
     *
     * @param entityId 主键
     * @return 实体对象
     * @throws Exception
     * @since 1.0.0
     */
    T query(String entityId) throws Exception;

    /**
     * <p> 多条件查询 </p>
     *
     * @param paramterMap 多条件参数
     * @return 实体对象集合
     * @throws Exception
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramterMap) throws Exception;

    /**
     * <p> 多条件排序查询 </p>
     *
     * @param paramterMap 多条件参数
     * @param attrName    属性名
     * @param orderBy     排序方式（desc：降序，asc：升序）
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy) throws Exception;

    /**
     * <p> 多条件分页查询 </p>
     *
     * @param paramterMap 多条件参数
     * @param start       开始查询记录行
     * @param max         一次最大查询数量
     * @return 实体对象集合
     * @throws Exception
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramterMap, int start, int max) throws Exception;

    /**
     * <p> 多条件分页排序查询 </p>
     *
     * @param paramterMap 多条件参数
     * @param attrName    属性名
     * @param orderBy     排序方式（desc：降序，asc：升序）
     * @param start       开始查询记录行
     * @param max         一次最大查询数量
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramterMap, String attrName, String orderBy, int start, int max) throws Exception;

    /**
     * <p> 查询所有数据 </p>
     *
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    List<T> queryAll() throws Exception;

    /**
     * <p> 查询所有数据(排序) </p>
     *
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    List<T> queryAll(String attrName, String orderBy) throws Exception;

    /**
     * <p> 分页查询所有的数据 </p>
     *
     * @param start 开始查询记录行
     * @param max   一次最大查询数量
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    List<T> queryAll(int start, int max) throws Exception;

    /**
     * <p> 分页排序查询所有的数据 </p>
     *
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @param start    开始查询记录行
     * @param max      一次最大查询数量
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    List<T> queryAll(String attrName, String orderBy, int start, int max) throws Exception;

    /**
     * <p> 查询表中数据的总数 </p>
     *
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    int queryCount() throws Exception;

    /**
     * <p> 带条件查询表中数据的总数 </p>
     *
     * @param paramterMap 多条件参数
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    int queryCount(Map<String, Object> paramterMap) throws Exception;

    /**
     * <p> 删除某条数据 </p>
     *
     * @param entityId 实体类的主键
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    boolean remove(long entityId) throws Exception;

    /**
     * <p> 删除某条数据 </p>
     *
     * @param entityId 实体类的主键
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    boolean remove(String entityId) throws Exception;

    /**
     * <p> 保存一条记录,可用于更新 </p>
     *
     * @param entity 一条记录对应的实体类
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    T store(final T entity) throws Exception;

    /**
     * <p> 批量更新 </p>
     *
     * @param entitys
     * @throws Exception
     * @since 1.0.0
     */
    List<T> batchUpdate(List<T> entitys) throws Exception;

    /**
     * <p> 保存一条记录 </p>
     *
     * @param entity 一条记录对应的实体类
     * @return
     * @throws Exception
     * @since 1.0.0
     */
    void save(final T entity) throws Exception;

    /**
     * <p> 批量保存 </p>
     *
     * @param entitys 待保存的实体集合
     * @throws Exception
     * @since 1.0.0
     */
    void batchSave(List<T> entitys) throws Exception;

    /**
     * <p> 更新一条记录 </p>
     *
     * @param entity 一条记录对应的实体类
     * @return 更新成功返回true
     * @throws Exception
     * @since 1.0.0
     */
    boolean update(final T entity) throws Exception;
}
