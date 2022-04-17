package com.huazie.fleaframework.common.util.xml;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源导入集，即多个 {@code <import resource=""/>} xml节点。
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class ImportList {

    private List<Import> importList = new ArrayList<>();

    public List<Import> getImportList() {
        return importList;
    }

    public void addImport(Import mImport) {
        importList.add(mImport);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
