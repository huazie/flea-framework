package com.huazie.frame.common;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * <p> Flea树 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaTree<T> implements Serializable {

    private static final long DEFAULT_ROOT_NODE_ID = -1L;

    private static final int DEFAULT_ROOT_NODE_HEIGHT = 1;

    private static final String TAB = "\t";

    private static final String ENTER = "\n";

    private final Comparator<? super T> comparator;

    private transient TreeNode<T> rootNode = null;

    private transient int size = 0; // 树的节点总数

    private transient LinkedList<TreeNode<T>> tempTreeNodes = new LinkedList<>(); // 临时存放暂时无法放置的树节点

    public FleaTree() {
        this.comparator = null;
    }

    public FleaTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * <p> 返回树节点的总数 </p>
     *
     * @return 树节点的总数
     */
    public int size() {
        return size;
    }

    /**
     * <p> 添加根节点 </p>
     */
    public void addRootTreeNote(T element) {

        if (ObjectUtils.isEmpty(element)) {
            throw new NullPointerException("待添加的根节点元素不能为空");
        }

        if (ObjectUtils.isEmpty(rootNode)) {
            rootNode = new TreeNode<>(element, DEFAULT_ROOT_NODE_ID, DEFAULT_ROOT_NODE_HEIGHT, null);
            size++;
        }
    }

    /**
     * <p> 添加树节点 </p>
     *
     * @param current 现节点元素
     * @param id      现节点编号
     * @param height  现节点高度
     * @param parent  父节点元素
     * @param pId     父节点编号
     * @param pHeight 父节点高度
     * @since 1.0.0
     */
    public void addTreeNote(T current, long id, int height, T parent, long pId, int pHeight) {

        if (ObjectUtils.isEmpty(rootNode)) {
            throw new NullPointerException("请先添加树的根节点");
        }

        if (pHeight < DEFAULT_ROOT_NODE_HEIGHT) {
            throw new RuntimeException("待添加的现节点，其父节点高度不能低于根节点高度【height = 1】");
        }

        if (height - pHeight != CommonConstants.NumeralConstants.INT_ONE) {
            throw new RuntimeException("待添加的现节点和其父节点高度相差不为1");
        }

        if (ObjectUtils.isEmpty(current)) {
            throw new NullPointerException("待添加的现节点元素不能为空");
        }

        // 现节点的父节点即为根节点
        if (rootNode.height == pHeight) {
            addSubTreeNode(current, id, height, rootNode);
        } else {
            // 递归添加树节点
            addTreeNode1(rootNode.subNotes, current, id, height, parent, pId, pHeight, true);
        }
    }

    /**
     * <p> 递归添加树节点 </p>
     *
     * @param subNotes 子节点链表
     * @param current  现节点元素
     * @param id       现节点编号
     * @param height   现节点高度
     * @param parent   父节点元素
     * @param pId      父节点编号
     * @param pHeight  父节点高度
     * @since 1.0.0
     */
    private void addTreeNode1(LinkedList<TreeNode<T>> subNotes, T current, long id, int height, T parent, long pId, int pHeight, boolean isFirst) {

        // 添加临时树节点
        if (isFirst) {
            addTempTreeNode(current, id, height, parent, pId, pHeight);
        }

        if (CollectionUtils.isNotEmpty(subNotes)) {

            ListIterator<TreeNode<T>> subNotesIt = subNotes.listIterator();

            while (subNotesIt.hasNext()) {
                TreeNode<T> mTreeNode = subNotesIt.next();
                // mTreeNode 为 现节点的父节点
                if (mTreeNode.id == pId && mTreeNode.height == pHeight) {
                    // 添加现节点到 mTreeNode子节点中
                    addSubTreeNode(current, id, height, mTreeNode);
                    // 删除临时节点
                    removeTempTreeNode(id, height);
                    break;
                }

                // 如果mTreeNode不是，则尝试添加到mTreeNode的子节点中【采用递归方式】
                addTreeNode1(mTreeNode.subNotes, current, id, height, parent, pId, pHeight, false);

            }

        }
    }

    /**
     * <p> 添加临时树节点 </p>
     *
     * @param current 现节点元素
     * @param id      现节点编号
     * @param height  现节点高度
     * @param parent  父节点元素
     * @param pId     父节点编号
     * @param pHeight 父节点高度
     * @since 1.0.0
     */
    private void addTempTreeNode(T current, long id, int height, T parent, long pId, int pHeight) {
        TreeNode<T> parentNode = new TreeNode<>(parent, pId, pHeight, null);
        TreeNode<T> currentNode = new TreeNode<>(current, id, height, parentNode);
        tempTreeNodes.add(currentNode);
    }

    /**
     * <p> 去除临时树节点 </p>
     *
     * @param id     现节点编号
     * @param height 现节点高度
     * @since 1.0.0
     */
    private void removeTempTreeNode(long id, int height) {
        if (ObjectUtils.isNotEmpty(tempTreeNodes)) {
            ListIterator<TreeNode<T>> tempTreeNodesIt = tempTreeNodes.listIterator();
            while (tempTreeNodesIt.hasNext()) {
                TreeNode<T> mTreeNode = tempTreeNodesIt.next();
                if (mTreeNode.id == id && mTreeNode.height == height) {
                    tempTreeNodesIt.remove();
                    break;
                }
            }
        }
    }

    /**
     * <p> 处理临时树节点 </p>
     *
     * @param mTreeNode 树节点
     * @since 1.0.0
     */
    private void handleTempTreeNode(TreeNode<T> mTreeNode) {
        // 遍历临时树节点，如果 mTreeNode为临时树节点的父节点，则添加进去
        if (CollectionUtils.isNotEmpty(tempTreeNodes)) {
            ListIterator<TreeNode<T>> tempTreeNodesIt = tempTreeNodes.listIterator();
            while (tempTreeNodesIt.hasNext()) {
                TreeNode<T> tempTreeNode = tempTreeNodesIt.next();
                TreeNode<T> tempParentTreeNode = tempTreeNode.parentNote;
                if (mTreeNode.id == tempParentTreeNode.id && mTreeNode.height == tempParentTreeNode.height) {
                    // 添加现节点到 mTreeNode子节点中
                    addSubTreeNode1(tempTreeNode.element, tempTreeNode.id, tempTreeNode.height, mTreeNode);
                    // 删除临时节点
                    tempTreeNodesIt.remove();
                }
            }
        }
    }

    /**
     * <p> 添加子节点 </p>
     *
     * @param current 现节点元素
     * @param id      现节点编号
     * @param height  现节点高度
     * @param parent  父节点
     */
    private void addSubTreeNode(T current, long id, int height, TreeNode<T> parent) {
        TreeNode<T> currentNode = addSubTreeNode1(current, id, height, parent);
        // 遍历临时树节点，如果 mTreeNode为临时树节点的父节点，则添加进去
        handleTempTreeNode(currentNode);
    }

    /**
     * <p> 添加子节点 </p>
     *
     * @param current 现节点元素
     * @param id      现节点编号
     * @param height  现节点高度
     * @param parent  父节点
     */
    private TreeNode<T> addSubTreeNode1(T current, long id, int height, TreeNode<T> parent) {
        TreeNode<T> currentNode = new TreeNode<>(current, id, height, parent);
        parent.insertSubNode(currentNode, comparator);
        size++;
        return currentNode;
    }

    @Override
    public String toString() {

        StringBuilder fleaTreeString = new StringBuilder();
        T element = rootNode.element;
        fleaTreeString.append(element.toString()).append(ENTER);

        LinkedList<TreeNode<T>> subNodes = rootNode.subNotes;
        toString(fleaTreeString, subNodes);
        return fleaTreeString.toString();

    }

    // 递归获取Flea树结构
    private void toString(StringBuilder fleaTreeString, LinkedList<TreeNode<T>> subNodes) {

        if (CollectionUtils.isNotEmpty(subNodes)) {

            ListIterator<TreeNode<T>> subNodesIt = subNodes.listIterator();
            while (subNodesIt.hasNext()) {
                TreeNode<T> treeNode = subNodesIt.next();
                fleaTreeString.append(TAB).append(treeNode.element).append(ENTER);
                toString(fleaTreeString, treeNode.subNotes);
                fleaTreeString.append(TAB);
            }

        }

    }

    static final class TreeNode<T> {

        T element; // 节点元素

        long id; // 节点编号

        int height; // 节点高度

        TreeNode<T> parentNote; // 父节点

        LinkedList<TreeNode<T>> subNotes = null; // 子节点链表

        TreeNode(T element, long id, int height, TreeNode<T> parentNote) {
            this.element = element;
            this.id = id;
            this.height = height;
            this.parentNote = parentNote;
        }

        /**
         * <p> 添加子节点 </p>
         *
         * @param subNote 子节点
         * @since 1.0.0
         */
        void insertSubNode(TreeNode<T> subNote, Comparator<? super T> comparator) {

            if (ObjectUtils.isEmpty(subNote)) {
                return;
            }

            if (ObjectUtils.isEmpty(subNotes)) {
                subNotes = new LinkedList<>();
            }

            if (subNotes.isEmpty()) {
                subNotes.addFirst(subNote);
            } else {
                ListIterator<TreeNode<T>> subNoteListIt = subNotes.listIterator();

                while (subNoteListIt.hasNext()) {
                    TreeNode<T> currentNode = subNoteListIt.next();
                    int currentIndex = subNoteListIt.nextIndex();
                    int comp = compare(currentNode.element, subNote.element, comparator);
                    // 当前节点元素的顺序大于待添加节点的元素的顺序
                    if (comp > CommonConstants.NumeralConstants.INT_ZERO) {
                        // 将待添加节点插入到当前节点处
                        subNotes.add(currentIndex, subNote);
                        break;
                    } else if (comp < CommonConstants.NumeralConstants.INT_ZERO && !subNoteListIt.hasNext()) {
                        subNotes.addLast(subNote);
                        break;
                    }
                }
            }

        }

        /**
         * <p> 使用Flea树中正确的比较器方法，比较两个树节点元素 </p>
         */
        @SuppressWarnings({"unchecked"})
        final int compare(T t1, T t2, Comparator<? super T> comparator) {
            // comparator为空，则需要树节点元素T 实现Comparable<? super T>接口才行
            return comparator == null ? ((Comparable<? super T>) t1).compareTo(t2) : comparator.compare(t1, t2);
        }

    }

}
