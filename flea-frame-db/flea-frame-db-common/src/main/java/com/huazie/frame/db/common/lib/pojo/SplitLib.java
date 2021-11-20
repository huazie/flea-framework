package com.huazie.frame.db.common.lib.pojo;

/**
 * 分库信息，包含了与分库配置相关的内容
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class SplitLib {

    private String libName; // 模板库名

    private String splitLibName; // 分库名

    private String transactionName; // 模板事物名

    private String splitLibTxName; // 分库事物名

    private boolean isExistSplitLib; // 是否存在分库 【true：存在 false：不存在】

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getSplitLibName() {
        return splitLibName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getSplitLibTxName() {
        return splitLibTxName;
    }

    public void setSplitLibTxName(String splitLibTxName) {
        this.splitLibTxName = splitLibTxName;
    }

    public void setSplitLibName(String splitLibName) {
        this.splitLibName = splitLibName;
    }

    public boolean isExistSplitLib() {
        return isExistSplitLib;
    }

    public void setExistSplitLib(boolean existSplitLib) {
        isExistSplitLib = existSplitLib;
    }
}
