package com.huazie.fleaframework.common.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Flea对象池配置
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaObjectPoolConfig extends GenericObjectPoolConfig {

    /**
     * 无参构造方法，初始化部分默认配置
     *
     * @since 1.0.0
     */
    public FleaObjectPoolConfig() {
        // 指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除. true：是
        setTestWhileIdle(true);
        // 连接在池中保持空闲而不被空闲连接回收器线程(如果有)回收的最小时间值，单位毫秒
        setMinEvictableIdleTimeMillis(60000);
        // 在空闲连接回收器线程运行期间休眠的时间值,以毫秒为单位. 如果设置为非正数,则不运行空闲连接回收器线程
        setTimeBetweenEvictionRunsMillis(30000);
        // 在每次空闲连接回收器线程(如果有)运行时检查的连接数量
        // 如果 numTestsPerEvictionRun>=0, 则取numTestsPerEvictionRun 和池内的链接数 的较小值 作为每次检测的链接数; Math.min(numTestsPerEvictionRun, this.idleObjects.size())
        // 如果 numTestsPerEvictionRun<0，则每次检查的链接数是检查时池内链接的总数乘以这个值的负倒数再向上取整的结果。 (int)Math.ceil((double)this.idleObjects.size() / Math.abs((double)numTestsPerEvictionRun))
        setNumTestsPerEvictionRun(-1);
    }

}
