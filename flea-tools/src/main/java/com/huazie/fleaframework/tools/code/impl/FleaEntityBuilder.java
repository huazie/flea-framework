package com.huazie.fleaframework.tools.code.impl;

import com.huazie.fleaframework.common.util.IOUtils;

/**
 * Flea实体类代码建造者实现
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaEntityBuilder extends AbstractFleaEntityBuilder {

    @Override
    protected String toEntityCodeFileContent() {
        return IOUtils.toNativeStringFromResource("flea/code/entity/FleaEntity.code");
    }
}
