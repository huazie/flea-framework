package org.eclipse.persistence.internal.sequencing;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea序列管理器EclipseLink版实现，由于{@code SequencingManager}
 * 为EclipseLink私有，所以该类包目录同{@code SequencingManager}，
 * 该类主要用于分表场景的分表序列的获取。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public final class FleaSequencingManager extends SequencingManager {

    private static final ConcurrentMap<String, FleaSequencingManager> fleaSequencingManagerMap = new ConcurrentHashMap<>();

    /**
     * Flea序列管理器构造
     *
     * @param sequencing 序列管理器
     * @since 1.2.0
     */
    public FleaSequencingManager(Sequencing sequencing, ClassDescriptor descriptor) {
        super(((SequencingManager) sequencing).getOwnerSession());
        setShouldUseSeparateConnection(true);
        onAddDescriptor(descriptor);
    }

    /**
     * 获取Flea序列管理器
     *
     * @param sequenceName 序列名
     * @return Flea序列管理器
     * @since 1.2.0
     */
    public static FleaSequencingManager getFleaSequencingManager(String sequenceName, Sequencing sequencing, ClassDescriptor descriptor) {
        if (!fleaSequencingManagerMap.containsKey(sequenceName)) {
            synchronized (fleaSequencingManagerMap) {
                if (!fleaSequencingManagerMap.containsKey(sequenceName)) {
                    fleaSequencingManagerMap.put(sequenceName, new FleaSequencingManager(sequencing, descriptor));
                }
            }
        }
        return fleaSequencingManagerMap.get(sequenceName);
    }

    /**
     * 添加实体类描述信息，初始化实体类的序列对象
     *
     * @param descriptor 实体类描述信息
     * @since 1.2.0
     */
    private void onAddDescriptor(ClassDescriptor descriptor) {
        Collection<ClassDescriptor> descriptorsToAdd = new ArrayList<>(1);
        descriptorsToAdd.add(descriptor);
        this.onAddDescriptors(descriptorsToAdd);
    }

    /**
     * 获取下一个主键值
     *
     * @param sequenceName 序列名
     * @return 下一个主键值
     * @since 1.2.0
     */
    public Number getNextValue(String sequenceName) {
        return (Number) getNextValue(getOwnerSession(), sequenceName);
    }

    /**
     * 获取下一个主键值
     *
     * @param writeSession 数据库会话
     * @param sequenceName 序列名
     * @return 下一个主键值
     * @since 1.2.0
     */
    private synchronized Object getNextValue(AbstractSession writeSession, String sequenceName) {
        Sequence sequence = getSequence(sequenceName);
        State state = this.getState(sequence.shouldUsePreallocation(), sequence.shouldUseTransaction());
        return state.getNextValue(sequence, writeSession);
    }

}
