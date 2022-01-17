package com.huazie.fleaframework.db.jpa.common;

import com.huazie.fleaframework.common.exception.CommonException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据处理的基本接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJPABaseDataHandler<T> {

    /**
     * 获取下一个的主键值
     *
     * @param entity 实体类对象实例【主要适用于分表，非分表的场景提前获取主键可传null】
     * @return 下一个的主键值
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    Number getFleaNextValue(T entity) throws CommonException;

    /**
     * 根据主键查询
     *
     * @param entityId 主键编号
     * @return 实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    T query(long entityId) throws CommonException;

    /**
     * 根据主键查询
     *
     * @param entityId 主键编号
     * @return 实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    T query(String entityId) throws CommonException;

    /**
     * 根据主键查询 (用于分表)
     *
     * @param entityId 主键编号
     * @param entity   实体对象实例（包含 相应分表字段值）
     * @return 实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    T query(long entityId, T entity) throws CommonException;

    /**
     * 根据主键查询 (用于分表)
     *
     * @param entityId 主键编号
     * @param entity   实体对象实例（包含 相应分表字段值）
     * @return 实体对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    T query(String entityId, T entity) throws CommonException;

    /**
     * 多条件查询
     *
     * @param paramMap 多条件参数
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramMap) throws CommonException;

    /**
     * 多条件排序查询
     *
     * @param paramMap 多条件参数
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramMap, String attrName, String orderBy) throws CommonException;

    /**
     * 多条件分页查询
     *
     * @param paramMap 多条件参数
     * @param start    开始查询记录行
     * @param max      一次最大查询数量
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramMap, int start, int max) throws CommonException;

    /**
     * 多条件分页排序查询
     *
     * @param paramMap 多条件参数
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @param start    开始查询记录行
     * @param max      一次最大查询数量
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Map<String, Object> paramMap, String attrName, String orderBy, int start, int max) throws CommonException;

    /**
     * 查询所有数据
     *
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll() throws CommonException;

    /**
     * 查询所有数据(排序)
     *
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(String attrName, String orderBy) throws CommonException;

    /**
     * 分页查询所有的数据
     *
     * @param start 开始查询记录行
     * @param max   一次最大查询数量
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(int start, int max) throws CommonException;

    /**
     * 分页排序查询所有的数据
     *
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @param start    开始查询记录行
     * @param max      一次最大查询数量
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(String attrName, String orderBy, int start, int max) throws CommonException;

    /**
     * 查询表中数据的总数
     *
     * @return 总数据量
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    long queryCount() throws CommonException;

    /**
     * 带条件查询表中数据的总数
     *
     * @param paramMap 多条件参数
     * @return 带条件的总数据量
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    long queryCount(Map<String, Object> paramMap) throws CommonException;

    /**
     * 多条件查询 (用于分表)
     *
     * @param attrNames 多条件查询属性名集合
     * @param entity    实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Set<String> attrNames, T entity) throws CommonException;

    /**
     * 多条件排序查询 (用于分表)
     *
     * @param attrNames 多条件查询属性名集合
     * @param attrName  属性名
     * @param orderBy   排序方式（desc：降序，asc：升序）
     * @param entity    实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Set<String> attrNames, String attrName, String orderBy, T entity) throws CommonException;

    /**
     * 多条件分页查询 (用于分表)
     *
     * @param attrNames 多条件查询属性名集合
     * @param start     开始查询记录行
     * @param max       一次最大查询数量
     * @param entity    实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Set<String> attrNames, int start, int max, T entity) throws CommonException;

    /**
     * 多条件分页排序查询 (用于分表)
     *
     * @param attrNames 多条件查询属性名集合
     * @param attrName  属性名
     * @param orderBy   排序方式（desc：降序，asc：升序）
     * @param start     开始查询记录行
     * @param max       一次最大查询数量
     * @param entity    实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> query(Set<String> attrNames, String attrName, String orderBy, int start, int max, T entity) throws CommonException;

    /**
     * 查询所有数据 (用于分表)
     *
     * @param entity 实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(T entity) throws CommonException;

    /**
     * 查询所有数据(排序) (用于分表)
     *
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @param entity   实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(String attrName, String orderBy, T entity) throws CommonException;

    /**
     * 分页查询所有的数据 (用于分表)
     *
     * @param start  开始查询记录行
     * @param max    一次最大查询数量
     * @param entity 实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(int start, int max, T entity) throws CommonException;

    /**
     * 分页排序查询所有的数据 (用于分表)
     *
     * @param attrName 属性名
     * @param orderBy  排序方式（desc：降序，asc：升序）
     * @param start    开始查询记录行
     * @param max      一次最大查询数量
     * @param entity   实体对象实例（包含 相应分表字段值）
     * @return 实体对象集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(String attrName, String orderBy, int start, int max, T entity) throws CommonException;

    /**
     * 查询表中数据的总数 (用于分表)
     *
     * @param entity 实体对象实例（包含 相应分表字段值）
     * @return 总数据量
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    long queryCount(T entity) throws CommonException;

    /**
     * 带条件查询表中数据的总数 (用于分表)
     *
     * @param attrNames 多条件查询属性名集合
     * @param entity    实体对象实例（包含 相应分表字段值）
     * @return 带条件的总数据量
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    long queryCount(Set<String> attrNames, T entity) throws CommonException;

    /**
     * 删除指定主键的实体数据
     *
     * @param entityId 实体类的主键
     * @return true:删除成功，false：删除失败
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    boolean remove(long entityId) throws CommonException;

    /**
     * 删除指定主键的实体数据
     *
     * @param entityId 实体类的主键
     * @return true：删除成功，false：删除失败
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    boolean remove(String entityId) throws CommonException;

    /**
     * 删除某条实体数据
     *
     * @param entity 实体对象
     * @return true：删除成功，false：删除失败
     * @throws CommonException 通用异常
     * @since 1.2.0
     */
    boolean remove(T entity) throws CommonException;

    /**
     * 删除某条数据 (用于分表)
     *
     * @param entityId 实体类的主键
     * @param entity   实体对象实例（包含 相应分表字段值）
     * @return true:删除成功，false：删除失败
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    boolean remove(long entityId, T entity) throws CommonException;

    /**
     * 删除某条数据 (用于分表)
     *
     * @param entityId 实体类的主键
     * @param entity   实体对象实例（包含 相应分表字段值）
     * @return true:删除成功，false：删除失败
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    boolean remove(String entityId, T entity) throws CommonException;

    /**
     * 更新一条记录
     *
     * @param entity 待更新的实体
     * @return 更新后的实体
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    T update(final T entity) throws CommonException;

    /**
     * 批量更新
     *
     * @param entities 待更新的实体集合
     * @return 批量更新后的实体集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> batchUpdate(List<T> entities) throws CommonException;

    /**
     * 保存一条记录
     *
     * @param entity 待保存的实体
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void save(final T entity) throws CommonException;

    /**
     * 批量保存
     *
     * @param entities 待保存的实体集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void batchSave(List<T> entities) throws CommonException;

    /**
     * 通过SELECT SQL模板，查询数据（其中<code>relationId</code>为SQL关系编号）
     *
     * @param relationId 关系编号
     * @param entity     实体类
     * @return 实体类数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<T> queryAll(String relationId, T entity) throws CommonException;

    /**
     * 通过SELECT SQL模板，查询数据（其中<code>relationId</code>为SQL关系编号, 单个查询结果返回）
     *
     * @param relationId 关系编号
     * @param entity     实体类
     * @return 实体类数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    Object querySingle(String relationId, T entity) throws CommonException;

    /**
     * 通过INSERT SQL模板，保存数据（其中<code>relationId</code>为SQL关系编号）
     *
     * @param relationId 关系编号
     * @param entity     实体类
     * @return 新增的记录数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    int insert(String relationId, T entity) throws CommonException;

    /**
     * 通过UPDATE SQL模板，更新数据（其中<code>relationId</code>为SQL关系编号）
     *
     * @param relationId 关系编号
     * @param entity     实体类
     * @return 更新的记录数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    int update(String relationId, T entity) throws CommonException;

    /**
     * 通过DELETE SQL模板，删除数据（其中<code>relationId</code>为SQL关系编号）
     *
     * @param relationId 关系编号
     * @param entity     实体类
     * @return 删除的记录数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    int delete(String relationId, T entity) throws CommonException;

    /**
     * 同步持久化上下文环境，即将持久化上下文环境的所有未保存实体的状态信息保存到数据库中
     *
     * @since 1.0.0
     */
    void flush();

    /**
     * 同步持久化上下文环境，兼容flush()功能，同时也支持分库场景
     *
     * @param entity 实体类
     * @since 1.2.0
     */
    void flush(T entity) throws CommonException;
}
