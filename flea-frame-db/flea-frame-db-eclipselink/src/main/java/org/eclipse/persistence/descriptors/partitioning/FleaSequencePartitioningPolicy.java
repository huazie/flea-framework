package org.eclipse.persistence.descriptors.partitioning;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.internal.sessions.AbstractRecord;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.queries.DatabaseQuery;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 为TableSequence序列提供统一的连接访问器。用以解决在并发环境下，
 * 默认从连接池取连接，主键更新语句和主键查询语句获取的连接不一致，
 * 从而导致下一个主键值获取异常的情况。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class FleaSequencePartitioningPolicy extends PartitioningPolicy {

    private static final long serialVersionUID = -4278440392908048725L;

    private String nameFieldName = "SEQ_NAME"; // 序列表中指定序列名称的列的名称

    private ThreadLocal<List<Accessor>> accessorsThreadLocal = new ThreadLocal<>();

    /**
     * 设置自定义的序列表中指定序列名称的列的名称
     *
     * @param nameFieldName 序列表中指定序列名称的列的名称
     * @since 1.2.0
     */
    public void setNameFieldName(String nameFieldName) {
        this.nameFieldName = nameFieldName;
    }

    @Override
    public List<Accessor> getConnectionsForQuery(AbstractSession session, DatabaseQuery query, AbstractRecord arguments) {

        String sequenceName = getSequenceName(arguments);

        if (StringUtils.isBlank(sequenceName)) {
            return null;
        }

        if (ObjectUtils.isNotEmpty(accessorsThreadLocal.get())) {
            try {
                // 线程对象存在线程访问器，则直接返回对应的连接访问器
                return accessorsThreadLocal.get();
            } finally {
                // 将当前线程中连接访问器清空，防止该线程下次使用出错
                accessorsThreadLocal.remove();
            }
        }

        List<Accessor> accessors = new ArrayList<>(1);
        accessors.add(getAccessor(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME, session, query, false));

        // 将连接访问器 添加到线程对象中
        accessorsThreadLocal.set(accessors);

        return accessors;
    }

    /**
     * 从SQL参数中获取对应的序列名
     *
     * @param arguments SQL参数
     * @return 序列名
     * @since 1.2.0
     */
    private String getSequenceName(AbstractRecord arguments) {
        String sequenceName = "";
        for (Enumeration enumeration = arguments.keys(); enumeration.hasMoreElements(); ) {
            DatabaseField databaseField = (DatabaseField) enumeration.nextElement();
            if (nameFieldName.equals(databaseField.getName())) {
                sequenceName = (String) arguments.get(databaseField);
            }
        }
        return sequenceName;
    }
}
