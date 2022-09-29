package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.util.IOUtils;

/**
 * 持久化单元DAO层实现构建者，所有DAO层实现均继承指定的持久化单元DAO层实现
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPersistenceUnitDaoImplBuilder extends AbstractPersistenceUnitDaoImplBuilder {

    @Override
    protected String toEntityCodeFileContent() {
        return IOUtils.toNativeStringFromResource("flea/code/dao/FleaPersistenceUnitDAOImpl.code");
    }

}
