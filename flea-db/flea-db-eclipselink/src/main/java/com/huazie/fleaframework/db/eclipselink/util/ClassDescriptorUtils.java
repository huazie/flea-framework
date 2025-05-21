package com.huazie.fleaframework.db.eclipselink.util;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.MapUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import com.huazie.fleaframework.db.common.table.pojo.SplitTable;
import com.huazie.fleaframework.db.jpa.persistence.FleaEntityManager;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.InheritancePolicy;
import org.eclipse.persistence.descriptors.ReturningPolicy;
import org.eclipse.persistence.internal.descriptors.FleaRelationalDescriptor;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.eclipse.persistence.internal.jpa.CMP3Policy;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.mappings.querykeys.QueryKey;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sequencing.TableSequence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 实体类持久化信息描述符工具类
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class ClassDescriptorUtils {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(ClassDescriptorUtils.class);

    private ClassDescriptorUtils() {
    }

    /**
     * 获取分表对应的实体类的持久化信息描述符
     *
     * @param descriptor 原实体类的持久化信息描述符
     * @param session    数据库会话对象
     * @param entity     实体类对象
     * @return 分表对应的实体类的持久化信息描述符
     * @since 1.2.0
     */
    public static ClassDescriptor getSplitDescriptor(ClassDescriptor descriptor, AbstractSession session, Object entity) {
        try {
            SplitTable splitTable = FleaEntityManager.getSplitTable(entity);
            return getSplitDescriptor(descriptor, session, splitTable);
        } catch (CommonException e) {
            LOGGER.error1(new Object() {}, "Getting the ClassDescriptor of split table occurs Exception : \n", e);
        }
        return descriptor;
    }

    /**
     * 获取分表对应的实体类的持久化信息描述符
     *
     * @param descriptor 原实体类的持久化信息描述符
     * @param session    数据库会话对象
     * @param splitTable 分表信息
     * @return 分表对应的实体类的持久化信息描述符
     * @since 1.2.0
     */
    public static ClassDescriptor getSplitDescriptor(ClassDescriptor descriptor, AbstractSession session, SplitTable splitTable) {
        // 根据分表信息重新获取 ClassDescriptor
        if (ObjectUtils.isNotEmpty(splitTable) && splitTable.isExistSplitTable()) {
            String splitTableName = splitTable.getSplitTableName();
            ClassDescriptor splitClassDescriptor = session.getClassDescriptorForAlias(splitTableName);
            if (ObjectUtils.isEmpty(splitClassDescriptor)) {
                splitClassDescriptor = new FleaRelationalDescriptor();
                splitClassDescriptor.setProperty(DBConstants.LibTableSplitConstants.IS_SPLIT_TABLE, true);
                splitClassDescriptor.setAlias(splitTableName);
                splitClassDescriptor.getQueryManager().checkDatabaseForDoesExist();
                splitClassDescriptor.setJavaClass(descriptor.getJavaClass());
                splitClassDescriptor.setJavaClassName(descriptor.getJavaClassName());
                splitClassDescriptor.setTableName(splitTableName);
                DatabaseTable databaseTable = splitClassDescriptor.getTable(splitTableName);

                List<DatabaseField> splitPrimaryKeyFields = splitClassDescriptor.getPrimaryKeyFields();

                List<DatabaseField> fields = descriptor.getFields();
                Map<String, DatabaseField> splitFieldMap = new HashMap<>(); // 分表场景，实体字段集合
                for (DatabaseField field : fields) {
                    DatabaseField splitField = field.clone();
                    splitField.setTable(databaseTable);
                    if (field.isPrimaryKey()) {
                        splitPrimaryKeyFields.add(splitField);
                    }
                    splitFieldMap.put(splitField.getName(), splitField);
                }

                Vector<DatabaseMapping> splitMappings = splitClassDescriptor.getMappings();
                Vector<DatabaseMapping> mappings = descriptor.getMappings();
                for (DatabaseMapping mapping : mappings) {
                    DatabaseMapping splitMapping = (DatabaseMapping) mapping.clone();
                    DatabaseField field = splitMapping.getField();
                    ((DirectToFieldMapping) splitMapping).setField(splitFieldMap.get(field.getName()));
                    splitMapping.setDescriptor(splitClassDescriptor);
                    splitMappings.add(splitMapping);
                }

                Map<String, QueryKey> queryKeys = descriptor.getQueryKeys();
                if (MapUtils.isNotEmpty(queryKeys)) {
                    Map<String, QueryKey> splitQueryKeys = new HashMap<>(queryKeys.size() + 2);
                    for (QueryKey queryKey : queryKeys.values()) {
                        queryKey = (QueryKey) queryKey.clone();
                        queryKey.setDescriptor(splitClassDescriptor);
                        splitQueryKeys.put(queryKey.getName(), queryKey);
                    }
                    splitClassDescriptor.setQueryKeys(splitQueryKeys);
                }

                if (descriptor.hasInheritance()) {
                    splitClassDescriptor.setInheritancePolicy((InheritancePolicy) descriptor.getInheritancePolicy().clone());
                    splitClassDescriptor.getInheritancePolicy().setDescriptor(splitClassDescriptor);
                }

                if (descriptor.hasReturningPolicy()) {
                    splitClassDescriptor.setReturningPolicy((ReturningPolicy) descriptor.getReturningPolicy().clone());
                    splitClassDescriptor.getReturningPolicy().setDescriptor(splitClassDescriptor);
                }

                CMP3Policy cmp3Policy = (CMP3Policy) descriptor.getCMPPolicy();
                CMP3Policy splitCMP3Policy = new CMP3Policy();
                splitCMP3Policy.setPrimaryKeyClassName(cmp3Policy.getPKClassName());
                splitClassDescriptor.setCMPPolicy(splitCMP3Policy);

                String splitTablePkColumnValue = splitTable.getSplitTablePkColumnValue();
                splitClassDescriptor.setSequenceNumberName(splitTablePkColumnValue);
                DatabaseField sequenceNumberField = descriptor.getSequenceNumberField();
                if (ObjectUtils.isNotEmpty(sequenceNumberField)) {
                    splitClassDescriptor.setSequenceNumberField(splitFieldMap.get(sequenceNumberField.getName()));
                }

                Sequence sequence = descriptor.getSequence();
                if (ObjectUtils.isNotEmpty(sequence)) {
                    Sequence splitSequence;
                    if (sequence instanceof TableSequence) {
                        splitSequence = new TableSequence();
                        splitSequence.setName(splitTablePkColumnValue);
                        ((TableSequence) splitSequence).setCounterFieldName(((TableSequence) sequence).getCounterFieldName());
                        ((TableSequence) splitSequence).setNameFieldName(((TableSequence) sequence).getNameFieldName());
                        ((TableSequence) splitSequence).setTable(((TableSequence) sequence).getTable());
                        splitSequence.setInitialValue(sequence.getInitialValue());
                        splitSequence.setPreallocationSize(sequence.getPreallocationSize());
                    } else {
                        splitSequence = (Sequence) sequence.clone();
                    }
                    // 初始化分表的Sequence
                    splitSequence.onConnect(session.getDatasourcePlatform());
                    if (splitSequence instanceof TableSequence) {
                        // 为主键值查询SQL添加分表的实体类的描述信息
                        ((TableSequence) splitSequence).getSelectQuery().setDescriptor(splitClassDescriptor);
                        // 为主键值更新SQL添加分表的实体类的描述信息
                        ((TableSequence) splitSequence).getUpdateQuery().setDescriptor(splitClassDescriptor);
                    }
                    splitClassDescriptor.setSequence(splitSequence);
                    session.getDatasourcePlatform().addSequence(splitSequence, session.isConnected());
                }

                splitClassDescriptor.preInitialize(session);
                splitClassDescriptor.initialize(session);
                splitClassDescriptor.postInitialize(session);

                session.addAlias(splitTableName, splitClassDescriptor);
            }
            if (ObjectUtils.isNotEmpty(splitClassDescriptor)) {
                descriptor = splitClassDescriptor;
            }
        }
        return descriptor;
    }
}
