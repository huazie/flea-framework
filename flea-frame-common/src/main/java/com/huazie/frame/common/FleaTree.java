package com.huazie.frame.common;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * 树 {@code FleaTree}，包含一个根节点，树下每个节点都可以有多个子节点；
 *
 * <p> 树节点 {@code TreeNode}，包含节点元素，节点编号，节点高度，
 * 父节点，子节点集合；
 *
 * <p> 节点高度 {@code height}，根节点高度为1，子节点高度根据节点位置依次+1；
 *
 * <p> 父节点 {@code parentNote}, 当前节点的上层节点，有且仅有一个；
 *
 * <p> 子节点集合 {@code subNotes}, 用 {@code LinkedList} 进行存储，
 * 子节点的存储先后顺序取决于提供的比较器{@code comparator} 或者
 * 节点元素自身实现的 {@code Comparable}接口；
 *
 * <p> 树叶子节点，不包含任何子节点的树节点；
 *
 * @param <T> Flea树中包含的元素的类型
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaTree<T> implements Serializable {

    private static final long serialVersionUID = 1775075091403620285L;

    private static final String TREE_NODE_ELEMENT = "ELEMENT";

    private static final String TREE_NODE_ID = "ID";

    private static final String TREE_NODE_HEIGHT = "HEIGHT";

    private static final String TREE_NODE_P_ELEMENT = "P_ELEMENT";

    private static final String TREE_NODE_P_ID = "P_ID";

    private static final String TREE_NODE_P_HEIGHT = "P_HEIGHT";

    private static final String TREE_SUB_NOTES = "SUB_NOTES";

    private static final String HAS_SUB_NOTES = "HAS_SUB_NOTES";

    private static final long DEFAULT_ROOT_NODE_ID = -1L;

    private static final int DEFAULT_ROOT_NODE_HEIGHT = 1;

    private static final String ENTER = "\n";

    private final Comparator<? super T> comparator;

    private transient TreeNode<T> rootNode = null;

    private transient int size = 0; // 树的节点总数

    private transient LinkedList<TreeNode<T>> tempTreeNodes = new LinkedList<>(); // 临时存放暂时无法放置的树节点

    private transient LinkedList<TreeNode<T>> treeLeafNodes = new LinkedList<>(); // 存放树的叶子节点

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

            for (; subNotesIt.hasNext(); ) {
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

        // 取mTreeNode的子节点
        LinkedList<TreeNode<T>> mSubTreeNotes = mTreeNode.subNotes;
        if (CollectionUtils.isNotEmpty(mSubTreeNotes)) {
            for (TreeNode<T> subTreeNode : mSubTreeNotes) {
                handleTempTreeNode(subTreeNode);
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
        // 将当前节点currentNode添加到其父节点parent中
        parent.insertSubNode(currentNode, comparator);
        // 树节点个数+1
        size++;
        // 处理叶子节点集合，剔除非叶子节点（即包含了子节点的节点）
        handleTreeLeafNode(currentNode, parent);
        return currentNode;
    }

    /**
     * <p> 处理叶子节点集合 </p>
     * <p> 叶子节点集合中存在节点是 当前待添加节点的父节点，
     * 说明该节点有子节点，则需要从叶子节点集合中剔除 </p>
     *
     * @param parent 当前待添加节点的父节点
     * @since 1.0.0
     */
    private void handleTreeLeafNode(TreeNode<T> currentNode, TreeNode<T> parent) {

        // 遍历叶子节点集合
        if (CollectionUtils.isNotEmpty(treeLeafNodes)) {
            ListIterator<TreeNode<T>> treeLeafNodesIt = treeLeafNodes.listIterator();
            while (treeLeafNodesIt.hasNext()) {
                TreeNode<T> treeLeafNode = treeLeafNodesIt.next();
                // 叶子节点集合中存在节点是 当前待添加节点的父节点
                if (treeLeafNode.id == parent.id && treeLeafNode.height == parent.height) {
                    // 将当前处理的节点从叶子节点集合中剔除
                    treeLeafNodesIt.remove();
                }
            }
        }

        // 添加当前节点到叶子节点集合treeLeafNodes中
        treeLeafNodes.add(currentNode);
    }

    /**
     * <p> 获取根节点元素 </p>
     *
     * @return 根节点元素
     * @since 1.0.0
     */
    public T getRootElement() {
        return rootNode.element;
    }

    /**
     * <p> 获取指定编号的树叶子节点元素 </p>
     *
     * @param id 节点编号
     * @return 指定编号的树叶子节点元素
     * @since 1.0.0
     */
    public T getTreeLeafElement(long id) {

        T element = null;

        if (CollectionUtils.isNotEmpty(treeLeafNodes)) {
            for (TreeNode<T> treeNode : treeLeafNodes) {
                if (treeNode.id == id) {
                    element = treeNode.element;
                    break;
                }
            }
        }
        return element;
    }

    /**
     * <p> 获取所有的树叶子节点元素 </p>
     *
     * @return 树叶子节点元素集合
     * @since 1.0.0
     */
    public List<T> getAllTreeLeafElement() {

        List<T> treeLeafElements = null;

        if (CollectionUtils.isNotEmpty(treeLeafNodes)) {
            treeLeafElements = new ArrayList<>();
            for (TreeNode<T> treeNode : treeLeafNodes) {
                treeLeafElements.add(treeNode.element);
            }
        }
        return treeLeafElements;
    }

    /**
     * <p> 判断是否Flea树中是否有节点（包括根节点） </p>
     *
     * @return true: 没有 false: 有
     * @since 1.0.0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {

        StringBuilder fleaTreeString = new StringBuilder();
        fleaTreeString.append(toString(rootNode.element)).append(ENTER);

        LinkedList<TreeNode<T>> subNodes = rootNode.subNotes;
        toString(fleaTreeString, subNodes, rootNode.height, new HashMap<Integer, Boolean>());
        return fleaTreeString.toString();
    }

    // 递归获取Flea树结构
    private void toString(StringBuilder fleaTreeString, LinkedList<TreeNode<T>> subNodes, int height, Map<Integer, Boolean> cHeightNodeLast) {

        if (CollectionUtils.isNotEmpty(subNodes)) {
            ListIterator<TreeNode<T>> subNodesIt = subNodes.listIterator();
            while (subNodesIt.hasNext()) {
                TreeNode<T> treeNode = subNodesIt.next();
                for (int i = 1; i < height; i++) {
                    if (cHeightNodeLast.get(i)) {
                        fleaTreeString.append("   ");
                    } else {
                        fleaTreeString.append("│  ");
                    }
                }
                if (!subNodesIt.hasNext()) {
                    fleaTreeString.append("└──");
                    cHeightNodeLast.put(height, true);
                } else {
                    fleaTreeString.append("├──");
                    cHeightNodeLast.put(height, false);
                }
                fleaTreeString.append(toString(treeNode.element)).append(ENTER);
                toString(fleaTreeString, treeNode.subNotes, treeNode.height, cHeightNodeLast);
            }
        }
    }

    /**
     * <p> 树形结构展示，默认显示数节点的元素 </p>
     *
     * @param element 元素
     * @return 数节点的元素
     */
    protected String toString(T element) {
        return element.toString();
    }

    /**
     * <p> 以{@code List<Map<String, Object>>}形式返回 所有的树节点信息 </p>
     *
     * @param isContains 是否包含根节点 【true: 包含 false: 不包含】
     * @return 树的节点信息
     * @since 1.0.0
     */
    public List<Map<String, Object>> toMapList(boolean isContains) {
        LinkedList<TreeNode<T>> treeNodes;
        if (isContains) {
            treeNodes = new LinkedList<>();
            treeNodes.add(rootNode);
        } else {
            treeNodes = rootNode.subNotes;
        }
        return toMapList(treeNodes);
    }

    /**
     * <p> 以List<Map<String, Object>>形式, 递归返回所有【不包含根节点】的树节点信息 </p>
     *
     * @param subNotes 树节点的子节点信息
     * @return 树的节点信息
     * @since 1.0.0
     */
    private List<Map<String, Object>> toMapList(LinkedList<TreeNode<T>> subNotes) {
        List<Map<String, Object>> treeNodeMapList = null;

        if (CollectionUtils.isNotEmpty(subNotes)) {
            treeNodeMapList = new ArrayList<>();
            for (TreeNode<T> subNote : subNotes) {
                if (ObjectUtils.isNotEmpty(subNote)) {
                    TreeNode<T> parentNode = subNote.parentNote;
                    Map<String, Object> treeNodeMap;
                    if (ObjectUtils.isEmpty(parentNode)) {
                        treeNodeMap = toMap(subNote.element, subNote.id, subNote.height, null, CommonConstants.NumeralConstants.MINUS_TWO, CommonConstants.NumeralConstants.INT_ZERO, CollectionUtils.isNotEmpty(subNote.subNotes));
                    } else {
                        treeNodeMap = toMap(subNote.element, subNote.id, subNote.height, parentNode.element, parentNode.id, parentNode.height, CollectionUtils.isNotEmpty(subNote.subNotes));
                    }
                    treeNodeMap.put(getMapKeyForSubNotes(), toMapList(subNote.subNotes));
                    // 重处理树节点信息，子类可实现更细粒度的功能
                    reHandleTreeNodeMap(treeNodeMap);
                    treeNodeMapList.add(treeNodeMap);
                }
            }
        }

        return treeNodeMapList;
    }

    /**
     * <p> 以{@code Map<String, Object>}形式返回某个树节点信息 </p>
     * <p> 子类可以重写该方法，实现返回自定义的树节点元素 </p>
     *
     * @param element  现节点元素
     * @param id       现节点编号
     * @param height   现节点高度
     * @param pElement 父节点元素
     * @param pId      父节点编号
     * @param pHeight  父节点高度
     * @return 某个树节点信息
     * @since 1.0.0
     */
    protected Map<String, Object> toMap(T element, long id, int height, T pElement, long pId, int pHeight, boolean isHasSubNotes) {
        Map<String, Object> map = new HashMap<>();
        map.put(TREE_NODE_ELEMENT, element);
        map.put(TREE_NODE_ID, id);
        map.put(TREE_NODE_HEIGHT, height);
        map.put(TREE_NODE_P_ELEMENT, pElement);
        map.put(TREE_NODE_P_ID, pId);
        map.put(TREE_NODE_P_HEIGHT, pHeight);
        map.put(HAS_SUB_NOTES, isHasSubNotes);
        return map;
    }

    /**
     * <p> 获取现节点对应的子节点列表的{@code Map}键 </p>
     * <p> 子类可以重写该方法，实现返回自定义的现节点对应的子节点列表的{@code Map}键 </p>
     *
     * @return 现节点对应的子节点列表的{@code Map}键
     * @since 1.0.0
     */
    protected String getMapKeyForSubNotes() {
        return TREE_SUB_NOTES;
    }

    /**
     * <p> 重新处理树节点信息 </p>
     * <p> 子类可以重写该方法，实现更细粒度的树节点元素展示  </p>
     *
     * @param treeNodeMap 以{@code Map<String, Object>}形式返回某个树节点信息
     */
    protected void reHandleTreeNodeMap(Map<String, Object> treeNodeMap) {
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

            // 无子节点
            if (subNotes.isEmpty()) {
                // 将待添加节点插入到子节点链表的头部
                subNotes.addFirst(subNote);
            } else {
                ListIterator<TreeNode<T>> subNoteListIt = subNotes.listIterator();

                while (subNoteListIt.hasNext()) {
                    TreeNode<T> currentNode = subNoteListIt.next();
                    int currentIndex = subNoteListIt.nextIndex();
                    int comp = compare(currentNode.element, subNote.element, comparator);
                    // 当前节点元素的顺序大于待添加节点的元素的顺序
                    if (comp > CommonConstants.NumeralConstants.INT_ZERO) {
                        // 将待添加节点插入到当前节点处，当前节点链接到待添加节点的后面
                        subNotes.add(currentIndex - 1, subNote);
                        break;
                    } else if (comp < CommonConstants.NumeralConstants.INT_ZERO && !subNoteListIt.hasNext()) {
                        // 当前节点元素的顺序小于待添加节点的元素的顺序，并且没有后续子节点了
                        // 将待添加节点插入到子节点链表的尾部
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
