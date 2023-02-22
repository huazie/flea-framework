package com.huazie.fleaframework.algorithm.leetcode.medium.m779;

/**
 * <h1>第K个语法符号 </h1>
 * <p> 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。
 * 接下来的每一行，将前一行中的0替换为01，1替换为10。
 * <ul>
 *     <li> 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。 </li>
 * </ul>
 * <p> 给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
 * <p> 来源：力扣（LeetCode）
 * <p> 链接：https://leetcode.cn/problems/k-th-symbol-in-grammar/
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class KthGrammar {

    /**
       这里的表 可以想象成一棵二叉树
        <pre>
                        0
                0               1
            0       1       1       0
          0   1   1   0   1   0   0   1
         0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0
        </pre>
       <p> 左节点 和 父节点元素一致【即（父节点0，左节点0）或 （父节点1，左节点1）】
       <p> 右节点 和 父节点元素相反【即（父节点0，右节点1）或 （父节点1，右节点0）】
     */
    public int kthGrammar(int n, int k) {
        return kthGrammar1(n, k);
    }

    private int kthGrammar1(int n, int k) {
        // 第一层特殊处理
        if (n == 1 && k == 1) {
            return 0;
        }
        if (k % 2 == 1) {
            // 左节点
            return kthGrammar1(n - 1, (k + 1) / 2, true);
        } else {
            // 右节点
            return kthGrammar1(n - 1, k / 2, false);
        }
    }

    private int kthGrammar1(int n, int k, boolean isLeft) {
        // 递归到第一层，根节点0
        if (n == 1 && k == 1) {
            return kthGrammar2(0, isLeft);
        }

        return kthGrammar2(kthGrammar1(n, k), isLeft);
    }

    private int kthGrammar2(int p, boolean isLeft) {
        if (isLeft) {
            // 左节点和父节点一致【即（父节点0，左节点0）或 （父节点1，左节点1）】
            return p;
        } else {
            // 右节点和父节点相反【即（父节点0，右节点1）或 （父节点1，右节点0）】
            return p == 0 ? 1 : 0;
        }
    }
}
